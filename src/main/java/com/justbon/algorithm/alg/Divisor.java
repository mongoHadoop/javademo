package com.justbon.algorithm.alg;

/**
 * @author ganli
 * @version 1.0
 * @file Divisor.java
 * @Modified By：
 * @date 2020-11-23 下午4:41
 * @description  最大公约数
 * 欧几里 算法试
 * 两个正整数 ab,他们的最大公约数
 * 等于 a/b 的余数c,c和b之间的最大公约数
 * 递归 也就是
 *
 */
public class Divisor {

    public  static  int getGreatestCommonDivisor(int a,int b){
        int big =a>b?a:b;
        int small=a<b?a:b;
        if(big%small==0){
            return  big;
        }else{
            return  getGreatestCommonDivisor(big%small,small);
        }
    }

    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisor(9,12));
    }
}
