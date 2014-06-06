package com.liusy.analysismodel.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 */
public class Conversion {
   /**
    * // public static List<Object> getList(Object[] value) { // List<Object>
    * result = new ArrayList<Object>(); // int length = value.length; // for
    * (int i = 0; i < length; i++) { // result.add(value[i]); // } // return
    * result; // }
    * /***************************************************************************
    * file to other start
    */
   /**
    * </pre>
    * 
    * @param file
    * @return
    */
   //	public static DataHandler getFileToDataHandler(File file) {
   //		return new DataHandler(new FileDataSource(file));
   //	}
   /**
    * <pre>
    * </pre>
    * 
    * @param file
    * @return
    */
   //	public static InputStream getFileToInputStream(File file) {
   //		try {
   //			return new FileInputStream(file);
   //		} catch (FileNotFoundException e) {
   //			return null;
   //		}
   //	}
   /****************************************************************************
    * file to other end
    */

   /****************************************************************************
    * object to other start
    */
   /**
    * <pre>
    * 
    * &#064;param value
    * &#064;return
    * 
    */
   //	public static int getObjectToInt(Object value) {
   //		try {
   //			return Integer.parseInt(value.toString().trim());
   //		} catch (Exception e) {
   //			return 0;
   //		}
   //	}
   /**
    * @param value
    * @return
    */
   public static java.sql.Date getObjectToSqlDate(Object value) {
      if (value == null) { return null; }
      if (value instanceof java.sql.Date) { return (java.sql.Date) value; }
      if (value instanceof java.util.Date) { return getUtilDateToSqlDate((java.util.Date) value); }
      return getUtilDateToSqlDate(getObjectToUtilDate(value));
   }

   /**
    * <pre>
    * &#064;param value
    * &#064;return
    * 
    */
   public static String getObjectToString(Object value) {
      if (value == null) { return ""; }
      String str = value.toString().trim();
      if ("null".equals(str)) { return ""; }
      return value.toString();
   }

   /**
    * <pre>
    * 
    * &#064;param value
    * &#064;return
    * 
    */
   public static java.util.Date getObjectToUtilDate(Object value) {
      String str_date = getObjectToString(value);
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      try {
         return dateFormat.parse(str_date);
      }
      catch (ParseException e) {
         return null;
      }

   }

   public static java.sql.Timestamp getObjectToSqlTime(Object value) {
      String str_date = getObjectToString(value);
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      try {
         Calendar tCal = Calendar.getInstance();
         java.util.Date date = dateFormat.parse(str_date);
         tCal.setTime(date);
         return new Timestamp(tCal.getTime().getTime());
      }
      catch (ParseException e) {
         return null;
      }
   }

   public static String getSqlDateToString(java.sql.Date value, String format) {
      if (value == null) { return ""; }
      java.util.Date utilDate = getSqlDateToUtilDate(value);
      return getUtilDateToString(utilDate, format);
   }

   public static java.util.Date getSqlDateToUtilDate(java.sql.Date value) {
      if (value == null) { return null; }
      return new java.util.Date(value.getTime());
   }

   /****************************************************************************
    * sqldate to other end
    */

   /****************************************************************************
    * utildate to other end
    * 
    * @param value
    * @return
    */
   public static java.sql.Date getUtilDateToSqlDate(java.util.Date value) {
      if (value == null) { return null; }
      return new java.sql.Date(value.getTime());
   }

   /**
    * @param value
    * @param format
    * @return
    */
   public static String getUtilDateToString(java.util.Date value, String format) {
      String dateStr = "";
      SimpleDateFormat dateFormat = new SimpleDateFormat(format);
      if (value == null) { return ""; }
      if (value instanceof java.sql.Timestamp) {
         dateStr = value.toString().substring(0, 19);
         return dateStr;
      }
      if (value instanceof java.util.Date) { return dateFormat.format(value); }
      return "";
   }

   public static String getDateTimeToString(java.util.Date value) {

      String dateStr = "";
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mi:ss");
      if (value == null) { return ""; }
      if (value instanceof java.sql.Timestamp) {
         dateStr = value.toString().substring(0, 16);
         return dateStr;
      }
      if (value instanceof java.util.Date) { return dateFormat.format(value); }
      return "";

   }

   public static String getDateToString(Object date) {
      if (date instanceof java.util.Date) {

      }
      if (date instanceof java.sql.Timestamp) {

      }
      return null;
   }

   public static String getStringToDate(String date) {

      return null;
   }

   /**
    */
   public static java.util.Date getStringToUtilDateTime(String dateStr, String timeStr) {

      if (dateStr == null || timeStr == null || "".equals(dateStr) || "".equals(timeStr)) {
         return null;
      }
      else {
         String[] dateStrs = dateStr.split("/");
         if (dateStrs.length != 3) { return null; }
         String[] timeStrs = timeStr.split(":");
         if (timeStrs.length != 3) { return null; }
         int year = Integer.parseInt(dateStrs[0]);
         int month = Integer.parseInt(dateStrs[1]);
         int day = Integer.parseInt(dateStrs[2]);
         int hour = Integer.parseInt(timeStrs[0]);
         int min = Integer.parseInt(timeStrs[1]);
         int sec = Integer.parseInt(timeStrs[2]);
         Calendar cl = Calendar.getInstance();
         try {
            cl.set(year, month - 1, day, hour, min, sec);
         }
         catch (ArrayIndexOutOfBoundsException e) {
            return null;
         }
         catch (Exception e) {
            return null;
         }
         java.util.Date date = cl.getTime();
         return date;
      }

   }

   public static boolean isAllNumber(String str) {

      if (str == null || "".equals(str)) { return false; }
      Boolean boo = true;
      for (int i = 0; i < str.length(); i++) {
         if (!Character.isDigit(str.charAt(i))) {
            boo = false;
         }
      }
      return boo;
   }

   public static boolean isNumeric(String str) {

      if (str == null || "".equals(str)) { return false; }
      Boolean boo = true;
      int n = 0;
      int m = 0;
      for (int i = 0; i < str.length(); i++) {
         if (Character.isDigit(str.charAt(i))) {
            n++;
         }
         if (str.charAt(i) == '.') {
            m++;
         }
      }
      if (m > 1) {
         boo = false;
      }
      if (n + m != str.length()) {
         boo = false;
      }
      if (str.charAt(0) == '.') {
         boo = false;
      }
      if (str.charAt(str.length() - 1) == '.') {
         boo = false;
      }
      return boo;
   }

   public static boolean compareAfter(String date1, Date Date2) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      try {
         Date tempDate = dateFormat.parse(date1);
         tempDate.setHours(23);
         tempDate.setMinutes(59);
         tempDate.setSeconds(59);
         if (tempDate.after(Date2)) { return true; }
      }
      catch (ParseException e) {
         return false;
      }
      return false;

   }

   public static final String getDate(Date date) {
      SimpleDateFormat df = null;
      String returnValue = "";

      if (date != null) {
         df = new SimpleDateFormat("yyyy-MM-dd");
         returnValue = df.format(date);
      }

      return (returnValue);
   }

}
