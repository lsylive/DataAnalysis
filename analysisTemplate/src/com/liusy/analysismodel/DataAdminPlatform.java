package com.liusy.analysismodel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.internal.util.BundleUtility;
import org.osgi.framework.Bundle;

/**
 * 初始化系统类
 * 
 * @author andy
 */
public class DataAdminPlatform {
   private static Logger   log          = Logger.getLogger(DataAdminPlatform.class);

   //   private static final String         BUNDLE_NAME     = "dataplatform";
   //   private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

   public static String    DATABASE_DRIVE;                                          //数据库驱动名
   public static String    DATABASE_URL;                                            //数据库连接url
   public static String    DATABASE_TYPE;                                           //数据库类型
   public static String    DATABASE_USER;                                           //数据库连接用户
   public static String    DATABASE_PASSWORD;                                       //数据库连接密码
   

   public static String    IQ_DATABASE_DRIVE;                                          //IQ数据库驱动名
   public static String    IQ_DATABASE_URL;                                            //IQ数据库连接url
   public static String    IQ_DATABASE_USER;                                           //IQ数据库连接用户
   public static String    IQ_DATABASE_PASSWORD;                                       //IQ数据库连接密码
   
   
   public static final int INIT_SUCCESS = 0;                                        //初始化成功
   public static final int INIT_FAIL    = 1;                                        //初始化失败

   public static String    LOCAL_STRING;                                            //语言系统
   public static String    CSV_DIR;                                                 //增量CSV存放路径
   public static boolean   TIME_ENABLE  = false;                                    //定时器启动标志
   
   public static String	   BLACKLIST_START_TIME;									//黑名单定时运行时刻
   public static String    DATA_FILE_DIR;										    //数据导出文件路径
   

   public static int init() {
      //      PropertyConfigurator.configure (configFilename);
      log.info("欢迎使用数据管理系统，系统正在初始化...");
      String str = "";
      try {
         str = FileLocator.toFileURL(Platform.getBundle(Application.PLUGIN_ID).getEntry("")).getPath();
      }
      catch (Exception e) {

      }
//      log.info("AppPath:" + str);//osgi/BUNDLE的目录
//      log.info(Platform.getInstallLocation().getURL().getPath());//系统运行的主目录
//      log.info(Platform.getLocation().toString());//系统运行时workspace的目录
      String productRootPath = Platform.getInstallLocation().getURL().getPath();

      File log4jConfig = new File(productRootPath + "/log4j.properties");
      File dbConfig = new File(productRootPath + "/dbConfig.properties");
      Bundle bundle = Platform.getBundle(Application.PLUGIN_ID);
      URL log4jPropertyFilePath = BundleUtility.find(bundle, "/src/log4j.properties");
      if (!log4jConfig.exists()) {
         try {
            log4jConfig.createNewFile();
            InputStream inputStream = new BufferedInputStream(log4jPropertyFilePath.openStream());
            FileOutputStream fos = new FileOutputStream(log4jConfig);
            byte[] buffer = new byte[1024 * 4];
            int n = 0;
            while ((n = inputStream.read(buffer)) != -1) {
               fos.write(buffer, 0, n);
            }
            inputStream.close();
            fos.close();
         }
         catch (IOException e) {
            e.printStackTrace();
            return INIT_FAIL;
         }

      }
      if (!dbConfig.exists()) {
         URL dbPropertyFilePath = BundleUtility.find(bundle, "/src/dataplatform.properties");
         try {
            dbConfig.createNewFile();
            InputStream inputStream = new BufferedInputStream(dbPropertyFilePath.openStream());
            FileOutputStream fos = new FileOutputStream(dbConfig);
            byte[] buffer = new byte[1024 * 4];
            int n = 0;
            while ((n = inputStream.read(buffer)) != -1) {
               fos.write(buffer, 0, n);
            }
            inputStream.close();
            fos.close();
         }
         catch (IOException e) {
            e.printStackTrace();
            return INIT_FAIL;
         }

      }
      try {
         InputStream in = new BufferedInputStream(new FileInputStream(log4jConfig));
         Properties p = new Properties();
         p.load(in);
         String logFileLocation = p.getProperty("log4j.appender.R.File");
         in.close();
         //修改LOG4J文件
         InputStream inputStream = new BufferedInputStream(log4jPropertyFilePath.openStream());
         Properties sourceLog4jProp = new Properties();
         sourceLog4jProp.load(inputStream);
         String sourceLog4jPath = sourceLog4jProp.getProperty("log4j.appender.R.File");
         if (!logFileLocation.equals(sourceLog4jPath)) {
            sourceLog4jProp.setProperty("log4j.appender.R.File", logFileLocation);
            URL url = FileLocator.toFileURL(log4jPropertyFilePath);
            FileOutputStream stream = new FileOutputStream(url.getFile());
            sourceLog4jProp.store(stream, "modify log loaction.");
            stream.close();
         }
         inputStream.close();
      }
      catch (FileNotFoundException e1) {
         e1.printStackTrace();
         return INIT_FAIL;
      }
      catch (IOException e) {
         e.printStackTrace();
         return INIT_FAIL;
      }
      try {
         InputStream in = new BufferedInputStream(new FileInputStream(dbConfig));
         Properties p = new Properties();
         p.load(in);
         DATABASE_TYPE = p.getProperty("database.type");
         DATABASE_DRIVE = p.getProperty("jdbc.driver");
         DATABASE_URL = p.getProperty("database.url");
         DATABASE_USER = p.getProperty("database.user");
         DATABASE_PASSWORD = p.getProperty("database.password");
         LOCAL_STRING = p.getProperty("system.lanuage");
         CSV_DIR = p.getProperty("CsvDir");
         BLACKLIST_START_TIME = p.getProperty("Blacklist_Start_Time");
         DATA_FILE_DIR = p.getProperty("Data_File_Dir");
         
         IQ_DATABASE_DRIVE = p.getProperty("iq.jdbc.driver");
         IQ_DATABASE_URL = p.getProperty("iq.database.url");
         IQ_DATABASE_USER = p.getProperty("iq.database.user");
         IQ_DATABASE_PASSWORD = p.getProperty("iq.database.password");

         String tmp = p.getProperty("timer.enable");
         if (tmp != null && tmp.equals("true")) TIME_ENABLE = true;
      }
      catch (FileNotFoundException e1) {
         e1.printStackTrace();
         return INIT_FAIL;
      }
      catch (IOException e) {
         e.printStackTrace();
         return INIT_FAIL;
      }
      
      // for test
//      com.liusy.analysismodel.datatransport.DataExporter.exportTable
//      ("dcs_data",new String[]{"ID","CFD","HBRQ"},new String[]{"01","01","02"},"CFD='SZX'");
      
      
      
      return INIT_SUCCESS;
   }

   /**
    * 初始化语言
    * 
    * @return
    */
   private static int initLocal() {
      try {
         if (LOCAL_STRING.trim().equalsIgnoreCase("zh_cn")) {
            Locale.setDefault(new Locale("zh", "CN"));
            return DataAdminPlatform.INIT_SUCCESS;
         }

      }
      catch (Exception e) {
         e.printStackTrace();
         return DataAdminPlatform.INIT_FAIL;
      }
      return DataAdminPlatform.INIT_FAIL;

   }

}
