package com.justbon.algorithm.structor.queue;

/**
 * @author ganli
 * @version 1.0
 * @file MyQueue.java
 * @Modified By：
 * @date 2020-11-02 上午11:50
 * @description 队列实现 链表实现 先进先出
 */
public class MyQueue3 {

    public static Node header;
    public static Node tail;

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
            tail=header;
        }
        Node temp=new Node(data);
        temp.next=header;
        header=temp;

    }

    public static  void pop(){
        //如果队列空
        if (header == null) {
            System.out.println("队列已空");
            return ;
        }
        Node node =header;
        while (node.next!=null){
            if(tail==node.next){
                tail=node;
                break;
            }
            node=node.next;
        }

        System.out.println(node.data);

    }

    public static void main(String[] args) throws Exception{
        MyQueue3.push(1);
        MyQueue3.push(3);
        MyQueue3.push(4);
        MyQueue3.push(6);
        MyQueue3.pop();
        MyQueue3.pop();
        MyQueue3.pop();
    }
}
