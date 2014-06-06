package com.liusy.analysismodel.util.compress;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
public class FileCompress {

	public static void compress(String dest, File... files) {
		FileOutputStream fos = null;
		CheckedOutputStream cos = null;
		ZipOutputStream zos = null;
		BufferedOutputStream bos = null;
		try {
			fos = new FileOutputStream(dest);
			cos = new CheckedOutputStream(fos, new Adler32());
			zos = new ZipOutputStream(cos);
			bos = new BufferedOutputStream(zos);
			for (File file : files) {
				BufferedInputStream bis = new BufferedInputStream(
						new FileInputStream(file));
				zos.putNextEntry(new ZipEntry(file.getName()));
				int len;
				byte[] buf = new byte[1024];
				while ((len = bis.read(buf)) != -1) {
					bos.write(buf, 0, len);
				}
				bis.close();
				bos.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (cos != null) {
				try {
					cos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}