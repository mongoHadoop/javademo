package com.justbon.algorithm.sort.fastsort;

/**
 *  快速排序演示
 *  @author Lvan
 * @author ganli
 * @version 1.0
 * @file QuickSort.java
 * @Modified By：
 * @date 2020-11-30 上午9:15
 * @description
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {5, 1, 7, 3, 1, 6, 9, 4};

        quickSort(arr, 0, arr.length - 1);

        for (int i : arr) {
            System.out.print(i + "\t");
        }
    }

    /**
     * @param arr        待排序列
     * @param leftIndex  待排序列起始位置
     * @param rightIndex 待排序列结束位置
     */
    private static void quickSort(int[] arr, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return;
        }

        int left = leftIndex;
        int right = rightIndex;
        //待排序的第一个元素作为基准值
        int key = arr[left];

        //从左右两边交替扫描，直到left = right
        while (left < right) {
            while (right > left && arr[right] >= key) {
                //从右往左扫描，找到第一个比基准值小的元素 5, 1, 7, 3, 1, 6, 9, 4
                right--;
                System.out.println("right:"+right+" left:"+left);

            }

            //找到这种元素将arr[right]放入arr[left]中
            arr[left] = arr[right];

            while (left < right && arr[left] <= key) {
                //从左往右扫描，找到第一个比基准值大的元素  5, 1, 7, 3, 1, 6, 9, 4
                left++;
            }
            // 5, 1, 7, 3, 1, 6, 9, 4
            //找到这种元素将arr[left]放入arr[right]中
            arr[right] = arr[left];
        }
        //基准值归位
        arr[left] = key;
        //对基准值左边的元素进行递归排序
        quickSort(arr, leftIndex, left - 1);
        //对基准值右边的元素进行递归排序。
        quickSort(arr, right + 1, rightIndex);
    }

//    /***
//     * 双边递归循环法
//     * @param arr
//     * @param startIndex
//     * @param endIndex
//     */
//    public static  void quicksort1(int[]arr,int startIndex,int endIndex){
//        if(startIndex>=endIndex){
//            return;
//        }
//        //得到基準元素位置
//        int pivotIndex=partition1(arr,startIndex,endIndex);
//        quicksort1(arr,startIndex,pivotIndex-1);
//        quicksort1(arr,pivotIndex+1,endIndex);
//    }
//    public static  void quicksort2(int[]arr,int startIndex,int endIndex){
//        if(startIndex>=endIndex){
//            return;
//        }
//        //得到基準元素位置
//        int pivotIndex=partition2(arr,startIndex,endIndex);
//        quicksort2(arr,startIndex,pivotIndex-1);
//        quicksort2(arr,pivotIndex+1,endIndex);
//    }
//    /**
//     * 分治 双边循环法
//     * @param arr
//     * @param startIndex
//     * @param endIndex
//     * @return
//     */
//    private static int partition1(int[] arr,int startIndex,int endIndex){
//        int pivot=arr[startIndex];
//        int left=startIndex;
//        int right=endIndex;
//        while (left!=right){
//            //控制right 指针比较并左移动
//            while (left < right && arr[right] > pivot){
//                right--;
//            }
//            //控制left指针比较并右边移动
//            while (left<right&&arr[left]<pivot){
//                left++;
//            }
//            //交换left 与 right 指针所指向的元素
//            if(left<right){
//                int p=arr[left];
//                arr[left]=arr[right];
//                arr[right]=p;
//            }
//
//        }
//        //pivot 和指针重合点交换
//        arr[startIndex]=arr[left];
//        arr[left]=pivot;
//        return left;
//    }
//
//    /**
//     * 单边递归
//     * @return
//     */
//    private static int partition2(int[]arr,int startIndex,int endIndex){
//
//        //取第一个位置的元素为基准元素
//        int pivot=arr[startIndex];
//        int mark=startIndex;
//        for(int i=startIndex+1;i<endIndex;i++){
//            if(arr[i]<pivot){
//                mark++;
//                int p= arr[mark];
//                arr[mark]=arr[i];
//                arr[i]=p;
//            }
//        }
//        arr[startIndex]=arr[mark];
//        arr[mark]=pivot;
//        return mark;
//    }

}