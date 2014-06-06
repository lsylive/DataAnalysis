package com.liusy.datapp.web.compare.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.liusy.core.util.CollectionMapConvert;
import com.liusy.core.util.Const;
import com.liusy.core.util.JsonUtil;
import com.liusy.core.util.Session;
import com.liusy.dataapplatform.base.exception.WebException;
import com.liusy.dataapplatform.base.util.ConvertUtil;
import com.liusy.dataapplatform.base.util.PageQuery;
import com.liusy.dataapplatform.base.util.StringUtil;
import com.liusy.datapp.dao.query.SynthesisTempSpaceDao;
import com.liusy.datapp.model.compare.CompareIndicator;
import com.liusy.datapp.model.compare.CompareInfo;
import com.liusy.datapp.model.compare.CompareTableRelation;
import com.liusy.datapp.model.datastandard.StandardCategory;
import com.liusy.datapp.model.datastandard.StandardIndicator;
import com.liusy.datapp.model.resource.ResourceColumn;
import com.liusy.datapp.model.resource.ResourceTable;
import com.liusy.datapp.service.compare.CompareIndicatorService;
import com.liusy.datapp.service.compare.CompareInfoService;
import com.liusy.datapp.service.compare.CompareTableRelationService;
import com.liusy.datapp.service.datastandard.StandardCategoryService;
import com.liusy.datapp.service.datastandard.StandardIndicatorService;
import com.liusy.datapp.service.pool.QueryMetaIndPool;
import com.liusy.datapp.service.pool.TableConfigPool;
import com.liusy.datapp.service.resource.ResourceTableService;
import com.liusy.datapp.service.resource.ResourceColumnService;
import com.liusy.datapp.util.poolobj.ColumnPoolObj;
import com.liusy.datapp.web.BaseAction;
import com.liusy.datapp.web.compare.form.CompareInfoForm;
import com.liusy.datapp.web.compare.form.CompareTableForm;

public class CompareTableAction extends BaseAction {

	@Override
	public ActionForward doExecute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String action = request.getParameter("action");
		if (action == null)
			action = LIST;
		if (log.isDebugEnabled())
			log.debug("action:" + action);
		ActionForward forward;
		CompareTableForm theForm = (CompareTableForm) form;
		try {
			if ("BASE".equalsIgnoreCase(action))
				forward = baseConfig(mapping, theForm, request, response);
			else if ("SAVEBASE".equalsIgnoreCase(action))
				forward = saveBaseConfig(mapping, theForm, request, response);
			// else if(SAVE.equalsIgnoreCase(action)) forward =
			// saveCompareInfo(mapping, theForm, request, response);
			// else if(EDIT.equalsIgnoreCase(action)) forward =
			// editCompareInfo(mapping, theForm, request, response);
			// else if(UPDATE.equalsIgnoreCase(action)) forward =
			// updateCompareInfo(mapping, theForm, request, response);
			// else if (DELETE.equalsIgnoreCase(action)) forward =
			// deleteCompareInfo(mapping, theForm, request, response);// ɾ��
			else if ("ADDPTABLE".equalsIgnoreCase(action))
				forward = searchPersonalResourceTable(mapping, theForm, request,
						response); // �򿪸��˿ռ�����Դ��ҳ��
			else if ("ADDRTABLE".equalsIgnoreCase(action))
				forward = getSubjectTableTree(mapping, theForm, request,
						response); // ����Դ����Դ��ҳ��
			else if ("ADDSFIELD".equalsIgnoreCase(action))
				forward = getSubjectTableFields(mapping, theForm, request,
						response, 1); // ���������Դ�Ա��ֶ�ҳ��
			else if ("ADDMFIELD".equalsIgnoreCase(action))
				forward = getSubjectTableFields(mapping, theForm, request,
						response, 0); // ����ӶԱ���Դ�Ա��ֶ�ҳ��
			else {
				request.setAttribute("err", new WebException("�Ҳ�����action������"
						+ action));
				forward = mapping.findForward(ERROR);// �Ҳ������ʵ�action
			}

		} catch (Exception e) {// ����ϵͳ���
			request.setAttribute("err", e);
			e.printStackTrace();
			forward = mapping.findForward(ERROR);
		}
		return forward;
	}

	public ActionForward baseConfig(ActionMapping mapping,
			CompareTableForm theForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'baseConfig' method");
		String id = theForm.getRecord().get("compId");

		CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
		CompareInfo compareInfo = (CompareInfo) compareInfoService
				.findCompareInfo(new Integer(id));
		ConvertUtil.objectToMap(theForm.getRecord(), compareInfo);

		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
		CompareTableRelationService compareTableRelationService = (CompareTableRelationService) getBean("compareTableRelationService");
		
		if (compareInfo.getDtId() != null) {
			theForm.getRecord().put("mainTabId",compareInfo.getDtId().toString());
			theForm.getRecord().put("mainTabName",resourceTableService.findResourceTable(compareInfo.getDtId()).getCnName());
		}
		if (compareInfo.getColList() != null
				&& compareInfo.getColList().length() > 0) {
			String fieldIdStr = compareInfo.getColList();
			theForm.getRecord().put("mainTabFieldIds", fieldIdStr+",");
			String[] fieldIds = fieldIdStr.split(",");
			StringBuffer fieldNameStr = new StringBuffer();
			for (int i = 0; i < fieldIds.length; i++) {
				ResourceColumn column  = resourceColumnService.findResourceColumn(
						Integer.parseInt(fieldIds[i]));
				if (column!=null)
				{
					String fieldName = resourceColumnService.findResourceColumn(
						Integer.parseInt(fieldIds[i])).getCnName();
					fieldNameStr.append(fieldName).append(",");
				}
			}
			theForm.getRecord().put("mainTabFieldNames",
					fieldNameStr.toString());
		}
		Collection<CompareTableRelation> relations = compareTableRelationService.findByFields(CompareTableRelation.PROP_COMP_ID, Integer.parseInt(id));
		StringBuffer slaveTabNames= new StringBuffer(), slaveTabIds = new StringBuffer();
		if (relations!=null)
		{
			String[][] slaveTabFieldIdArr = new String[relations.size()][];
			String[][] slaveTabFieldNameArr = new String[relations.size()][];
			int i= 0;
			for (Iterator<CompareTableRelation> iter = relations.iterator(); iter.hasNext();) {
				CompareTableRelation relation = iter.next();
				slaveTabIds.append(relation.getDtId()).append(",");
				ResourceTable rt = resourceTableService.findResourceTable(relation.getDtId());
				slaveTabNames.append(rt.getCnName()).append(",");
		
				String colList = relation.getColList();
				if (colList==null || colList.length()==0) 
				{
					i++;
					continue;
				}
				String[] colListArr = colList.split(",");
				slaveTabFieldIdArr[i] = new String[colListArr.length];
				slaveTabFieldNameArr[i] = new String[colListArr.length];
				for(int j =0; j<colListArr.length; j++)
				{
					slaveTabFieldIdArr[i][j] = colListArr[j];
					ResourceColumn column = resourceColumnService.findResourceColumn(Integer.parseInt(colListArr[j]));
					if (column!=null){
						slaveTabFieldNameArr[i][j] = column.getCnName();
					}
				}
				i++;
			}
			JSONArray jsonNames = JSONArray.fromObject(slaveTabFieldNameArr);
			JSONArray jsonIds = JSONArray.fromObject(slaveTabFieldIdArr);
			theForm.getRecord().put("slaveTabFieldNames", jsonNames.toString());
			theForm.getRecord().put("slaveTabFieldIds", jsonIds.toString());
		}
		theForm.getRecord().put("slaveTabIds", slaveTabIds.toString());
		theForm.getRecord().put("slaveTabNames", slaveTabNames.toString());
		
		

		/*
		 * CompareTableRelationService
		 * compareTableRelationService=(CompareTableRelationService)getBean("compareTableRelationService");
		 * List<CompareTableRelation> list=(List<CompareTableRelation>)
		 * compareTableRelationService.findByFields(CompareTableRelation.PROP_COMP_ID,
		 * Integer.valueOf(id)); CollectionMapConvert<CompareTableRelation>
		 * convert=new CollectionMapConvert<CompareTableRelation>(); Map<String,List<CompareTableRelation>>
		 * map=convert.convertToMapByParentKey(list,
		 * CompareTableRelation.PROP_IS_BASED);
		 * 
		 * TableConfigPool
		 * tableConfigPool=(TableConfigPool)getBean("tableConfigPool");
		 * if(map!=null && !map.isEmpty()){ List<CompareTableRelation>
		 * mainlist=map.get("1"); List<CompareTableRelation>
		 * slavelist=map.get("0"); if(mainlist!=null && !mainlist.isEmpty()){
		 * CompareTableRelation main=mainlist.get(0); Map<String,String>
		 * tableMap=tableConfigPool.getTableMap(String.valueOf(main.getDtId()));
		 * theForm.getRecord().put("mainTabName", tableMap.get("cName"));
		 * theForm.getRecord().put("mainTabId", tableMap.get("id")); }
		 * if(slavelist!=null && !slavelist.isEmpty()){ StringBuffer
		 * slavenamebuffer=new StringBuffer(); StringBuffer slaveidbuffer=new
		 * StringBuffer(); for(CompareTableRelation comp:slavelist){ Map<String,String>
		 * tableMap=tableConfigPool.getTableMap(String.valueOf(comp.getDtId()));
		 * slavenamebuffer.append(tableMap.get("cName")+",");
		 * slaveidbuffer.append(tableMap.get("id")+","); }
		 * theForm.getRecord().put("slaveTabNames",
		 * slavenamebuffer.substring(0,slavenamebuffer.length()-1));
		 * theForm.getRecord().put("slaveTabIds",
		 * slaveidbuffer.substring(0,slaveidbuffer.length()-1)); } }
		 * CompareIndicatorService
		 * compareIndicatorService=(CompareIndicatorService)getBean("compareIndicatorService");
		 * List<CompareIndicator> indList=(List<CompareIndicator>)
		 * compareIndicatorService.findByFields(CompareIndicator.PROP_COMP_ID,
		 * Integer.valueOf(id)); if(indList!=null && !indList.isEmpty()){
		 * StringBuffer indnamebuffer=new StringBuffer(); StringBuffer
		 * indidbuffer=new StringBuffer(); QueryMetaIndPool
		 * queryMetaIndPool=(QueryMetaIndPool)getBean("queryMetaIndPool");
		 * for(CompareIndicator ind:indList){ Map<String,String>indMap=queryMetaIndPool.getIndicatorPool(String.valueOf(ind.getIndId()));
		 * indnamebuffer.append(indMap.get("cnName")+",");
		 * indidbuffer.append(String.valueOf(ind.getIndId())+",");
		 * theForm.getRecord().put("indNames",
		 * indnamebuffer.substring(0,indnamebuffer.length()-1));
		 * theForm.getRecord().put("indIds",
		 * indidbuffer.substring(0,indidbuffer.length()-1)); } }
		 */
		return mapping.findForward("SHOWBASE");
	}

	public ActionForward saveBaseConfig(ActionMapping mapping,CompareTableForm theForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'saveBaseConfig' method");
		String id = theForm.getRecord().get("compId");
		String mainTabId = theForm.getRecord().get("mainTabId");
		String slaveTabIds = theForm.getRecord().get("slaveTabIds");
		String indIds = theForm.getRecord().get("indIds");
		String mainTabFieldIds = theForm.getRecord().get("mainTabFieldIds");
		String slaveTabFieldIds = theForm.getRecord().get("slaveTabFieldIds");

		try {
			if (mainTabId != null && mainTabId.length() > 0) {
				CompareInfoService compareInfoService = (CompareInfoService) getBean("compareInfoService");
				CompareInfo compareInfo = compareInfoService.findCompareInfo(Integer.parseInt(id));
				compareInfo.setDtId(Integer.parseInt(mainTabId));
				if (mainTabFieldIds != null && mainTabFieldIds.length() > 0) {
					compareInfo.setColList(mainTabFieldIds.substring(0,	mainTabFieldIds.length() - 1));
					compareInfoService.updateCompareInfo(compareInfo);
				}
			}

			CompareTableRelationService compareTableRelationService = (CompareTableRelationService) getBean("compareTableRelationService");
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");

			if (slaveTabIds != null && slaveTabIds.length() > 0) {
				String[] slaveTabFieldIdArr = slaveTabFieldIds.split(",");
				Collection<CompareTableRelation> colletionToDel = compareTableRelationService
						.findByFields(CompareTableRelation.PROP_COMP_ID, Integer.parseInt(id));
				Collection<Integer> idsToDel = new ArrayList<Integer>();
				for (Iterator<CompareTableRelation> iter = colletionToDel.iterator(); iter.hasNext();) {
					CompareTableRelation toDel = iter.next();
					idsToDel.add(toDel.getId());
				}
				Integer[] idsToDel1 = new Integer[idsToDel.size()];
				idsToDel.toArray(idsToDel1);
				compareTableRelationService.removeCompareTableRelations(idsToDel1);

				String[] slaveTabIdArr = slaveTabIds.split(",");
				int fieldCountPerTable = slaveTabFieldIdArr.length / slaveTabIdArr.length; 

				for(int i = 0; i < slaveTabIdArr.length; i ++)
				{
					CompareTableRelation newRelation = new CompareTableRelation();
					newRelation.setCompId(Integer.parseInt(id));
					newRelation.setDtId(Integer.parseInt(slaveTabIdArr[i]));
					StringBuffer slaveTabFieldIdStr = new StringBuffer();
					for (int j = 0; j < fieldCountPerTable ; j ++)
					{
						int fieldIndex = i*fieldCountPerTable + j;
						slaveTabFieldIdStr.append(slaveTabFieldIdArr[fieldIndex]).append(",");
					}
					newRelation.setColList(slaveTabFieldIdStr.substring(0,slaveTabFieldIdStr.length()-1));
					compareTableRelationService.createCompareTableRelation(newRelation);
					
				}

			}
			// compareTableRelationService.modifyCompareTableIndicator(Integer.valueOf(id),
			// filterDigitalFromString(mainTabId),
			// filterDigitalFromString(slaveTabIds.split(","), ","),
			// filterDigitalFromString(indIds.split(","),","));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnForward(mapping, request, RETURN_NORMAL);
	}

	public ActionForward getSubjectTableTree(ActionMapping mapping,
			CompareTableForm theForm, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'getSubjectTableTree' method");
		String id = theForm.getId();
		String mutilSelect = request.getParameter("mutilSelect");
		String type = request.getParameter("type");
		StandardCategoryService standardCategoryService = (StandardCategoryService) getBean("standardCategoryService");
		ActionForward forward = null;
		String tableId = request.getParameter("tableId");// Ҫ�����к��Ե����id

		try {
			List<StandardCategory> cataList = standardCategoryService
					.findCatagoryByCode();
			if (type.equalsIgnoreCase("TABLE")) {
				ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
				CompareTableRelationService compareTableRelationService = (CompareTableRelationService) getBean("compareTableRelationService");

				List<ResourceTable> tableList = resourceTableService
						.findAllTable();
				List<String> selTabIdList = null;
				if (id != null && "TRUE".equalsIgnoreCase(mutilSelect)) {
					PageQuery pageQuery = new PageQuery();
					Map<String, String> param = new HashMap<String, String>();
					param.put("compId", id);
					pageQuery.setParameters(param);
					pageQuery.setPageSize("0");
					pageQuery.setSelectParamId("GET_COMPARETABLE_GET");
					List<Map<String, String>> tabMapList = compareTableRelationService
							.queryCompareTableRelation(pageQuery)
							.getRecordSet();
					selTabIdList = StringUtil.getSingleColumnListByMap(
							tabMapList, "TABLEID");
				}
				List<Map<String, String>> paramList = getTreeConstructList(
						cataList, tableList, null, selTabIdList, null, tableId);
				request.setAttribute("treeList", paramList);
				forward = mapping.findForward("ADDRTABLE");
			} else if (type.equalsIgnoreCase("IND")) {
				StandardIndicatorService standardIndicatorService = (StandardIndicatorService) getBean("standardIndicatorService");
				List<StandardIndicator> indList = (List<StandardIndicator>) standardIndicatorService
						.findAllStandardIndicators();
				CompareIndicatorService compareIndicatorService = (CompareIndicatorService) getBean("compareIndicatorService");
				PageQuery pageQuery = new PageQuery();
				Map<String, String> param = new HashMap<String, String>();
				param.put("compId", id);
				pageQuery.setParameters(param);
				pageQuery.setPageSize("0");
				pageQuery.setSelectParamId("GET_COMPAREINDICATOR");
				List<Map<String, String>> indIdList = compareIndicatorService
						.queryCompareIndicator(pageQuery).getRecordSet();
				List<String> selIndList = new ArrayList<String>();
				selIndList = StringUtil.getSingleColumnListByMap(indIdList,
						"INDID");
				List<Map<String, String>> paramList = getTreeConstructList(
						cataList, null, indList, null, selIndList, null);
				request.setAttribute("treeList", paramList);
				forward = mapping.findForward("ADDIND");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	
	public ActionForward searchPersonalResourceTable(ActionMapping mapping, CompareTableForm personalTableForm, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (log.isDebugEnabled()) log.debug("Entering 'searchResource' method");
		ActionForward forward = null;
		try {
			ResourceTableService ResourceTableService = (ResourceTableService) getBean("resourceTableService");
			PageQuery pageQuery = personalTableForm.getQuery();
			pageQuery.setSelectParamId("GET_USERRESOURCETABLE_PAGE");
			Session session = (Session) request.getSession().getAttribute(Const.SESSION);
			if (session.getSpaceId() != null && !"".equals(session.getSpaceId())) {
				String queryString = getQueryString(pageQuery, session.getSpaceId());
				pageQuery.getParameters().put("queryString", queryString);
				// ִ�в�ѯ�������浽pagequery�У������һ��list<map>������map��ÿ���ֶ�����key��ֵ��value������4
				ResourceTableService.queryResourceTable(pageQuery);
				setPage(pageQuery);
				request.setAttribute("tables", pageQuery.getRecordSet());
				//initForm(personalTableForm, request);
			}
			forward = mapping.findForward("ADDPTABLE");
		}
		catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err", e);
			forward = mapping.findForward("error");
		}
		return forward;
	}

	public ActionForward getSubjectTableFields(ActionMapping mapping,
			CompareTableForm theForm, HttpServletRequest request,
			HttpServletResponse response, int flag) throws Exception {
		if (log.isDebugEnabled())
			log.debug("Entering 'getSubjectTableFields' method");
		String tableId = request.getParameter("tableId");
		String mainTabType = request.getParameter("mainTabType");
		ActionForward forward = null;

		try {
			
			ResourceColumnService resourceColumnService = (ResourceColumnService) getBean("resourceColumnService");
			List<ResourceColumn> columns = resourceColumnService
					.findColumnByTableIdSort(tableId);
			if (mainTabType.equals("0") && (columns==null || columns.size() == 0))
			{//��ȡ���˿ռ��ṹ
				columns = retrievePersonalTableColumns(request,tableId);
			}
			Map<Integer, String> resultMap = new HashMap<Integer, String>();
			for (Iterator<ResourceColumn> i = columns.iterator(); i.hasNext();) {
				ResourceColumn column = i.next();
				resultMap.put(column.getId(), column.getName());
			}
			request.setAttribute("fields", columns);
			request.setAttribute("isMajor", flag);
			request.setAttribute("tableId", tableId);
			request.setAttribute("cellId", "");
			if (flag == 0) {
				request.setAttribute("cellId", request.getParameter("cellId"));
			}
			forward = mapping.findForward("ADDFIELD");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forward;
	}
	
	private List<ResourceColumn> retrievePersonalTableColumns(HttpServletRequest request,String tableId)
	{
		List<ResourceColumn> columns = new LinkedList<ResourceColumn>();
		SynthesisTempSpaceDao synthesisTempSpaceDao = (SynthesisTempSpaceDao) getBean("synthesisTempSpaceDao");
		TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
		Map<String, String> tableMap = tableConfigPool.getTableMap(tableId);
		Collection<String> fieldNames = synthesisTempSpaceDao.getTableFieldNames(tableMap.get("name"));
		ResourceColumnService resourceColumnService	= (ResourceColumnService)getBean("resourceColumnService");
		for(String fieldName: fieldNames)
		{
			ResourceColumn column = new ResourceColumn();
			column.setTableId(Integer.parseInt(tableId));
			column.setCnName(fieldName);
			column.setName(fieldName);
			if (fieldName.equalsIgnoreCase("id"))
			{
				column.setIsPrimary("1");
			}
			column.setDataType(Const.META_TYPE_STRING);//�ַ�����
			resourceColumnService.createResourceColumn(column);
		}
		ResourceTableService resourceTableService = (ResourceTableService) getBean("resourceTableService");
		ResourceTable resourceTable = resourceTableService.findResourceTable(Integer.parseInt(tableId));
		NotifyPool(resourceTable); // ���»���
		
		columns = resourceColumnService.findColumnByTableIdSort(tableId);
		return columns;
	}
		
		private void NotifyPool(ResourceTable table) {
			TableConfigPool tableConfigPool = (TableConfigPool) getBean("tableConfigPool");
			tableConfigPool.clearTablePool(table.getId().toString(), table.getName());
		}

	private List<Map<String, String>> getTreeConstructList(
			List<StandardCategory> list1, List<ResourceTable> tableList,
			List<StandardIndicator> indList, List<String> selTabIdList,
			List<String> selIndIdList, String tableId) {
		List<Map<String, String>> retList = new ArrayList<Map<String, String>>();
		if (list1 != null) {
			boolean haschild = false;
			for (int i = 0; i < list1.size(); i++) {
				StandardCategory so = list1.get(i);
				Map<String, String> map = new HashMap<String, String>();
				// if(parentCataMap.get(String.valueOf(so.getId()))!=null ||
				// parentTableMap.get(String.valueOf(so.getId()))!=null)
				// haschild=true;
				String parentid = "0";
				if (so.getParentId() != null
						&& !"0".equals(so.getParentId().toString().trim()))
					parentid = "cata" + so.getParentId().toString();
				map.put("parentId", parentid);
				map.put("id", "cata" + String.valueOf(so.getId()));
				map.put("label", so.getName());
				map.put("action", "0");
				if (haschild) {
					map.put("imageOpen", "0");
					map.put("imageClose", "0");
					map.put("imageLeaf", "0");
				} else {
					map.put("imageOpen", "0");
					map.put("imageClose", "0");
					map.put("imageLeaf", "0");
				}
				map.put("hasChild", "1");
				map.put("isCheck", "0");
				retList.add(map);
			}
			if (tableList != null) {

				for (int j = 0; j < tableList.size(); j++) {
					ResourceTable so = tableList.get(j);
					if (String.valueOf(so.getId()).equals(tableId)) {// ������ѡ������id
						continue;
					}
					Map<String, String> map = new HashMap<String, String>();
					map.put("parentId", "cata" + so.getCategoryId().toString());
					map.put("id", String.valueOf(so.getId()));
					map.put("label", so.getCnName());
					map.put("action", "0");

					map.put("imageOpen", "0");
					map.put("imageClose", "0");
					map.put("imageLeaf", "1");
					map.put("hasChild", "0");
					if (selTabIdList != null
							&& selTabIdList.contains(so.getId().toString()))
						map.put("isCheck", "1");
					else
						map.put("isCheck", "0");
					retList.add(map);
				}
			} else if (indList != null) {
				for (int j = 0; j < indList.size(); j++) {
					StandardIndicator so = indList.get(j);
					Map<String, String> map = new HashMap<String, String>();
					map.put("parentId", "cata" + so.getCategoryId().toString());
					map.put("id", "ind" + String.valueOf(so.getId()));
					map.put("label", so.getCnName());
					map.put("action", "0");

					map.put("imageOpen", "0");
					map.put("imageClose", "0");
					map.put("imageLeaf", "1");
					map.put("hasChild", "0");
					if (selIndIdList != null
							&& selIndIdList.contains(so.getId().toString()))
						map.put("isCheck", "1");
					else
						map.put("isCheck", "0");
					retList.add(map);
				}
			}

		}
		return retList;
	}

	private String filterDigitalFromString(String[] inpstrArr, String splitstr) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < inpstrArr.length; i++) {
			String str = inpstrArr[i];
			for (int j = 0; j < str.length(); j++) {
				if (Character.isDigit(str.charAt(j))) {
					buffer.append(str.charAt(j));
				}
			}
			buffer.append(splitstr);
		}
		String retstr = "";
		if (buffer.length() > 0)
			retstr = buffer.substring(0, buffer.length() - splitstr.length());
		return retstr;
	}

	private String filterDigitalFromString(String inpstrArr) {
		StringBuffer buffer = new StringBuffer();

		String str = inpstrArr;
		for (int j = 0; j < str.length(); j++) {
			if (Character.isDigit(str.charAt(j))) {
				buffer.append(str.charAt(j));
			}
		}
		String retstr = "";
		if (buffer.length() > 0)
			retstr = buffer.toString();
		return retstr;
	}
	
	private String getQueryString(PageQuery pageQuery, String spaceId) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(" and t." + ResourceTable.COL_SPACEID + "=" + spaceId);
		String order = pageQuery.getOrder();
		if (order == null) {
			order = "";
		}
		String dire = pageQuery.getOrderDirection();
		if (dire == null) {
			dire = "";
		}
		if ("".equals(order) && "".equals(dire)) {
			pageQuery.setOrder("CN_NAME");
			pageQuery.setOrderDirection(PageQuery.ASC);
		}

		String cnName = pageQuery.getParameters().get("cnName");

		if (cnName != null && !"".equals(cnName)) {
			if (cnName.indexOf("%") > -1) {
				buffer.append(" and t.cn_name like '" + cnName + "'");
			}
			else {
				buffer.append(" and t.cn_name like '%" + cnName + "%'");
			}
		}
		return buffer.toString();
	}
	
	private void initForm(CompareTableForm resourceForm, HttpServletRequest request) {
		Session session = (Session) request.getSession().getAttribute(Const.SESSION);
		if (session.getSpaceId() != null && !"".equals(session.getSpaceId())) {
			request.setAttribute("hasspace", "1");
			resourceForm.getRecord().put("isSpaceTable", "1");
			resourceForm.getRecord().put("spaceId", session.getSpaceId());
		}
		else request.setAttribute("hasspace", "0");
	}

}
