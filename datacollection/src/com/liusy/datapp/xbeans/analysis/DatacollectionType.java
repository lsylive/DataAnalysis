



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans.analysis:
//			ParametersType, MetaType, DisplaycolType, CodesetsType

public interface DatacollectionType
	extends XmlObject
{
	public static final class Factory
	{

		public static DatacollectionType newInstance()
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().newInstance(DatacollectionType.type, null);
		}

		public static DatacollectionType newInstance(XmlOptions xmloptions)
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().newInstance(DatacollectionType.type, xmloptions);
		}

		public static DatacollectionType parse(String s)
			throws XmlException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(s, DatacollectionType.type, null);
		}

		public static DatacollectionType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(s, DatacollectionType.type, xmloptions);
		}

		public static DatacollectionType parse(File file)
			throws XmlException, IOException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(file, DatacollectionType.type, null);
		}

		public static DatacollectionType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(file, DatacollectionType.type, xmloptions);
		}

		public static DatacollectionType parse(URL url)
			throws XmlException, IOException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(url, DatacollectionType.type, null);
		}

		public static DatacollectionType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(url, DatacollectionType.type, xmloptions);
		}

		public static DatacollectionType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(inputstream, DatacollectionType.type, null);
		}

		public static DatacollectionType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(inputstream, DatacollectionType.type, xmloptions);
		}

		public static DatacollectionType parse(Reader reader)
			throws XmlException, IOException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(reader, DatacollectionType.type, null);
		}

		public static DatacollectionType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(reader, DatacollectionType.type, xmloptions);
		}

		public static DatacollectionType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, DatacollectionType.type, null);
		}

		public static DatacollectionType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, DatacollectionType.type, xmloptions);
		}

		public static DatacollectionType parse(Node node)
			throws XmlException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(node, DatacollectionType.type, null);
		}

		public static DatacollectionType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(node, DatacollectionType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static DatacollectionType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, DatacollectionType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static DatacollectionType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (DatacollectionType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, DatacollectionType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, DatacollectionType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, DatacollectionType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((DatacollectionType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("datacollectiontype134btype");

	public abstract String getTemplateid();

	public abstract XmlString xgetTemplateid();

	public abstract void setTemplateid(String s);

	public abstract void xsetTemplateid(XmlString xmlstring);

	public abstract ParametersType getParameters();

	public abstract void setParameters(ParametersType parameterstype);

	public abstract ParametersType addNewParameters();

	public abstract MetaType getMeta();

	public abstract void setMeta(MetaType metatype);

	public abstract MetaType addNewMeta();

	public abstract DisplaycolType getDisplaycol();

	public abstract void setDisplaycol(DisplaycolType displaycoltype);

	public abstract DisplaycolType addNewDisplaycol();

	public abstract CodesetsType getCodesets();

	public abstract void setCodesets(CodesetsType codesetstype);

	public abstract CodesetsType addNewCodesets();


}
