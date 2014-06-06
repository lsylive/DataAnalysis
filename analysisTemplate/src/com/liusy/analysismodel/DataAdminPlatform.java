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
 * ��ʼ��ϵͳ��
 * 
 * @author andy
 */
public class DataAdminPlatform {
   private static Logger   log          = Logger.getLogger(DataAdminPlatform.class);

   //   private static final String         BUNDLE_NAME     = "dataplatform";
   //   private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

   public static String    DATABASE_DRIVE;                                          //���ݿ�������
   public static String    DATABASE_URL;                                            //���ݿ�����url
   public static String    DATABASE_TYPE;                                           //���ݿ�����
   public static String    DATABASE_USER;                                           //���ݿ������û�
   public static String    DATABASE_PASSWORD;                                       //���ݿ���������
   

   public static String    IQ_DATABASE_DRIVE;                                          //IQ���ݿ�������
   public static String    IQ_DATABASE_URL;                                            //IQ���ݿ�����url
   public static String    IQ_DATABASE_USER;                                           //IQ���ݿ������û�
   public static String    IQ_DATABASE_PASSWORD;                                       //IQ���ݿ���������
   
   
   public static final int INIT_SUCCESS = 0;                                        //��ʼ���ɹ�
   public static final int INIT_FAIL    = 1;                                        //��ʼ��ʧ��

   public static String    LOCAL_STRING;                                            //����ϵͳ
   public static String    CSV_DIR;                                                 //����CSV���·��
   public static boolean   TIME_ENABLE  = false;                                    //��ʱ��������־
   
   public static String	   BLACKLIST_START_TIME;									//��������ʱ����ʱ��
   public static String    DATA_FILE_DIR;										    //���ݵ����ļ�·��
   

   public static int init() {
      //      PropertyConfigurator.configure (configFilename);
      log.info("��ӭʹ�����ݹ���ϵͳ��ϵͳ���ڳ�ʼ��...");
      String str = "";
      try {
         str = FileLocator.toFileURL(Platform.getBundle(Application.PLUGIN_ID).getEntry("")).getPath();
      }
      catch (Exception e) {

      }
//      log.info("AppPath:" + str);//osgi/BUNDLE��Ŀ¼
//      log.info(Platform.getInstallLocation().getURL().getPath());//ϵͳ���е���Ŀ¼
//      log.info(Platform.getLocation().toString());//ϵͳ����ʱworkspace��Ŀ¼
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
         //�޸�LOG4J�ļ�
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
    * ��ʼ������
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
