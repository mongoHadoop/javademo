package com.justbon.io.byt.test;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.lang.SecurityException;

/**BufferedInputStream 
 * 的作用是为另一个输入流添加一些功能，例如，提供“缓冲功能”以及支持“mark()标记”和“reset()重置方法”。
 * 可以提高性能 Buffered开头的流只是加了缓冲区，为了读写提高效率
 * @author lg
 *
 */
public class BufferInputStreamTest2 {
	public static String targetPath = "D:/home/gl/";

    public static void main(String[] args) {
    	BufferInputStreamTest2.copyFile("D:\\gl\\caep0_20160314.rar");
    }

    
    public static void copyFile(String path){
    	BufferedInputStream bufin = null;
    	BufferedOutputStream bufout = null;
			try {
				File file = new File(path);
				 if(!file.exists()){  
				        throw new RuntimeException("要读取的文件不存在");  
				    } 
				 bufin= new BufferedInputStream(new FileInputStream(file));
				// 文件输入流

				 bufout = new BufferedOutputStream(
						     new FileOutputStream(new File(targetPath
										+ file.getName())));

				 // 文件输出
				byte[] buffer = new byte[1024];
				  int transLen  = 0;
				while (true) {
					 int read =0;
					 read=  bufin.read(buffer);
					 if(read==-1)
						 break;
					transLen = transLen+read;
					bufout.write(buffer,0,read);
				
				}
				System.out.println("copy complited");
				System.out.println(file.getName() + "---长度是： " + transLen + "字节");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					bufin.close();
					bufout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
}