



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.CodeType;
import com.liusy.datapp.xbeans.analysis.CodesetType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CodesetTypeImpl extends XmlComplexContentImpl
	implements CodesetType
{

	private static final QName CODE$0 = new QName("", "code");
	private static final QName NAME$2 = new QName("", "name");

	public CodesetTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public CodeType[] getCodeArray()
	{
		Object obj = monitor();
		CodeType acodetype[];
		check_orphaned();
		ArrayList arraylist = new ArrayList();
		get_store().find_all_element_users(CODE$0, arraylist);
		acodetype = new CodeType[arraylist.size()];
		arraylist.toArray(acodetype);
		return acodetype;
	}

	public CodeType getCodeArray(int i)
	{
		Object obj = monitor();
		CodeType codetype;
		check_orphaned();
		codetype = null;
		codetype = (CodeType)get_store().find_element_user(CODE$0, i);
		if (codetype == null)
			throw new IndexOutOfBoundsException();
		return codetype;
	}

	public int sizeOfCodeArray()
	{
		Object obj = monitor();
		check_orphaned();
		return get_store().count_elements(CODE$0);
	}

	public void setCodeArray(CodeType acodetype[])
	{
		synchronized (monitor())
		{
			check_orphaned();
			arraySetterHelper(acodetype, CODE$0);
		}
	}

	public void setCodeArray(int i, CodeType codetype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			CodeType codetype1 = null;
			codetype1 = (CodeType)get_store().find_element_user(CODE$0, i);
			if (codetype1 == null)
				throw new IndexOutOfBoundsException();
			codetype1.set(codetype);
		}
	}

	public CodeType insertNewCode(int i)
	{
		Object obj = monitor();
		CodeType codetype;
		check_orphaned();
		codetype = null;
		codetype = (CodeType)get_store().insert_element_user(CODE$0, i);
		return codetype;
	}

	public CodeType addNewCode()
	{
		Object obj = monitor();
		CodeType codetype;
		check_orphaned();
		codetype = null;
		codetype = (CodeType)get_store().add_element_user(CODE$0);
		return codetype;
	}

	public void removeCode(int i)
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_element(CODE$0, i);
		}
	}

	public String getName()
	{
		Object obj = monitor();
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_attribute_user(NAME$2);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
	}

	public XmlString xgetName()
	{
		Object obj = monitor();
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_attribute_user(NAME$2);
		return xmlstring;
	}

	public boolean isSetName()
	{
		Object obj = monitor();
		check_orphaned();
		return get_store().find_attribute_user(NAME$2) != null;
	}

	public void setName(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_attribute_user(NAME$2);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_attribute_user(NAME$2);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetName(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_attribute_user(NAME$2);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_attribute_user(NAME$2);
			xmlstring1.set(xmlstring);
		}
	}

	public void unsetName()
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_attribute(NAME$2);
		}
	}

}
