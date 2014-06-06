package com.liusy.analysismodel.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class FileUtil {

   /**
    * 拷贝目录
    */
   public static void copyDirectory(String sourceDirName, String destinationDirName) throws Exception {
      copyDirectory(new File(sourceDirName), new File(destinationDirName));
   }

   public static void copyDirectory(File source, File destination) throws Exception {
      if (source.exists() && source.isDirectory()) {
         if (!destination.exists()) destination.mkdirs();
         File fileArray[] = source.listFiles();
         for (int i = 0; i < fileArray.length; i++)
            if (fileArray[i].isDirectory()) copyDirectory(fileArray[i], new File(destination.getPath() + File.separator + fileArray[i].getName()));
            else copyFile(fileArray[i], new File(destination.getPath() + File.separator + fileArray[i].getName()));

      }
   }

   /**
    * 删除目录
    */
   public static void deltree(String directory) {
      deltree(new File(directory));
   }

   public static void deltree(File directory) {
      if (directory.exists() && directory.isDirectory()) {
         File fileArray[] = directory.listFiles();
         for (int i = 0; i < fileArray.length; i++)
            if (fileArray[i].isDirectory()) deltree(fileArray[i]);
            else fileArray[i].delete();

         directory.delete();
      }
   }

   public static boolean exists(String fileName) {
      File file = new File(fileName);
      return file.exists();
   }

   /**
    * 显示文件列表
    */
   public static String[] listDirs(String fileName) throws IOException {
      return listDirs(new File(fileName));
   }

   public static String[] listDirs(File file) throws IOException {
      List<String> dirs = new ArrayList<String>();
      File fileArray[] = file.listFiles();
      for (int i = 0; i < fileArray.length; i++)
         if (fileArray[i].isDirectory()) dirs.add(fileArray[i].getName());

      return (String[]) dirs.toArray(new String[0]);
   }

   public static String[] listFiles(String fileName) throws IOException {
      return listFiles(new File(fileName));
   }
   
   public static List listUnScannedCSVFiles(String fileName) throws IOException
   {
	   String[] fileNames = listFiles(new File(fileName));
	   if (fileNames==null) return null;
	   List<String> fileNameList = new LinkedList<String>();
	   for(String fn : fileNames)
	   {
		   if (!fn.endsWith(".csv") && !fn.endsWith(".CSV")) continue;
		   fileNameList.add(fn);
	   }
	   return fileNameList;
   }

   public static String[] listFiles(File file) throws IOException {
      List<String> files = new ArrayList<String>();
      File fileArray[] = file.listFiles();
      if (fileArray==null) return null;
      for (int i = 0; i < fileArray.length; i++)
         if (fileArray[i].isFile()) files.add(fileArray[i].getName());

      return (String[]) files.toArray(new String[0]);
   }

   /**
    * 创建目录
    */
   public static void mkdirs(String pathName) {
      File file = new File(pathName);
      if (!file.exists()) file.mkdirs();
   }

   /**
    * 读取文件内容
    */
   public static String read(String fileName) throws IOException {
      return read(new File(fileName));
   }

   /**
    * 根据编码格式读取文件
    */
   public static String readByEncoding(String fileName, String encoding) throws IOException {
      return readByEncoding(new File(fileName), encoding);
   }

   public static String read(File file) throws IOException {
      BufferedReader br = new BufferedReader(new FileReader(file));
      StringBuffer sb = new StringBuffer();
      for (String line = null; (line = br.readLine()) != null;)
         sb.append(line).append('\n');

      br.close();
      return sb.toString().trim();
   }

   public static String read(File file, int type) throws IOException {
      InputStreamReader br = new InputStreamReader(new FileInputStream(file), "utf-8");
      FileInputStream fis = new FileInputStream(file);
      char strings[] = new char[fis.available()];

      br.read(strings);
      br.close();
      String returnString = new String(strings);
      return returnString;
   }

   public static String readByEncoding(File file, String encoding) throws IOException {
      InputStreamReader br = new InputStreamReader(new FileInputStream(file), encoding);
      FileInputStream fis = new FileInputStream(file);
      char strings[] = new char[fis.available()];

      br.read(strings);
      br.close();
      String returnString = new String(strings);
      return returnString;
   }

   /**
    * 生成文件
    */
   public static void write(File file, String s) throws IOException {
      write(file, s, "UTF-8");
   }

   public static void write(File file, String s, String encoding) throws IOException {
      if (file.getParent() != null) mkdirs(file.getParent());
      OutputStreamWriter bw = new OutputStreamWriter(new FileOutputStream(file), encoding);
      bw.flush();
      bw.write(s);
      bw.close();
   }

   /**
    * 根据编辑格式生成文件
    */
   public static void writeByEncoding(String fileName, String s, String encoding) throws IOException {
      write(new File(fileName), s, encoding);
   }

   public static void write(String pathName, String fileName, String s) throws IOException {
      write(new File(pathName, fileName), s);
   }

   /**
    * 根据日期获得目录名称
    */
   public static String getDirectoryNameByDate() {
      Date nowDate = new Date();

      int year = nowDate.getYear();
      int month = nowDate.getMonth();
      int day = nowDate.getDay();
      return year + "-" + month + "-" + day;
   }

   public static String readFile(File file) throws Exception {
      return readByEncoding(file, "UTF-8");
   }

   public static void writeString2File(String content, File file) throws IOException {
      write(file, content);
   }

   /**
    * 拷贝文件
    */
   public static void copyFile(File source, File destination) {
      if (!source.exists()) { return; }

      if ((destination.getParentFile() != null) && (!destination.getParentFile().exists())) {

         destination.getParentFile().mkdirs();
      }

      try {
         FileInputStream fis = new FileInputStream(source);
         FileOutputStream fos = new FileOutputStream(destination);

         byte[] buffer = new byte[1024 * 4];
         int n = 0;

         while ((n = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, n);
         }

         fis.close();
         fos.close();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void copyFile(String sourceFileName, String destinationFileName) {
      copyFile(new File(sourceFileName), new File(destinationFileName));
   }

}