package com.justbon.algorithm.structor.linkedlist;


/**
 * @author ganli
 * @version 1.0
 * @file MyLinkedList.java
 * @Modified By：
 * @date 2020-11-02 上午10:26
 * @description 链表结构
 */
public class MyLinkedList {

    //头部节点
    private Node  head;
    //尾部
    private Node last;

    private int size;

    public  void  insert(int data,int index){

        if(index<0||index>size){

            throw  new IndexOutOfBoundsException("out of list");
        }
        Node insertNode = new Node(data);
        if(size==0){
            head=insertNode;
            last=insertNode;
        }else  if(index==0){
            //插入在头部
            insertNode.next=head;
            head=insertNode;
        }else if(size==index){
            //插入尾部
            last.next=insertNode;
            last=insertNode;
        }else {
            //插入在中间
            Node preNode= get(index);
            insertNode.next=preNode.next;
            preNode.next=insertNode;

        }

        size++;
    }


    public Node get(int index){
        if(index<0||index>=size){
            throw  new IndexOutOfBoundsException("out of linkedlist");
        }
        Node temp = head;
        for (int i=0;i<index;i++){
            temp=temp.next;
        }
        return temp;
    }

    public void  outPut(){
        Node temp= head;
        while ( temp!=null){
            System.out.println(temp.item);
            temp=temp.next;
        }
    }

    private    static class Node<E> {
        E item;
        Node<E> next;
        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }

        public Node(E element) {
            this.item = element;
        }
    }



    /*private static class Node<E> {
        E item;
        MyLinkedList.Node<E> next;
        MyLinkedList.Node<E> prev;

        Node(MyLinkedList.Node<E> prev, E element, MyLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }*/

    public Node remove(int index){
        if(index<0||index>=size){
            throw  new IndexOutOfBoundsException("out of linkedlist");
        }
        Node removeNode=null;
        if(index==0){
            //删除头节点
            removeNode=head;
            head=head.next;
        }else if(index==size-1){
            //删除末尾节点
            Node prevNode=get(index-1);
            removeNode=prevNode.next;
            prevNode.next=null;
            last=prevNode;
        }else {
            //删除中间节点
            Node preNode= get(index-1);
            Node nextNode = preNode.next.next;
            removeNode=preNode.next;
            preNode.next=nextNode;
        }
        size--;

        return removeNode;
    }


    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.insert(3,0);
        list.insert(7,1);
        list.insert(8,2);
        list.insert(9,3);
        list.insert(49,4);
        list.remove(0);
        list.outPut();
    }
}
