


//   MMseg4jTokenizer.java

package com.liusy.dataapplatform.base.util;

import com.chenlb.mmseg4j.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.Analyzer;

// Referenced classes of package com.liusy.dataapplatform.base.util:
//			IAnalyzerFactory

public class MMseg4jTokenizer
	implements IAnalyzerFactory
{

	public MMseg4jTokenizer()
	{
	}

	public Analyzer CreateAnalyzer()
	{
		Analyzer analyzer = new SimpleAnalyzer();
		return analyzer;
	}
}
