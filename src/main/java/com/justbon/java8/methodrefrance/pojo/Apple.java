package com.justbon.java8.methodrefrance.pojo;

import lombok.Data;

@Data
public class Apple {
    private  Integer weight;
    private String color;
    public  Apple(int weight){
        this.weight =weight;
    }

    public Apple(){

    }
    public Apple(String color, Integer weight){
        this.color=color;
        this.weight=weight;
    }

}
