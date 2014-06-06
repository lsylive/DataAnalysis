



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.CodeType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.JavaStringHolderEx;
import org.apache.xmlbeans.impl.values.TypeStore;

public class CodeTypeImpl extends JavaStringHolderEx
	implements CodeType
{

	private static final QName LABEL$0 = new QName("", "label");
	private static final QName DATA$2 = new QName("", "data");

	public CodeTypeImpl(SchemaType schematype)
	{
		super(schematype, true);
	}

	protected CodeTypeImpl(SchemaType schematype, boolean flag)
	{
		super(schematype, flag);
	}

	public String getLabel()
	{
		Object obj = monitor();
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_attribute_user(LABEL$0);
		if (simplevalue == null)
			return null;
		return		simplevalue.getStringValue();
	}

	public XmlString xgetLabel()
	{
		Object obj = monitor();
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_attribute_user(LABEL$0);
		return xmlstring;
	}

	public boolean isSetLabel()
	{
		Object obj = monitor();
		check_orphaned();
		return get_store().find_attribute_user(LABEL$0) != null;
	}

	public void setLabel(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_attribute_user(LABEL$0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_attribute_user(LABEL$0);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetLabel(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_attribute_user(LABEL$0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_attribute_user(LABEL$0);
			xmlstring1.set(xmlstring);
		}
	}

	public void unsetLabel()
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_attribute(LABEL$0);
		}
	}

	public String getData()
	{
		Object obj = monitor();
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_attribute_user(DATA$2);
		if (simplevalue == null)
			return null;
		return	simplevalue.getStringValue();
	}

	public XmlString xgetData()
	{
		Object obj = monitor();
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_attribute_user(DATA$2);
		return xmlstring;
	}

	public boolean isSetData()
	{
		Object obj = monitor();
		check_orphaned();
		return get_store().find_attribute_user(DATA$2) != null;
	}

	public void setData(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_attribute_user(DATA$2);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_attribute_user(DATA$2);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetData(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_attribute_user(DATA$2);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_attribute_user(DATA$2);
			xmlstring1.set(xmlstring);
		}
	}

	public void unsetData()
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_attribute(DATA$2);
		}
	}

}
