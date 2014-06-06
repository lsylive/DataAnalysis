package com.liusy.datapp.web.query.form;


import java.util.List;


import com.liusy.dataapplatform.base.form.InputForm;
import com.liusy.dataapplatform.base.util.DynamicList;
import com.liusy.datapp.util.ColumnCfgPageVO;

public class ColumnSynthesisConfigForm extends InputForm{
	private String columnId;
	private String tableId;
	private List<ColumnCfgPageVO> parameters=null;
	
	public ColumnSynthesisConfigForm(){
		parameters=new DynamicList(ColumnCfgPageVO.class);
		
	}
	public String getColumnId() {
		return columnId;
	}

	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public List getParameters() {
		return parameters;
	}

	public void setParameters(List parameters) {
		this.parameters=parameters;
	}
	

}
