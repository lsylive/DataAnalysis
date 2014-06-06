



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.ColType;
import com.liusy.datapp.xbeans.analysis.MetaType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class MetaTypeImpl extends XmlComplexContentImpl
	implements MetaType
{

	private static final QName COL$0 = new QName("", "col");

	public MetaTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public ColType[] getColArray()
	{
		Object obj = monitor();
		
		ColType acoltype[];
		check_orphaned();
		ArrayList arraylist = new ArrayList();
		get_store().find_all_element_users(COL$0, arraylist);
		acoltype = new ColType[arraylist.size()];
		arraylist.toArray(acoltype);
		return acoltype;
	}

	public ColType getColArray(int i)
	{
		Object obj = monitor();
		
		ColType coltype;
		check_orphaned();
		coltype = null;
		coltype = (ColType)get_store().find_element_user(COL$0, i);
		if (coltype == null)
			throw new IndexOutOfBoundsException();
		return coltype;
	}

	public int sizeOfColArray()
	{
		Object obj = monitor();
		
		check_orphaned();
		return get_store().count_elements(COL$0);
	}

	public void setColArray(ColType acoltype[])
	{
		synchronized (monitor())
		{
			check_orphaned();
			arraySetterHelper(acoltype, COL$0);
		}
	}

	public void setColArray(int i, ColType coltype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			ColType coltype1 = null;
			coltype1 = (ColType)get_store().find_element_user(COL$0, i);
			if (coltype1 == null)
				throw new IndexOutOfBoundsException();
			coltype1.set(coltype);
		}
	}

	public ColType insertNewCol(int i)
	{
		Object obj = monitor();
		
		ColType coltype;
		check_orphaned();
		coltype = null;
		coltype = (ColType)get_store().insert_element_user(COL$0, i);
		return coltype;
	}

	public ColType addNewCol()
	{
		Object obj = monitor();
		
		ColType coltype;
		check_orphaned();
		coltype = null;
		coltype = (ColType)get_store().add_element_user(COL$0);
		return coltype;
	}

	public void removeCol(int i)
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_element(COL$0, i);
		}
	}

}
