
package com.liusy.analysis.template.model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.liusy.analysis.template.model.base.Element;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.dialogProperties.DiagramProperties;
import com.liusy.analysis.template.model.node.EndNode;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.IRunAlone;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysis.template.model.vo.CodeSet;
import com.liusy.analysis.template.model.vo.DataField;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.DataTable;
import com.liusy.analysis.template.model.vo.FieldConfig;

// Referenced classes of package com.liusy.analysis.template.model:
//			Consts, DiagramParameter

public class Diagram extends Element
{

	private static Logger log = Logger.getLogger(Diagram.class);
	private static final long serialVersionUID = 1L;
	public static String PROP_NODE = "NODE";
	public static String PROP_NAME = "NAME";
	public static String PROP_TYPE = "TYPE";
	public static String PROP_EXTENDPROPERTY = "EXTENDPROPERTY";
	protected int id;
	protected String name;
	protected int type;
	protected String description;
	  protected List<INode> nodes = new ArrayList<INode>();
	  protected List<DiagramParameter> parameterList = new ArrayList<DiagramParameter>();
	  protected int nodeCount = 0;
	  protected DiagramProperties diagramPropertiesBean = new DiagramProperties();
	  protected java.sql.Connection dbConnection;
	  protected Map<String, String> parameterValues;
	  protected List<CodeSet> codeSetList = new ArrayList<CodeSet>();
	  protected String visiable = Consts.YES;

	public Diagram()
	{
		id = 0;
		name = "";
		type = 0;
		description = "";
		nodeCount = 0;
		visiable = Consts.YES;
	}

	public void addNode(INode node)
	{
		nodeCount++;
		node.setId(getNodeCount());
		node.setDiagram(this);
		nodes.add(node);
		fireStructureChange(PROP_NODE, nodes);
	}

	public void recoverNode(INode node)
	{
		nodes.add(node);
		fireStructureChange(PROP_NODE, nodes);
	}

	public void removeNode(INode node)
	{
		nodes.remove(node);
		fireStructureChange(PROP_NODE, nodes);
	}

	public List<INode> getNodes()
	{
		return nodes;
	}

	public int getNodeCount()
	{
		return nodeCount;
	}

	public void setNodeCount(int nodeCount)
	{
		this.nodeCount = nodeCount;
	}

	public String getInterfaceType()
	{
		List<INode> nodes = getNodes();
		EndNode endNode = null;
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();)
		{
			INode node = (INode)iterator.next();
			if (node instanceof EndNode)
			{
				endNode = (EndNode)node;
				break;
			}
		}

		List<Connection>  cons = endNode.getIncomingConnections();
		if (cons == null || cons.size() == 0)
			return null;
		else
			return ((Connection)cons.get(0)).getSource().getClass().getSimpleName();
	}

	public List<Map<String, Object>> run()
	{
		List<INode> nodes = getNodes();
		EndNode endNode = null;
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();)
		{
			INode node = (INode)iterator.next();
			if (node instanceof EndNode)
			{
				endNode = (EndNode)node;
				break;
			}
		}

		if (endNode == null)
			return null;
		log.info((new StringBuilder(" Diagram '")).append(name).append("' run begin...").toString());
		List<Connection> cons = endNode.getIncomingConnections();
		if (cons == null || cons.size() == 0)
			return null;
		List<DataSet> returnDataSetList = new ArrayList<DataSet>();
		DataSet ds;
		for (Iterator iterator1 = cons.iterator(); iterator1.hasNext(); returnDataSetList.add(ds))
		{
			Connection con = (Connection)iterator1.next();
			INode source = con.getSource();
			List<Map<String,Object>> returnDataSet;
			if (source.getIncomingConnections() == null || source.getIncomingConnections().size() == 0)
			{
				log.info((new StringBuilder(" Node '")).append(source.getName()).append("' run begin...").toString());
				returnDataSet = source.run(null);
				log.info((new StringBuilder(" Node '")).append(source.getName()).append("' run end...").toString());
			} else
			{
				returnDataSet = run(source);
			}
			ds = new DataSet(source.getId(), returnDataSet);
		}

		log.info((new StringBuilder(" Diagram '")).append(name).append("' run end...").toString());
		return ((DataSet)returnDataSetList.get(0)).getResultSet();
	}

	private List<Map<String,Object>> run(INode node)
	{
		if (!node.isEnable())
			return null;
		if (node.isRunFlag())
			return node.getResultSet();
		List<Connection> cons = node.getIncomingConnections();
		if (cons == null || cons.size() == 0)
		{
			log.info((new StringBuilder(" Node '")).append(node.getName()).append("' run begin...").toString());
			List<Map<String,Object>> result = node.run(null);
			log.info((new StringBuilder(" Node '")).append(node.getName()).append("' run end...").toString());
			return result;
		}
		List<DataSet> returnDataSetList = new ArrayList<DataSet>();
		DataSet ds;
		for (Iterator iterator = cons.iterator(); iterator.hasNext(); returnDataSetList.add(ds))
		{
			Connection con = (Connection)iterator.next();
			INode source = con.getSource();
			List<Map<String,Object>> returnDataSet;
			if (source.getIncomingConnections() == null || source.getIncomingConnections().size() == 0)
			{
				log.info((new StringBuilder(" Node '")).append(source.getName()).append("' run begin...").toString());
				returnDataSet = source.run(null);
				log.info((new StringBuilder(" Node '")).append(source.getName()).append("' run end...").toString());
			} else
			{
				returnDataSet = run(source);
			}
			ds = new DataSet(source.getId(), returnDataSet);
		}

		log.info((new StringBuilder(" Node '")).append(node.getName()).append("' run begin...").toString());
		List<Map<String,Object>> result = node.run(returnDataSetList);
		log.info((new StringBuilder(" Node '")).append(node.getName()).append("' run end...").toString());
		return result;
	}

	private String genMessage(String msg)
	{
		StringBuffer xmlStr = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
		xmlStr.append("<error>\r\n");
		xmlStr.append("   <message>\r\n      ").append(msg).append("\r\n   </message>\r\n</error>\r\n");
		return xmlStr.toString();
	}

	public String runNodeXml(String id, String pkId)
	{
		INode node = getNodeById(Integer.parseInt(id));
		if (node == null)
			return genMessage("找不到该查询，请联系系统管理员。");
		List<Connection> cons = node.getIncomingConnections();
		if (cons == null || cons.size() == 0)
		{
			StringBuffer xmlStr = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
			if (pkId == null || pkId.length() == 0)
			{
				List<Metadata> metaList = node.getMeta();
				if (metaList == null || metaList.size() == 0)
					return genMessage("该查询配置错误，请联系系统管理员。");
				if (node instanceof IRunAlone)
				{
					IRunAlone dataNode = (IRunAlone)node;
					xmlStr.append((new StringBuilder("<pkname>")).append(dataNode.getDataTable().getPkName()).append("</pkname>\r\n").toString());
					xmlStr.append((new StringBuilder("<tablename>")).append(dataNode.getDataTable().getName()).append("</tablename>\r\n").toString());
				}
				genNodeMetaXml(xmlStr, node);
				log.info((new StringBuilder(" Node '")).append(node.getName()).append("' run begin...").toString());
				List<Map<String,Object>> result = node.run(null);
				log.info((new StringBuilder(" Node '")).append(node.getName()).append("' run end...").toString());
				genDataXml(xmlStr, result);
				return xmlStr.toString();
			}
			if (node instanceof IRunAlone)
			{
				IRunAlone dataNode = (IRunAlone)node;
				log.info((new StringBuilder(" Node '")).append(node.getName()).append("' run begin...").toString());
				genRecordXML(xmlStr, dataNode.getDataTable(pkId));
				log.info((new StringBuilder(" Node '")).append(node.getName()).append("' run end...").toString());
				return xmlStr.toString();
			} else
			{
				 return genMessage("该查询配置错误，请联系系统管理员。");
			}
		} else
		{
			  return genMessage("该查询配置错误，请联系系统管理员。");
		}
	}
   

	private void genRecordXML(StringBuffer xmlStr, DataTable dt)
	{
		if (dt.getRecord() == null)
			return;
		String fName = "";
		xmlStr.append("<displaycol>\r\n");
		List<DataField>  columnCfgList = dt.getFields();
		if (columnCfgList != null && !columnCfgList.isEmpty())
		{
			for (int i = 0; i < columnCfgList.size(); i++)
			{
				DataField columnCfg = (DataField)columnCfgList.get(i);
				if (columnCfg.getName().equalsIgnoreCase(dt.getPkName()))
				{
					DataField col = (DataField)columnCfg.clone();
					col.setName("ID");
					xmlStr.append("   <dcol>\r\n").append(col.toXML()).append("   </dcol>\r\n");
				} else
				{
					xmlStr.append("   <dcol>\r\n").append(columnCfg.toXML()).append("   </dcol>\r\n");
				}
				if (columnCfg.getDataType() == Consts.DATATYPE_IMAGE)
					fName = columnCfg.getName();
			}

		} else
		{
			xmlStr.append("   <dcol>\r\n      <name>ID</name>\r\n   </dcol>\r\n");
		}
		xmlStr.append("</displaycol>\r\n");
		xmlStr.append("<records>\r\n   <record>\r\n");
		String colName;
		for (Iterator iter = dt.getRecord().keySet().iterator(); iter.hasNext(); xmlStr.append("</").append(colName).append(">\r\n"))
		{
			colName = (String)iter.next();
			Object value = dt.getRecord().get(colName);
			String strValue;
			if (value == null)
				strValue = "";
			else
				strValue = value.toString();
			if (colName.equalsIgnoreCase(dt.getPkName()))
				colName = "ID";
			xmlStr.append("      <").append(colName).append(">");
			if (colName.equals(fName))
				xmlStr.append(StringUtil.getFileNames(strValue));
			else
				xmlStr.append(strValue.trim());
		}

		xmlStr.append("   </record>\r\n</records>\r\n");
	}

	private void genDataXml(StringBuffer xmlStr, List result)
	{
		xmlStr.append("<records>\r\n");
		for (Iterator iterator = result.iterator(); iterator.hasNext(); xmlStr.append("   </record>\r\n"))
		{
			Map map = (Map)iterator.next();
			xmlStr.append("   <record>\r\n");
			String colName;
			String strValue;
			for (Iterator iter = map.keySet().iterator(); iter.hasNext(); xmlStr.append("      <").append(colName).append(">").append(strValue.trim()).append("</").append(colName).append(">\r\n"))
			{
				colName = (String)iter.next();
				Object value = map.get(colName);
				if (value == null)
					strValue = "";
				else
					strValue = value.toString();
			}

		}

		xmlStr.append("</records>\r\n");
	}

	private void genNodeMetaXml(StringBuffer xmlStr, INode source)
	{
		List<DiagramParameter> list = new ArrayList<DiagramParameter>();
		Map<String, Boolean> betweenPrameterMap = getBetweenPrameterMap(list);
		xmlStr.append((new StringBuilder("<id>")).append(source.getId()).append("</id>\r\n").toString());
		xmlStr.append((new StringBuilder("<name>")).append(source.getName()).append("</name>\r\n").toString());
		xmlStr.append((new StringBuilder("<description>")).append(source.getDescription() != null && !"".equals(source.getDescription()) ? source.getDescription() : " ").append("</description>\r\n").toString());
		xmlStr.append("<datacollection>\r\n");
		xmlStr.append("<parameters>\r\n");
		for (int i = 0; i < list.size(); i++)
		{
			DiagramParameter pram = (DiagramParameter)list.get(i);
			xmlStr.append("   <parameter>\r\n").append(pram.toXML());
			if (betweenPrameterMap.containsKey(pram.getName()))
				xmlStr.append("      <isbetween>true</isbetween>\r\n");
			xmlStr.append("   </parameter>\r\n");
		}

		xmlStr.append("</parameters>\r\n");
		xmlStr.append("<displaycol>\r\n");
		List<Metadata> columnCfgList = source.getMeta();
		List<FieldConfig> fieldCfgList = source.getFieldConfigs();
		if (fieldCfgList != null)
		{
			if (!fieldCfgList.isEmpty())
			{
				for (int i = 0; i < fieldCfgList.size(); i++)
				{
					FieldConfig columnCfg = (FieldConfig)fieldCfgList.get(i);
					xmlStr.append("   <dcol>\r\n").append(columnCfg.toXML()).append("   </dcol>\r\n");
				}

			} else
			{
				xmlStr.append("   <dcol>\r\n      <name>ID</name>\r\n   </dcol>\r\n");
			}
		} else
		if (columnCfgList != null && !columnCfgList.isEmpty())
		{
			for (int i = 0; i < columnCfgList.size(); i++)
			{
				Metadata columnCfg = (Metadata)columnCfgList.get(i);
				xmlStr.append("   <dcol>\r\n").append(columnCfg.toXML()).append("   </dcol>\r\n");
			}

		} else
		{
			xmlStr.append("   <dcol>\r\n      <name>ID</name>\r\n   </dcol>\r\n");
		}
		xmlStr.append("</displaycol>\r\n");
		if (source.getExtraConfigs() != null)
			xmlStr.append((new StringBuilder(String.valueOf(source.getExtraConfigs()))).append("\r\n").toString());
		xmlStr.append("<codesets>\r\n");
		if (codeSetList != null)
		{
			CodeSet cs;
			for (Iterator iterator = codeSetList.iterator(); iterator.hasNext(); xmlStr.append(cs.toXML()))
				cs = (CodeSet)iterator.next();

		}
		xmlStr.append("</codesets>\r\n");
		xmlStr.append("</datacollection>\r\n");
	}

	public InputStream getAsStream()
		throws IOException
	{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream out = new ObjectOutputStream(os);
		out.writeObject(this);
		out.close();
		InputStream istream = new ByteArrayInputStream(os.toByteArray());
		os.close();
		return istream;
	}

	public static Diagram makeFromStream(InputStream istream)
		throws IOException, ClassNotFoundException
	{
		ObjectInputStream ois = new ObjectInputStream(istream);
		Diagram diagram = (Diagram)ois.readObject();
		ois.close();
		return diagram;
	}

	public DiagramProperties getDiagramPropertiesBean()
	{
		return diagramPropertiesBean;
	}

	public void setDiagramPropertiesBean(DiagramProperties diagramPropertiesBean)
	{
		if (this.diagramPropertiesBean.equals(diagramPropertiesBean))
		{
			return;
		} else
		{
			this.diagramPropertiesBean = diagramPropertiesBean;
			return;
		}
	}

	  public List<DiagramParameter> getParameterList() {
		    return this.parameterList;
		  }

		  public void setParameterList(List<DiagramParameter> pramList) {
		    this.parameterList = pramList;
		  }

	public Map<String, Boolean> getBetweenPrameterMap(List<DiagramParameter>  list)
	{
		Map<String, Boolean> colunmMap = new HashMap<String, Boolean>();
		for (int i = 0; i < parameterList.size(); i++)
		{
			DiagramParameter p1 = (DiagramParameter)parameterList.get(i);
			String sName = p1.getName();
			if (sName.endsWith("_s"))
			{
				for (int j = 0; j < parameterList.size(); j++)
				{
					DiagramParameter p2 = (DiagramParameter)parameterList.get(j);
					if (!sName.equals(p2.getName()) && sName.substring(0, sName.length() - 2).equals(p2.getName().substring(0, p2.getName().length() - 2)))
					{
						DiagramParameter tmp = null;
						try
						{
							tmp = (DiagramParameter)p1.clone();
							tmp.setName(sName.substring(0, sName.length() - 2));
							if (tmp.getValue() != null && p2.getValue() != null)
								tmp.setValue((new StringBuilder(String.valueOf(tmp.getValue()))).append(";").append(p2.getValue()).toString());
							colunmMap.put(sName.substring(0, sName.length() - 2), Boolean.valueOf(true));
						}
						catch (Exception e)
						{
							e.printStackTrace();
						}
						if (tmp != null)
							list.add(tmp);
					}
				}

			} else
			if (!p1.getName().endsWith("_e"))
				list.add(p1);
		}

		return colunmMap;
	}

	public INode getNodeById(int id)
	{
		for (int i = 0; i < nodes.size(); i++)
		{
			INode node = (INode)nodes.get(i);
			if (node.getId() == id)
				return node;
		}

		return null;
	}

	public List<Metadata> getMetaData()
	{
		List<Metadata> metaList = new ArrayList<Metadata>();
		List<INode> nodes = getNodes();
		INode endNode = null;
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();)
		{
			INode node = (INode)iterator.next();
			if (node instanceof EndNode)
			{
				endNode = node;
				break;
			}
		}

		List<Connection> cons = endNode.getIncomingConnections();
		INode source = ((Connection)cons.get(0)).getSource();
		List<FieldConfig> columnCfgList = source.getFieldConfigs();
		Metadata meta;
		for (Iterator iterator1 = columnCfgList.iterator(); iterator1.hasNext(); metaList.add(meta))
		{
			FieldConfig fc = (FieldConfig)iterator1.next();
			meta = new Metadata();
			meta.setCnName(fc.getCnName());
			meta.setName(fc.getName());
			meta.setDataType(fc.getDataType());
		}

		return metaList;
	}

	public String getMetaDataXml()
	{
		List<INode> nodes = getNodes();
		INode endNode = null;
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();)
		{
			INode node = (INode)iterator.next();
			if (node instanceof EndNode)
			{
				endNode = node;
				break;
			}
		}

		List<Connection> cons = endNode.getIncomingConnections();
		INode source = ((Connection)cons.get(0)).getSource();
		List<DiagramParameter> list = new ArrayList<DiagramParameter>();
		Map<String, Boolean> betweenPrameterMap = getBetweenPrameterMap(list);
		StringBuffer xmlStr = new StringBuffer("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
		xmlStr.append((new StringBuilder("<id>")).append(id).append("</id>\r\n").toString());
		xmlStr.append((new StringBuilder("<name>")).append(name).append("</name>\r\n").toString());
		xmlStr.append((new StringBuilder("<typeName>")).append(Consts.TEMP_LABEL[type]).append("</typeName>\r\n").toString());
		xmlStr.append((new StringBuilder("<description>")).append(description != null && !"".equals(description) ? description : " ").append("</description>\r\n").toString());
		xmlStr.append("<datacollection>\r\n");
		xmlStr.append("<parameters>\r\n");
		for (int i = 0; i < list.size(); i++)
		{
			DiagramParameter pram = (DiagramParameter)list.get(i);
			xmlStr.append("   <parameter>\r\n").append(pram.toXML());
			if (betweenPrameterMap.containsKey(pram.getName()))
				xmlStr.append("      <isbetween>true</isbetween>\r\n");
			xmlStr.append("   </parameter>\r\n");
		}

		xmlStr.append("</parameters>\r\n");
		xmlStr.append("<displaycol>\r\n");
		List<FieldConfig> columnCfgList = source.getFieldConfigs();
		if (columnCfgList != null && !columnCfgList.isEmpty())
		{
			for (int i = 0; i < columnCfgList.size(); i++)
			{
				FieldConfig columnCfg = (FieldConfig)columnCfgList.get(i);
				xmlStr.append("   <dcol>\r\n").append(columnCfg.toXML()).append("   </dcol>\r\n");
			}

		} else
		{
			xmlStr.append("   <dcol>\r\n      <name>ID</name>\r\n   </dcol>\r\n");
		}
		xmlStr.append("</displaycol>\r\n");
		if (source.getExtraConfigs() != null)
			xmlStr.append((new StringBuilder(String.valueOf(source.getExtraConfigs()))).append("\r\n").toString());
		xmlStr.append("<codesets>\r\n");
		if (codeSetList != null)
		{
			CodeSet cs;
			for (Iterator iterator1 = codeSetList.iterator(); iterator1.hasNext(); xmlStr.append(cs.toXML()))
				cs = (CodeSet)iterator1.next();

		}
		xmlStr.append("</codesets>\r\n");
		xmlStr.append("</datacollection>\r\n");
		return xmlStr.toString();
	}

	public String getResultXml()
	{
		List<Map<String, Object>> result = run();
		List<INode> nodes = getNodes();
		EndNode endNode = null;
		for (Iterator iterator = nodes.iterator(); iterator.hasNext();)
		{
			INode node = (INode)iterator.next();
			if (node instanceof EndNode)
			{
				endNode = (EndNode)node;
				break;
			}
		}

		List displayList = new ArrayList();
		List<Connection> cons = endNode.getIncomingConnections();
		INode source = ((Connection)cons.get(0)).getSource();
		List columnCfigList = source.getFieldConfigs();
		for (Iterator iterator1 = columnCfigList.iterator(); iterator1.hasNext();)
		{
			FieldConfig colCfig = (FieldConfig)iterator1.next();
			if ("1".equals(colCfig.getVisible()))
				displayList.add(colCfig.getName());
		}

		StringBuffer xmlStr = new StringBuffer("");
		xmlStr.append("<records>\r\n");
		for (Iterator iterator2 = result.iterator(); iterator2.hasNext(); xmlStr.append("   </record>\r\n"))
		{
			Map map = (Map)iterator2.next();
			xmlStr.append("   <record>\r\n");
			String colName;
			String strValue;
			for (Iterator iter = map.keySet().iterator(); iter.hasNext(); xmlStr.append("      <").append(colName).append(">").append(strValue.trim()).append("</").append(colName).append(">\r\n"))
			{
				colName = (String)iter.next();
				Object value = map.get(colName);
				if (value == null)
					strValue = "";
				else
					strValue = value.toString();
			}

		}

		xmlStr.append("</records>\r\n");
		return xmlStr.toString();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public java.sql.Connection getDbConnection()
	{
		return dbConnection;
	}

	public void setDbConnection(java.sql.Connection dbConnection)
	{
		this.dbConnection = dbConnection;
	}

	public Map<String, String>  getParameterValues()
	{
		return parameterValues;
	}

	public void setParameterValues(Map<String, String>  parameterValues)
	{
		this.parameterValues = parameterValues;
	}

	public List<CodeSet>  getCodeSetList()
	{
		return codeSetList;
	}

	public void setCodeSetList(List<CodeSet> codeSetList)
	{
		this.codeSetList = codeSetList;
	}

	public String getVisiable()
	{
		return visiable;
	}

	public void setVisiable(String visiable)
	{
		this.visiable = visiable;
	}

}
