package com.liusy.datapp.service.query.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKQueryParser;
import org.wltea.analyzer.lucene.IKSimilarity;

import com.liusy.datapp.service.query.SearchEngineService;

public class SearchEngineServiceImpl extends junit.framework.TestCase implements SearchEngineService {

	private String indexFolder;
	private String resourceFolder;
	private String mmseg_dic_path;

	private String prefixHTML = "<font class=\"highlight\">";
	private String suffixHTML = "</font>";

	
	public void testIndex() {
		 try {
			    String indexPath = "D:/fullindex/index";
			    final File docDir = new File("D:/fullindex/data");
			    Date start = new Date(); 
			      System.out.println("索引目录：'" + indexPath + "'...");
			      Directory dir = FSDirectory.open(new File(indexPath));
			      //Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
			      Analyzer analyzer = new IKAnalyzer();
			      IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_31, analyzer);
			      iwc.setOpenMode(OpenMode.CREATE);
			      IndexWriter writer = new IndexWriter(dir, iwc);
			      indexDocs(writer, docDir);
			      writer.close();
			      Date end = new Date();
			      System.out.println(end.getTime() - start.getTime() + " 所花费的时间");
				} catch (Exception e) {
					System.out.println("建索引出错！");
				}
	}
	
	public void testSearch() {
		try{
		IndexSearcher searcher = new IndexSearcher(FSDirectory
				.open(new File("D:/fullindex/index")));
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
		// QueryParser parser = new QueryParser(Version.LUCENE_31, field,
		// analyzer);
		Query query = IKQueryParser.parse("contents", "apache");
		searcher.setSimilarity(new IKSimilarity());
		TopDocs results = searcher.search(query, 5);
		ScoreDoc[] hits = results.scoreDocs;
		for (int i = 0; i < hits.length; i++) {
			Map<String, String> map = new HashMap<String,String>();
			Document doc = searcher.doc(hits[i].doc);
			String path = doc.get("path");
			if (path != null) {
				map.put("path", path);
				System.out.println((i + 1) + ". " + path);
				String title = doc.get("title");
				if (title != null) {
					map.put("title", title);
					System.out.println("   Title: " + doc.get("title"));
				}
				String contents = doc.get("contents");
				if (contents != null) {
					map.put("contents", contents);
					System.out.println("   contents: "+ doc.get("contents"));
				}
			} else {
				System.out.println((i + 1) + ". "+ "没有找到路径！");
			}
		}
	} catch (Exception e) {
		System.out.println("全文检索出错了！");
	}
	}
	
	
	public List<Map<String, String>> search(String queryStr, int top) {
		
			List<Map<String, String>> list = new ArrayList<Map<String, String>>();
			try {
			IndexSearcher searcher = new IndexSearcher(FSDirectory
					.open(new File(indexFolder)));
			Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
			// QueryParser parser = new QueryParser(Version.LUCENE_31, field,
			// analyzer);
			Query query = IKQueryParser.parse("contents", queryStr);
			searcher.setSimilarity(new IKSimilarity());
			TopDocs results = searcher.search(query, top);
			ScoreDoc[] hits = results.scoreDocs;
			for (int i = 0; i < hits.length; i++) {
				Map<String, String> map = new HashMap<String,String>();
				Document doc = searcher.doc(hits[i].doc);
				String path = doc.get("path");
				if (path != null) {
					map.put("path", path);
					System.out.println((i + 1) + ". " + path);
					String title = doc.get("title");
					if (title != null) {
						map.put("title", title);
						System.out.println("   Title: " + doc.get("title"));
					}
					String contents = doc.get("contents");
					if (contents != null) {
						map.put("contents", contents);
						System.out.println("   contents: "+ doc.get("contents"));
					}
				} else {
					System.out.println((i + 1) + ". "+ "没有找到路径！");
				}
				list.add(map);
			}
		} catch (Exception e) {
			System.out.println("全文检索出错了！");
		}
		

		
		/*
		 * TopDocs td = null; try { Directory directory =
		 * FSDirectory.getDirectory(indexFolder); IndexSearcher isearcher = new
		 * IndexSearcher(directory); System.setProperty("mmseg.dic.path",
		 * mmseg_dic_path); Analyzer analyzer = new
		 * MMseg4jTokenizer().CreateAnalyzer(); QueryParser parser = new
		 * QueryParser("contents", analyzer); Query query =
		 * parser.parse(queryStr); td = isearcher.search(query, top);
		 * 
		 * 
		 * SimpleHTMLFormatter htmlFormatter = new
		 * SimpleHTMLFormatter(prefixHTML,suffixHTML); Highlighter highlighter =
		 * new Highlighter(htmlFormatter, new QueryScorer(query));
		 * 
		 * for (ScoreDoc sd : td.scoreDocs) { Document doc =
		 * isearcher.doc(sd.doc); Map<String, String> map = new HashMap<String,
		 * String>(); map.put("title", doc.get("title")); String content =
		 * doc.get("contents")==null?"":doc.get("contents"); content =
		 * highlighter.getBestFragment(analyzer, "contents", content);
		 * map.put("contents", content);
		 * 
		 * String path = doc.get("path")==null?"":doc.get("path"); path =
		 * URLEncoder.encode(path, "utf-8"); path = URLEncoder.encode(path,
		 * "utf-8"); // path = path.replaceAll("\\\\", "/"); // path =
		 * path.replaceFirst(resourceFolder, "files"); // String fileName =
		 * path.substring(path.lastIndexOf("/"),path.length()); // path =
		 * path.substring(0,
		 * path.lastIndexOf("/")+1)+URLEncoder.encode(fileName, "utf-8");
		 * map.put("path", path); //map.put("title", doc.get("path"));
		 * 
		 * list.add(map); }
		 * 
		 * isearcher.close(); directory.close();
		 *  } catch (Exception e) { e.printStackTrace(); }
		 */
		return list;
	}

	
		  public void indexDocs(IndexWriter writer, File file)
		    throws IOException {
		    if (file.canRead()) {
		      if (file.isDirectory()) {
		        String[] files = file.list();
		        if (files != null) {
		          for (int i = 0; i < files.length; i++) {
		            indexDocs(writer, new File(file, files[i]));
		          }
		        }
		      } else {
		        FileInputStream fis;
		        try {
		          fis = new FileInputStream(file);
		        } catch (FileNotFoundException fnfe) {
		          return;
		        }
		        try {
		          Document doc = new Document();
		          Field pathField = new Field("path", file.getPath(), Field.Store.YES, Field.Index.NOT_ANALYZED_NO_NORMS);
		          pathField.setOmitTermFreqAndPositions(true);
		          doc.add(pathField);
		          NumericField modifiedField = new NumericField("modified");
		          modifiedField.setLongValue(file.lastModified());
		          doc.add(modifiedField);
		          doc.add(new Field("contents", new BufferedReader(new InputStreamReader(fis, "UTF-8"))));
		          System.out.println("正在加裁： " + file);
		          writer.addDocument(doc);
		        } finally {
		          fis.close();
		        }
		      }
		    }
		  }

	public String getIndexFolder() {
		return indexFolder;
	}

	public void setIndexFolder(String indexFolder) {
		this.indexFolder = indexFolder;
	}

	public String getResourceFolder() {
		return resourceFolder;
	}

	public void setResourceFolder(String resourceFolder) {
		this.resourceFolder = resourceFolder;
	}

	public String getMmseg_dic_path() {
		return mmseg_dic_path;
	}

	public void setMmseg_dic_path(String mmsegDicPath) {
		mmseg_dic_path = mmsegDicPath;
	}

}
