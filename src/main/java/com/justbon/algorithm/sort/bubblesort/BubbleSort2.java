package com.justbon.algorithm.sort.bubblesort;

/**
 * @author ganli
 * @version 1.0
 * @file BubbleSort.java
 * @Modified By：
 * @date 2021-04-20 下午5:03
 * @description
 * 冒泡排序
 * 优化版本
 */
public class BubbleSort2 {


    public static void popsort(int arrays[]){

        System.out.println("排序前的数组");
        System.out.println(arrays);

        for (int i=0;i<arrays.length-1;i++){
            boolean isSorted=true;
            for(int j=0;j<arrays.length-1-i;j++){
                if(arrays[j]>arrays[j+1]){
                    int temp=arrays[j];
                    arrays[j+1]=temp;
                    arrays[j]=arrays[j+1];
                    //即还有数据在交换则表明是非有序的
                    isSorted=false;
                }
                if (isSorted){
                    break;
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
