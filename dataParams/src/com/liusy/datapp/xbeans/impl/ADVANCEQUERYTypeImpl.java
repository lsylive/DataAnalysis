



package com.liusy.datapp.xbeans.impl;

import com.liusy.datapp.xbeans.ADVANCEQUERYType;
import com.liusy.datapp.xbeans.LINESType;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class ADVANCEQUERYTypeImpl extends XmlComplexContentImpl
	implements ADVANCEQUERYType
{

	private static final QName ATABLEID$0 = new QName("", "ATABLEID");
	private static final QName LINES$2 = new QName("", "LINES");

	public ADVANCEQUERYTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public String getATABLEID()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(ATABLEID$0, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		
		
		
		 
	}

	public XmlString xgetATABLEID()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(ATABLEID$0, 0);
		return xmlstring;
		
		
		 
	}

	public void setATABLEID(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(ATABLEID$0, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(ATABLEID$0);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetATABLEID(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(ATABLEID$0, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(ATABLEID$0);
			xmlstring1.set(xmlstring);
		}
	}

	public LINESType[] getLINESArray()
	{
		Object obj = monitor();
		
		LINESType alinestype[];
		check_orphaned();
		ArrayList arraylist = new ArrayList();
		get_store().find_all_element_users(LINES$2, arraylist);
		alinestype = new LINESType[arraylist.size()];
		arraylist.toArray(alinestype);
		return alinestype;
		
		
		 
	}

	public LINESType getLINESArray(int i)
	{
		Object obj = monitor();
		
		LINESType linestype;
		check_orphaned();
		linestype = null;
		linestype = (LINESType)get_store().find_element_user(LINES$2, i);
		if (linestype == null)
			 new IndexOutOfBoundsException();
		return linestype;
		
		
		 
	}

	public int sizeOfLINESArray()
	{
		Object obj = monitor();
		
		check_orphaned();
		return get_store().count_elements(LINES$2);
		
		
		 
	}

	public void setLINESArray(LINESType alinestype[])
	{
		synchronized (monitor())
		{
			check_orphaned();
			arraySetterHelper(alinestype, LINES$2);
		}
	}

	public void setLINESArray(int i, LINESType linestype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			LINESType linestype1 = null;
			linestype1 = (LINESType)get_store().find_element_user(LINES$2, i);
			if (linestype1 == null)
				 new IndexOutOfBoundsException();
			linestype1.set(linestype);
		}
	}

	public LINESType insertNewLINES(int i)
	{
		Object obj = monitor();
		
		LINESType linestype;
		check_orphaned();
		linestype = null;
		linestype = (LINESType)get_store().insert_element_user(LINES$2, i);
		return linestype;
		
		
		 
	}

	public LINESType addNewLINES()
	{
		Object obj = monitor();
		
		LINESType linestype;
		check_orphaned();
		linestype = null;
		linestype = (LINESType)get_store().add_element_user(LINES$2);
		return linestype;
		
		
		 
	}

	public void removeLINES(int i)
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_element(LINES$2, i);
		}
	}

}
