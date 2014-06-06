



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.ParameterType;
import com.liusy.datapp.xbeans.analysis.ParametersType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class ParametersTypeImpl extends XmlComplexContentImpl
	implements ParametersType
{

	private static final QName PARAMETER$0 = new QName("", "parameter");

	public ParametersTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public ParameterType[] getParameterArray()
	{
		Object obj = monitor();
		
		ParameterType aparametertype[];
		check_orphaned();
		ArrayList arraylist = new ArrayList();
		get_store().find_all_element_users(PARAMETER$0, arraylist);
		aparametertype = new ParameterType[arraylist.size()];
		arraylist.toArray(aparametertype);
		return aparametertype;
	}

	public ParameterType getParameterArray(int i)
	{
		Object obj = monitor();
		
		ParameterType parametertype;
		check_orphaned();
		parametertype = null;
		parametertype = (ParameterType)get_store().find_element_user(PARAMETER$0, i);
		if (parametertype == null)
			throw new IndexOutOfBoundsException();
		return parametertype;
	}

	public int sizeOfParameterArray()
	{
		Object obj = monitor();
		
		check_orphaned();
		return get_store().count_elements(PARAMETER$0);
	}

	public void setParameterArray(ParameterType aparametertype[])
	{
		synchronized (monitor())
		{
			check_orphaned();
			arraySetterHelper(aparametertype, PARAMETER$0);
		}
	}

	public void setParameterArray(int i, ParameterType parametertype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			ParameterType parametertype1 = null;
			parametertype1 = (ParameterType)get_store().find_element_user(PARAMETER$0, i);
			if (parametertype1 == null)
				throw new IndexOutOfBoundsException();
			parametertype1.set(parametertype);
		}
	}

	public ParameterType insertNewParameter(int i)
	{
		Object obj = monitor();
		
		ParameterType parametertype;
		check_orphaned();
		parametertype = null;
		parametertype = (ParameterType)get_store().insert_element_user(PARAMETER$0, i);
		return parametertype;
	}

	public ParameterType addNewParameter()
	{
		Object obj = monitor();
		
		ParameterType parametertype;
		check_orphaned();
		parametertype = null;
		parametertype = (ParameterType)get_store().add_element_user(PARAMETER$0);
		return parametertype;
	}

	public void removeParameter(int i)
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_element(PARAMETER$0, i);
		}
	}

}
