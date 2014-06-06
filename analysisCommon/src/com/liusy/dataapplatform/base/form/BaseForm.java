


//   BaseForm.java

package com.liusy.dataapplatform.base.form;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import com.liusy.dataapplatform.code.Code;


public class BaseForm extends ActionForm
{

	private static final long serialVersionUID = 1L;
	private String action;
	private String status;
	private String errorMessage;
	private Map<String, List<Code>> codeSets;

	public BaseForm()
	{
		errorMessage = "";
	}

	public String getAction()
	{
		return action;
	}

	public void setAction(String action)
	{
		this.action = action;
	}

	public Map<String, List<Code>> getCodeSets()
	{
		if (codeSets == null)
			codeSets = new HashMap<String, List<Code>>();
		return codeSets;
	}

	public void setCodeSets(Map<String, List<Code>> codeSets)
	{
		this.codeSets = codeSets;
	}

	public String getErrorMessage()
	{
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage)
	{
		this.errorMessage = errorMessage;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
}
