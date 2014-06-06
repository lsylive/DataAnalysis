



package com.liusy.datapp.xbeans.impl;

import com.liusy.datapp.xbeans.PARAMSType;
import com.liusy.datapp.xbeans.PARAMType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class PARAMSTypeImpl extends XmlComplexContentImpl
	implements PARAMSType
{

	private static final QName PARAM$0 = new QName("", "PARAM");

	public PARAMSTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public PARAMType getPARAM()
	{
		Object obj = monitor();
		
		PARAMType paramtype;
		check_orphaned();
		paramtype = null;
		paramtype = (PARAMType)get_store().find_element_user(PARAM$0, 0);
		if (paramtype == null)
			return null;
		return paramtype;
		 
	}

	public void setPARAM(PARAMType paramtype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			PARAMType paramtype1 = null;
			paramtype1 = (PARAMType)get_store().find_element_user(PARAM$0, 0);
			if (paramtype1 == null)
				paramtype1 = (PARAMType)get_store().add_element_user(PARAM$0);
			paramtype1.set(paramtype);
		}
	}

	public PARAMType addNewPARAM()
	{
		Object obj = monitor();
		
		PARAMType paramtype;
		check_orphaned();
		paramtype = null;
		paramtype = (PARAMType)get_store().add_element_user(PARAM$0);
		return paramtype;
		 
	}

}
