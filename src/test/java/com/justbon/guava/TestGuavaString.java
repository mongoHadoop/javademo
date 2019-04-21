package com.justbon.guava;

import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestGuavaString {

    /***
     *
     * 使用com.google.common.base.Strings类的isNullOrEmpty(input)方法判断字符串是否为空
     */
    @Test
    public  void testString(){
        String input = "";
        boolean isNullOrEmpty = Strings.isNullOrEmpty(input);
        System.out.println("input " + (isNullOrEmpty?"is":"is not") + " null or empty.");
        //Strings.commonPrefix(a,b) demo
        /***
         * 获得两个字符串相同的前缀或者后缀
         */
        String a = "com.jd.coo.Hello";
        String b = "com.jd.coo.Hi";

        String ourCommonPrefix = Strings.commonPrefix(a,b);
        System.out.println("a,b common prefix is " + ourCommonPrefix);
        //Strings.commonSuffix(a,b) demo
        String c = "com.google.Hello";
        String d = "com.jd.Hello";
        String ourSuffix = Strings.commonSuffix(c,d);
        System.out.println("c,d common suffix is " + ourSuffix);
        /**
         *  Strings的padStart和padEnd方法来补全字符串
         *  补全字符串 0001
         *
         */

        int minLength = 4;
        String padEndResult = Strings.padEnd("123", minLength, '0');
        System.out.println("padEndResult is " + padEndResult);
        String padStartResult = Strings.padStart("1", 4, '0');
        System.out.println("padStartResult is " + padStartResult);
    }
    /***
     * 使用Splitter类来拆分字符串
     * Splitter类可以方便的根据正则表达式来拆分字符串,
     * 可以去掉拆分结果中的空串，可以对拆分后的字串做trim操作,
     * 还可以做二次拆分
     */
    @Test
    public void testStringSplit(){
        Iterable<String> splitResults = Splitter.onPattern("[,，]{1,}")
                .trimResults()
                .omitEmptyStrings()
                .split("hello,word,,世界，水平");

        for (String item : splitResults) {
            System.out.println(item);
        }
    }

    /**
     *  有拆分字符串必然就有合并字符串，guava为我们提供了Joiner类来做字符串的合并
     */
    @Test
    public void testStringJoiner(){
        String joinResult = Joiner.on(" ").join(new String[]{"hello","world"});
        System.out.println(joinResult);
        /**
         * 使用withKeyValueSeparator方法可以对map做合并。合并的结果是:a=b,c=d
         *
         */
        Map<String,String> map =  Maps.newHashMap();
        map.put("a","b");
        map.put("c","d");
        String mapJoinResult = Joiner.on(",").withKeyValueSeparator("=").join(map);
        System.out.println(mapJoinResult);

    }

    /**
     *  guava库中还可以对字符串做大小写转换（CaseFormat枚举），可以对字符串做模式匹配。使用起来都很方便
     */
    @Test
    public void testCaseFormat() {
        System.out.println(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "test-data"));
        System.out.println(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "test_data"));
        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "test_data"));

        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "testdata"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "TestData"));
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "testData"));
    }

}
