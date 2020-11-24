package com.justbon.algorithm.popsort;

/**
 * @author ganli
 * @version 1.0
 * @file PopSort.java
 * @Modified By：
 * @date 2020-11-24 上午11:10
 * @description
 */
public class PopSort {
    public static void popsort(){
        int[] arr = {8, 7, 6, 4, 5};
        System.out.println("交换之前：");
        for(int num:arr){
            System.out.print(num+" ");
        }
        for(int i=0 ;i<arr.length-1;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp =arr[j];
                    arr[j]=arr[j+1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println();
        System.out.println("交换后：");
        for(int num:arr){
            System.out.print(num+" ");
        }
    }

    public static void main(String[] args) {
        popsort();
    }
}
