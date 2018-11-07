package com.justbon.designmodel.proxy.statics;

/** 
 *  
 *定义一个潘金莲是什么样的人 
 */  
public class PanJinLian  implements KindWoman{  
  
    @Override  
    public void happyWithMan() {  
        System.out.println("潘金莲和男人在happy...");  
          
    }  
  
    @Override  
    public void makeEyesWithMan() {  
        System.out.println("潘金莲抛媚眼...");  
          
    }  
  
} 