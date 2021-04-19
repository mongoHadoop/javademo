package com.justbon.algorithm.structor.array;


import java.util.Arrays;

/**
 * @author ganli
 * @version 1.0
 * @file MyArray.java
 * @Modified By：
 * @date 2020-11-02 上午11:07
 * @description 数组插入元素
 */
public class MyArray <E>{
    private int [] array;
    private int size;

    public MyArray(int capactiy){
        array= new int[capactiy];
        size=0;
    }



    public void insert(int element, int index) throws Exception {

        //判断访问下标是否超出范围
        if(index<0 || index>array.length){

            throw new IndexOutOfBoundsException("超出数组实际元 素范围！");
        }
        if(size>=array.length){
            resize();
        }
        //从右向左循环，将元素逐个向右挪1位
        for(int i=size-1; i>=index; i--){
            array[i+1] = array[i];
        }
        //腾出的位置放入新元素
        array[index] = element;
        size++;
    }
    public void output(){
        for(int i=0; i<size; i++){
            System.out.println(array[i]);
        }
        System.out.println(size);
    }
    public void resize(){
        int[] array2=Arrays.copyOf(array,array.length*2);
        array=array2;
    }

    public int delete (int index) throws  Exception{

        if(index<0||index>=size){
            throw new IndexOutOfBoundsException("超出数组实际元 素范围！");
        }
        int delItem=array[index];
        //从左边向右移动,将元素一个个向左边移动1位
        for(int i=index;i<size-1;i++){
            array[i]=array[i+1];
        }
        size--;
        return delItem;
    }

    public static void main(String[] args) throws Exception {
        MyArray myArray = new MyArray(10);
        //myArray.insert(3,0);

        myArray.insert(7,1);
        myArray.insert(9,2);
        myArray.insert(5,3);
        myArray.insert(6,1);
        myArray.insert(7,1);
        myArray.insert(9,2);
        myArray.insert(5,3);
        myArray.insert(6,1);
        myArray.insert(7,1);
        myArray.insert(9,2);
        myArray.insert(5,3);
        myArray.insert(6,1);
        myArray.insert(7,1);
        myArray.insert(9,2);
        myArray.insert(5,3);
        myArray.insert(6,1);
        myArray.output();
        /**
         * 数组这种数据结构 有非常高效的随机访问能力,只要给出index就可以找到对应元素
         * 至于缺点 体现在插入和删除元素,会导致列表元素,被迫一个个移动,影响效率
         */
    }
}
