package com.justbon.guava;

import com.google.common.base.Objects;
import org.junit.Test;

public class TestGuavaEqual {

    /**
     * 在开发中经常会需要比较两个对象是否相等，这时候我们需要考虑比较的两个对象是否为null，
     * 然后再调用equals方法来比较是否相等
     * ，google guava库的com.google.common.base.Objects类提供了一个静态方法equals
     * 可以避免我们自己做是否为空的判断，示例如下
     */
    @Test
    public void testEqual(){

          Object a = null;
           Object b = new Object();
          boolean aEqualsB = Objects.equal(a, b);
        System.out.println(aEqualsB);
    }

}
