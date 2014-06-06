



package com.liusy.datapp.xbeans.impl;

import com.liusy.datapp.xbeans.QUERYPARAMDocument;
import com.liusy.datapp.xbeans.QUERYPARAMType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class QUERYPARAMDocumentImpl extends XmlComplexContentImpl
	implements QUERYPARAMDocument
{

	private static final QName QUERYPARAM$0 = new QName("", "QUERYPARAM");

	public QUERYPARAMDocumentImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public QUERYPARAMType getQUERYPARAM()
	{
		Object obj = monitor();
		
		QUERYPARAMType queryparamtype;
		check_orphaned();
		queryparamtype = null;
		queryparamtype = (QUERYPARAMType)get_store().find_element_user(QUERYPARAM$0, 0);
		if (queryparamtype == null)
			return null;
		return queryparamtype;
		 
		
		
	}

	public void setQUERYPARAM(QUERYPARAMType queryparamtype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			QUERYPARAMType queryparamtype1 = null;
			queryparamtype1 = (QUERYPARAMType)get_store().find_element_user(QUERYPARAM$0, 0);
			if (queryparamtype1 == null)
				queryparamtype1 = (QUERYPARAMType)get_store().add_element_user(QUERYPARAM$0);
			queryparamtype1.set(queryparamtype);
		}
	}

	public QUERYPARAMType addNewQUERYPARAM()
	{
		Object obj = monitor();
		
		QUERYPARAMType queryparamtype;
		check_orphaned();
		queryparamtype = null;
		queryparamtype = (QUERYPARAMType)get_store().add_element_user(QUERYPARAM$0);
		return queryparamtype;
		 
		
		
	}

}
