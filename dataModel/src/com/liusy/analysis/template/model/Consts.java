
package com.liusy.analysis.template.model;


public class Consts
{
  public static String[] TEMP_LABEL = { "", "飞行分析", "通讯分析", "住宿分析", "出入境分析", "综合分析" };
  public static String[] CHART_LABEL = { "", "线性图", "柱状图", "饼状图" };

  public static String[] CHART_LABEL_1 = { "", "时序图", "轨迹图" };

  public static String[] X_Y_LABEL = { "", "X轴", "Y轴" };
  public static String[] X_Y_AXIS = { "", "X轴", "Y轴" };
  public static String X_AXIS = "X";
  public static String Y_AXIS = "Y";

  public static String[] GRAPH_DATA_TYPE = { "", "数值型", "日期型" };
  public static String[] AXIS_DATE_STEPLENGTH = { "", "seconds", "minutes", "hours", "days", "months", "years" };
  public static String[] AXIS_DATE_STEPLENGTH_LABEL = { "", "秒", "分钟", "小时", "日", "月", "年" };
  public static String AXIS_DATE_YEAR = "years";
  public static String AXIS_DATE_MONTH = "months";
  public static String AXIS_DATE_DAY = "days";
  public static String AXIS_DATE_HOURS = "hours";
  public static String AXIS_DATE_MINUTES = "minutes";
  public static String AXIS_DATE_SECONDS = "secends";

  public static String[] YESNO_LABEL = { "否", "是" };
  public static String YES = "1";
  public static String NO = "0";
  public static String[] YESNO = { "0", "1" };

  public static String[] SEARCH_LABEL = { "", "起始字段", "结束字段" };
  public static String SEARCH_NONE = "none";
  public static String SEARCH_START = "start";
  public static String SEARCH_END = "end";
  public static String[] SEARCH = { SEARCH_NONE, SEARCH_START, SEARCH_END };

  public static String[] DATATYPE_LABEL = { "字符型", "日期型", "数值型", "图片型" };
  public static String DATATYPE_STRING = "01";
  public static String DATATYPE_DATE = "02";
  public static String DATATYPE_NUMERIC = "03";
  public static String DATATYPE_IMAGE = "04";
  public static String[] DATATYPE = { DATATYPE_STRING, DATATYPE_DATE, DATATYPE_NUMERIC, DATATYPE_IMAGE };

  public static String[] SORTDIRECT_LABEL = { "", "正序", "倒序" };
  public static String SORTDIRECT_NONE = "";
  public static String SORTDIRECT_ASC = "asc";
  public static String SORTDIRECT_DESC = "desc";
  public static String[] SORTDIRECT = { SORTDIRECT_NONE, SORTDIRECT_ASC, SORTDIRECT_DESC };

  public static String[] CONNECT_LABEL = { "内链接", "左外链接", "右外链接" };
  public static String CONNECT_IN = "=";
  public static String CONNECT_LEFT = "(+)=";
  public static String CONNECT_RIGHT = "=(+)";
  public static String[] CONNECT = { CONNECT_IN, CONNECT_LEFT, CONNECT_RIGHT };

  public static String[] OPERATOR_LABEL = { "等于(=)", "不等于(!=)", "大于(>)", "小于(<)", "大于等于(>=)", "小于等于(<=)", "相似(like)", "区间(between)", "包含(in)", 
    "空值(null)", "非空值(not null)" };

  public static String OPERATOR_LIKE = "like";
  public static String OPERATOR_BETWEEN = "between";
  public static String OPERATOR_IN = "in";
  public static String OPERATOR_NULL = "null";
  public static String OPERATOR_NOTNULL = "notnull";
  public static String[] OPERATOR = { "=", "!=", ">", "<", ">=", "<=", OPERATOR_LIKE, OPERATOR_BETWEEN, OPERATOR_IN, OPERATOR_NULL, 
    OPERATOR_NOTNULL };

  public static String[] AGGREGATE_LABEL = { "", "计数", "总和", "平均值", "最大值", "最小值" };
  public static String[] AGGREGATE = { "", "count", "sum", "avg", "max", "min" };

  public static String[] LEFT_PARENTHESIS = { "", "(", "((", "(((" };
  public static String[] RIGHT_PARENTHESIS = { "", ")", "))", ")))" };

  public static String[] LOGIC_LABEL = { "", "并且", "或者", "非" };
  public static String[] LOGIC = { "", "and", "or", "not" };

  public static String[] ALIGN_LABEL = { "左对齐", "居中", "右对齐" };
  public static String[] ALIGN = { "left", "center", "right" };

  public static String[] LINK_TYPE = { "无箭头", "单向箭头", "双向箭头" };
  public static String[] NODE_ICON = { "", "电话", "人物", "飞机", "旅馆", "机场", "海关", "邮箱", "城市" };

  public static String GENCOLUMN_CONST = "常量";
  public static String GENCOLUMN_PARAMETER = "参数";
  public static String GENCOLUMN_CODE = "代码值";
  public static String GENCOLUMN_EXPRESSION = "表达式";
  public static String[] GENCOLUMN_LABEL = { GENCOLUMN_CONST, GENCOLUMN_PARAMETER, GENCOLUMN_CODE, GENCOLUMN_EXPRESSION };

  public static String ROW_PARAMETER_PRE = "#";

  public static String DETAIL_LINKTYPE_NONE = "";
  public static String DETAIL_LINKTYPE_NODE = "node";
  public static String DETAIL_LINKTYPE_DIAGRAM = "diagram";
}