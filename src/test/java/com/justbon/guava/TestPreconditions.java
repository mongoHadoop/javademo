package com.justbon.guava;

import com.google.common.base.Preconditions;
import org.junit.Test;

public class TestPreconditions {

    /**
     * 1 checkArgument 接受一个boolean类型的参数和一个可选的errorMsg参数，
     *  这个方法用来判断参数是否符合某种条件，
     *  符合什么条件google guava不关心，在不符合条件时会抛出IllegalArgumentException异常
     *
     */
    @Test
    public void testPreconditions() {
        String name = "";
        int age = 1;
        String desc = "";
        Preconditions.checkNotNull(name, "name may not be null");
        Preconditions.checkArgument(age >= 18 && age < 99, "age must in range (18,99)");
        Preconditions.checkArgument(desc != null && desc.length() < 10, "desc too long, max length is ", 10);
    }
}
