package com.bzdepot.bzgate.filter;


import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.*;

/*
*  全局过滤器 （负责收集统一异常，统一资源权限验证）
* */
@Configuration
public class OverallFilter implements GlobalFilter {
    
    private final static Logger loger = LoggerFactory.getLogger(OverallFilter.class);

    @Value("${auth.outserver}")
    private String[] outserver;

    @Value("${auth.token.tokenheader}")
    private String tokenheader;

    @Value("${auth.token.tokenkey}")
    private String tokenkey;

    /**
     * 网关过滤器方法
     * @param serverWebExchange
     * @param gatewayFilterChain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain gatewayFilterChain) {
        LinkedHashSet requiredAttribute = serverWebExchange.getRequiredAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        ServerHttpRequest request = serverWebExchange.getRequest();
        String requestUri = request.getPath().pathWithinApplication().value();
        boolean LJ = true; //是否需要拦截 True 需要拦截 False 不需要拦截
        if (requiredAttribute != null) {
            Iterator<URI> iterator = requiredAttribute.iterator();
            while (iterator.hasNext()){
                URI next = iterator.next();
                if(this.CheckServerToken(next.getPath())){
                    LJ = false;
                }
            }
        }
        ServerHttpRequest.Builder mutate = request.mutate();
        //不需要拦截
        if(LJ == false){
            ServerHttpRequest build = mutate.build();
            return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());
        }
        //开始拦截验证Token是否有效
        Claims UserInfo = null;
        try {
            UserInfo = this.CheckJwtToken(request,mutate);
        }catch (Exception e){
            loger.error(e.getMessage());
            return this.getVoidMono(serverWebExchange,"请登陆后在试！");
        }
        if(UserInfo == null){
            return this.getVoidMono(serverWebExchange,"用户信息异常,请重重新登陆！");
        }
        mutate.header("user_id", UserInfo.get("user_id").toString());
        mutate.header("username", UserInfo.get("username").toString());
        mutate.header("nickname", UserInfo.get("nickname").toString());
        if(UserInfo.get("userType") != null){
            mutate.header("sellerId", UserInfo.get("sellerId").toString());
            mutate.header("levelId", UserInfo.get("levelId").toString());
            mutate.header("userType", UserInfo.get("userType").toString());
        }
        String ReToken = this.RefreshToken(UserInfo.getExpiration().getTime(),UserInfo.get("username").toString(), UserInfo.get("nickname").toString(),UserInfo.get("user_id").toString(),UserInfo.get("sellerId").toString(),UserInfo.get("levelId").toString(),UserInfo.get("userType").toString());
        if(ReToken != null){
            mutate.header("retoken", ReToken);
            loger.info("重置的Token:"+ReToken);
        }
        loger.info(Long.toString(UserInfo.getExpiration().getTime()));
        loger.info(UserInfo.getExpiration().toString());
        loger.info(Long.toString(new Date().getTime()));
        ServerHttpRequest build = mutate.build();

       return gatewayFilterChain.filter(serverWebExchange.mutate().request(build).build());

    }

    /*网关抛异常*/
    private Mono<Void> getVoidMono(ServerWebExchange serverWebExchange, String Msg) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.OK);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error_code",-1);
        jsonObject.put("msg",Msg);
        byte[] bytes = JSONObject.toJSONString(jsonObject).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = serverWebExchange.getResponse().bufferFactory().wrap(bytes);
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    /*排除不需要拦截的服务*/
    private boolean CheckServerToken(String ServerUrl){
        boolean Status = false;
        for(int i = 0; i < this.outserver.length; i++){
            if(ServerUrl.startsWith("/"+this.outserver[i])){
                Status = true;
            }
        }
        return Status;
    }

    private Claims parseToken(String token,String TokenKey) {
        return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(TokenKey))
                .parseClaimsJws(token).getBody();
    }

    /*验证Token方法*/
    private Claims CheckJwtToken(ServerHttpRequest request, ServerHttpRequest.Builder ctx)throws Exception {
        List<String> strings = request.getHeaders().get(this.tokenheader);
        String authToken = null;
        if (strings != null) {
            authToken = strings.get(0);
        }
        if (StringUtils.isBlank(authToken)) {
            strings = request.getQueryParams().get("token");
            if (strings != null) {
                authToken = strings.get(0);
            }
        }
        return this.parseToken(authToken,this.tokenkey);
    }

    /**
     * 刷新马上过期的Token，重置Token
     * @param TokenExptime
     * @return
     */
    private String RefreshToken(Long TokenExptime,String UserName,String Nickname,String UserId,String sellerId,String levelId,String userType){
        Long Exptime = TokenExptime - 5*60*1000; //到期的2分钟之前的时间
        Long NowTime = new Date().getTime(); //现在的时间戳
        //符合条件那么开始刷新Token
        if(NowTime >= Exptime && Exptime <= TokenExptime){
            Map<String,Object> TokenData = new HashMap<String, Object>();
            TokenData.put("username", UserName);
            TokenData.put("nickname", Nickname);
            TokenData.put("user_id", UserId);
            TokenData.put("sellerId", sellerId);
            TokenData.put("levelId", levelId);
            TokenData.put("userType", userType);
            String TokenStr = this.createToken(this.tokenkey,TokenData);
            return TokenStr;
        }
        return null;
    }

    /**
     * 1、选择签名的算法
     * 2、生成签名的密钥
     * 3、构建Token信息
     * 4、利用算法和密钥生成Token
     */
    private String createToken(String TokenKey, Map<String, Object> claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(TokenKey);
        Key signingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
         /*   Map<String, Object> claims = new HashMap<String, Object>();
            claims.put("username", "token");
            claims.put("role", "admin");*/
        JwtBuilder builder = Jwts.builder().setClaims(claims)
                .setId("tokenid")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+35*60*1000))
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }
}
