
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.vo.DataField;

public class SortProperties
	implements Cloneable, Serializable
{

	 private static final long serialVersionUID = 1L;
	  private List<DataField> fields = new ArrayList<DataField>();

	  public List<DataField> getFields() {
	    return this.fields;
	  }

	  public void setFields(List<DataField> fields) {
	    this.fields = fields;
	  }

	  public List<Metadata> getMeta() {
	    List<Metadata> mts = new ArrayList<Metadata>();

	    for (DataField df : this.fields) {
	      mts.add(df);
	    }
	    return mts;
	  }
	}