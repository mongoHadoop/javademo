package com.justbon.algorithm.structor.binarytree;

/**
 *
 *  * 二叉堆虽然是一个完全二叉 树，但它的存储方式并不是链式存储，而是顺序存储。换句话说，二叉 堆的所有节点都存储在数组中。
 *  * 在数组中，在没有左、右指针的情况下，如何定位一个父节点的左孩子 和右孩子呢？
 *  * 假设父节点的下标是parent，那么它的左孩子下标就是 2×parent+1 ；右 孩子下标就是2×parent+2
 *  * 例如上面的例子中，节点6包含9和10两个孩子节点，节点6在数组中的 下标是3，节点9在数组中的下标是7，节点10在数组中的下标是8。 那么， 7 = 3×2+1， 8 = 3×2+2， 刚好符合规律
 *  *
 *  *
 *  * 二叉树可以用哪些物理存储结构来表达呢？ 1. 链式存储结构。 2. 数组。 让我们分别看看二叉树如何使用这两种结构进行存储吧。 首先来看一看链式存储结构
 *  *
 *  * 链式存储是二叉树最直观的存储方式。 上一章讲过链表，链表是一对一的存储方式，每一个链表节点拥有data 变量和一个指向下一节点的next指针。
 *  * 而二叉树稍微复杂一些，一个节点最多可以指向左右两个孩子节点，所 以二叉树的每一个节点包含3部分。 存储数据的data变量 指向左孩子的left指针 指向右孩子的right指针
 *  *
 *  * 使用数组存储时，会按照层级顺序把二叉树的节点放到数组中对应的位 置上。如果某一个节点的左孩子或右孩子空缺，则数组的相应位置也空 出来。
 *  * 为什么这样设计呢？因为这样可以更方便地在数组中定位二叉树的孩子 节点和父节点。
 *  *
 *  * 假设一个父节点的下标是parent，那么它的左孩子节点下标就 是2×parent + 1 ；右孩子节点下标就是2×parent + 2 。
 *  * 反过来，假设一个左孩子节点的下标是leftChild，那么它的父节点下标 就是（leftChild-1）/ 2 。
 *  * 假如节点4在数组中的下标是3，节点4是节点2的左孩子，节点2的下标 可以直接通过计算得出。 节点2的下标 = (3-1)/2 = 1
 * @author ganli
 * @version 1.0
 * @file BinaryHeap.java
 * @Modified By：
 * @date 2020-11-09 下午3:23
 * @description
 *
 *
 * 1.二叉堆的核心代码是上述的upAdjust()、downAdjust()函数，在实现时要注意 while
 * 循环条件判断时是判断childrenIndex以及在更新父子节点下标的值顺序不可倒置；
 * 2.二叉堆是优先队列的理论基石。理解了二叉堆之后，优先队列就很简单了。
 *
 */

import java.util.Arrays;

public class BinaryHeap {
    /**
     * 上浮
     * @param array 数据数组
     */
    public static void upAdjust(int[] array) {
        // 先求出父子节点的下标
        int childrenIndex = array.length - 1;
        int parentIndex = (childrenIndex - 1) / 2;
        // 记录子节点数据，用于最后赋值
        int temp = array[childrenIndex];
        // 开始上浮
        while (childrenIndex > 0 && temp > array[parentIndex]) {
            // 直接单向赋值，无需做交换操作
            array[childrenIndex] = array[parentIndex];
            // 更新父子节点下标的值，下面两句代码顺序不可相反
            childrenIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        // 最后赋值
        array[childrenIndex] = temp;
    }

    /**
     * 下沉节点
     * @param index 要下浮的节点的下标
     * @param array 数据数组
     */
    public static void downAdjust(int index, int[] array) {
        // 先记录父节点及左子节点的下标
        int parentIndex = index;
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
     * 构建二叉堆
     * @param array 数据数组
     */
    public static void  buildBinaryHeap(int[] array) {
        for (int i = (array.length/2)-1; i >= 0; i--) {
            downAdjust(i, array);
        }
    }

    public static void main(String[] args) {
        // 构建二叉堆
        int[] arr01 = {5, 3, 6, 9, 8, 6, 7, 2, 4, 6, 3};
        buildBinaryHeap(arr01);
        System.out.println(Arrays.toString(arr01));
        // 添加一个数据，测试上浮操作
        int[] arr02 = {9, 8, 7, 4, 6, 6, 6, 2, 3, 5, 3, 20};
        upAdjust(arr02);
        System.out.println(Arrays.toString(arr02));
        // 删除一个数据，册数下沉操作
        int[] arr03 = {1, 8, 7, 4, 6, 0, 61, 2, 3, 5};
        downAdjust(0, arr03);
        System.out.println(Arrays.toString(arr03));
    }
}