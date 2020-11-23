package com.justbon.algorithm.alg;

/**
 * @author ganli
 * @version 1.0
 * @file CycleAlg.java
 * @Modified By：
 * @date 2020-11-09 下午4:20
 * @description
 * 有环链表
 *首先创建两个指针p1和p2（在Java里就是两个对象引用），让它们同时
 * 指向这个链表的头节点。然后开始一个大循环，在循环体中，让指针p1
 * 每次向后移动1个节点，让指针p2每次向后移动2个节点，然后比较两个
 * 指针指向的节点是否相同。如果相同，则可以判断出链表有环，如果不 同，则继续下一次循环
 *
 * 求环长度:
 * 当两个指针首次相遇，证明链表有环的时候，让两个指针从相遇点继续
 * 循环前进，并统计前进的循环次数，直到两个指针第2次相遇。此时，
 * 统计出来的前进次数就是环长。
 * 因为指针p1每次走1步，指针p2每次走2步，两者的速度差是1步。当两
 * 个指针再次相遇时，p2比p1多走了整整1圈。
 * 因此，环长 = 每一次速度差 × 前进次数 = 前进次数。
 *
 */
public class CycleAlg {

    private  static class Node{

        int data;
        Node next;
        Node(int data){
            this.data=data;
        }
    }

    public static  boolean isCycle(Node head){
        Node p1 = head;
        Node p2 = head;
        while (p2!=null&&p2.next!=null){
            p1=p1.next;
            p2=p2.next.next;
            if(p1==p2){
                return true;
            }
        }

        return false;
    }

    public static  int cycleLength(Node head){
        Node p1 = head;
        Node p2 = head;
        int count=0;
        boolean flag= false;
        while (p2!=null&&p2.next!=null){
            p1=p1.next;
            p2=p2.next.next;
            if(p1==p2){
                if(count>0){
                    break;
                }else {
                    count=0;
                }
            }
            count++;
        }
        int length=count*1;
        return length;
    }

    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;
        node5.next=node2;
        System.out.println(isCycle(node1));
        System.out.println(cycleLength(node1));
    }
}
