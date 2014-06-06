
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import java.io.Serializable;

public class SearchField extends Metadata
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String searchSequence;

	public SearchField()
	{
		searchSequence = Consts.SEARCH_NONE;
	}

	public SearchField(Metadata mt)
	{
		searchSequence = Consts.SEARCH_NONE;
		setId(mt.getId());
		setCnName(mt.getCnName());
		setDataType(mt.getDataType());
		setDescription(mt.getDescription());
		setName(mt.getName());
		setLength(mt.getLength());
		setPrecision(mt.getPrecision());
	}

	public Metadata getMeta()
	{
		Metadata mt = new Metadata();
		mt.setId(getId());
		mt.setCnName(getCnName());
		mt.setDataType(getDataType());
		mt.setDescription(getDescription());
		mt.setName(getName());
		mt.setLength(getLength());
		mt.setPrecision(getPrecision());
		return mt;
	}

	public SearchField clone()
	{
		return (SearchField)super.clone();
	}

	public String getSearchSequence()
	{
		return searchSequence;
	}

	public void setSearchSequence(String searchSequence)
	{
		this.searchSequence = searchSequence;
	}

}
