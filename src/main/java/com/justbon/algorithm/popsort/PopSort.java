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


    public static  void pop2(int[] arrays){

        System.out.print("排序前的数字");
        for (int num:arrays
             ) {
            System.out.print(num);
        }
        System.out.println("开始排序");
        for(int i=0;i<arrays.length;i++){
            for(int j=0;j<arrays.length-1-i;j++){
                        if(arrays[j]>arrays[j+1]){
                            int temp=arrays[j];
                            //把小值此动作是向下顶
                            arrays[j]=arrays[j+1];
                            // 此动作是往上顶
                            arrays[j+1]=temp;
                        }

            }
        }
        System.out.println("交换后：");
        for(int num:arrays){
            System.out.print(num+" ");
        }
    }


//    public static void popsort(){
//        int[] arr = {8, 7, 6, 4, 5};
//        System.out.println("交换之前：");
//        for(int num:arr){
//            System.out.print(num+" ");
//        }
//        for(int i=0 ;i<arr.length-1;i++){
//            for(int j=0;j<arr.length-1-i;j++){
//                if(arr[j]>arr[j+1]){
//                    int temp =arr[j];
//                    arr[j]=arr[j+1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println();
//        System.out.println("交换后：");
//        for(int num:arr){
//            System.out.print(num+" ");
//        }
//
//        for(int i=0;i<arr.length-1;i++){
//            for(int j=0;i<arr.length-1-i;j--){
//                int temp =arr[j];
//                arr[j]=arr[j+1];
//                arr[j+1]=temp;
//            }
//        }
//    }

    public static void main(String[] args)
    {
        int[] arr = {8, 7, 6, 4, 5};
        pop2(arr);
    }
}
