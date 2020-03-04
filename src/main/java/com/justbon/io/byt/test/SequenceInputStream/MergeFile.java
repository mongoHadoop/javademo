package com.justbon.io.byt.test.SequenceInputStream;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

public class MergeFile {
    public static void main(String[] args) throws IOException {
        File pathDir=new File("C:\\Users\\Administrator\\Desktop\\cutFiles");
        //获取配置文件
        File configFile=getconfigFile(pathDir);
        //获取配置文件信息的属性集
        Properties prop=getProperties(configFile);
        System.out.println(prop);
        //获取属性集信息，将属性集信息传递到合并方法中
        merge(pathDir,prop);
    }

    private static Properties getProperties(File configFile) throws IOException {
        FileInputStream fis=null;
        Properties prop=null;
        try {
            //读取流和配置文件相关联
            fis=new FileInputStream(configFile);
            prop=new Properties();
            //流中的数据加载到集合中
            prop.load(fis);
        }finally {
            if(fis!=null) {
                fis.close();
            }
        }
        return prop;
    }

    public static File getconfigFile(File pathDir) {
        //判断是否存在properties文件
        if(!(pathDir.exists()&&pathDir.isDirectory())) {
            throw new RuntimeException(pathDir.toString()+"不是有效目录");
        }
        File[] files=pathDir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".properties");
            }

        });
        if(files.length!=1) {
            throw new RuntimeException(pathDir.toString()+"properties扩展名的文件不存在或者不唯一");
        }
        File configFile=files[0];
        return configFile;
    }

    public static void merge(File pathDir, Properties prop) throws FileNotFoundException, IOException {
        String fileName=prop.getProperty("fileName");
        int partCount=Integer.valueOf(prop.getProperty("partCount"));
        List<FileInputStream> list=new ArrayList<FileInputStream>();
        for(int i=1;i<partCount;i++) {
            list.add(new FileInputStream(pathDir.toString()+"\\"+i+".part"));
        }
        //List自身无法获取Enumeration工具类，到Collection中找
        Enumeration<FileInputStream> en=Collections.enumeration(list);
        SequenceInputStream sis=new SequenceInputStream(en);
        FileOutputStream fos=new FileOutputStream(pathDir.toString()+"\\"+fileName);
        byte[] buf=new byte[1024];
        int len=0;
        while((len=sis.read(buf))!=-1) {
            fos.write(buf,0,len);
        }
        fos.close();
        sis.close();
    }

}