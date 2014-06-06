


//   DynamicList.java

package com.liusy.dataapplatform.base.util;

import java.util.*;

public class DynamicList
	implements List
{

	private ArrayList al;
	private Class clazz;

	public DynamicList()
	{
		al = new ArrayList();
		clazz = (new Object()).getClass();
	}

	public DynamicList(int initialCapacity)
	{
		al = new ArrayList(initialCapacity);
		clazz = (new Object()).getClass();
	}

	public DynamicList(Class clazz)
	{
		al = new ArrayList();
		this.clazz = clazz;
	}

	public DynamicList(int initialCapacity, Class clazz)
	{
		al = new ArrayList(initialCapacity);
		this.clazz = clazz;
	}

	public Object get(int index)
	{
		if (al == null)
			al = new ArrayList();
		if (index + 1 > al.size())
		{
			int oldSize = al.size();
			for (int i = oldSize; i < index + 1; i++)
				try
				{
					Object object = clazz.newInstance();
					al.add(object);
				}
				catch (Exception ex)
				{
					return null;
				}

		}
		return al.get(index);
	}

	public boolean isEmpty()
	{
		return al.isEmpty();
	}

	public void clear()
	{
		al.clear();
	}

	public Iterator iterator()
	{
		return al.iterator();
	}

	public int size()
	{
		return al.size();
	}

	public Object[] toArray()
	{
		return al.toArray();
	}

	public ListIterator listIterator()
	{
		return al.listIterator();
	}

	public ListIterator listIterator(int index)
	{
		return al.listIterator(index);
	}

	public void add(int index, Object o)
	{
		al.add(index, o);
	}

	public boolean add(Object o)
	{
		return al.add(o);
	}

	public boolean addAll(Collection c)
	{
		return al.addAll(c);
	}

	public boolean addAll(int index, Collection c)
	{
		return al.addAll(index, c);
	}

	public Object set(int index, Object element)
	{
		return al.set(index, element);
	}

	public Object[] toArray(Object a[])
	{
		return al.toArray(a);
	}

	public List subList(int fromIndex, int toIndex)
	{
		return al.subList(fromIndex, toIndex);
	}

	public boolean contains(Object o)
	{
		return al.contains(o);
	}

	public boolean containsAll(Collection c)
	{
		return al.containsAll(c);
	}

	public int indexOf(Object o)
	{
		return al.indexOf(o);
	}

	public int lastIndexOf(Object o)
	{
		return al.indexOf(o);
	}

	public Object remove(int index)
	{
		return al.remove(index);
	}

	public boolean remove(Object o)
	{
		return al.remove(o);
	}

	public boolean removeAll(Collection c)
	{
		return al.remove(c);
	}

	public boolean retainAll(Collection c)
	{
		return al.retainAll(c);
	}
}
