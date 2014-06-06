



package com.liusy.datapp.xbeans.impl;

import com.liusy.datapp.xbeans.COLType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.values.TypeStore;

public class COLTypeImpl extends JavaStringHolderEx
	implements COLType
{

	private static final QName ID$0 = new QName("", "id");

	public COLTypeImpl(SchemaType schematype)
	{
		super(schematype, true);
	}

	protected COLTypeImpl(SchemaType schematype, boolean flag)
	{
		super(schematype, flag);
	}

	public String getId()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_attribute_user(ID$0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		
		 
	}

	public XmlString xgetId()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_attribute_user(ID$0);
		return xmlstring;
		 
		
		 
	}

	public boolean isSetId()
	{
		Object obj = monitor();
		
		check_orphaned();
		return get_store().find_attribute_user(ID$0) != null;
		 
		
		 
	}

	public void setId(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_attribute_user(ID$0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_attribute_user(ID$0);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetId(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_attribute_user(ID$0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_attribute_user(ID$0);
			xmlstring1.set(xmlstring);
		}
	}

	public void unsetId()
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_attribute(ID$0);
		}
	}

}
