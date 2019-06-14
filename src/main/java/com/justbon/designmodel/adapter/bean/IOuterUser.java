package com.justbon.designmodel.adapter.bean;

import java.util.Map;

/**
 * @author ganli
 * @version 1.0
 * @file IOuterUser.java
 * @Modified By：
 * @date 2019-05-13 上午8:54
 * @description
 */
@SuppressWarnings("all")
public interface IOuterUser {
    //基本信息,比如名称,性别,手机号码了等
    public Map getUserBaseInfo();
    //工作区域信息
    public Map getUserOfficeInfo();
    //用户的家庭信息
    public Map getUserHomeInfo();
}