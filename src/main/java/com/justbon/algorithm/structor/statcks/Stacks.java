package com.justbon.algorithm.structor.statcks;

import com.justbon.algorithm.structor.queue.MyQueue2;

/**
 * @author ganli
 * @version 1.0
 * @file Stacks.java
 * @Modified By：
 * @date 2021-04-20 下午2:12
 * @description
 */
public class Stacks {


    public static  Node header;

    private static class Node{
        int data;
        Node next;
        public Node(int data){
            this.data=data;
        }
    }

    public static void push(int data){
        if(header==null){
            header=new Node(data);
            return;
        }
        Node temp = new Node(data);
        temp.next=header;
        header=temp;

    }

    public static void pop(){

        Node node=header;
        if(header==null){
            System.out.println("已空了");
            return;
        }
        if(node.next!=null){
            header=node.next;
        }
        if(node.next==null){
            header=null;
        }

        System.out.println(node.data);
    }


    public static void main(String[] args) {
        Stacks.push(1);
        Stacks.push(3);
        Stacks.push(4);
        Stacks.push(6);
        Stacks.pop();
        Stacks.pop();
        Stacks.pop();
        Stacks.pop();
        Stacks.pop();
        Stacks.pop();
    }
}
