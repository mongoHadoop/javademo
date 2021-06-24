package com.justbon.jsonutil;

import net.sf.json.JSONObject;

/**
 * @author ganli
 * @version 1.0
 * @file JsonLibUtil.java
 * @Modified By：
 * @date 2021-06-24 上午10:19
 * @description
 */

public class JsonLibUtil {

    public static String bean2Json(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    @SuppressWarnings("unchecked")
    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return (T) JSONObject.toBean(JSONObject.fromObject(jsonStr), objClass);
    }
}