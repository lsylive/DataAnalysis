
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.base.Metadata;
import java.io.Serializable;

public class SyncContrastMetaConfig extends Metadata
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	private String isContrastMeta;

	public SyncContrastMetaConfig()
	{
		isContrastMeta = Consts.YES;
	}

	public SyncContrastMetaConfig(Metadata mt)
	{
		isContrastMeta = Consts.YES;
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

	public SyncContrastMetaConfig clone()
	{
		return (SyncContrastMetaConfig)super.clone();
	}

	public String getIsContrastMeta()
	{
		return isContrastMeta;
	}

	public void setIsContrastMeta(String isContrastMeta)
	{
		this.isContrastMeta = isContrastMeta;
	}

	public static long getSerialVersionUID()
	{
		return 1L;
	}

}
