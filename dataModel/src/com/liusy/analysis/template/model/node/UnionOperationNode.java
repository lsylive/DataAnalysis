
package com.liusy.analysis.template.model.node;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.UnionOperationProperties;
import com.liusy.analysis.template.model.vo.*;
import java.util.*;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode

public class UnionOperationNode extends Node<UnionOperationProperties>
implements INode
{

	private static final long serialVersionUID = 1L;
	  private List<UnionOperationConfig> unionOperationConfigList;

	  private List<UnionOperationField> unionOperationFilterList ;

	  private Map<Integer, String[]> relateFieldById ;

	  private Diagram diagram;

	  private String outputStructNodename = "";

	  private List<Metadata> outputMetaList;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public UnionOperationNode()
	{
		unionOperationConfigList = new ArrayList<UnionOperationConfig>();
		unionOperationFilterList = new ArrayList<UnionOperationField>();
		relateFieldById = new HashMap<Integer, String[]>();
		diagram = new Diagram();
		outputStructNodename = "";
		outputMetaList = new ArrayList<Metadata> ();
	    this.name = "合集运算";
		properties = new UnionOperationProperties();
	}

	  private Metadata FetchMetaDataByNodeName(Map<String, String> relateMetaList, String nodeName) {
		Metadata m = new Metadata();
		List nodeList = ((UnionOperationProperties)properties).getNodeList();
		for (int i = 0; i < nodeList.size(); i++)
		{
			INode node = (INode)nodeList.get(i);
			if (node.getName().equals(nodeName))
			{
				String nodeIdKey = String.valueOf(node.getId());
				for (Iterator iterator = node.getMeta().iterator(); iterator.hasNext();)
				{
					Metadata data = (Metadata)iterator.next();
					if (data.getCnName().equals(relateMetaList.get(nodeIdKey)))
					{
						m = data;
						return m;
					}
				}

			}
		}

		return m;
	}

	  public List<Metadata> getMeta()
	{
		unionOperationConfigList = ((UnionOperationProperties)properties).getUnionOperationConfigList();
		unionOperationFilterList = ((UnionOperationProperties)properties).getUnionOperationFilterList();
		outputStructNodename = ((UnionOperationProperties)properties).getOutNodeStruct();
		List metaList = new ArrayList();
		int size = unionOperationConfigList.size();
		for (int i = 0; i < size; i++)
		{
			UnionOperationConfig uoc = (UnionOperationConfig)unionOperationConfigList.get(i);
			Map relateMetaList = uoc.getRelateMetaList();
			Metadata metadata = FetchMetaDataByNodeName(relateMetaList, outputStructNodename);
			metaList.add(metadata);
		}

		int filterSize = unionOperationFilterList.size();
		for (int j = 0; j < filterSize; j++)
		{
			Metadata userMeta = new Metadata();
			String cnName = ((UnionOperationField)unionOperationFilterList.get(j)).getCnName();
			String name = ((UnionOperationField)unionOperationFilterList.get(j)).getName();
			String dataType = ((UnionOperationField)unionOperationFilterList.get(j)).getDataType();
			userMeta.setCnName(cnName);
			userMeta.setName(name);
			userMeta.setDataType(dataType);
			metaList.add(userMeta);
		}

		return metaList;
	}

	  public List<Map<String, Object>> run(List<DataSet> inputDataSets)
	  {
		if (runFlag)
			return resultSet;
		outputMetaList = getMeta();
		List resultList = new ArrayList();
		String nodeId[] = ((UnionOperationProperties)properties).getNodeId();
		diagram = getDiagram();
		if (inputDataSets.size() != 2 || inputDataSets == null)
		{
			runFlag = true;
			resultSet = null;
			return resultSet;
		}
		getRelateFieldById(nodeId);
		int outputStructNodeId = getNodeIdByNodeName(((UnionOperationProperties)properties).getOutNodeStruct());
		List firstDataMap = new ArrayList();
		firstDataMap = getDataMapByNodeId(outputStructNodeId, inputDataSets);
		for (int i = 0; i < nodeId.length; i++)
			if (Integer.parseInt(nodeId[i]) != outputStructNodeId)
			{
				int compareNodeId = Integer.parseInt(nodeId[i]);
				List comparedDataMap = new ArrayList();
				comparedDataMap = getDataMapByNodeId(compareNodeId, inputDataSets);
				resultList = UnionResultSet(firstDataMap, outputStructNodeId, comparedDataMap, compareNodeId);
			}

		resultSet = resultList;
		runFlag = true;
		return resultSet;
	}

	  private Map<Integer, String[]> getRelateFieldById(String[] nodeId) {
		unionOperationConfigList = ((UnionOperationProperties)properties).getUnionOperationConfigList();
		relateFieldById = new HashMap();
		for (int i = 0; i < nodeId.length; i++)
		{
			String relateField[] = new String[unionOperationConfigList.size()];
			String relateFieldAlianName[] = new String[unionOperationConfigList.size()];
			for (int j = 0; j < unionOperationConfigList.size(); j++)
			{
				UnionOperationConfig config = (UnionOperationConfig)unionOperationConfigList.get(j);
				Map relateMetaList = config.getRelateMetaList();
				relateField[j] = (String)relateMetaList.get(nodeId[i]);
				List nodeList = ((UnionOperationProperties)properties).getNodeList();
				for (Iterator iterator = nodeList.iterator(); iterator.hasNext();)
				{
					INode node = (INode)iterator.next();
					if (node.getId() == Integer.parseInt(nodeId[i]))
					{
						for (Iterator iterator1 = node.getMeta().iterator(); iterator1.hasNext();)
						{
							Metadata data = (Metadata)iterator1.next();
							if (data.getCnName().equals(relateField[j]))
								relateFieldAlianName[j] = data.getName();
						}

					}
				}

			}

			relateFieldById.put(Integer.valueOf(Integer.parseInt(nodeId[i])), relateFieldAlianName);
		}

		return relateFieldById;
	}

	private int getNodeIdByNodeName(String nodeName)
	{
		int i = 0;
		List nodeList = ((UnionOperationProperties)properties).getNodeList();
		for (Iterator iterator = nodeList.iterator(); iterator.hasNext();)
		{
			INode node = (INode)iterator.next();
			if (node.getName().equals(nodeName))
			{
				i = node.getId();
				break;
			}
		}

		return i;
	}

	  private List<Map<String, Object>> getDataMapByNodeId(int nodeId, List<DataSet> inputDataSets) {
		List source = new ArrayList();
		List target = new ArrayList();
		if (inputDataSets != null)
		{
			for (Iterator iterator = inputDataSets.iterator(); iterator.hasNext();)
			{
				DataSet dataset = (DataSet)iterator.next();
				if (dataset.getId() == nodeId)
				{
					source = dataset.getResultSet();
					break;
				}
			}

		}
		target.addAll(source);
		return target;
	}

	  public List<Map<String, Object>> UnionResultSet(List<Map<String, Object>> firstDataMap, int outputStructNodeId, List<Map<String, Object>> comparedDataMap, int comparedNodeId) {
		List unionResultSet = new ArrayList();
		String comparedRow_relateFields[] = (String[])relateFieldById.get(Integer.valueOf(comparedNodeId));
		String firstRow_relateFields[] = (String[])relateFieldById.get(Integer.valueOf(outputStructNodeId));
		String outputStructNodeName = diagram.getNodeById(outputStructNodeId).getName();
		String comparedNodeName = diagram.getNodeById(comparedNodeId).getName();
		int relateFieldsLength = firstRow_relateFields.length;
		if (comparedDataMap.size() == 0)
		{
			for (int i = 0; i < firstDataMap.size(); i++)
			{
				Map map = new HashMap();
				for (int j = 0; j < relateFieldsLength; j++)
				{
					Object data = ((Map)firstDataMap.get(i)).get(firstRow_relateFields[j]);
					map.put(comparedRow_relateFields[j], data);
				}

				map = setOtherFieldAsEmpty(map, comparedNodeId);
				comparedDataMap.add(map);
			}

		}
		if (firstDataMap.size() == 0)
		{
			for (int i = 0; i < comparedDataMap.size(); i++)
			{
				Map map = new HashMap();
				for (int j = 0; j < relateFieldsLength; j++)
				{
					Object data = ((Map)comparedDataMap.get(i)).get(comparedRow_relateFields[j]);
					map.put(firstRow_relateFields[j], data);
				}

				map = setOtherFieldAsEmpty(map, outputStructNodeId);
				firstDataMap.add(map);
			}

		}
		for (int i = 0; i < firstDataMap.size(); i++)
		{
			Map f = (Map)firstDataMap.get(i);
			for (int j = 0; j < comparedDataMap.size(); j++)
			{
				boolean mark = true;
				Map c = (Map)comparedDataMap.get(j);
				for (int k = 0; k < relateFieldsLength; k++)
				{
					String firstRowData = f.get(firstRow_relateFields[k]) != null ? f.get(firstRow_relateFields[k]).toString().trim() : "";
					String comparedRowData = c.get(comparedRow_relateFields[k]) != null ? c.get(comparedRow_relateFields[k]).toString().trim() : "";
					if (!firstRowData.equals(comparedRowData))
					{
						mark = false;
						break;
					}
					if (k == relateFieldsLength - 1)
						mark = true;
				}

				if (!mark)
					continue;
				Map RowData = new HashMap();
				RowData = Operator(f, outputStructNodeName, c, comparedNodeName);
				RowData = removeField(RowData);
				unionResultSet.add(RowData);
				comparedDataMap.remove(c);
				j--;
				firstDataMap.remove(f);
				i--;
				break;
			}

		}

		for (int i = 0; i < firstDataMap.size(); i++)
		{
			Map first = (Map)firstDataMap.get(i);
			Map compare = new HashMap();
			for (int j = 0; j < relateFieldsLength; j++)
			{
				Object a = ((Map)firstDataMap.get(i)).get(firstRow_relateFields[j]);
				compare.put(firstRow_relateFields[j], a);
			}

			compare = setOtherFieldAsEmpty(compare, comparedNodeId);
			Map RowData = Operator(first, outputStructNodeName, compare, comparedNodeName);
			RowData = removeField(RowData);
			unionResultSet.add(RowData);
		}

		for (int i = 0; i < comparedDataMap.size(); i++)
		{
			Map first = (Map)comparedDataMap.get(i);
			Map compare = new HashMap();
			for (int j = 0; j < relateFieldsLength; j++)
			{
				Object a = ((Map)comparedDataMap.get(i)).get(comparedRow_relateFields[j]);
				compare.put(firstRow_relateFields[j], a);
			}

			compare = setOtherFieldAsEmpty(compare, outputStructNodeId);
			Map RowData = Operator(compare, outputStructNodeName, first, comparedNodeName);
			RowData = removeField(RowData);
			unionResultSet.add(RowData);
		}

		return unionResultSet;
	}

	  public Map<String, Object> setOtherFieldAsEmpty(Map<String, Object> RowData, int nodeid) {
		INode compardNode = getDiagram().getNodeById(nodeid);
		List metalist = compardNode.getMeta();
		ArrayList orgField = new ArrayList();
		for (int k = 0; k < metalist.size(); k++)
			orgField.add(((Metadata)metalist.get(k)).getName());

		String key;
		for (Iterator iterator = RowData.entrySet().iterator(); iterator.hasNext(); orgField.remove(key))
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			key = (String)entry.getKey();
		}

		for (int a = 0; a < orgField.size(); a++)
		{
			String fieldName = (String)orgField.get(a);
			String type = Fieldtype(fieldName, metalist);
			if (type.equals(Consts.DATATYPE_NUMERIC))
				RowData.put((String)orgField.get(a), Integer.valueOf(0));
			if (type.equals(Consts.DATATYPE_STRING) || type.equals(Consts.DATATYPE_DATE))
				RowData.put((String)orgField.get(a), "");
		}

		return RowData;
	}

	  public String Fieldtype(String fieldName, List<Metadata> metalist) {

		String type = "";
		for (Iterator iterator = metalist.iterator(); iterator.hasNext();)
		{
			Metadata data = (Metadata)iterator.next();
			if (data.getName().equals(fieldName))
				type = data.getDataType();
		}

		return type;
	}

	  public Map<String, Object> removeField(Map<String, Object> RowData) {
		ArrayList outputNodeFieldName = new ArrayList();
		ArrayList removeNodeFieldName = new ArrayList();
		for (int m = 0; m < outputMetaList.size(); m++)
		{
			Metadata field = (Metadata)outputMetaList.get(m);
			outputNodeFieldName.add(field.getName());
		}

		for (Iterator iterator = RowData.entrySet().iterator(); iterator.hasNext();)
		{
			java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
			String key = (String)entry.getKey();
			if (!outputNodeFieldName.contains(key))
				removeNodeFieldName.add(key);
		}

		for (int n = 0; n < removeNodeFieldName.size(); n++)
			RowData.remove(removeNodeFieldName.get(n));

		return RowData;
	}

	  public Map<String, Object> Operator(Map<String, Object> OperatorDataF, String OperatorDataF_nodeName, Map<String, Object> OperatorDataS, String OperatorDataS_nodeName) {
		Map result = new HashMap();
		List unionOperationFilterList = ((UnionOperationProperties)properties).getUnionOperationFilterList();
		Evaluator evaluator = new Evaluator();
		Map parameters = diagram.getParameterValues();
		setParameters(unionOperationFilterList, parameters);
		Object resultValue = new Object();
		UnionOperationField ExpressionConfig;
		for (Iterator iterator = unionOperationFilterList.iterator(); iterator.hasNext(); OperatorDataF.put(ExpressionConfig.getName(), resultValue))
		{
			ExpressionConfig = (UnionOperationField)iterator.next();
			String exp = ExpressionConfig.getExpression();
			if (exp.length() == 0 || exp == null)
			{
				if (ExpressionConfig.getDataType().equals(Consts.DATATYPE_STRING))
					resultValue = "";
				if (ExpressionConfig.getDataType().equals(Consts.DATATYPE_NUMERIC))
					resultValue = Integer.valueOf(0);
				OperatorDataF.put(ExpressionConfig.getName(), resultValue);
				result = OperatorDataF;
				return result;
			}
			if (ExpressionConfig.getDataType().equals(Consts.DATATYPE_STRING))
				resultValue = "";
			if (ExpressionConfig.getDataType().equals(Consts.DATATYPE_NUMERIC))
				resultValue = Integer.valueOf(0);
			exp = setVariables(OperatorDataF, OperatorDataF_nodeName, exp);
			exp = setVariables(OperatorDataS, OperatorDataS_nodeName, exp);
			try
			{
				resultValue = evaluator.evaluate(exp);
				if (ExpressionConfig.getDataType().equals(Consts.DATATYPE_NUMERIC))
				{
					double value = Double.parseDouble((String)resultValue);
					resultValue = Double.valueOf(value);
				}
			}
			catch (EvaluationException ee)
			{
				ee.printStackTrace();
				OperatorDataF.put(ExpressionConfig.getName(), resultValue);
				result = OperatorDataF;
				return result;
			}
		}

		result = OperatorDataF;
		return result;
	}

	  private String setVariables(Map<String, Object> OperatorDataF, String OperatorDataF_nodeName, String exp) {
		String reverseExp = "";
		if (exp == null || exp.length() == 0)
			return reverseExp;
		for (Iterator it = OperatorDataF.keySet().iterator(); it.hasNext();)
		{
			String key = (String)it.next();
			if (exp.indexOf((new StringBuilder("[")).append(OperatorDataF_nodeName).append(".").append(key).append("]").toString()) >= 0)
			{
				Object value = OperatorDataF.get(key);
				String v;
				if (value == null)
					if (value instanceof String)
					{
						exp = exp.replaceAll((new StringBuilder("\\[")).append(OperatorDataF_nodeName).append(".").append(key).append("\\]").toString(), "''");
					} else
					{
						v = "0";
						exp = exp.replaceAll((new StringBuilder("\\[")).append(OperatorDataF_nodeName).append(".").append(key).append("\\]").toString(), v);
					}
				if (value instanceof String)
					v = (new StringBuilder("'")).append(value.toString()).append("'").toString();
				else
					v = value.toString();
				exp = exp.replaceAll((new StringBuilder("\\[")).append(OperatorDataF_nodeName).append(".").append(key).append("\\]").toString(), v);
			}
		}

		reverseExp = exp;
		return reverseExp;
	}

	  private void setParameters(List<UnionOperationField> unionOperationFilterList, Map<String, String> parameters)
	  {
		UnionOperationField ExpressionConfig;
		String exp;
		for (Iterator iterator = unionOperationFilterList.iterator(); iterator.hasNext(); ExpressionConfig.setExpression(exp))
		{
			ExpressionConfig = (UnionOperationField)iterator.next();
			exp = ExpressionConfig.getExpression();
			for (Iterator it = parameters.keySet().iterator(); it.hasNext();)
			{
				String key = (String)it.next();
				exp = exp.replaceAll((new StringBuilder("\\{")).append(key).append("\\}").toString(), (String)parameters.get(key));
			}

		}

	}


	  public List<FieldConfig> getFieldConfigs() {
	    return null;
	  }

	  public String getExtraConfigs() {
	    return null;
	  }
}
