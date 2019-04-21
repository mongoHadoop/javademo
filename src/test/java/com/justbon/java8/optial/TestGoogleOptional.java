package com.justbon.java8.optial;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestGoogleOptional {

/*
    @Test
    public void testOpt(){
        Map<String, Map<String, String>> map = Maps.newHashMap();
        List<List<Map<String, String>>> list = Lists.newArrayList();
        Set<String> set = Sets.newHashSet("one","two","three");
        List<String> list1 = Lists.newArrayList("one","two","three");

        list1.forEach(e-> System.out.println(e));
    }
    *//**
     * Immutable Collections: 不可变的集合，还在使用 Collections.unmodifiableXXX()
     * Immutable Collections 这才是真正的不可修改的集合Immutable Collections:
     * 不可变的集合，还在使用 Collections.unmodifiableXXX()
     * Immutable Collections 这才是真正的不可修改的集合
     *//*

    @Test
    public void testOpt2(){
        ImmutableSet<String> immutableSet = ImmutableSet.of("RED", "GREEN");

        ImmutableSet.Builder<String> builder = ImmutableSet.builder();
        Set<String> set = Sets.newHashSet("one","two","three");
        ImmutableSet<String> immutableSet2 = builder.add("RED").addAll(set).build();
        immutableSet2.forEach(e-> System.out.println(e));
    }*/
}
