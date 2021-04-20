package com.justbon.algorithm.sort.bubblesort;

/**
 * @author ganli
 * @version 1.0
 * @file BubbleSort.java
 * @Modified By：
 * @date 2021-04-20 下午5:03
 * @description
 * 冒泡排序
 */
public class BubbleSort {


    public static void popsort(int arrays[]){

        System.out.println("排序前的数组");
        System.out.println(arrays);

        for (int i=0;i<arrays.length-1;i++){
            for(int j=0;j<arrays.length-1-i;j++){
                if(arrays[j]>arrays[j+1]){
                    int temp=arrays[j];
                    arrays[j+1]=temp;
                    arrays[j]=arrays[j+1];
                }
            }
        }
        System.out.print("排序后:");
        System.out.print(arrays);
    }

    public static void main(String[] args) {
        int[] arr = {8, 7, 6, 4, 5};
        popsort(arr);
    }
}
