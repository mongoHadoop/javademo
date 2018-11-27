package com.justbon.java8.methodrefrance.pojo;

import lombok.Data;

@Data
public class Apple2 {
    private String color;
    private int weight;

   public Apple2(String color, Integer weight){
    this.color=color;
    this.weight=weight;
    }
}
