
package com.liusy.analysis.template.model.vo;

import com.liusy.analysis.template.model.base.Metadata;
import java.io.Serializable;

public class DataField extends Metadata
	implements Cloneable, Serializable
{

	private static final long serialVersionUID = 1L;
	protected String aliasName;
	protected String sortNo;
	protected String sortDirect;
	protected String aggregate;
	protected String aggregateNo;
	protected String output;
	public static String ALIASNAME = "aliasName";
	public static String SORTNO = "sortNo";
	public static String SORTDIRECT = "sortDirect";
	public static String AGGREGATE = "aggregate";
	public static String AGGREGATENO = "aggregateNo";
	public static String OUTPUT = "output";

	public DataField()
	{
		aliasName = "";
		sortNo = "";
		sortDirect = "";
		aggregate = "";
		aggregateNo = "";
		output = "";
	}

	public DataField(Metadata mt)
	{
		aliasName = "";
		sortNo = "";
		sortDirect = "";
		aggregate = "";
		aggregateNo = "";
		output = "";
		name = mt.getName();
		cnName = mt.getCnName();
		dataType = mt.getDataType();
		description = mt.getDescription();
		id = mt.getId();
		length = mt.getLength();
		precision = mt.getPrecision();
	}

	public Object clone()
	{
		return super.clone();
	}

	public Metadata getMeta()
	{
		Metadata mt = new Metadata();
		mt.setCnName(cnName);
		mt.setName(name);
		mt.setDataType(dataType);
		mt.setDescription(description);
		mt.setId(id);
		mt.setLength(length);
		mt.setPrecision(precision);
		return mt;
	}

	public static String[] getColNames()
	{
		String tmp[] = new String[9];
		tmp[0] = META_CNNAME;
		tmp[1] = META_NAME;
		tmp[2] = ALIASNAME;
		tmp[3] = META_DATATYPE;
		tmp[4] = SORTNO;
		tmp[5] = SORTDIRECT;
		tmp[6] = AGGREGATE;
		tmp[7] = AGGREGATENO;
		tmp[8] = OUTPUT;
		return tmp;
	}

	public String getAliasName()
	{
		return aliasName;
	}

	public void setAliasName(String aliasName)
	{
		this.aliasName = aliasName;
	}

	public String getSortNo()
	{
		return sortNo;
	}

	public void setSortNo(String sortNo)
	{
		this.sortNo = sortNo;
	}

	public String getSortDirect()
	{
		return sortDirect;
	}

	public void setSortDirect(String sortDirect)
	{
		this.sortDirect = sortDirect;
	}

	public String getAggregate()
	{
		return aggregate;
	}

	public void setAggregate(String aggregate)
	{
		this.aggregate = aggregate;
	}

	public String getOutput()
	{
		return output;
	}

	public void setOutput(String output)
	{
		this.output = output;
	}

	public String getAggregateNo()
	{
		return aggregateNo;
	}

	public void setAggregateNo(String aggregateNo)
	{
		this.aggregateNo = aggregateNo;
	}

}
