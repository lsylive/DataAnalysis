



package com.liusy.datapp.xbeans.impl;

import com.liusy.datapp.xbeans.LINESType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class LINESTypeImpl extends XmlComplexContentImpl
	implements LINESType
{

	private static final QName LINKOPER$0 = new QName("", "LINKOPER");
	private static final QName PREVOPER$2 = new QName("", "PREVOPER");
	private static final QName COLID$4 = new QName("", "COLID");
	private static final QName OPER$6 = new QName("", "OPER");
	private static final QName PARAMVALUE1$8 = new QName("", "PARAMVALUE1");
	private static final QName PARAMVALUE2$10 = new QName("", "PARAMVALUE2");
	private static final QName NEXTOPER$12 = new QName("", "NEXTOPER");

	public LINESTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public String getLINKOPER()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(LINKOPER$0, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		 
	}

	public XmlString xgetLINKOPER()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(LINKOPER$0, 0);
		return xmlstring;
		
		
		 
	}

	public void setLINKOPER(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(LINKOPER$0, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(LINKOPER$0);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetLINKOPER(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(LINKOPER$0, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(LINKOPER$0);
			xmlstring1.set(xmlstring);
		}
	}

	public String getPREVOPER()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(PREVOPER$2, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		
		 
	}

	public XmlString xgetPREVOPER()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(PREVOPER$2, 0);
		return xmlstring;
		
		
		 
	}

	public void setPREVOPER(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(PREVOPER$2, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(PREVOPER$2);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetPREVOPER(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(PREVOPER$2, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(PREVOPER$2);
			xmlstring1.set(xmlstring);
		}
	}

	public String getCOLID()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(COLID$4, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		 
	}

	public XmlString xgetCOLID()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(COLID$4, 0);
		return xmlstring;
		
		
		 
	}

	public void setCOLID(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(COLID$4, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(COLID$4);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetCOLID(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(COLID$4, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(COLID$4);
			xmlstring1.set(xmlstring);
		}
	}

	public String getOPER()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(OPER$6, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		
		 
	}

	public XmlString xgetOPER()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(OPER$6, 0);
		return xmlstring;
		
		
		 
	}

	public void setOPER(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(OPER$6, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(OPER$6);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetOPER(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(OPER$6, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(OPER$6);
			xmlstring1.set(xmlstring);
		}
	}

	public String getPARAMVALUE1()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(PARAMVALUE1$8, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		
		
		 
	}

	public XmlString xgetPARAMVALUE1()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(PARAMVALUE1$8, 0);
		return xmlstring;
		
		
		 
	}

	public void setPARAMVALUE1(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(PARAMVALUE1$8, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(PARAMVALUE1$8);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetPARAMVALUE1(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(PARAMVALUE1$8, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(PARAMVALUE1$8);
			xmlstring1.set(xmlstring);
		}
	}

	public String getPARAMVALUE2()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(PARAMVALUE2$10, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		
		
		 
	}

	public XmlString xgetPARAMVALUE2()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(PARAMVALUE2$10, 0);
		return xmlstring;
		
		
		 
	}

	public void setPARAMVALUE2(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(PARAMVALUE2$10, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(PARAMVALUE2$10);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetPARAMVALUE2(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(PARAMVALUE2$10, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(PARAMVALUE2$10);
			xmlstring1.set(xmlstring);
		}
	}

	public String getNEXTOPER()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(NEXTOPER$12, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		
		 
	}

	public XmlString xgetNEXTOPER()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(NEXTOPER$12, 0);
		return xmlstring;
		
		
		 
	}

	public void setNEXTOPER(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(NEXTOPER$12, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(NEXTOPER$12);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetNEXTOPER(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(NEXTOPER$12, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(NEXTOPER$12);
			xmlstring1.set(xmlstring);
		}
	}

}
