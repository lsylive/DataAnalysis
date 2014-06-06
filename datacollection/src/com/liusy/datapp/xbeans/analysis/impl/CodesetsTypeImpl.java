



package com.liusy.datapp.xbeans.analysis.impl;

import com.liusy.datapp.xbeans.analysis.CodesetType;
import com.liusy.datapp.xbeans.analysis.CodesetsType;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.values.TypeStore;
import org.apache.xmlbeans.impl.values.XmlComplexContentImpl;

public class CodesetsTypeImpl extends XmlComplexContentImpl
	implements CodesetsType
{

	private static final QName CODESET$0 = new QName("", "codeset");

	public CodesetsTypeImpl(SchemaType schematype)
	{
		super(schematype);
	}

	public CodesetType[] getCodesetArray()
	{
		Object obj = monitor();
		CodesetType acodesettype[];
		check_orphaned();
		ArrayList arraylist = new ArrayList();
		get_store().find_all_element_users(CODESET$0, arraylist);
		acodesettype = new CodesetType[arraylist.size()];
		arraylist.toArray(acodesettype);
		return acodesettype;
	}

	public CodesetType getCodesetArray(int i)
	{
		Object obj = monitor();
		CodesetType codesettype;
		check_orphaned();
		codesettype = null;
		codesettype = (CodesetType)get_store().find_element_user(CODESET$0, i);
		if (codesettype == null)
			throw new IndexOutOfBoundsException();
		return codesettype;
	}

	public int sizeOfCodesetArray()
	{
		Object obj = monitor();
		check_orphaned();
		return get_store().count_elements(CODESET$0);
	}

	public void setCodesetArray(CodesetType acodesettype[])
	{
		synchronized (monitor())
		{
			check_orphaned();
			arraySetterHelper(acodesettype, CODESET$0);
		}
	}

	public void setCodesetArray(int i, CodesetType codesettype)
	{
		synchronized (monitor())
		{
			check_orphaned();
			CodesetType codesettype1 = null;
			codesettype1 = (CodesetType)get_store().find_element_user(CODESET$0, i);
			if (codesettype1 == null)
				throw new IndexOutOfBoundsException();
			codesettype1.set(codesettype);
		}
	}

	public CodesetType insertNewCodeset(int i)
	{
		Object obj = monitor();
		CodesetType codesettype;
		check_orphaned();
		codesettype = null;
		codesettype = (CodesetType)get_store().insert_element_user(CODESET$0, i);
		return codesettype;
	}

	public CodesetType addNewCodeset()
	{
		Object obj = monitor();
		CodesetType codesettype;
		check_orphaned();
		codesettype = null;
		codesettype = (CodesetType)get_store().add_element_user(CODESET$0);
		return codesettype;
	}

	public void removeCodeset(int i)
	{
		synchronized (monitor())
		{
			check_orphaned();
			get_store().remove_element(CODESET$0, i);
		}
	}

}
