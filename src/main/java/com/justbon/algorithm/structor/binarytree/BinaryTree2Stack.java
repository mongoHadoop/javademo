package com.justbon.algorithm.structor.binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author ganli
 * @version 1.0
 * @file BinaryTree.java
 * @Modified By：
 * @date 2020-11-05 下午7:48
 * @description
 */
public class BinaryTree2Stack {

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
        System.out.println(node.data);
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
            return;
        }
        inOrderTraveral(node.leftChild);
        System.out.println(node.data);
        inOrderTraveral(node.rightChild);

    }

    /**
     *  左子树 右子树 根节点
     * @param node
     */
    public static  void  postOrderTraveral(TreeNode node){
        if(node==null){
            return;
        }
        postOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
        System.out.println(node.data);
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
        /*  System.out.println("前序遍历");
        preOrderTraveral(treeNode);
        System.out.println("中序遍历");
        inOrderTraveral(treeNode);
        System.out.println("后序遍历");
        postOrderTraveral(treeNode);*/

        //preOrderTravlerWithStack(treeNode);
        levelOrderTraversal(treeNode);
    }

    public  static  void  preOrderTravlerWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode treeNode = root;
        while (treeNode!=null||!stack.isEmpty()){
            //迭代访问节点的左孩子,并入栈
            while (treeNode!=null){
                System.out.println(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            //如果节点即没左孩子 则弹出栈顶节点,访问右边孩子
            if(!stack.isEmpty()){
                treeNode=stack.pop();
                treeNode = treeNode.rightChild;
            }
        }


    }

    /**
     * 广度优先遍历
     * @param root
     */

    public  static  void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> queue= new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.println(node.data);
            if(node.leftChild!=null){
                queue.offer(node.leftChild);
            }
            if(node.rightChild!=null){
                queue.offer(node.rightChild);
            }

        }

    }
}
