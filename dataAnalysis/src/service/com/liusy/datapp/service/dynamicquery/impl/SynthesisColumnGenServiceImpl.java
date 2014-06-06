package com.liusy.datapp.service.dynamicquery.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import com.liusy.core.util.Const;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.form.QueryForm;
import com.liusy.dataapplatform.base.util.DateUtil;
import com.liusy.dataapplatform.base.util.QueryParam;
import com.liusy.datapp.model.datastandard.StandardCode;
import com.liusy.datapp.model.datastandard.StandardCodeset;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.service.dynamicquery.SynthesisColumnGenService;
import com.liusy.datapp.service.pool.BussCodeSetPool;
import com.liusy.datapp.service.resource.ResourceColumnUserCfgService;
import com.liusy.datapp.service.util.SynthesisUserColumnCfgParam;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.util.poolobj.SubjectColumnConfigPoolObj;

/**
 * <p>
 * Title: 数据管理中心
 * </p>
 * <p>
 * Description:综查辅助类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 * <p>
 * Company: 
 * </p>
 * 
 * @author luoming
 * @version 1.0
 */
public class SynthesisColumnGenServiceImpl implements SynthesisColumnGenService {
   private ResourceColumnUserCfgService resourceColumnUserCfgService;

   public String getInputHtmlCode(SubjectColumnConfigPoolObj pool, BussCodeSetPool busspool, QueryForm theForm) {
      StringBuffer buffer = new StringBuffer();
      String indId = pool.getIndId();
      String codeSetNo = pool.getCodeSetNo();
      String valuefrom = theForm.getRecord().get("col" + indId + "from");
      String valueto = theForm.getRecord().get("col" + indId + "to");
      String twofieldcoltd = "<td width=\"30%\">";
      String seltd = "<td width=\"30%\" class=\"sel\">";
      String tdend = "</td>";
      if (valuefrom == null) valuefrom = "";//DateUtil.getDateTime("yyyy", new Date(System.currentTimeMillis()))+"-01-01";
      if (valueto == null) valueto = "";//DateUtil.getDateTime("yyyy-MM-dd", new Date(System.currentTimeMillis()));
      String value = theForm.getRecord().get("col" + indId);
      if (value == null) value = "";
      if (codeSetNo != null && !"".equals(codeSetNo.trim())) {
         String valuecode = theForm.getRecord().get("col" + indId);
         buffer.append(seltd + "<select name=\"record(col" + indId + ")\" title=\"" + pool.getCnName() + "\"><option value=\"\"></option>");

         // StandardCodesetService
         // standardCodesetService=(StandardCodesetService)getBean("standardCodesetService");
         String codeSetId = busspool.getCodeSetByCodeNo(pool.getCodeSetNo()).getId().toString();
         List<StandardCode> codeSets = busspool.getCodeListByCodeSetId(codeSetId);

         if (codeSets != null) {
            for (StandardCode code : codeSets) {
               String selected = valuecode != null && code.getValue().equals(valuecode) ? "selected" : "";
               buffer.append("<option value=\"" + code.getValue() + "\"" + selected + ">" + code.getName() + "</option>");
            }
         }
         buffer.append("</select>");
      }
      else {
         if (pool.getDataType().equals(Const.META_TYPE_DATE)) {

            if (pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)) {
               buffer.append(twofieldcoltd + "<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"><tr><td width=\"50%\">");
               buffer.append("<input id=\"record(col" + indId + "from)\" name=\"record(col" + indId
                     + "from)\" type=\"text\" readonly=\"readonly\" class=\"Wdate\" onclick=\"WdatePicker({isShowWeek:true});\" value=\"" + valuefrom
                     + "\" /></td><td width=\"50%\">");
               buffer.append("<input id=\"record(col" + indId + "to)\" name=\"record(col" + indId
                     + "to)\" type=\"text\" readonly=\"readonly\" class=\"Wdate\" onfocus=\"WdatePicker({isShowWeek:true})\" value=\"" + valueto
                     + "\" /></td></tr></table>");
            }
            else buffer.append("<input id=\"record(col" + indId + ")\" name=\"record(col" + indId + ")\" title=\"" + pool.getCnName()
                  + "\" type=\"text\" readonly=\"readonly\" class=\"Wdate\" onclick=\"WdatePicker({isShowWeek:true});\" value=\"" + value + "\" />");
         }
         else {
            if (pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)) {
               buffer.append(twofieldcoltd + "<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"><tr><td>");
               buffer.append("<input id=\"record(col" + indId + "from)\" name=\"record(col" + indId + "from)\" type=\"text\" class=\"spa\"  value=\""
                     + valuefrom + "\" /></td><td>");
               buffer.append("<input id=\"record(col" + indId + "to)\" name=\"record(col" + indId + "to)\" type=\"text\"  class=\"spa\" value=\"" + valueto
                     + "\" /></td></tr></table>");
            }
            else buffer.append(twofieldcoltd + "<input id=\"record(col" + indId + ")\" name=\"record(col" + indId + ")\" title=\"" + pool.getCnName()
                  + "\" type=\"text\" class=\"spa\" value=\"" + value + "\" />");
         }

      }
      return buffer.toString();
   }

   public String getDisplayDataType(ColumnPoolObj obj) {
      if (obj.getDataType().equals(Const.META_TYPE_NUMERIC)) return "int";
      else if (obj.getDataType().equals(Const.META_TYPE_DATE)) return "date";
      else if (obj.getDataType().equals(Const.META_TYPE_STRING)) return "str";
      else return "str";
   }

   private int rowcount = 1;   // 每行显示字段数

   public void setRowCount(int count) {
      rowcount = count;
   }

   public String genenrateResultHtmlCode(List<ColumnPoolObj> colPoolObj, Map<String, String> record, HttpServletRequest request) {

      StringBuffer buffer = new StringBuffer();
      String retStr = "";
      String trstart = "</tr><tr>";
      String onenamecoltd = "<td width=\"20%\">";
      String onefieldcoltd = "<td width=\"80%\" style=\"text-align:left;\">";
      String twonamecoltd = "<td width=\"20%\">";
      String twofieldcoltd = "<td width=\"30%\" style=\"text-align:left;\">";
      String tdbegin = "<td>";
      String tdend = "</td>";
      String trend = "</tr>";
      buffer.append("<tr>");
      int count = 0;
      List<ColumnPoolObj> picPools = new ArrayList<ColumnPoolObj>();
      //		ColumnPoolObj picPool = null;
      for (ColumnPoolObj pool : colPoolObj) {
         if (Const.META_TYPE_PICTRUE.equals(pool.getDataType())) {
            picPools.add(pool);
         }
         else {
            count++;
            String value = record.get(pool.getName()).equals("") ? "&nbsp" : record.get(pool.getName());
            if (rowcount == 1) {
               buffer.append(onenamecoltd + pool.getCnName() + "：" + tdend);
               buffer.append(onefieldcoltd + value + tdend);
               buffer.append(trstart);
            }
            else {
               if (count % rowcount == 0) {
                  buffer.append(twonamecoltd + pool.getCnName() + "：" + tdend);
                  buffer.append(twofieldcoltd + value + tdend);
                  buffer.append(trstart);
               }
               else {
                  buffer.append(twonamecoltd + pool.getCnName() + "：" + tdend);
                  buffer.append(twofieldcoltd + value + tdend);
               }
            }
         }
      }

      if (count % rowcount == 0) {
         retStr = buffer.substring(0, buffer.length() - 4);
      }// 去掉最后的<tr>
      else {
         int repnum = (rowcount - count % rowcount) * 2;

         buffer.append("<td colspan=\"" + repnum + "\">&nbsp;</td>");
         retStr = buffer.append("</tr>").toString();
      }
      if (!picPools.isEmpty()) {
         List<String> res = new ArrayList<String>();
         for (int i = 0; i < picPools.size(); i++) {
            ColumnPoolObj picPool = picPools.get(i);
            if (picPool != null) {
               String url = record.get(picPool.getName()) == null ? "" : record.get(picPool.getName());
               if (!"".equals(url)) {
                  getStrBy(url, res);
                  //						String lastTr = "<tr><td colspan=\""+rowcount*2+"\" ><img src=\""+contextPath+url+"\" /> </td></tr>";
                  //						retStr += lastTr;
               }
            }
         }
         request.setAttribute("picURL", res);
      }

      return retStr;
   }

   public String getResultXml(List<Map<String, String>> list, List<String> colNameList, ResourceColumn obj) {
      StringBuffer buffer = new StringBuffer();
      buffer.append("<rows>");
      int id = 1;
      if (list != null && !list.isEmpty()) {

         for (Map<String, String> map : list) {
            String idstr = "0";
            if (obj != null) idstr = map.get(obj.getName());
            buffer.append("<row id=\"" + idstr + "\">");
            if (obj != null) buffer.append("<cell>" + idstr + "</cell>");
            for (String col : colNameList) {
               buffer.append("<cell>" + map.get(col) + "</cell>");
            }
            buffer.append("</row>");
         }
      }
      buffer.append("</rows>");
      return buffer.toString();
   }

   public String filterColumnType(ColumnPoolObj obj) {
      if (obj.getDataType().equals(Const.META_TYPE_NUMERIC)) return QueryParam.COLUMN_TYPE_INT;
      else if (obj.getDataType().equals(Const.META_TYPE_DATE)) return QueryParam.COLUMN_TYPE_DATE;
      else if (obj.getDataType().equals(Const.META_TYPE_STRING)) return QueryParam.COLUMN_TYPE_STRING;
      else return QueryParam.COLUMN_TYPE_STRING;
   }

   public String getBatchInputHtmlCode(SubjectColumnConfigPoolObj pool, BussCodeSetPool busspool, QueryForm theForm, JSONObject jsonObj) {
      StringBuffer buffer = new StringBuffer();
      String indId = pool.getIndId();
      String codeSetNo = pool.getCodeSetNo();

      if (codeSetNo != null && !"".equals(codeSetNo.trim())) {

         String valuecode = getJSONString(jsonObj, "col" + indId);
         StandardCodeset codeset = busspool.getCodeSetByCodeNo(codeSetNo);
         String codeName = "";
         if (valuecode != null && !"".equals(valuecode)) {
            Map<String, String> codeMap = busspool.getCodeSetValueMap(codeset.getId().toString());
            codeName = codeMap.get(valuecode);
         }

         // buffer.append("<input type=\"text\" readonly=\"readonly\"
         // onfocus=\"showdict("+codeset.getId().toString()+",this,"+indId+");\"
         // value=\""+codeName+"\" />")
         // .append("<input type=\"hidden\" name=\"col"+indId+"\"
         // value=\""+valuecode+"\" /> ");
         buffer.append("<select name=\"col" + indId + "\" ><option value=\"\"></option>");

         String codeSetId = codeset.getId().toString();
         List<StandardCode> codeSets = busspool.getCodeListByCodeSetId(codeSetId);

         if (codeSets != null) {
            for (StandardCode code : codeSets) {
               String selected = valuecode != null && code.getValue().equals(valuecode) ? "selected" : "";
               buffer.append("<option value=\"" + code.getValue() + "\"" + selected + ">" + code.getName() + "</option>");
            }
         }
         buffer.append("</select>");
      }
      else {
         if (pool.getDataType().equals(Const.META_TYPE_DATE)) {

            if (pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)) {
               String valuefrom = getJSONString(jsonObj, "col" + indId + "from");
               if (valuefrom == null || "".equals(valuefrom)) valuefrom = "";//DateUtil.getDateTime("yyyy", new Date(System.currentTimeMillis()))+"-01-01";
               String valueto = getJSONString(jsonObj, "col" + indId + "to");
               if (valueto == null || "".equals(valueto)) valueto = "";//DateUtil.getDateTime("yyyy-MM-dd", new Date(System.currentTimeMillis()));
               buffer.append("<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"><tr><td width=\"50%\">");
               buffer.append("<input name=\"col" + indId
                     + "from\" type=\"text\" readonly=\"readonly\" class=\"Wdate\" onclick=\"WdatePicker({isShowWeek:true});\" value=\"" + valuefrom
                     + "\" /></td><td width=\"50%\">");
               buffer.append("<input name=\"col" + indId
                     + "to\" type=\"text\" readonly=\"readonly\" class=\"Wdate\" onclick=\"WdatePicker({isShowWeek:true});\" value=\"" + valueto
                     + "\" /></td></tr></table>");
            }
            else {
               String value = getJSONString(jsonObj, "col" + indId);
               buffer.append("<input name=\"col" + indId
                     + "\" type=\"text\" readonly=\"readonly\" class=\"Wdate\" onclick=\"WdatePicker({isShowWeek:true});\" value=\"" + value + "\" />");
            }
         }
         else {
            if (pool.getFilterOperator().equals(Const.FILTER_OPER_BETWEEN)) {
               String valuefrom = getJSONString(jsonObj, "col" + indId + "from");
               String valueto = getJSONString(jsonObj, "col" + indId + "to");
               buffer.append("<table cellspacing=\"0\" cellpadding=\"0\" width=\"100%\"><tr><td>");
               buffer.append("<input name=\"col" + indId + "from\" type=\"text\" value=\"" + valuefrom + "\" /></td><td>");
               buffer.append("<input name=\"col" + indId + "to\" type=\"text\" value=\"" + valueto + "\" /></td></tr></table>");
            }
            else {
               String value = getJSONString(jsonObj, "col" + indId);
               buffer.append("<input name=\"col" + indId + "\" type=\"text\" value=\"" + value + "\" />");
            }
         }

      }
      return buffer.toString();
   }

   public String getColumnHtmlCode(ColumnPoolObj pool, BussCodeSetPool busspool, QueryForm theForm) {
      StringBuffer buffer = new StringBuffer();
      String indId = pool.getId();
      // String codeSetNo=pool.getCodeSetNo();
      String codeSetId = pool.getCodesetId();
      String valuefrom = theForm.getRecord().get("col" + indId + "from");
      String valueto = theForm.getRecord().get("col" + indId + "to");
      String twofieldcoltd = "<td width=\"30%\">";
      String seltd = "<td width=\"30%\" class=\"sel\">";
      String tdend = "</td>";
      if (valuefrom == null) valuefrom = "";
      if (valueto == null) valueto = "";
      String value = theForm.getRecord().get("col" + indId);
      if (value == null) value = "";
      if (codeSetId != null && !"".equals(codeSetId.trim())) {
         String valuecode = theForm.getRecord().get("col" + indId);
         buffer.append(seltd + "<select name=\"record(col" + indId + ")\" title=\"" + pool.getCnName() + "\"><option value=\"\"></option>");

         // StandardCodesetService
         // standardCodesetService=(StandardCodesetService)getBean("standardCodesetService");

         List<StandardCode> codeSets = busspool.getCodeListByCodeSetId(codeSetId);

         if (codeSets != null) {
            for (StandardCode code : codeSets) {
               String selected = valuecode != null && code.getValue().equals(valuecode) ? "selected" : "";
               buffer.append("<option value=\"" + code.getValue() + "\"" + selected + ">" + code.getName() + "</option>");
            }
         }
         buffer.append("</select>");
      }
      else {
         if (pool.getDataType().equals(Const.META_TYPE_DATE)) {

            buffer.append("<input id=\"record(col" + indId + ")\" name=\"record(col" + indId + ")\" title=\"" + pool.getCnName()
                  + "\" type=\"text\" readonly=\"readonly\" class=\"Wdate\" onclick=\"WdatePicker({isShowWeek:true});\" value=\"" + value + "\" />");
         }
         else {
            buffer.append(twofieldcoltd + "<input id=\"record(col" + indId + ")\" name=\"record(col" + indId + ")\" title=\"" + pool.getCnName()
                  + "\" type=\"text\" value=\"" + value + "\" />");
         }

      }
      return buffer.toString();
   }

   /**
    * 按照安全等级过滤查询结果
    * 
    * @param tableId
    *           表ID
    * @param resultList
    *           查询结果
    * @param columnPoolList
    *           字段缓存
    * @param session
    *           用户session
    */
   public void filterSecurityLevel(String tableId, List<Map<String, String>> resultList, List<ColumnPoolObj> columnPoolList, ResourceColumn pkobj,
         Session session) {
      List<SynthesisUserColumnCfgParam> paramList = resourceColumnUserCfgService.getTableColumnSercurityCfg(tableId, columnPoolList);
      for (Map<String, String> map : resultList) {
         for (SynthesisUserColumnCfgParam obj : paramList) {
            if (!obj.getId().equals(String.valueOf(pkobj.getId()))) {
               String securitylevel = obj.getSercurityLevel();
               String userlevel = session.getSecurityLevel();

               //目前只是针对用户行政等级进行了过滤，对用户分配的资源权限没有过滤
               if (securitylevel != null && securitylevel.compareTo(userlevel) < 0) {
                  map.put(obj.getName(), "*");
               }
            }
            // 过滤空字符
            if (map.get(obj.getName()) == null || "null".equals(map.get(obj.getName()))) map.put(obj.getName(), "");
         }
      }
   }

   public String getCodeSetHtml(ColumnPoolObj obj, BussCodeSetPool busspool, String ctrlName) {
      String codeSetId = obj.getCodesetId();
      // String codeSetNo=busspool.getCodeSetById(codeSetId).getCodesetNo();
      StringBuffer buffer = new StringBuffer();
      buffer.append("<select name=\"" + ctrlName + "\"><option value=\"\"></option>");

      // StandardCodesetService
      // standardCodesetService=(StandardCodesetService)getBean("standardCodesetService");
      List<StandardCode> codeSets = busspool.getCodeListByCodeSetId(codeSetId);

      if (codeSets != null) {
         for (StandardCode code : codeSets) {
            buffer.append("<option value=\"" + code.getValue() + "\">" + code.getName() + "</option>");
         }
      }
      buffer.append("</select>");
      return buffer.toString();
   }

   public String getCodeSetHtml(SubjectColumnConfigPoolObj obj, BussCodeSetPool busspool, String ctrlName) {

      String codeSetId = busspool.getCodeSetByCodeNo(obj.getCodeSetNo()).getId().toString();
      StringBuffer buffer = new StringBuffer();
      buffer.append("<select size=\"12\" class=\"select\" onblur=\"hidefloat()\" ondblclick=\"getselectvalue()\" name=\"" + ctrlName
            + "\"><option value=\"\"></option>");

      // StandardCodesetService
      // standardCodesetService=(StandardCodesetService)getBean("standardCodesetService");
      List<StandardCode> codeSets = busspool.getCodeListByCodeSetId(codeSetId);

      if (codeSets != null) {
         for (StandardCode code : codeSets) {
            buffer.append("<option value=\"" + code.getValue() + "\">" + code.getName() + "</option>");
         }
      }
      buffer.append("</select>");
      return buffer.toString();
   }

   public String getTableCountResultXml(List<Map<String, String>> list) {
      StringBuffer buffer = new StringBuffer();
      buffer.append("<rows>");
      if (list != null && !list.isEmpty()) {
         for (Map<String, String> map : list) {
            buffer.append("<row id=\"" + map.get("id") + "\">");
            buffer.append("<cell>" + map.get("cName") + "</cell>");
            buffer.append("<cell>" + map.get("count") + "</cell>");
            buffer.append("</row>");
         }
      }
      buffer.append("</rows>");
      return buffer.toString();
   }

   private static String getJSONString(JSONObject object, String columnName) {
      String retStr = "";
      if (object != null && object.has(columnName)) retStr = object.getString(columnName);
      return retStr;
   }

   public void setResourceColumnUserCfgService(ResourceColumnUserCfgService resourceColumnUserCfgService) {
      this.resourceColumnUserCfgService = resourceColumnUserCfgService;
   }

   private void getStrBy(String str, List<String> res) {
      boolean isEnd = true;
      if (str == null) {
         str = "";
      }
      if (!"".equals(str)) {
         do {
            if (str.indexOf("{") > -1) {
               str = str.substring(str.indexOf("{") + 1);
               if (str.indexOf("}") > -1) {
                  String strTemp = str.substring(0, str.indexOf("}"));
                  if (strTemp != null && !strTemp.equals("")) {
                     strTemp = strTemp.replaceAll("\\\\", "/");
                     res.add(strTemp);
                  }
                  if (str.length() > str.indexOf("}") + 1) {
                     str = str.substring(str.indexOf("}") + 1);
                  }
                  else {
                     isEnd = false;
                  }
               }
            }
            else {
               //如果没有｛了，检查有没有[
               if (str != null && !str.equals("")) {
                  if (str.indexOf("[") > -1) {
                     if (str.indexOf("]") > -1) {
                        if (str.length() > str.indexOf("]") + 1) {
                           str = str.substring(str.indexOf("]") + 1);
                        }
                        else {
                           isEnd = false;
                        }
                     }
                     else {
                        isEnd = false;
                     }
                  }
                  else {
                     str = str.replaceAll("\\\\", "/");
                     res.add(str);
                     isEnd = false;
                  }
               }
               else {
                  isEnd = false;
               }
            }
         }
         while (isEnd);
      }
      return;
      //		if (str.indexOf("{")>-1) {
      //			str = str.substring(str.indexOf("{")+1);
      //			if (str.indexOf("}")>-1) {
      //				String strTemp = str.substring(0,str.indexOf("}"));
      //				if (strTemp!=null&&!strTemp.equals("")) {
      //					res.add(strTemp);
      //				}
      //				str = str.substring(str.indexOf("}")+1);
      //				getStrBy(str,res);
      //			}				
      //		}else {
      //			if (str!=null&&!str.equals("")) {
      //				res.add(str);
      //				return;
      //			}
      //		}
   }
}
