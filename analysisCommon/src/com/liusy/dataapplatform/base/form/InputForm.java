


//   InputForm.java

package com.liusy.dataapplatform.base.form;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.liusy.dataapplatform.base.form:
//			BaseForm

public class InputForm extends BaseForm
{

	private static final long serialVersionUID = 1L;
	private Map record;

	public InputForm()
	{
	}

	public Map getRecord()
	{
		if (record == null)
			record = new HashMap();
		return record;
	}

	public void setRecord(Map record)
	{
		this.record = record;
	}
}
