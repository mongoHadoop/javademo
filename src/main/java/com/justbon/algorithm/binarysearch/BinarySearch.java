package com.justbon.algorithm.binarysearch;

/**
 * @author ganli
 * @version 1.0
 * @file BinarySearch.java
 * @Modified By：
 * @date 2021-04-14 下午4:56
 * @description 二分查找
 * 折半查找的算法思想是将数列按有序化(递增或递减)排列，查找过程中采用跳跃式方式查找，
 * 即先以有序数列的中点位置为比较对象，如果要找的元素值小 于该中点元素，则将待查序列缩小为左半部分，
 * 否则为右半部分。通过一次比较，将查找区间缩小一半。
 * 折半查找是一种高效的查找方法。
 * 它可以明显减少比较次数，提高查找效率。
 * 但是，折半查找的先决条件是查找表中的数据元素必须有序。
 *
 *
 *
 * 二分算法步骤描述
 *
 * ① 首先确定整个查找区间的中间位置 mid = （ left + right ）/ 2
 * ② 用待查关键字值与中间位置的关键字值进行比较；
 * 若相等，则查找成功
 * 若大于，则在后（右）半个区域继续进行折半查找
 * 若小于，则在前（左）半个区域继续进行折半查找
 *
 * ③ 对确定的缩小区域再按折半公式，重复上述步骤。
 * 最后，得到结果：要么查找成功， 要么查找失败。折半查找的存储结构采用一维数组存放。 折半查找算法举例
 * 对给定数列（有序）{ 3,5,11,17,21,23,28,30,32,50,64,78,81,95,101}，按折半查找算法，查找关键字值为81的数据元素。
 */
public class BinarySearch {


    public static int binSearch(int srcArray[],int start, int end, int key){
        //初始中间位置 注意 取值中间的区别
        int mid= (end - start) / 2 +start;
        if (srcArray[mid] == key) {
            return mid;
        }

        if(key>srcArray[mid]){
            return binSearch(srcArray,mid+1,end,key);
        }
        if(key<srcArray[mid]){
            return binSearch(srcArray,start,mid-1,key);
        }
        return -1;
    }
    /**
     * 使用递归的二分查找
     *title:recursionBinarySearch
     *@param arr 有序数组
     *@param key 待查找关键字
     *@return 找到的位置
     */
    public static int recursionBinarySearch(int[] arr,int key,int low,int high) {

        if (key < arr[low] || key > arr[high] || low > high) {
            return -1;
        }
        //初始中间位置
        //int mid= (end - start) / 2 +start;
        int middle = (low + high) / 2;
        if (arr[middle] > key) {
            //比关键字大则关键字在左区域
            return recursionBinarySearch(arr, key, low, middle - 1);
        } else if (arr[middle] < key) {
            //比关键字小则关键字在右区域
            return recursionBinarySearch(arr, key, middle + 1, high);
        } else {
            return middle;
        }
    }

    public static void main(String[] args) {
        int srcArray[] = {3,5,11,17,21,23,28,30,32,50,64,78,81,95,101};
        System.out.println(binSearch(srcArray, 0, srcArray.length - 1, 81));
        System.out.println(recursionBinarySearch(srcArray,81,0,srcArray.length-1));
    }

}
