
package com.liusy.analysis.template.model.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{
  public static String tableName = "LOGTABLES";
  public static String dateFormat = "yyyy-MM-dd";
  public static String dateTimeFormat = "yyyy-MM-dd hh:mm:ss";
  public static String timeStartTag = " 00:00:00";
  public static String timeEndTag = " 23:59:59";
  public static String imageNameRex = "\\{\\w+\\.\\w+\\}";
  public static String filePathRex = "(^\\.|^/|^[a-zA-Z])?:?/.+(/$)?";
  public static String defFileDictory = "(^\\.|^/|^[a-zA-Z])?:?/";
  public static String fileNameRex = "((\\w)|\\-|（\\d）|[\\u4e00-\\u9fa5])+\\.txt";
  public static String fileNameExtension = ".txt";
  public static String defFilePath = "D:\\";
  public static String defFileName = "one.txt";
  public static String searchExceptionMessage = "检索异常发生。";
  public static String firstPageWarnMessage = "已经是第一页了！";
  public static String lastPageWarnMessage = "已经是最后一页了！";
  public static String pageNumOverMessage = "页数超过！";
  public static String nodeName = "节点";
  public static String startNodeName = "开始节点";
  public static String dataNodeName = "数据节点";
  public static String operateNodeName = "操作节点";
  public static String resultNodeName = "结果节点";
  public static String endNodeName = "结束节点";
  public static String connectionName = "连接";
  public static String addFlg = "A";
  public static String updateFlg = "U";
  public static String deleteFlg = "D";
  public static String templateTreeType_Directory = "D";
  public static String templateTreeType_File = "F";
  public static String deleteId = "MyDelete";
  public static String arrayKey = "KeyMove";
  public static String redoId = "MyRedo";
  public static String undoId = "MyUndo";
  public static String createBendPointCmd = "生成弯点";
  public static String createConnectionCmd = "生成连接";
  public static String createNodeCmd = "生成节点";
  public static String nodePropertyModifyCmd = "节点属性变更";
  public static String dataNodePropertyModifyCmd = "数据节点属性变更";
  public static String deleteBendPointCmd = "删除弯点";
  public static String deleteConnectionCmd = "删除连接";
  public static String deleteNodeCmd = "删除节点";
  public static String diagramPropertyModifyCmd = "图例属性修改";
  public static String moveBendPointCmd = "移动弯点";
  public static String moveNodeCmd = "移动节点";
  public static String reConnectionCmd = "重新连接";
  public static String reNameConnectionCmd = "连线重命名";
  public static String reNameNodeCmd = "连线重命名";
  public static String statisticNodePropertyModifyCmd = "统计节点属性修改";

  public static String dateToString(Date theDate) {
    if (theDate == null) {
      return "";
    }

    return theDate.toString();
  }

  public static String formateDate(Date theDate, String formate)
  {
    if (theDate == null) {
      return "";
    }

    SimpleDateFormat df = new SimpleDateFormat(formate);

    return df.format(theDate);
  }

  public static boolean isInteger(String str)
  {
    if ((str == null) || (str.length() == 0)) return false; try
    {
      int i = Integer.parseInt(str);
      return true;
    } catch (Exception e) {
    }
    return false;
  }

  public static boolean isDate(String str)
  {
    if ((str == null) || (str.length() == 0)) return false;
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
      Date i = df.parse(str);
      return true;
    } catch (Exception e) {
    }
    return false;
  }

  public static boolean isNumeric(String str) {
    if ((str == null) || (str.length() == 0)) return false; try
    {
      Double d = Double.valueOf(Double.parseDouble(str));
      return true;
    } catch (Exception e) {
    }
    return false;
  }

  public static String timeStampToString(Date theDate)
  {
    if (theDate == null) {
      return "";
    }

    return theDate.toString().substring(0, 19);
  }

  public static String getStringNotNull(Object string)
  {
    if ((string == null) || ("null".equals(string))) {
      return "";
    }

    return string.toString();
  }

  public static String getFliterQueryCondition(Object string)
  {
    if ((string == null) || ("null".equals(string))) {
      return "";
    }

    return ((String)string).replaceAll("'", "\"");
  }

  public static String getFileNames(String str)
  {
    Pattern p = Pattern.compile(imageNameRex);
    Matcher m = p.matcher(str);
    StringBuffer sb = new StringBuffer("");
    boolean result = m.find();
    while (result) {
      sb.append(m.group()).append(";");
      result = m.find();
    }
    return sb.toString();
  }
}