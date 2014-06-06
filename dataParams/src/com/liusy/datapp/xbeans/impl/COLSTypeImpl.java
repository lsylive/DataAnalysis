



package com.liusy.datapp.xbeans.impl;

import com.liusy.datapp.xbeans.COLSType;
import com.liusy.datapp.xbeans.COLType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class COLSTypeImpl extends XmlComplexContentImpl
	implements COLSType
{

	private static final QName COL$0 = new QName("", "COL");

	public COLSTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public COLType[] getCOLArray()
	{
		Object obj = monitor();
		
		COLType acoltype[];
		check_orphaned();
		ArrayList arraylist = new ArrayList();
		get_store().find_all_element_users(COL$0, arraylist);
		acoltype = new COLType[arraylist.size()];
		arraylist.toArray(acoltype);
		return acoltype;
		
		
		 
	}

	public COLType getCOLArray(int i)
	{
		Object obj = monitor();
		
		COLType coltype;
		check_orphaned();
		coltype = null;
		coltype = (COLType)get_store().find_element_user(COL$0, i);
		if (coltype == null)
			 new IndexOutOfBoundsException();
		return coltype;
		
		
		 
	}

	public int sizeOfCOLArray()
	{
		Object obj = monitor();
		
		check_orphaned();
		return get_store().count_elements(COL$0);
		
		
		 
	}

	public void setCOLArray(COLType acoltype[])
	{
		synchronized (monitor())
		{
			check_orphaned();
			arraySetterHelper(acoltype, COL$0);
		}
	}

	public void setCOLArray(int i, COLType coltype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			COLType coltype1 = null;
			coltype1 = (COLType)get_store().find_element_user(COL$0, i);
			if (coltype1 == null)
				 new IndexOutOfBoundsException();
			coltype1.set(coltype);
		}
	}

	public COLType insertNewCOL(int i)
	{
		Object obj = monitor();
		
		COLType coltype;
		check_orphaned();
		coltype = null;
		coltype = (COLType)get_store().insert_element_user(COL$0, i);
		return coltype;
		
		
		 
	}

	public COLType addNewCOL()
	{
		Object obj = monitor();
		
		COLType coltype;
		check_orphaned();
		coltype = null;
		coltype = (COLType)get_store().add_element_user(COL$0);
		return coltype;
		 
	}

	public void removeCOL(int i)
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_element(COL$0, i);
		}
	}

}
