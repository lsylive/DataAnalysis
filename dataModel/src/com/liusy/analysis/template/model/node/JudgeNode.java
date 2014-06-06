
package com.liusy.analysis.template.model.node;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.dialogProperties.JudgeNodeProperties;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;

import java.util.*;
import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;


public class JudgeNode extends Node<JudgeNodeProperties>
implements INode
{

	private static final long serialVersionUID = 1L;

	protected Object clone()
		throws CloneNotSupportedException
	{
		return super.clone();
	}

	public String getExtraConfigs()
	{
		return null;
	}

	public JudgeNode()
	{
		this.name = "判断";
		properties = new JudgeNodeProperties();
	}

	public List<Metadata> getMeta()
	{
		if (getIncomingConnections() == null || getIncomingConnections().get(0) == null)
		{
			return null;
		} else
		{
			INode runNode = ((Connection)getIncomingConnections().get(0)).getSource();
			return runNode.getMeta();
		}
	}

	public boolean getRunEnable()
	{
		String expression = ((JudgeNodeProperties)properties).getExpression();
		Evaluator evaluator = new Evaluator();
		Map parameters = diagram.getParameterValues();
		String exp1 = setParameters(expression, parameters);
		if (exp1 == null || exp1.length() == 0)
			return false;
		String checkResult = "";
		try
		{
			checkResult = evaluator.evaluate(exp1);
		}
		catch (EvaluationException e)
		{
			e.printStackTrace();
		}
		return checkResult.equals("1.0");
	}

	public boolean isEnable()
	{
		return getRunEnable();
	}

	public List<Map<String, Object>> run(List<DataSet> inputDataSets)
	{
		if (runFlag)
			return resultSet;
		if (inputDataSets == null)
		{
			runFlag = true;
			resultSet = null;
			return resultSet;
		} else
		{
			resultSet = ((DataSet)inputDataSets.get(0)).getResultSet();
			return resultSet;
		}
	}

	public List<FieldConfig> getFieldConfigs()
	{
		return null;
	}

	private String setParameters(String exp, Map parameters)
	{
		String res = exp;
		for (Iterator it = parameters.keySet().iterator(); it.hasNext();)
		{
			String key = (String)it.next();
			res = res.replaceAll((new StringBuilder("\\{")).append(key).append("\\}").toString(), (String)parameters.get(key));
		}

		return res;
	}
}
