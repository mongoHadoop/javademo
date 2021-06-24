package com.justbon.jsonutil.pojo;

/**
 * @author ganli
 * @version 1.0
 * @file FullName.java
 * @Modified By：
 * @date 2021-06-24 上午10:20
 * @description
 */
public class FullName {
    private String firstName;
    private String middleName;
    private String lastName;

    public FullName() {
    }

    public FullName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // 省略getter和setter

    @Override
    public String toString() {
        return "[firstName=" + firstName + ", middleName="
                + middleName + ", lastName=" + lastName + "]";
    }
}
