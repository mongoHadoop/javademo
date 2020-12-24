package com.justbon.algorithm.insertsort;

/**
 * @author ganli
 * @version 1.0
 * @file InsertSort.java
 * @Modified By：
 * @date 2020-11-26 下午4:16
 * @description
 *
 * 插入排序（英语：Insertion Sort）是一种简单直观的排序算法.
 * 它的工作原理是通过构建有序序列,对于未排序数据,
 * 在已排序序列中从后向前扫描,找到相应位置并插入.
 * 插入排序在实现上,在从后向前扫描过程中，需要反复把已排序元素逐步向后挪位,为最新元素提供插入空间.
 *
 */
public class InsertSort{

    public static <T extends Comparable<? super T>> void insertSort(T[] a){
        for(int p = 1; p < a.length; p++)
        {
            T tmp = a[p];
            //保存当前位置p的元素，其中[0,p-1]已经有序
            int j;
            for(j = p; j > 0 && tmp.compareTo(a[j-1]) < 0; j--)
            {
                a[j] = a[j-1];
                //后移一位
            }
            a[j] = tmp;
            //插入到合适的位置
        }
    }



    public static int[] sort(int[] ins){

        for(int i=1; i<ins.length; i++){
            for(int j=i; j>0; j--){
                if(ins[j]<ins[j-1]){
                    int temp = ins[j-1];
                    ins[j-1] = ins[j];
                    ins[j] = temp;
                }
            }
        }
        return ins;
    }

    /**
     * 针对上面的这个排序算法改进：首先上面的这个每次替换都要定义一个temp赋值需要插入的数，这样会造成不必要的浪费：
     *
     * 所以我们可以吧所有的大于需要插入的数先保存，然后进行比较，然后将最后的正确位置空出来。吧之前保存的需要插入的数放到正确位置上；
     * @param ins
     * @return
     */

    public static int[] sort2(int[] ins){

        for(int i=1; i<ins.length; i++){
            //保存每次需要插入的那个数
            int temp = ins[i];
            int j;
            //这个较上面有一定的优化
            for(j=i; j>0&&ins[j-1]>temp; j--){
                //把大于需要插入的数往后移动。最后不大于temp的数就空出来j
                ins[j] = ins[j-1];
            }
            //将需要插入的数放入这个位置
            ins[j] = temp;
        }
        return ins;
    }
    //for test purpose
    public static void main(String[] args) {
        Integer[] arr = {34,8,64,51,32,21};
        insertSort(arr);

        for (Integer i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("--------------");
        int[] arr2 = {34,8,64,51,32,21};
        int[] arr2_=sort(arr2);
        for (int i : arr2_) {
            System.out.print(i+" ");
        }

    }
}