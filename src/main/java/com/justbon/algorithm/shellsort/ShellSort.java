package com.justbon.algorithm.shellsort;

/**
 * @author ganli
 * @version 1.0
 * @file ShellSort.java
 * @Modified By：
 * @date 2020-12-24 上午9:14
 * @description 希尔排序
 *
 * 希尔排序(Shell Sort)是插入排序的一种,
 * 是针对直接插入排序算法的改进,
 * 是将整个无序列分割成若干小的子序列分别进行插入排序,希尔排序并不稳定.
 * 该方法又称缩小增量排序,因DL．Shell于1959年提出而得名.
 *基本思想
 *
 * 　　先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。
 *    所有距离为d1的倍数的记录放在同一个组中。
 *    先在各组内进行直接插入排序；
 *    然后，取第二个增量d2<d1重复上述的分组和排序，
 *    直至所取的增量dt=1(dt<dt-l<…<d2<d1)，
 *    即所有记录放在同一组中进行直接插入排序为止。
 *
 *
 *
 * 排序思想
 *  希尔排序(Shell’s Sort)是插入排序的一种，是直接插入排序算法的一种更高版本的改进版本。
 *
 * 把记录按步长gap分组，对每组记录采用直接插入排序方法进行排序；
 * 随着步长逐渐减小，所分成的组包含的记录越来越多；
 * 当步长值减小到1时，整个数据合成一组，构成一组有序记录，完成排序；
 *
 *
 * 步长的选择是希尔排序的重要部分。只要最终步长为1任何步长序列都可以工作。
 *
 * 算法最开始以一定的步长进行排序。然后会继续以一定步长进行排序，最终算法以步长为1进行排序。当步长为1时，算法变为插入排序，这就保证了数据一定会被排序。
 * Donald Shell 最初建议步长选择为N/2并且对步长取半直到步长达到1。虽然这样取可以比O(N2)类的算法（插入排序）更好，但这样仍然有减少平均时间和最差时间的余地。可能希尔排序最重要的地方在于当用较小步长排序后，以前用的较大步长仍然是有序的。比如，如果一个数列以步长5进行了排序然后再以步长3进行排序，那么该数列不仅是以步长3有序，而且是以步长5有序。如果不是这样，那么算法在迭代过程中会打乱以前的顺序，那就
 *
 * 不会以如此短的时间完成排序了。
 *
 * 时间复杂度
 * 平均O(Nlog2N) 最多O(N1.5)
 */
public class ShellSort {

    //希尔排序
    public static void shellSort(int[] array) {
        //分组的个数
        int gap = array.length;
        while (true) {
            gap = gap / 3 + 1;
            //分组的插入排序
            insertSortWithGap(array, gap);
            if (gap == 1) {
                return;
            }
        }
    }
    //每组的组内插入排序
    private static void insertSortWithGap(int[] array, int gap) {
        //假设前gap组已经有序，所以只循环arr.length-gap次
        for (int i = 0; i < array.length - gap; i++) {
            //组内排序
            int key = array[i + gap];
            int j;
            for (j = i; j >= 0 && key < array[j]; j -= gap) {
                array[j + gap] = array[j];
            }
            array[j + gap] = key;
        }
    }


    public static void shellSort2(int[] a){
        //增量长度
        double gap = a.length;
        int dk,sentinel,k;
        //逐渐减小增量长度
        while(true){
            gap = (int)Math.ceil(gap/2);
            //确定增量长度
            dk = (int)gap;
            for(int i=0;i<dk;i++){
                //用增量将序列分割，分别进行直接插入排序。随着增量变小为1，最后整体进行直接插入排序
                for(int j=i+dk;j<a.length;j = j+dk){
                    k = j-dk;
                    sentinel = a[j];
                    while(k>=0 && sentinel<a[k]){
                        a[k+dk] = a[k];
                        k = k-dk;
                    }
                    a[k+dk] = sentinel;
                }
            }
            //当dk为1的时候，整体进行直接插入排序
            if(dk==1){
                break;
            }
        }
    }

}
