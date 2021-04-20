package com.justbon.algorithm.structor.queue;

/**
 * @author ganli
 * @version 1.0
 * @file MyQueue.java
 * @Modified By：
 * @date 2020-11-02 上午11:50
 * @description 队列实现 数组实现
 */
public class MyQueue {
    private int[] array;
    //头元素
    private int front;
    //尾元素
    private int rear;

    public  MyQueue(int capacity){
        this.array=new int[capacity];
    }

    /**
     * 入队
     */
    public  void  enQueue(int element)throws Exception{
        if((rear+1)%array.length==front){
            throw  new Exception("队列已满");
        }
        array[rear]=element;
        //利用此求余函数 让尾指针周期循环起来, 这样就是循环队列
        rear=(rear+1)%array.length;
    }

    public  int deQueue()throws Exception{
        if(rear==front){
            throw  new Exception("队列已空");
        }
        int deQueueElement=array[front];
        //让头指针动起来,循环起来
        front=(front+1)%array.length;
        return deQueueElement;
    }


    /**
     * 输出队列
     */

    public void  outPut(){
        for(int i=front;i!=rear;i=(i+1)%array.length){
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) throws Exception{
        MyQueue myQueue = new MyQueue(7);
        myQueue.enQueue(3);
        myQueue.enQueue(5);
        myQueue.enQueue(6);
        myQueue.enQueue(8);
        myQueue.enQueue(1);

        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.enQueue(9);
        myQueue.enQueue(10);
        myQueue.outPut();
        System.out.println("front:"+myQueue.front);
        System.out.println("rear:"+myQueue.rear);
        //System.out.println(2/5);
    }
}
