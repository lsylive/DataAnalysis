



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.DatacollectionDocument;
import com.liusy.datapp.xbeans.analysis.DatacollectionType;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class DatacollectionDocumentImpl extends XmlComplexContentImpl
	implements DatacollectionDocument
{

	private static final QName DATACOLLECTION$0 = new QName("", "datacollection");

	public DatacollectionDocumentImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public DatacollectionType getDatacollection()
	{
		Object obj = monitor();
		DatacollectionType datacollectiontype;
		check_orphaned();
		datacollectiontype = null;
		datacollectiontype = (DatacollectionType)get_store().find_element_user(DATACOLLECTION$0, 0);
		if (datacollectiontype == null)
			return null;
		return datacollectiontype;
	}

	public void setDatacollection(DatacollectionType datacollectiontype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			DatacollectionType datacollectiontype1 = null;
			datacollectiontype1 = (DatacollectionType)get_store().find_element_user(DATACOLLECTION$0, 0);
			if (datacollectiontype1 == null)
				datacollectiontype1 = (DatacollectionType)get_store().add_element_user(DATACOLLECTION$0);
			datacollectiontype1.set(datacollectiontype);
		}
	}

	public DatacollectionType addNewDatacollection()
	{
		Object obj = monitor();
		DatacollectionType datacollectiontype;
		check_orphaned();
		datacollectiontype = null;
		datacollectiontype = (DatacollectionType)get_store().add_element_user(DATACOLLECTION$0);
		return datacollectiontype;
	}

}
