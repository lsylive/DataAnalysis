



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.*;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class DatacollectionTypeImpl extends XmlComplexContentImpl
	implements DatacollectionType
{

	private static final QName TEMPLATEID$0 = new QName("", "templateid");
	private static final QName PARAMETERS$2 = new QName("", "parameters");
	private static final QName META$4 = new QName("", "meta");
	private static final QName DISPLAYCOL$6 = new QName("", "displaycol");
	private static final QName CODESETS$8 = new QName("", "codesets");

	public DatacollectionTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public String getTemplateid()
	{
		Object obj = monitor();
		
		SimpleValue simplevalue;
		check_orphaned();
		simplevalue = null;
		simplevalue = (SimpleValue)get_store().find_element_user(TEMPLATEID$0, 0);
		if (simplevalue == null)
			return null;
		return simplevalue.getStringValue();
		

		
		
		 
	}

	public XmlString xgetTemplateid()
	{
		Object obj = monitor();
		
		XmlString xmlstring;
		check_orphaned();
		xmlstring = null;
		xmlstring = (XmlString)get_store().find_element_user(TEMPLATEID$0, 0);
		return xmlstring;
		
		
		 
	}

	public void setTemplateid(String s)
	{
		synchronized (monitor())
		{
			check_orphaned();
			SimpleValue simplevalue = null;
			simplevalue = (SimpleValue)get_store().find_element_user(TEMPLATEID$0, 0);
			if (simplevalue == null)
				simplevalue = (SimpleValue)get_store().add_element_user(TEMPLATEID$0);
			simplevalue.setStringValue(s);
		}
	}

	public void xsetTemplateid(XmlString xmlstring)
	{
		synchronized (monitor())
		{
			check_orphaned();
			XmlString xmlstring1 = null;
			xmlstring1 = (XmlString)get_store().find_element_user(TEMPLATEID$0, 0);
			if (xmlstring1 == null)
				xmlstring1 = (XmlString)get_store().add_element_user(TEMPLATEID$0);
			xmlstring1.set(xmlstring);
		}
	}

	public ParametersType getParameters()
	{
		Object obj = monitor();
		
		ParametersType parameterstype;
		check_orphaned();
		parameterstype = null;
		parameterstype = (ParametersType)get_store().find_element_user(PARAMETERS$2, 0);
		if (parameterstype == null)
			return null;
		return parameterstype;
		

		
		
		 
	}

	public void setParameters(ParametersType parameterstype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			ParametersType parameterstype1 = null;
			parameterstype1 = (ParametersType)get_store().find_element_user(PARAMETERS$2, 0);
			if (parameterstype1 == null)
				parameterstype1 = (ParametersType)get_store().add_element_user(PARAMETERS$2);
			parameterstype1.set(parameterstype);
		}
	}

	public ParametersType addNewParameters()
	{
		Object obj = monitor();
		
		ParametersType parameterstype;
		check_orphaned();
		parameterstype = null;
		parameterstype = (ParametersType)get_store().add_element_user(PARAMETERS$2);
		return parameterstype;
		
		
		 
	}

	public MetaType getMeta()
	{
		Object obj = monitor();
		
		MetaType metatype;
		check_orphaned();
		metatype = null;
		metatype = (MetaType)get_store().find_element_user(META$4, 0);
		if (metatype == null)
			return null;
		return metatype;
		
		

		
		
		 
	}

	public void setMeta(MetaType metatype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			MetaType metatype1 = null;
			metatype1 = (MetaType)get_store().find_element_user(META$4, 0);
			if (metatype1 == null)
				metatype1 = (MetaType)get_store().add_element_user(META$4);
			metatype1.set(metatype);
		}
	}

	public MetaType addNewMeta()
	{
		Object obj = monitor();
		
		MetaType metatype;
		check_orphaned();
		metatype = null;
		metatype = (MetaType)get_store().add_element_user(META$4);
		return metatype;
		
		
		 
	}

	public DisplaycolType getDisplaycol()
	{
		Object obj = monitor();
		
		DisplaycolType displaycoltype;
		check_orphaned();
		displaycoltype = null;
		displaycoltype = (DisplaycolType)get_store().find_element_user(DISPLAYCOL$6, 0);
		if (displaycoltype == null)
			return null;
		return displaycoltype;
		
		

		
		
		 
	}

	public void setDisplaycol(DisplaycolType displaycoltype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			DisplaycolType displaycoltype1 = null;
			displaycoltype1 = (DisplaycolType)get_store().find_element_user(DISPLAYCOL$6, 0);
			if (displaycoltype1 == null)
				displaycoltype1 = (DisplaycolType)get_store().add_element_user(DISPLAYCOL$6);
			displaycoltype1.set(displaycoltype);
		}
	}

	public DisplaycolType addNewDisplaycol()
	{
		Object obj = monitor();
		
		DisplaycolType displaycoltype;
		check_orphaned();
		displaycoltype = null;
		displaycoltype = (DisplaycolType)get_store().add_element_user(DISPLAYCOL$6);
		return displaycoltype;
		
		
		 
	}

	public CodesetsType getCodesets()
	{
		Object obj = monitor();
		
		CodesetsType codesetstype;
		check_orphaned();
		codesetstype = null;
		codesetstype = (CodesetsType)get_store().find_element_user(CODESETS$8, 0);
		if (codesetstype == null)
			return null;
		return codesetstype;

		

		
		
		 
	}

	public void setCodesets(CodesetsType codesetstype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			CodesetsType codesetstype1 = null;
			codesetstype1 = (CodesetsType)get_store().find_element_user(CODESETS$8, 0);
			if (codesetstype1 == null)
				codesetstype1 = (CodesetsType)get_store().add_element_user(CODESETS$8);
			codesetstype1.set(codesetstype);
		}
	}

	public CodesetsType addNewCodesets()
	{
		Object obj = monitor();
		
		CodesetsType codesetstype;
		check_orphaned();
		codesetstype = null;
		codesetstype = (CodesetsType)get_store().add_element_user(CODESETS$8);
		return codesetstype;
		
		
		 
	}

}
