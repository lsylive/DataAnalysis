package com.liusy.analysismodel.util;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

public class DateUtil {
   private static int x = 0, y = 0;
   private static boolean flagX = false, flagY = false;
   private static boolean flagW = true, flagH = true;
   private static int     btnWidth = 20, btnHeight = 20;

   @SuppressWarnings("deprecation")
   public static String getNumBeforMonth(Date yearMonth, int number) throws Exception {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(yearMonth);
      Integer year = calendar.get(Calendar.YEAR);
      Integer month = calendar.get(Calendar.MONTH) + 1;
      Integer temp = month - number;
      StringBuffer result = new StringBuffer("");
      if (temp > 0) {
         month = temp;
      }
      else {
         number = number - month;
         year--;
         int addYear = number / 12;
         year = year - addYear;
         int remainMonth = number % 12;
         month = 12 - remainMonth;

      }
      if (month < 10) {
         result.append(year.toString() + "0" + month.toString());

      }
      else {
         result.append(year.toString() + month.toString());
      }
      return result.toString();
   }

   @SuppressWarnings("deprecation")
   public static String getNumAfterMonth(Date yearMonth, int number) throws Exception {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(yearMonth);
      Integer year = calendar.get(Calendar.YEAR);
      Integer month = calendar.get(Calendar.MONTH) + 1;
      StringBuffer result = new StringBuffer("");
      if (month + number <= 12) {
         month = month + number;
      }
      else {
         Integer temp = number - (12 - month);
         year++;
         int addYear = temp / 12;
         year = year + addYear;
         int remainMonth = temp % 12;
         month = remainMonth;
      }
      if (month < 10) {
         result.append(year.toString() + "0" + month.toString());

      }
      else {
         result.append(year.toString() + month.toString());
      }
      return result.toString();

   }

   /**

    */
   @SuppressWarnings("deprecation")
   public static Date getLastMonthfirstDay(Date date) throws Exception {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(date);
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH) + 1;
      if (month == 1) {
         year--;
         month = 12;
      }
      else {
         month--;
      }
      calendar.clear();
      calendar.set(year, month - 1, 1);
      return calendar.getTime();
   }

   /**
    * @throws Exception
    */
   public static Date getLastMonthLastDay(Date date) throws Exception {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(date);
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH) + 1;
      int day = calendar.get(Calendar.DATE);
      if (month == 1) {
         year--;
         month = 12;
      }
      else {
         month--;
      }
      calendar.clear();
      calendar.set(year, month - 1, day);
      day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
      calendar.clear();
      calendar.set(year, month - 1, day);

      return calendar.getTime();
   }

   /**
    * <pre>
    * 
    * &#064;throws Exception
    * 
    */
   public static Date getNextMonthfirstDay(Date date) throws Exception {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(date);
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH) + 1;
      if (month == 12) {
         year++;
         month = 1;
      }
      else {
         month++;
      }
      calendar.clear();
      calendar.set(year, month - 1, 1);
      return calendar.getTime();
   }

   /**
    * @throws Exception
    */
   public static Date getNextMonthLastDay(Date date) throws Exception {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(date);
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH) + 1;
      int day = calendar.get(Calendar.DATE);
      if (month == 12) {
         year++;
         month = 1;
      }
      else {
         month++;
      }
      calendar.clear();
      calendar.set(year, month - 1, day);
      day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
      calendar.clear();
      calendar.set(year, month - 1, day);

      return calendar.getTime();

   }

   /**
    * @return
    */
   public static Date getThisMonthLastDay(Date theDate) {
      Calendar calendar = Calendar.getInstance();
      calendar.clear();
      calendar.setTime(theDate);
      Integer year = calendar.get(Calendar.YEAR);
      Integer month = calendar.get(Calendar.MONTH) + 1;
      Integer day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
      calendar.clear();
      calendar.set(year, month - 1, day);
      return calendar.getTime();
   }

   public static java.util.Date getNow() {
      Calendar cal = Calendar.getInstance();
      return cal.getTime();
   }

   public static int getXlocation(Object btn) {
      if (btn instanceof Button) {
         Object parent = ((Button) btn).getParent();
         if (parent instanceof Shell) {
            return ((Button) btn).getLocation().x + ((Shell) parent).getLocation().x;
         }
         else if (parent instanceof Group && !flagX) {
            x = x + ((Group) parent).getLocation().x;
            System.out.println("x:" + ((Group) parent).getLocation().x);
            if (flagW) {
               x = x + ((Button) btn).getLocation().x + btnWidth;
               System.out.println("width:" + ((Composite) parent).getBounds().width);
            }
            flagW = false;
            return getXlocation(((Composite) parent).getParent());
         }
         else if (parent instanceof Composite && !flagX) {
            x = x + ((Composite) parent).getLocation().x;
            System.out.println("x:" + ((Composite) parent).getLocation().x);

            return getXlocation(((Composite) parent).getParent());
         }
      }
      if (btn instanceof Shell) {
         flagX = true;
         System.out.println("x:" + ((Shell) btn).getLocation().x);
         return x + ((Shell) btn).getLocation().x + 6;
      }
      if (btn instanceof Composite && !flagX) {
         x = x + ((Composite) btn).getLocation().x;
         System.out.println("x:" + ((Composite) btn).getLocation().x);
         return getXlocation(((Composite) btn).getParent());
      }
      return 0;

   }

   public static int getYlocation(Object btn, boolean viewFlag) {
      if (btn instanceof Button) {
         Object parent = ((Button) btn).getParent();
         if (parent instanceof Shell) {
            return ((Button) btn).getLocation().y + ((Shell) parent).getLocation().y;
         }
         else if (parent instanceof Group && !flagY) {
            y = y + ((Group) parent).getLocation().y + 5;
            System.out.println(((Group) parent).getLocation().y + 5);
            if (flagH) {
               y = y + ((Button) btn).getLocation().y;
               System.out.println("width:" + ((Composite) parent).getBounds().width);
            }
            flagH = false;
            return getYlocation(((Group) parent).getParent(), viewFlag);
         }
         else if (parent instanceof Composite && !flagY) {
            y = y + ((Composite) parent).getLocation().y;
            System.out.println(((Composite) parent).getLocation().y);
            return getYlocation(((Composite) parent).getParent(), viewFlag);
         }
      }
      else if (btn instanceof Shell) {
         flagY = true;
         System.out.println(((Shell) btn).getLocation().y);
         if (viewFlag) {
            return y + ((Shell) btn).getLocation().y + 45;
         }
         else {
            return y + ((Shell) btn).getLocation().y + 25;
         }

      }
      else if (btn instanceof Group) {
         y = y + ((Group) btn).getLocation().y + 5;
         System.out.println(((Group) btn).getLocation().y + 5);
         return getYlocation(((Composite) btn).getParent(), viewFlag);
      }
      else if (btn instanceof Composite && !flagY) {
         y = y + ((Composite) btn).getLocation().y;
         System.out.println(((Composite) btn).getLocation().y);
         return getYlocation(((Composite) btn).getParent(), viewFlag);
      }
      return y;

   }

   public static void dateLocationInit() {
      setX(0);
      setY(0);
      setFlagX(false);
      setFlagY(false);
      setFlagH(true);
      setFlagW(true);
   }

   public static int getX() {
      return x;
   }

   public static void setX(int x) {
      DateUtil.x = x;
   }

   public static int getY() {
      return y;
   }

   public static void setY(int y) {
      DateUtil.y = y;
   }

   public static boolean isFlagX() {
      return flagX;
   }

   public static void setFlagX(boolean flagX) {
      DateUtil.flagX = flagX;
   }

   public static boolean isFlagY() {
      return flagY;
   }

   public static void setFlagY(boolean flagY) {
      DateUtil.flagY = flagY;
   }

   public static boolean isFlagW() {
      return flagW;
   }

   public static void setFlagW(boolean flagW) {
      DateUtil.flagW = flagW;
   }

   public static boolean isFlagH() {
      return flagH;
   }

   public static void setFlagH(boolean flagH) {
      DateUtil.flagH = flagH;
   }
}
