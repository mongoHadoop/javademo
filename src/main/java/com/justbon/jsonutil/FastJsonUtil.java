package com.justbon.jsonutil;

import com.alibaba.fastjson.JSON;

/**
 * @author ganli
 * @version 1.0
 * @file FastJsonUtil.java
 * @Modified By：
 * @date 2021-06-24 上午10:16
 * @description
 */
public class FastJsonUtil {
    public static String bean2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
        return JSON.parseObject(jsonStr, objClass);
    }
}