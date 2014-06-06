
package com.liusy.analysis.template.model.node;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.StatisticInterfaceProperties;
import com.liusy.analysis.template.model.vo.*;
import java.util.*;

// Referenced classes of package com.liusy.analysis.template.model.node:
//			Node, INode

public class StatisticInterfaceNode extends  Node<StatisticInterfaceProperties>
implements INode
{

	private static final long serialVersionUID = 1L;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public StatisticInterfaceNode()
	{
		  this.name = "统计配置";
		properties = new StatisticInterfaceProperties();
	}

	  public List<Metadata> getMeta()
	  {
	    return ((StatisticInterfaceProperties)this.properties).getFields();
	  }

	  public List<Map<String, Object>> run(List<DataSet> inputDataSets)
	  {
	    if (inputDataSets != null) return ((DataSet)inputDataSets.get(0)).getResultSet();
	    return null;
	  }

	  public List<FieldConfig> getFieldConfigs()
	  {
	    return ((StatisticInterfaceProperties)this.properties).getFieldConfigs();
	  }

	public String getExtraConfigs()
	{
		List grathList = new ArrayList();
		grathList = ((StatisticInterfaceProperties)properties).getFieldConfigs2();
		String xIndexName = "";
		String xCnName = "";
		String grathTitle = "";
		String successiveAxisTitle = "";
		String succAxis = "";
		int start = 0;
		int end = 0;
		int stepLength = 0;
		List yIndexs = new ArrayList();
		List yCnNames = new ArrayList();
		GrathFieldConfig grathCfig;
		for (Iterator iterator = grathList.iterator(); iterator.hasNext();)
		{
			grathCfig = (GrathFieldConfig)iterator.next();
			if ("X".equals(grathCfig.getAxis()))
			{
				xIndexName = grathCfig.getName();
				xCnName = grathCfig.getCnName();
			}
			if ("Y".equals(grathCfig.getAxis()))
			{
				yIndexs.add(grathCfig.getName());
				yCnNames.add(grathCfig.getCnName());
			}
		}

		StaticGrathConfig grathCfig1 = ((StatisticInterfaceProperties)properties).getStaticGrathCfig();
		grathTitle = grathCfig1.getGrathTitle();
		successiveAxisTitle = grathCfig1.getAxisTitle();
		start = grathCfig1.getAxisStart();
		end = grathCfig1.getAxisEnd();
		stepLength = grathCfig1.getStepLength();
		succAxis = grathCfig1.getColumnName();
		String templateType = String.valueOf(((StatisticInterfaceProperties)properties).getStaticGrathCfig().getTemplateType());
		StringBuffer sb = new StringBuffer("");
		sb.append("<grathicCfig>\r\n");
		sb.append("    <templateType>").append(templateType).append("</templateType>\r\n");
		sb.append("    <grathTitle>").append(grathTitle).append("</grathTitle>\r\n");
		sb.append("    <xAxis>\r\n");
		if (!"".equals(succAxis))
		{
			sb.append("    <isSuccAxis>yes</isSuccAxis>\r\n");
			sb.append("    <name>").append(succAxis).append("</name>\r\n");
			sb.append("    <cnName>").append(successiveAxisTitle).append("</cnName>\r\n");
			sb.append("    <start>").append(String.valueOf(start)).append("</start>\r\n");
			sb.append("    <end>").append(String.valueOf(end)).append("</end>\r\n");
			sb.append("    <stepLength>").append(String.valueOf(stepLength)).append("</stepLength>\r\n");
		} else
		{
			sb.append("    <isSuccAxis>no</isSuccAxis>\r\n");
			sb.append("    <name>").append(xIndexName).append("</name>\r\n");
			sb.append("    <cnName>").append(xCnName).append("</cnName>\r\n");
		}
		sb.append("    </xAxis>\r\n");
		sb.append("    <yAxises>").append("\r\n");
		for (int i = 0; i < yIndexs.size(); i++)
		{
			sb.append("      <yAxis>").append("\r\n");
			sb.append("         <name>").append((String)yIndexs.get(i)).append("</name>\r\n");
			sb.append("         <cnName>").append((String)yCnNames.get(i)).append("</cnName>\r\n");
			sb.append("      </yAxis>").append("\r\n");
		}

		sb.append("    </yAxises>").append("\r\n");
		int recordCount = ((StatisticInterfaceProperties)properties).getStaticGrathCfig().getRecordCount();
		sb.append("\t\t<recordCount>").append(String.valueOf(recordCount)).append("</recordCount>").append("\r\n");
		sb.append("</grathicCfig>\r\n");
		return sb.toString();
	}
}
