package com.liusy.datapp.web.resource.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.liusy.core.util.ClassLoaderUtil;
import com.liusy.datapp.web.BaseAction;




public class SystemMenuAction extends  BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		getSystemMenuXml(form, request, response);
		return null;
	}

	public void getSystemMenuDb(ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

	}

	public void getSystemMenuXml(ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
		String path = ClassLoaderUtil.getRelativeClassFilePath("../../xmlfiles/menu.xml");
		SAXBuilder builder = new SAXBuilder(false);
		Document doc = builder.build(path);
		Element sitexml = doc.getRootElement();
		List<Element> nodeelement = sitexml.getChild("MENUS").getChildren();
		JSONArray array = new JSONArray();
		for (Element ele : nodeelement) {
			String id = ele.getChildText("ID");
			String url = ele.getChild("URL").getText();
			String title = ele.getChildText("TITLE");
			String isleaf = ele.getChildText("ISLEAF");
			String parentid = ele.getChildText("PARENTID");
			String level=ele.getChildText("LEVEL");
			JSONObject object = new JSONObject();
			object.put("level", level);
			object.put("url", url);

			object.put("isLeaf", isleaf);
			object.put("parentId", parentid);
			object.put("title", title);
			object.put("id", id);
			array.add(object);
		}
		response.setContentType("application/json;charset=UTF-8");
		PrintWriter writer=response.getWriter();
		String result=array.toString();
		writer.write(result);//.substring(1,result.length()-1)
		writer.close();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
