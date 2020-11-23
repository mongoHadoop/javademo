package com.justbon.algorithm.alg;

import java.util.Stack;

/**
 * @author ganli
 * @version 1.0
 * @file MinStack.java
 * @Modified By：
 * @date 2020-11-12 上午10:46
 * @description 获取栈最小值
 */
public class MinStack {

    private Stack<Integer> mainStack = new Stack();

    private Stack<Integer> minStack = new Stack<>();

    private Stack<Integer> tempStack = new Stack();

    /**
     * 进栈
     * @param element
     */
    public void push(int element){
        mainStack.push(element);
        //如果辅助栈为空,或者新元素小于或等于辅助栈栈顶,则将新元素压入辅助栈
        if(minStack.empty()||element<=minStack.peek()){
            minStack.push(element);
        }

    }

    /**
     * 进栈
     * @param element
     */
    public void pushtemp(int element){
        tempStack.push(element);
        //如果辅助栈为空,或者新元素小于或等于辅助栈栈顶,则将新元素压入辅助栈
        if(minStack.empty()||element<=minStack.peek()){
            minStack.push(element);
        }

    }
    /**
     * 出栈
     */
    public  Integer pop(){
        //如果出栈元素和辅助栈栈顶元素值相等,辅助栈出栈
        if(mainStack.peek().equals(mainStack.peek())){
            minStack.pop();
        }

        if(minStack.isEmpty()){
            Object[] array = mainStack.toArray();
            for (Object o:array
                 ) {
                pushtemp(mainStack.pop());
            }
            mainStack = tempStack;
        }
        return mainStack.pop();
    }

    /**
     * 获取最小值
     */
    public  int getMin() throws  Exception{
        if(mainStack.empty()){
            throw  new Exception("stack is empty");
        }
        return  minStack.peek();
    }

    public static void main(String[] args) throws Exception{
        MinStack stack = new MinStack();
        stack.push(4);
        stack.push(9);
        stack.push(7);
        stack.push(3);
        stack.push(8);
        stack.push(5);
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
    }
}
