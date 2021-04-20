package com.justbon.algorithm.structor.queue;

/**
 * @author ganli
 * @version 1.0
 * @file MyQueue.java
 * @Modified By：
 * @date 2020-11-02 上午11:50
 * @description 队列实现 链表实现 先进先出
 */
public class MyQueue2 {

    public static Node header;

    private static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
        }
    }

    public static void  push(int data){
        if (header==null){
            header=new Node(data);
        }
        Node temp=new Node(data);
        temp.next=header;
        header=temp;

    }

    public static  void pop(){
        Node temp=header;
        while (temp.next!=null){
            if(temp.next.next==null){
                temp.next=null;
                break;
            }
            temp=temp.next;
        }
        System.out.println(temp.data);

    }

    public static void main(String[] args) throws Exception{
        MyQueue2.push(1);
        MyQueue2.push(3);
        MyQueue2.push(4);
        MyQueue2.push(6);
        MyQueue2.pop();
        MyQueue2.pop();
        MyQueue2.pop();
    }
}
