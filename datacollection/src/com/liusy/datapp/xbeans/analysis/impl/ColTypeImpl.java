



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.ColType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class ColTypeImpl extends XmlComplexContentImpl
	implements ColType
{

	private static final QName CNAME$0 = new QName("", "cname");
	private static final QName NAME$2 = new QName("", "name");
	private static final QName TYPE$4 = new QName("", "type");
	private static final QName CODESET$6 = new QName("", "codeset");
	private static final QName INDICATOR$8 = new QName("", "indicator");
	private static final QName ID$10 = new QName("", "id");

	public ColTypeImpl(SchemaType schematype)
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
return		simplevalue.getStringValue();
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
return 		simplevalue.getStringValue();
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

	public String getIndicator()
	{
		Object obj = monitor();
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(INDICATOR$8, 0);
		if (simplevalue == null)
			return null;
return 		simplevalue.getStringValue();
	}

	public XmlString xgetIndicator()
	{
		Object obj = monitor();
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(INDICATOR$8, 0);
		return xmlstring;
	}

	public void setIndicator(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(INDICATOR$8, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(INDICATOR$8);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetIndicator(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(INDICATOR$8, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(INDICATOR$8);
			xmlstring1.set(xmlstring);
		}
	}

	public String getId()
	{
		Object obj = monitor();
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_attribute_user(ID$10);
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
		xmlstring = (XmlString)get_store().find_attribute_user(ID$10);
		return xmlstring;
	}

	public boolean isSetId()
	{
		Object obj = monitor();
		check_orphaned();
		return get_store().find_attribute_user(ID$10) != null;
	}

	public void setId(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_attribute_user(ID$10);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_attribute_user(ID$10);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetId(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_attribute_user(ID$10);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_attribute_user(ID$10);
			xmlstring1.set(xmlstring);
		}
	}

	public void unsetId()
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_attribute(ID$10);
		}
	}

}
