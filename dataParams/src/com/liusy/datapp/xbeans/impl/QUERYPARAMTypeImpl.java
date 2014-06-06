



package com.liusy.datapp.xbeans.impl;

import com.liusy.datapp.xbeans.*;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class QUERYPARAMTypeImpl extends XmlComplexContentImpl
	implements QUERYPARAMType
{

	private static final QName COMMBATCHQUERY$0 = new QName("", "COMMBATCHQUERY");
	private static final QName ADVANCEQUERY$2 = new QName("", "ADVANCEQUERY");

	public QUERYPARAMTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public COMMBATCHQUERYType getCOMMBATCHQUERY()
	{
		Object obj = monitor();
		
		COMMBATCHQUERYType commbatchquerytype;
		check_orphaned();
		commbatchquerytype = null;
		commbatchquerytype = (COMMBATCHQUERYType)get_store().find_element_user(COMMBATCHQUERY$0, 0);
		if (commbatchquerytype == null)
			return null;
		return	commbatchquerytype;
	}

	public boolean isSetCOMMBATCHQUERY()
	{
		Object obj = monitor();
		
		check_orphaned();
		return get_store().count_elements(COMMBATCHQUERY$0) != 0;
		
		
		
	}

	public void setCOMMBATCHQUERY(COMMBATCHQUERYType commbatchquerytype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			COMMBATCHQUERYType commbatchquerytype1 = null;
			commbatchquerytype1 = (COMMBATCHQUERYType)get_store().find_element_user(COMMBATCHQUERY$0, 0);
			if (commbatchquerytype1 == null)
				commbatchquerytype1 = (COMMBATCHQUERYType)get_store().add_element_user(COMMBATCHQUERY$0);
			commbatchquerytype1.set(commbatchquerytype);
		}
	}

	public COMMBATCHQUERYType addNewCOMMBATCHQUERY()
	{
		Object obj = monitor();
		
		COMMBATCHQUERYType commbatchquerytype;
		check_orphaned();
		commbatchquerytype = null;
		commbatchquerytype = (COMMBATCHQUERYType)get_store().add_element_user(COMMBATCHQUERY$0);
		return commbatchquerytype;
		
		
		
	}

	public void unsetCOMMBATCHQUERY()
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_element(COMMBATCHQUERY$0, 0);
		}
	}

	public ADVANCEQUERYType getADVANCEQUERY()
	{
		Object obj = monitor();
		
		ADVANCEQUERYType advancequerytype;
		check_orphaned();
		advancequerytype = null;
		advancequerytype = (ADVANCEQUERYType)get_store().find_element_user(ADVANCEQUERY$2, 0);
		if (advancequerytype == null)
			return null;
		return	advancequerytype;
		
	}

	public boolean isSetADVANCEQUERY()
	{
		Object obj = monitor();
		
		check_orphaned();
		return get_store().count_elements(ADVANCEQUERY$2) != 0;
		
		
		
	}

	public void setADVANCEQUERY(ADVANCEQUERYType advancequerytype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			ADVANCEQUERYType advancequerytype1 = null;
			advancequerytype1 = (ADVANCEQUERYType)get_store().find_element_user(ADVANCEQUERY$2, 0);
			if (advancequerytype1 == null)
				advancequerytype1 = (ADVANCEQUERYType)get_store().add_element_user(ADVANCEQUERY$2);
			advancequerytype1.set(advancequerytype);
		}
	}

	public ADVANCEQUERYType addNewADVANCEQUERY()
	{
		Object obj = monitor();
		
		ADVANCEQUERYType advancequerytype;
		check_orphaned();
		advancequerytype = null;
		advancequerytype = (ADVANCEQUERYType)get_store().add_element_user(ADVANCEQUERY$2);
		return advancequerytype;
		
		
		
	}

	public void unsetADVANCEQUERY()
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_element(ADVANCEQUERY$2, 0);
		}
	}

}
