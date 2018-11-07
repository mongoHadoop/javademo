package com.justbon.io.byt.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
/**
 * 现有如下的一个需求，向已存在1G数据的txt文本里末尾追加一行文字，
 * 内容如下“Lucene是一款非常优秀的全文检索库”。可能大多数朋友会觉得这个需求很easy，
 * 说实话，确实easy，然后XXX君开始实现了，直接使用Java中的流读取了txt文本里原来所有的数据转成字符串后，
 * 然后拼接了“Lucene是一款非常优秀的全文检索库”，又写回文本里了，至此，大功告成。后来需求改了，向5G数据的txt文本里追加了，
 * 结果XXX君傻了，他内存只有4G，如果强制读取所有的数据并追加，会报内存溢出的异常。

其实上面的需求很简单，如果我们使用JAVA IO体系中的RandomAccessFile类来完成的话，
可以实现零内存追加。其实这就是支持任意位置读写类的强大之处。
，RandomAccessFile是Java中输入，输出流体系中功能最丰富的文件内容访问类，
它提供很多方法来操作文件，包括读写支持，与普通的IO流相比，它最大的特别之处就是支持任意访问的方式，
程序可以直接跳到任意地方来读写数据。
如果我们只希望访问文件的部分内容，而不是把文件从头读到尾，
使用RandomAccessFile将会带来更简洁的代码以及更好的性能。
 * @author lg
 *
 */
public class RandomAccessFileUtil {

	public static String  basePath="D:/home/gl/ganli.txt";
	public static  void randomRed(String path,int pointe){
		
        /** 
         * model各个参数详解 
         * r 代表以只读方式打开指定文件 
         * rw 以读写方式打开指定文件 
         * rws 读写方式打开，并对内容或元数据都同步写入底层存储设备 
         * rwd 读写方式打开，对文件内容的更新同步更新至底层存储设备 
         *  
         * **/  
        RandomAccessFile raf;
		try {
			raf = new RandomAccessFile(path, "r");
	
        //获取RandomAccessFile对象文件指针的位置，初始位置是0  
        System.out.println("RandomAccessFile文件指针的初始位置:"+raf.getFilePointer());  
        raf.seek(pointe);//移动文件指针位置  
        byte[]  buff=new byte[1024];  
        //用于保存实际读取的字节数  
        int hasRead=0;  
        //循环读取  
        int count =0;
        while((hasRead=raf.read(buff))>0){  
            //打印读取的内容,并将字节转为字符串输入
        	for(byte b:buff){
        		System.out.println(b);
        	}
            System.out.println(new String(buff,0,hasRead));  
              
        }  
        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	
	/** 
	 * 追加方式 
	 * 写的方法 
	 * @param path 文件路径 
	 * ***/  
	public static void randomWrite(String path,String content){  
	    try{  
	        /**以读写的方式建立一个RandomAccessFile对象**/  
	        RandomAccessFile raf=new RandomAccessFile(path, "rw");  
	        //将记录指针移动到文件最后  
	        raf.seek(raf.length());  
	        raf.write(content.getBytes());  
	          
	    }catch(Exception e){  
	        e.printStackTrace();  
	    }  
	      
	} 
	
	
	/** 
     * 实现向指定位置 
     * 插入数据 
     * @param fileName 文件名 
     * @param points 指针位置 
     * @param insertContent 插入内容 
     * **/  
    public static void insert(String fileName,long points,String insertContent){  
        try{  
        File tmp=File.createTempFile("tmp", null);  
        tmp.deleteOnExit();//在JVM退出时删除  
          
        RandomAccessFile raf=new RandomAccessFile(fileName, "rw");  
        //创建一个临时文件夹来保存插入点后的数据  
        FileOutputStream tmpOut=new FileOutputStream(tmp);  
        FileInputStream tmpIn=new FileInputStream(tmp);  
        raf.seek(points);  
        /**将插入点后的内容读入临时文件夹**/  
          
        byte [] buff=new byte[1024];  
        //用于保存临时读取的字节数  
        int hasRead=0;  
        //循环读取插入点后的内容  
        while((hasRead=raf.read(buff))>0){  
            // 将读取的数据写入临时文件中  
            tmpOut.write(buff, 0, hasRead);  
        }  
          
        //插入需要指定添加的数据  
        raf.seek(points);//返回原来的插入处  
        //追加需要追加的内容  
        raf.write(insertContent.getBytes());  
        //最后追加临时文件中的内容  
        while((hasRead=tmpIn.read(buff))>0){  
            raf.write(buff,0,hasRead);  
        }  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
	public static void main(String[] args) throws IOException {
	     int seekPointer=20;  
	     randomRed("D:/home/gl/test.txt",seekPointer);//读取的方法   
	     
	     randomWrite("D:/home/gl/test.txt","我是最佳内容");//追加写的方法  
	    
	      insert("D:/home/gl/test.txt", 33, "\nlucene是一个优秀的全文检索库");  
	}
}
