package com.bzdepot.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

        /**
         * 1、选择签名的算法
         * 2、生成签名的密钥
         * 3、构建Token信息
         * 4、利用算法和密钥生成Token
         */
        public static String createToken(String TokenKey, Map<String, Object> claims) {
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
        /*
        *   验证Token的方法
        * */
        public static Claims parseToken(String token,String TokenKey) {
            return Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(TokenKey))
                    .parseClaimsJws(token).getBody();
        }

     /*   public static void validateToken(String token) {
            try{
                Claims claims = parseToken(token);
                String username = claims.get("username").toString();
                String role = claims.get("role").toString();
                String tokenid = claims.getId();
                System.out.println("[username]:"+username);
                System.out.println("[role]:"+role);
                System.out.println("[tokenid]:"+tokenid);
            } catch(ExpiredJwtException e) {
                System.out.println("token expired");
            } catch (InvalidClaimException e) {
                System.out.println("token invalid");
            } catch (Exception e) {
                System.out.println("token error");
            }
        }*/
}
