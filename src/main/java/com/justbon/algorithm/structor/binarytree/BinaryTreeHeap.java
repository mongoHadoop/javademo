package com.justbon.algorithm.structor.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ganli
 * @version 1.0
 * @Modified By：
 * @date 2020-11-05 下午7:48
 * @description
 * 二叉堆虽然是一个完全二叉 树，但它的存储方式并不是链式存储，而是顺序存储。换句话说，二叉 堆的所有节点都存储在数组中。
 * 在数组中，在没有左、右指针的情况下，如何定位一个父节点的左孩子 和右孩子呢？
 * 假设父节点的下标是parent，那么它的左孩子下标就是 2×parent+1 ；右 孩子下标就是2×parent+2
 * 例如上面的例子中，节点6包含9和10两个孩子节点，节点6在数组中的 下标是3，节点9在数组中的下标是7，节点10在数组中的下标是8。 那么， 7 = 3×2+1， 8 = 3×2+2， 刚好符合规律
 *
 *
 * 二叉树可以用哪些物理存储结构来表达呢？ 1. 链式存储结构。 2. 数组。 让我们分别看看二叉树如何使用这两种结构进行存储吧。 首先来看一看链式存储结构
 *
 * 链式存储是二叉树最直观的存储方式。 上一章讲过链表，链表是一对一的存储方式，每一个链表节点拥有data 变量和一个指向下一节点的next指针。
 * 而二叉树稍微复杂一些，一个节点最多可以指向左右两个孩子节点，所 以二叉树的每一个节点包含3部分。 存储数据的data变量 指向左孩子的left指针 指向右孩子的right指针
 *
 * 使用数组存储时，会按照层级顺序把二叉树的节点放到数组中对应的位 置上。如果某一个节点的左孩子或右孩子空缺，则数组的相应位置也空 出来。
 * 为什么这样设计呢？因为这样可以更方便地在数组中定位二叉树的孩子 节点和父节点。
 *
 * 假设一个父节点的下标是parent，那么它的左孩子节点下标就 是2×parent + 1 ；右孩子节点下标就是2×parent + 2 。
 * 反过来，假设一个左孩子节点的下标是leftChild，那么它的父节点下标 就是（leftChild-1）/ 2 。
 * 假如节点4在数组中的下标是3，节点4是节点2的左孩子，节点2的下标 可以直接通过计算得出。 节点2的下标 = (3-1)/2 = 1
 *
 *
 * 二叉堆的实现 只能利用数组来实现
 *
 */
public class BinaryTreeHeap {

    /**
     * 上浮 调整
     * @param array
     */
    public static void  upAdjust(int[] array){
        int childIndex=array.length-1;
        int parentIndex=(childIndex-1)/2;
        //temp保存 插入的叶子节点值,用于最后的赋值
        int temp = array[childIndex];
        while (childIndex>0&& temp<array[parentIndex]){
            array[childIndex]=array[parentIndex];
            childIndex=parentIndex;
            parentIndex=(parentIndex-1)/2;
        }
        array[childIndex]=temp;

    }

    /**
     * 下沉 调整
     假设一个父节点的下标是parent，那么它的左孩子节点下标就 是2×parent + 1 ；右孩子节点下标就是2×parent + 2 。
     * @param index 要下浮的节点的下标
     * @param array 数据数组
     */
    public static void downAdjust(int index, int[] array) {
        // 先记录父节点及左子节点的下标
        int parentIndex = index;
        //左边节点
        int childrenIndex = 2 * parentIndex + 1;
        // 记录父节点的值，用于最后赋值
        int temp = array[parentIndex];
        // 若有左子节点则继续
        while (childrenIndex <= array.length - 1) {
            // 若有右子节点，且右子节点比左子节点大，则将 childrenIndex 记录为右子节点的下标
            if (childrenIndex + 1 <= array.length - 1 && array[childrenIndex + 1] > array[childrenIndex]) {
                childrenIndex++;
            }
            // 如果子节点大于父节点，则无需下沉，直接返回
            if (temp >= array[childrenIndex]) {
                break;
            }
            // 直接单向赋值，无需做交替操作
            array[parentIndex] = array[childrenIndex];
            // 更新父子节点下标的值，下面两句代码顺序不可相反
            parentIndex = childrenIndex;
            childrenIndex = 2 * childrenIndex + 1;
        }
        // 最后赋值
        array[parentIndex] = temp;
    }


    /**
     * 构建堆
     */

    public  static void  buildHeap(int[] array){
        for (int i = (array.length/2)-1; i >= 0; i--) {
            downAdjust(i, array);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};
        upAdjust(array);
        System.out.println(Arrays.toString(array));
        array=new int[]{7,1,3,10,5,2,8,9,6};
        buildHeap(array);
        System.out.println(Arrays.toString(array));
    }

}
