package com.justbon.algorithm.structor.binarytree;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author ganli
 * @version 1.0
 * @file BinaryTree.java
 * @Modified By：
 * @date 2020-11-05 下午7:48
 * @description
 */
public class BinaryTree {

    /**
     * 構建二差
     * @param inputList
     * @return
     */
    public  static  TreeNode createBinaryTree(LinkedList <Integer>inputList){

        TreeNode node = null;
        if(inputList ==null ||inputList.isEmpty()){
            return null;
        }
        Integer data = inputList.removeFirst();
        if(data!=null){
            node= new TreeNode(data);
            node.leftChild=createBinaryTree(inputList);
            node.rightChild=createBinaryTree(inputList);
        }
        return  node;

    }

    /**
     * 前序遍歷
     *  根节点 付 左子树  右子树
     * @param node
     */
    public  static  void  preOrderTraveral(TreeNode node ){
        if(node==null){
            return;
        }
        System.out.print(node.data);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }

    /**
     * 中序遍历
     * 左子树 根节点 右子树
     * @param node
     */

    public  static  void  inOrderTraveral(TreeNode node ){

        if(node==null){
         //   System.out.println("");
            return;
        }
        inOrderTraveral(node.leftChild);
        System.out.print(node.data);
        inOrderTraveral(node.rightChild);

    }

    /**
     * 后序遍历
     *  左子树 右子树 根节点
     * @param node
     */
    public static  void  postOrderTraveral(TreeNode node){
        if(node==null){
            return;
        }
        postOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
        System.out.print(node.data);
    }


    private static  class  TreeNode{
        int data;
        TreeNode  leftChild;
        TreeNode  rightChild;
        TreeNode(int data){
            this.data=data;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<>(Arrays.asList(3,2,9,null,null,
                10,null,null,8,null,4));

        TreeNode treeNode = createBinaryTree(inputList);
        System.out.println("前序遍历");
        preOrderTraveral(treeNode);
        System.out.print("\n");
        System.out.println("中序遍历");
        inOrderTraveral(treeNode);
        System.out.print("\n");
        System.out.println("后序遍历");
        postOrderTraveral(treeNode);

    }
}
