package com.justbon.io.byt.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 * 以上输出只会进行覆盖，如果要追加的话，请看FileOutputStream类的另一个构造方法：

public FileOutputStream(File file,boolean append)throws FileNotFoundException

在构造方法中，如果将append的值设置为true，则表示在文件的末尾追加内容。
 * @author lg
 *
 */
public class FileStreamCpUtil {
	public static String targetPath = "D:/home/gl/";

	public static void copy(String path) {
		InputStream inputFile = null;
		OutputStream outputFile = null;
		try {
			File file = new File(path);
			 if(!file.exists()){  
		            throw new RuntimeException("要读取的文件不存在");  
		        } 
			inputFile = new FileInputStream(file);
			// 文件输入流
			outputFile = new FileOutputStream(new File(targetPath
					+ file.getName()));
			 // 文件输出
			byte[] buffer = new byte[1024];
			  int transLen  = 0;
			while (true) {
				 int read =0;
				 read=  inputFile.read(buffer);
				 if(read==-1)
					 break;
				transLen = transLen+read;
				 outputFile.write(buffer);
			
			}
			System.out.println("copy complited");
			System.out.println(file.getName() + "---长度是： " + transLen + "字节");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputFile.close();
				outputFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static void main(String[] args) {
		FileStreamCpUtil.copy("D:\\gl\\caep0_20160314.rar");
	}

}