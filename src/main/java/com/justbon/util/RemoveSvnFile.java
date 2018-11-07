package com.justbon.util;

import java.io.File;

/**
 * 删除工程下所有.svn版本控制信息
 * 
 * @author goofus
 *
 */
public class RemoveSvnFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String path = "E:/workspace/SVN";
		process(path);
	}

	public static void process(String path) {
		File base = new File(path);
		if (base.exists()) {
			File[] files = base.listFiles();
			for (int a = 0, al = files.length; a < al; a++) {
				File f = files[a];
				if (f.isDirectory()) {
					if (f.getName().toLowerCase().endsWith(".svn")) {
						delete(f.getAbsolutePath());
					} else {
						process(f.getAbsolutePath());
					}
				}
			}
		}
	}

	public static void delete(String dir) {
		File svn = new File(dir);
		File[] files = svn.listFiles();
		for (int a = 0, al = files.length; a < al; a++) {
			File f = files[a];
			if (f.isDirectory()) {
				delete(f.getAbsolutePath());
			} else {
				f.delete();
			}
		}
		svn.delete();
	}

}