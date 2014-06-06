



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.DcolType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class DcolTypeImpl extends XmlComplexContentImpl
	implements DcolType
{

	private static final QName CNAME$0 = new QName("", "cname");
	private static final QName NAME$2 = new QName("", "name");
	private static final QName VISIBLE$4 = new QName("", "visible");

	public DcolTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public String getCname()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(CNAME$0, 0);
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
		xmlstring = (XmlString)get_store().find_element_user(CNAME$0, 0);
		return xmlstring;
		
		 
	}

	public void setCname(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(CNAME$0, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(CNAME$0);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetCname(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(CNAME$0, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(CNAME$0);
			xmlstring1.set(xmlstring);
		}
	}

	public String getName()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(NAME$2, 0);
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
		xmlstring = (XmlString)get_store().find_element_user(NAME$2, 0);
		return xmlstring;
		
		 
	}

	public void setName(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(NAME$2, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(NAME$2);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetName(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(NAME$2, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(NAME$2);
			xmlstring1.set(xmlstring);
		}
	}

	public String getVisible()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(VISIBLE$4, 0);
		if (simplevalue == null)
			return null;
return 		simplevalue.getStringValue();
		
		 
	}

	public XmlString xgetVisible()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(VISIBLE$4, 0);
		return xmlstring;
		
		 
	}

	public void setVisible(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(VISIBLE$4, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(VISIBLE$4);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetVisible(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(VISIBLE$4, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(VISIBLE$4);
			xmlstring1.set(xmlstring);
		}
	}

}
