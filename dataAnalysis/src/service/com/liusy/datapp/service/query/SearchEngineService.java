package com.liusy.datapp.service.query;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.lucene.search.TopDocs;

public interface SearchEngineService extends Serializable{

	public List<Map<String, String>> search(String queryStr,int top);
}
