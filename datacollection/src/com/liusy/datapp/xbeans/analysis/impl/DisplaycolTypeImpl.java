



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.DcolType;
import com.liusy.datapp.xbeans.analysis.DisplaycolType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class DisplaycolTypeImpl extends XmlComplexContentImpl
	implements DisplaycolType
{

	private static final QName DCOL$0 = new QName("", "dcol");

	public DisplaycolTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public DcolType[] getDcolArray()
	{
		Object obj = monitor();
		
		DcolType adcoltype[];
		check_orphaned();
		ArrayList arraylist = new ArrayList();
		get_store().find_all_element_users(DCOL$0, arraylist);
		adcoltype = new DcolType[arraylist.size()];
		arraylist.toArray(adcoltype);
		return adcoltype;
	}

	public DcolType getDcolArray(int i)
	{
		Object obj = monitor();
		
		DcolType dcoltype;
		check_orphaned();
		dcoltype = null;
		dcoltype = (DcolType)get_store().find_element_user(DCOL$0, i);
		if (dcoltype == null)
			throw new IndexOutOfBoundsException();
		return dcoltype;
	}

	public int sizeOfDcolArray()
	{
		Object obj = monitor();
		
		check_orphaned();
		return get_store().count_elements(DCOL$0);
	}

	public void setDcolArray(DcolType adcoltype[])
	{
		synchronized (monitor())
		{
			check_orphaned();
			arraySetterHelper(adcoltype, DCOL$0);
		}
	}

	public void setDcolArray(int i, DcolType dcoltype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			DcolType dcoltype1 = null;
			dcoltype1 = (DcolType)get_store().find_element_user(DCOL$0, i);
			if (dcoltype1 == null)
				throw new IndexOutOfBoundsException();
			dcoltype1.set(dcoltype);
		}
	}

	public DcolType insertNewDcol(int i)
	{
		Object obj = monitor();
		
		DcolType dcoltype;
		check_orphaned();
		dcoltype = null;
		dcoltype = (DcolType)get_store().insert_element_user(DCOL$0, i);
		return dcoltype;
	}

	public DcolType addNewDcol()
	{
		Object obj = monitor();
		
		DcolType dcoltype;
		check_orphaned();
		dcoltype = null;
		dcoltype = (DcolType)get_store().add_element_user(DCOL$0);
		return dcoltype;
	}

	public void removeDcol(int i)
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_element(DCOL$0, i);
		}
	}

}
