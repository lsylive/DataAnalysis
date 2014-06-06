package com.liusy.analysis.template.model.util;

import java.util.List;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.DiagramParameter;
import com.liusy.analysis.template.model.vo.DataField;
import com.liusy.analysis.template.model.vo.Filter;

public class DiagramParameterUtil {
	
	public static void FilterConvertDiagramParameter(Diagram diagram,List<Filter> filterList,List<DataField> dataFieldList)
	{
		  String name ="";
	      Filter f = null;
	     List<DiagramParameter> parameterList=diagram.getParameterList();
	      for (int i = 0; i < filterList.size(); i++) {
	    	f =  filterList.get(i);
	         name = f.getExpression().substring(f.getExpression().indexOf("{")+1, f.getExpression().indexOf("}"));
	    	 for (int j = 0; j < parameterList.size(); j++) {
	    	 if(parameterList.get(j).getName().equals(name))
	    		 {
	    			 parameterList.remove(j);
	    			 break;
	    		 }
			}
	        	 DiagramParameter  dp = new DiagramParameter();
	        	  for (int k = 0; k < dataFieldList.size(); k++) {
	        	    	DataField df = dataFieldList.get(k);
	        	    	if(df.getName().equals(f.getField()))
	        	    	{
	        	    		dp.setCnName(df.getCnName());
	        	    		dp.setDataType(df.getDataType());
	        	    		break;
	        	    	}
	        		}
	        	 dp.setName(name);
	        	 dp.setDescription(f.getField()+"  "+f.getOperator()+"  "+name+"  "+f.getCnName());
	        	 diagram.getParameterList().add(dp);
		}
	}

}
