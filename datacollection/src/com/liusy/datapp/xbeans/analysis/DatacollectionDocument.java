



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans.analysis:
//			DatacollectionType

public interface DatacollectionDocument
	extends XmlObject
{
	public static final class Factory
	{

		public static DatacollectionDocument newInstance()
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().newInstance(DatacollectionDocument.type, null);
		}

		public static DatacollectionDocument newInstance(XmlOptions xmloptions)
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().newInstance(DatacollectionDocument.type, xmloptions);
		}

		public static DatacollectionDocument parse(String s)
			throws XmlException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(s, DatacollectionDocument.type, null);
		}

		public static DatacollectionDocument parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(s, DatacollectionDocument.type, xmloptions);
		}

		public static DatacollectionDocument parse(File file)
			throws XmlException, IOException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(file, DatacollectionDocument.type, null);
		}

		public static DatacollectionDocument parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(file, DatacollectionDocument.type, xmloptions);
		}

		public static DatacollectionDocument parse(URL url)
			throws XmlException, IOException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(url, DatacollectionDocument.type, null);
		}

		public static DatacollectionDocument parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(url, DatacollectionDocument.type, xmloptions);
		}

		public static DatacollectionDocument parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(inputstream, DatacollectionDocument.type, null);
		}

		public static DatacollectionDocument parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(inputstream, DatacollectionDocument.type, xmloptions);
		}

		public static DatacollectionDocument parse(Reader reader)
			throws XmlException, IOException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(reader, DatacollectionDocument.type, null);
		}

		public static DatacollectionDocument parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(reader, DatacollectionDocument.type, xmloptions);
		}

		public static DatacollectionDocument parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, DatacollectionDocument.type, null);
		}

		public static DatacollectionDocument parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, DatacollectionDocument.type, xmloptions);
		}

		public static DatacollectionDocument parse(Node node)
			throws XmlException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(node, DatacollectionDocument.type, null);
		}

		public static DatacollectionDocument parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(node, DatacollectionDocument.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static DatacollectionDocument parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(xmlinputstream, DatacollectionDocument.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static DatacollectionDocument parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (DatacollectionDocument)XmlBeans.getContextTypeLoader().parse(xmlinputstream, DatacollectionDocument.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, DatacollectionDocument.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, DatacollectionDocument.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((DatacollectionDocument.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("datacollectiond281doctype");

	public abstract DatacollectionType getDatacollection();

	public abstract void setDatacollection(DatacollectionType datacollectiontype);

	public abstract DatacollectionType addNewDatacollection();



}
