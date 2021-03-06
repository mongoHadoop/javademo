package com.justbon.algorithm.structor.array;

import org.omg.CORBA.Object;

/**
 * @author ganli
 * @version 1.0
 * @file MyArray.java
 * @Modified By：
 * @date 2020-11-02 上午11:07
 * @description
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
        if(index<0 || index>size){

            throw new IndexOutOfBoundsException("超出数组实际元 素范围！");
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
    }
    public static void main(String[] args) throws Exception {
        MyArray myArray = new MyArray(10);
        myArray.insert(3,0);

        myArray.insert(7,1);
        myArray.insert(9,2);
        myArray.insert(5,3);
        myArray.insert(6,1);
        myArray.output();
    }
}
