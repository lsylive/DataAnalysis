package com.liusy.analysismodel.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.core.runtime.Platform;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

import com.liusy.analysismodel.template.ui.dialog.DataSourceBean;

public class XmlEditUtil {
   //   private static String xmlFilePath = Platform.getLocation().append("analisis").toOSString()+ "/" + "dataApp.xml";

   private static String xmlFilePath       = Platform.getLocation().append("dataSourceConf").toOSString();
   private static String xmlConfigFilePath = new String();

   public static void writeDataSourceXml(String filePath) throws IOException {
      // TODO Auto-generated method stub
      // 创建根节点 list;   
      Element root = new Element("data");
      // 根节点添加到文档中；   
      Document Doc = new Document(root);
      // data node
      Element records = new Element("records");
      root.addContent(records);

      Format ft = Format.getRawFormat();
      ft.setEncoding("utf-8");
      ft.setIndent("    ");//设置缩进 
      XMLOutputter xMlOut = new XMLOutputter(ft);
      //	      String filePath2 = Platform.getLocation().append("analisis").toOSString()+ "/" + "test.xml";
      String filePath2 = filePath;
      OutputStream os = new FileOutputStream(filePath2.toString());
      xMlOut.output(Doc, os);
      os.flush();
      os.close();
      System.gc();
   }

   public static int getMaxID() throws JDOMException, IOException {
      int max = 0;
      SAXBuilder builder = new SAXBuilder();
      InputStream file = new FileInputStream(getXmlPath(xmlFilePath));
      Document document = builder.build(file);//获得文档对象
      Element root = document.getRootElement();//获得根节点
      Element recordElem = root.getChild("records");
      List records = recordElem.getChildren("record");
      Iterator it = records.iterator();
      while (it.hasNext()) {
         Element record = (Element) it.next();
         String recordID = record.getAttributeValue("id");
         if (Integer.valueOf(recordID) > max) {
            max = Integer.valueOf(recordID);
         }
      }
      return max;
   }

   public static DataSourceBean getDataSourceByName(String dataSourceName) throws JDOMException, IOException {
      // TODO Auto-generated method stub
      SAXBuilder builder = new SAXBuilder();
      InputStream file = new FileInputStream(getXmlPath(xmlFilePath));
      Document document = builder.build(file);//获得文档对象
      Element root = document.getRootElement();//获得根节点
      Element recordElem = root.getChild("records");
      List records = recordElem.getChildren("record");
      Iterator it = records.iterator();
      Vector<DataSourceBean> vect = new Vector<DataSourceBean>();
      DataSourceBean bean = new DataSourceBean();
      boolean equalNameFlag = false;
      ArrayList<String> list = new ArrayList<String>();
      while (it.hasNext()) {
         Element record = (Element) it.next();
         List fields = record.getChildren();
         Iterator iterator = fields.iterator();
         while (iterator.hasNext()) {
            Element field = (Element) iterator.next();
            if ("sourceName".equals(field.getName())) {
               if (field.getText().equals(dataSourceName)) {
                  bean.setDataSourceName(dataSourceName);
                  equalNameFlag = true;
               }
            }
            if (equalNameFlag) {
               if ("driver".equals(field.getName())) {
                  bean.setDriver(field.getText());
               }
               else if ("url".equals(field.getName())) {
                  bean.setUrl(field.getText());
               }
               else if ("userName".equals(field.getName())) {
                  bean.setUserName(field.getText());
               }
               else if ("password".equals(field.getName())) {
                  bean.setPassword(field.getText());
               }
            }
         }
         if( equalNameFlag == true)
          	break;
      }
      return bean;
   }

   public static String[] getDataSources() throws JDOMException, IOException {
      // TODO Auto-generated method stub
      SAXBuilder builder = new SAXBuilder();
      InputStream file = new FileInputStream(getXmlPath(xmlFilePath));
      Document document = builder.build(file);//获得文档对象
      Element root = document.getRootElement();//获得根节点
      Element recordElem = root.getChild("records");
      List records = recordElem.getChildren("record");
      Iterator it = records.iterator();
      Vector<DataSourceBean> vect = new Vector<DataSourceBean>();
      ArrayList<String> list = new ArrayList<String>();
      while (it.hasNext()) {
         Element record = (Element) it.next();
         List fields = record.getChildren();
         Iterator iterator = fields.iterator();
         while (iterator.hasNext()) {
            Element field = (Element) iterator.next();
            if ("sourceName".equals(field.getName())) {
               list.add(field.getText());
            }
         }
      }
      String[] dataSources = new String[list.size()];
      return list.toArray(dataSources);

   }

   public static Vector<DataSourceBean> readDataSourceXml() throws JDOMException, IOException {
      // TODO Auto-generated method stub
      SAXBuilder builder = new SAXBuilder();
      InputStream file = new FileInputStream(getXmlPath(xmlFilePath));
      Document document = builder.build(file);//获得文档对象
      Element root = document.getRootElement();//获得根节点
      Element recordElem = root.getChild("records");
      List records = recordElem.getChildren("record");
      Iterator it = records.iterator();
      Vector<DataSourceBean> vect = new Vector<DataSourceBean>();

      while (it.hasNext()) {
         Element record = (Element) it.next();
         DataSourceBean bean = new DataSourceBean();
         String recordID = record.getAttributeValue("id");
         bean.setId(Integer.valueOf(recordID));
         List fields = record.getChildren();
         Iterator iterator = fields.iterator();
         while (iterator.hasNext()) {
            Element field = (Element) iterator.next();
            if ("sourceName".equals(field.getName())) {
               bean.setDataSourceName(field.getText());
            }
            else if ("driver".equals(field.getName())) {
               bean.setDriver(field.getText());
            }
            else if ("url".equals(field.getName())) {
               bean.setUrl(field.getText());
            }
            else if ("userName".equals(field.getName())) {
               bean.setUserName(field.getText());
            }
            else if ("password".equals(field.getName())) {
               bean.setPassword(field.getText());
            }
         }
         //         bean.setEditFlg(StringUtil.);
         vect.add(bean);
      }
      return vect;
   }

   public static void updateDataSourceXml(List<DataSourceBean> updateList) throws JDOMException, IOException {
      SAXBuilder builder = new SAXBuilder();
      xmlConfigFilePath = getXmlPath(xmlFilePath);
      InputStream file = new FileInputStream(xmlConfigFilePath);
      Document document = builder.build(file);//获得文档对象
      Element root = document.getRootElement();//获得根节点
      Element recordElem = root.getChild("records");
      List records = recordElem.getChildren("record");
      Iterator it = records.iterator();
      while (it.hasNext()) {
         Element record = (Element) it.next();
         String recordID = record.getAttributeValue("id");
         for (DataSourceBean bean : updateList) {
            if (bean.getId() == Integer.valueOf(recordID)) {
               record.getChild("sourceName").setText(bean.getDataSourceName());
               record.getChild("driver").setText(bean.getDriver());
               record.getChild("url").setText(bean.getUrl());
               record.getChild("userName").setText(bean.getUserName());
               record.getChild("password").setText(bean.getPassword());
               continue;
            }
         }
      }
      XMLOutputter xMlOut = new XMLOutputter();
      String filePath2 = xmlConfigFilePath;
      OutputStream os = new FileOutputStream(filePath2.toString());
      xMlOut.output(document, os);
      os.flush();
      os.close();
      System.gc();
   }

   public static void addDataSourceXml(List<DataSourceBean> addList) throws JDOMException, IOException {
      SAXBuilder builder = new SAXBuilder();
      xmlConfigFilePath = getXmlPath(xmlFilePath);
      InputStream file = new FileInputStream(xmlConfigFilePath);
      Document document = builder.build(file);//获得文档对象
      Element root = document.getRootElement();//获得根节点
      Element recordElem = root.getChild("records");
      for (DataSourceBean bean : addList) {
         Element recordItem = new Element("record");
         recordItem.setAttribute("id", String.valueOf(bean.getId()));
         Element sourceName = new Element("sourceName");
         sourceName.setText(bean.getDataSourceName());
         Element driver = new Element("driver");
         driver.setText(bean.getDriver());
         Element url = new Element("url");
         url.setText(bean.getUrl());
         Element userName = new Element("userName");
         userName.setText(bean.getUserName());
         Element password = new Element("password");
         password.setText(bean.getPassword());
         recordItem.addContent(sourceName);
         recordItem.addContent(driver);
         recordItem.addContent(url);
         recordItem.addContent(userName);
         recordItem.addContent(password);
         recordElem.addContent(recordItem);
      }
      Format ft = Format.getRawFormat();
      ft.setEncoding("utf-8");
      ft.setIndent("");//设置缩进 
      XMLOutputter xMlOut = new XMLOutputter();
      String filePath2 = xmlConfigFilePath;
      OutputStream os = new FileOutputStream(filePath2.toString());
      xMlOut.output(document, os);
      os.flush();
      os.close();
      System.gc();
   }

   public static void deleteDataSourceXml(List<DataSourceBean> deleteList) throws JDOMException, IOException {
      List delList = new ArrayList(5);
      SAXBuilder builder = new SAXBuilder();
      xmlConfigFilePath = getXmlPath(xmlFilePath);
      InputStream file = new FileInputStream(xmlConfigFilePath);
      Document document = builder.build(file);//获得文档对象
      Element root = document.getRootElement();//获得根节点
      Element recordElem = root.getChild("records");
      List records = recordElem.getChildren("record");
      Iterator it = records.iterator();
      while (it.hasNext()) {
         Element record = (Element) it.next();
         String recordID = record.getAttributeValue("id");
         for (DataSourceBean bean : deleteList) {
            if (bean.getId() == Integer.valueOf(recordID)) {
               delList.add(record);
               continue;
            }
         }
      }
      for (int i = 0; i < delList.size(); i++) {
         recordElem.removeContent((Element) delList.get(i));
      }
      Format ft = Format.getRawFormat();
      ft.setEncoding("utf-8");
      ft.setIndent("");//设置缩进 
      XMLOutputter xMlOut = new XMLOutputter();
      String filePath2 = xmlConfigFilePath;
      OutputStream os = new FileOutputStream(filePath2.toString());
      xMlOut.output(document, os);
      os.flush();
      os.close();
      System.gc();
   }

   public static String getXmlPath(String folder) throws IOException {
      File file = new File(folder);
      if (!file.exists()) {
         file.mkdir();
      }
      String path = folder + "\\dataSourceConfig.xml";
      File xmlFile = new File(path);
      if (!xmlFile.exists()) {
         writeDataSourceXml(path);
      }
      return path;
   }
}
