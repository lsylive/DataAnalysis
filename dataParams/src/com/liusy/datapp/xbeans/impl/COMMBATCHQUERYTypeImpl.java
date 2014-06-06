



package com.liusy.datapp.xbeans.impl;

import com.liusy.datapp.xbeans.COMMBATCHQUERYType;
import com.liusy.datapp.xbeans.PARAMSType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class COMMBATCHQUERYTypeImpl extends XmlComplexContentImpl
	implements COMMBATCHQUERYType
{

	private static final QName SUBJECTID$0 = new QName("", "SUBJECTID");
	private static final QName SELTABS$2 = new QName("", "SELTABS");
	private static final QName TABLEID$4 = new QName("", "TABLEID");
	private static final QName PARAMS$6 = new QName("", "PARAMS");

	public COMMBATCHQUERYTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public String getSUBJECTID()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(SUBJECTID$0, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		 
	}

	public XmlString xgetSUBJECTID()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(SUBJECTID$0, 0);
		return xmlstring;
		
		
		 
	}

	public void setSUBJECTID(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(SUBJECTID$0, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(SUBJECTID$0);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetSUBJECTID(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(SUBJECTID$0, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(SUBJECTID$0);
			xmlstring1.set(xmlstring);
		}
	}

	public String getSELTABS()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(SELTABS$2, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		 
	}

	public XmlString xgetSELTABS()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(SELTABS$2, 0);
		return xmlstring;
		
		
		 
	}

	public void setSELTABS(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(SELTABS$2, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(SELTABS$2);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetSELTABS(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(SELTABS$2, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(SELTABS$2);
			xmlstring1.set(xmlstring);
		}
	}

	public String getTABLEID()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(TABLEID$4, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		 
	}

	public XmlString xgetTABLEID()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(TABLEID$4, 0);
		return xmlstring;
		
		
		 
	}

	public void setTABLEID(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(TABLEID$4, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(TABLEID$4);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetTABLEID(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(TABLEID$4, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(TABLEID$4);
			xmlstring1.set(xmlstring);
		}
	}

	public PARAMSType getPARAMS()
	{
		Object obj = monitor();
		
		PARAMSType paramstype;
		check_orphaned();
		paramstype = null;
		paramstype = (PARAMSType)get_store().find_element_user(PARAMS$6, 0);
		if (paramstype == null)
			return null;
		return paramstype;
		 
	}

	public void setPARAMS(PARAMSType paramstype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			PARAMSType paramstype1 = null;
			paramstype1 = (PARAMSType)get_store().find_element_user(PARAMS$6, 0);
			if (paramstype1 == null)
				paramstype1 = (PARAMSType)get_store().add_element_user(PARAMS$6);
			paramstype1.set(paramstype);
		}
	}

	public PARAMSType addNewPARAMS()
	{
		Object obj = monitor();
		
		PARAMSType paramstype;
		check_orphaned();
		paramstype = null;
		paramstype = (PARAMSType)get_store().add_element_user(PARAMS$6);
		return paramstype;
		
		
		 
	}

}
