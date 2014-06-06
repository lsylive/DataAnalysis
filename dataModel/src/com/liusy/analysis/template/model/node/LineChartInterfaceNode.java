
package com.liusy.analysis.template.model.node;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.LineChartInterfaceProperties;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysis.template.model.vo.*;
import java.util.*;

public class LineChartInterfaceNode extends Node<LineChartInterfaceProperties>
implements INode
{

	private static final long serialVersionUID = 1L;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public LineChartInterfaceNode()
	{
		this.name = "线型图配置";
		properties = new LineChartInterfaceProperties();
	}

	  public List<Metadata> getMeta()
	{
		return ((LineChartInterfaceProperties)properties).getFields();
	}

	public List<Map<String,Object>> run(List<DataSet> inputDataSets)
	{
		if (inputDataSets != null)
		{
			List<Map<String,Object>> tempResult = ((DataSet)inputDataSets.get(0)).getResultSet();
			return tempResult;
		} else
		{
			return null;
		}
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return ((LineChartInterfaceProperties)properties).getFieldConfigs();
	}

	public String getExtraConfigs()
	{
		List grathList = new ArrayList();
		grathList = ((LineChartInterfaceProperties)properties).getFieldConfigs2();
		String xIndexName = "";
		String xCnName = "";
		String grathTitle = "";
		String successiveAxisTitle = "";
		String succAxis = "";
		int dataType = 0;
		int start = 0;
		int end = 0;
		java.util.Date startDate = null;
		java.util.Date endDate = null;
		int stepLength = 0;
		String dateStepType = "";
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

		LinearGrathConfig grathCfig1 = ((LineChartInterfaceProperties)properties).getLinearGraphCfig();
		grathTitle = grathCfig1.getGrathTitle();
		successiveAxisTitle = grathCfig1.getAxisTitle();
		dataType = grathCfig1.getDataType();
		start = grathCfig1.getAxisStart();
		end = grathCfig1.getAxisEnd();
		startDate = grathCfig1.getAxisStartDate();
		endDate = grathCfig1.getAxisEndDate();
		stepLength = grathCfig1.getStepLength();
		dateStepType = grathCfig1.getDateAxisStepLengthType();
		succAxis = grathCfig1.getColumnName();
		String templateType = String.valueOf(((LineChartInterfaceProperties)properties).getLinearGraphCfig().getTemplateType());
		StringBuffer sb = new StringBuffer("");
		sb.append("<grathicCfig>\r\n");
		sb.append("    <templateType>").append(templateType).append("</templateType>\r\n");
		sb.append("    <grathTitle>").append(grathTitle).append("</grathTitle>\r\n");
		sb.append("    <xAxis>\r\n");
		if (!"".equals(succAxis))
		{
			sb.append("    <isSuccAxis>yes</isSuccAxis>\r\n");
			if (dataType == 1)
			{
				sb.append("    <start>").append(String.valueOf(start)).append("</start>\r\n");
				sb.append("    <end>").append(String.valueOf(end)).append("</end>\r\n");
			} else
			if (dataType == 2)
			{
				String tempStartDate = StringUtil.formateDate(startDate, "yyyy-MM-dd");
				String tempEndDate = StringUtil.formateDate(endDate, "yyyy-MM-dd");
				sb.append("    <start>").append(tempStartDate).append("</start>\r\n");
				sb.append("    <end>").append(tempEndDate).append("</end>\r\n");
			}
			sb.append("    <dataType>").append(String.valueOf(dataType)).append("</dataType>\r\n");
			sb.append("    <name>").append(succAxis).append("</name>\r\n");
			sb.append("    <cnName>").append(successiveAxisTitle).append("</cnName>\r\n");
			sb.append("    <stepLength>").append(String.valueOf(stepLength)).append("</stepLength>\r\n");
			sb.append("    <dateStepLength>").append(String.valueOf(dateStepType)).append("</dateStepLength>\r\n");
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
		int recordCount = ((LineChartInterfaceProperties)properties).getLinearGraphCfig().getRecordCount();
		sb.append("\t\t<recordCount>").append(String.valueOf(recordCount)).append("</recordCount>").append("\r\n");
		sb.append("</grathicCfig>\r\n");
		return sb.toString();
	}
}
