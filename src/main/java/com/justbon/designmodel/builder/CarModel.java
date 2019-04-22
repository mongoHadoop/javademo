package com.justbon.designmodel.builder;

import java.util.ArrayList;

/**
 * I'm glad to share my knowledge with you all.
 * 定义一个车辆模型的抽象类,所有的车辆模型都继承这里类
 */
public abstract class CarModel {
    //这个参数是各个基本方法执行的顺序
    private ArrayList<String> sequence = new ArrayList<String>();
    /*
     * 模型是启动开始跑了
     */
    protected abstract void start();
    //能发动,那还要能停下来,那才是真本事
    protected abstract void stop();
    //喇叭会出声音,是滴滴叫,还是哔哔叫
    protected abstract void alarm();
    //引擎会轰隆隆的响,不响那是假的
    protected abstract void engineBoom();
    //那模型应该会跑吧,别管是人推的,还是电力驱动,总之要会跑
    final public void run() {
        //循环一遍,谁在前,就先执行谁
        for(int i=0;i<this.sequence.size();i++){
            String actionName = this.sequence.get(i);
            if(actionName.equalsIgnoreCase("start")){
                //如果是start关键字,
                this.start(); //开启汽车
            }else if(actionName.equalsIgnoreCase("stop")){
                //如果是stop关键字
                this.stop(); //停止汽车
            }else if(actionName.equalsIgnoreCase("alarm")){
                //如果是alarm关键字
                this.alarm(); //喇叭开始叫了
            }else if(actionName.equalsIgnoreCase("engine boom")){
                //如果是engine
                this.engineBoom();
                //引擎开始轰鸣
            }
        }
    }
    //把传递过来的值传递到类内
    /**
     * 其中 setSequence 方法是允许客户自己设置一个顺序,是要先跑起来在有引擎声音还是先有引擎声音再
     * 跑起来,还是说那个喇叭就不要响,对于一个具体的模型永远都固定的,那这个事由牛叉告诉我们,有 1W 件事
     * 这样的,1W 件事那样的顺序,目前的设计都能满足这个要求
     * @param sequence
     */
    final public void setSequence(ArrayList<String> sequence){
        this.sequence = sequence;
    }
}