package com.justbon.io.cha.test;

import java.io.*;

public class FileReaderCopyFile {
	public static String taragetPath = "d:/home/gl/";
   public static void copy(String path){

	      FileReader in = null;
	      FileWriter out = null;
	      File file = new File(path);
	      try {
	         in = new FileReader(file);
	         out = new FileWriter(taragetPath+file.getName());
	         int c;
	         while ((c = in.read()) != -1) {
	            out.write(c);
	         }
	      } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
	         try {
				if (in != null) {
				    in.close();
				 }
				 if (out != null) {
				    out.close();
				 }
			} catch (IOException e) {
				e.printStackTrace();
			}
	      }
	   
   }
	
	public static void main(String args[]) throws IOException
   {
		FileReaderCopyFile.copy("d:/gl/student.txt");
   }
}