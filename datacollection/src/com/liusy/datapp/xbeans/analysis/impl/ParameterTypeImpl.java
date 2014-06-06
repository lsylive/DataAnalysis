



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.ParameterType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class ParameterTypeImpl extends XmlComplexContentImpl
	implements ParameterType
{

	private static final QName NAME$0 = new QName("", "name");
	private static final QName CNAME$2 = new QName("", "cname");
	private static final QName TYPE$4 = new QName("", "type");
	private static final QName CODESET$6 = new QName("", "codeset");
	private static final QName ISBETWEEN$8 = new QName("", "isbetween");

	public ParameterTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public String getName()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(NAME$0, 0);
		if (simplevalue == null)
			return null;
return 		simplevalue.getStringValue();
	}

	public XmlString xgetName()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(NAME$0, 0);
		return xmlstring;
	}

	public void setName(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(NAME$0, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(NAME$0);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetName(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(NAME$0, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(NAME$0);
			xmlstring1.set(xmlstring);
		}
	}

	public String getCname()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(CNAME$2, 0);
		if (simplevalue == null)
			return null;
return 		simplevalue.getStringValue();
	}

	public XmlString xgetCname()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(CNAME$2, 0);
		return xmlstring;
	}

	public void setCname(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(CNAME$2, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(CNAME$2);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetCname(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(CNAME$2, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(CNAME$2);
			xmlstring1.set(xmlstring);
		}
	}

	public String getType()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(TYPE$4, 0);
		if (simplevalue == null)
			return null;
return 		simplevalue.getStringValue();
	}

	public XmlString xgetType()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(TYPE$4, 0);
		return xmlstring;
	}

	public void setType(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(TYPE$4, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(TYPE$4);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetType(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(TYPE$4, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(TYPE$4);
			xmlstring1.set(xmlstring);
		}
	}

	public String getCodeset()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(CODESET$6, 0);
		if (simplevalue == null)
			return null;
return		simplevalue.getStringValue();
	}

	public XmlString xgetCodeset()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(CODESET$6, 0);
		return xmlstring;
	}

	public void setCodeset(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(CODESET$6, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(CODESET$6);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetCodeset(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(CODESET$6, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(CODESET$6);
			xmlstring1.set(xmlstring);
		}
	}

	public String getIsbetween()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(ISBETWEEN$8, 0);
		if (simplevalue == null)
			return null;
return		simplevalue.getStringValue();
	}

	public XmlString xgetIsbetween()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(ISBETWEEN$8, 0);
		return xmlstring;
	}

	public void setIsbetween(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(ISBETWEEN$8, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(ISBETWEEN$8);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetIsbetween(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(ISBETWEEN$8, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(ISBETWEEN$8);
			xmlstring1.set(xmlstring);
		}
	}

}
