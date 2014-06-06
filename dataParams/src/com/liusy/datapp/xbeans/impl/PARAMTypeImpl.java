



package com.liusy.datapp.xbeans.impl;

import com.liusy.datapp.xbeans.COLSType;
import com.liusy.datapp.xbeans.PARAMType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class PARAMTypeImpl extends XmlComplexContentImpl
	implements PARAMType
{

	private static final QName COLS$0 = new QName("", "COLS");

	public PARAMTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public COLSType[] getCOLSArray()
	{
		Object obj = monitor();
		
		COLSType acolstype[];
		check_orphaned();
		ArrayList arraylist = new ArrayList();
		get_store().find_all_element_users(COLS$0, arraylist);
		acolstype = new COLSType[arraylist.size()];
		arraylist.toArray(acolstype);
		return acolstype;
		
		
		
	}

	public COLSType getCOLSArray(int i)
	{
		Object obj = monitor();
		
		COLSType colstype;
		check_orphaned();
		colstype = null;
		colstype = (COLSType)get_store().find_element_user(COLS$0, i);
		if (colstype == null)
			new IndexOutOfBoundsException();
		return colstype;
		
		
		
	}

	public int sizeOfCOLSArray()
	{
		Object obj = monitor();
		
		check_orphaned();
		return get_store().count_elements(COLS$0);
		
		
		
	}

	public void setCOLSArray(COLSType acolstype[])
	{
		synchronized (monitor())
		{
			check_orphaned();
			arraySetterHelper(acolstype, COLS$0);
		}
	}

	public void setCOLSArray(int i, COLSType colstype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			COLSType colstype1 = null;
			colstype1 = (COLSType)get_store().find_element_user(COLS$0, i);
			if (colstype1 == null)
				new IndexOutOfBoundsException();
			colstype1.set(colstype);
		}
	}

	public COLSType insertNewCOLS(int i)
	{
		Object obj = monitor();
		
		COLSType colstype;
		check_orphaned();
		colstype = null;
		colstype = (COLSType)get_store().insert_element_user(COLS$0, i);
		return colstype;
		
		
		
	}

	public COLSType addNewCOLS()
	{
		Object obj = monitor();
		
		COLSType colstype;
		check_orphaned();
		colstype = null;
		colstype = (COLSType)get_store().add_element_user(COLS$0);
		return colstype;
		
		
		
	}

	public void removeCOLS(int i)
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_element(COLS$0, i);
		}
	}

}
