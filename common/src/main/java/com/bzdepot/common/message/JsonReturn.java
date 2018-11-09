package com.bzdepot.common.message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bzdepot.common.util.UserUtil;

import java.util.Map;

public class JsonReturn {

    public static Object SetMsg(int Code , String Msg ,Object Datas){
        String ReToken = UserUtil.getReToken();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("error_code",Code);
        jsonObject.put("msg",Msg);
        jsonObject.put("data",Datas);
        if(ReToken != null){
            jsonObject.put("retoken",ReToken);
        }
        return jsonObject;
    }

    public static Map<String,Object> Parse(Object result){
        try {
            JSONObject jsonObject = (JSONObject) JSON.toJSON(result);
            Map<String, Object> itemMap = JSONObject.toJavaObject(jsonObject, Map.class);
            return itemMap;
        }catch (Exception e){
            return null;
        }
    }

}
