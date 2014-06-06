package com.liusy.datapp.web.dynamicquery.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.velocity.texen.util.PropertiesUtil;

import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.StringUtil;
import com.liusy.datapp.service.query.SearchEngineService;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.dynamicquery.form.SearchEngineForm;
import com.liusy.datapp.web.system.org.form.SysOrgForm;


public class SearchEngineAction extends BaseAction{

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		if (action == null) action = "INDEX";
		if (log.isDebugEnabled()) log.debug("action:" + action);
		ActionForward forward;
		SearchEngineForm searchEngineForm = (SearchEngineForm)form;
		try {
			if ("INDEX".equalsIgnoreCase(action)) forward = toIndexPage(mapping, searchEngineForm, request, response); // 打开查询列表页面
			else if ("RESULT".equalsIgnoreCase(action)) forward = toResultPage(mapping, searchEngineForm, request, response); // 打开树
			else if ("OPENFILE".equalsIgnoreCase(action)) forward = openFile(mapping, searchEngineForm, request, response); // 打开树
			else {
				request.setAttribute("err", new WebException("找不到该action方法：" + action));
				forward = mapping.findForward(ERROR);// 找不到合适的action
			}
		}
		catch (Exception e) {// 其他系统出错
			request.setAttribute("err", e);
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}
	
	public ActionForward toIndexPage(ActionMapping mapping, SearchEngineForm searchEngineForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'toResultPage' method");

		try {
			return mapping.findForward("INDEX");
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}
	
	public ActionForward toResultPage(ActionMapping mapping, SearchEngineForm searchEngineForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'toResultPage' method");

		try {
			String queryStr = searchEngineForm.getRecord().get("queryStr");
			if(queryStr==null)queryStr = "";
			String maxresults = searchEngineForm.getRecord().get("maxresults");
			if (maxresults==null)maxresults = "";
			if(!StringUtil.isNumberic(maxresults))maxresults = "100";
			
			SearchEngineService searchEngineService = (SearchEngineService)getBean("searchEngineService");
			List<Map<String, String>> list = searchEngineService.search(queryStr, Integer.parseInt(maxresults));
			if(list==null)list = new ArrayList<Map<String,String>>();
			
			request.setAttribute("results", list);
			return mapping.findForward("RESULT");
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}
	
	private ActionForward openFile(ActionMapping mapping, SearchEngineForm searchEngineForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'toResultPage' method");
		
		try {
//			String path = searchEngineForm.getRecord().get("openFilePath");
			String path = request.getParameter("record(openFilePath)");
			path = URLDecoder.decode(path, "utf-8");
			path = URLDecoder.decode(path, "utf-8");
			File file = new File(path);
			response.setContentType("application/x-download;charset=gbk");
			String filename = file.getName();
			filename = new String(filename.getBytes(),"iso8859_1");
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			ServletOutputStream out = response.getOutputStream();
			if (file.exists()) {
				FileInputStream fin = new FileInputStream(new File(path));
				byte[] data = new byte[8];

				boolean flag = true;
				
				while (flag) {
					if (fin.read(data)==-1) {
						flag = false;
					}
					
					out.write(data);
					out.flush();
					data = new byte[8];
				}
				
				out.close();
				fin.close();
			}
		
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			return mapping.findForward(ERROR);
		}
	}
}
