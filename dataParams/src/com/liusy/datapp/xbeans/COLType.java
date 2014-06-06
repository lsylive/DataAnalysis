



package com.liusy.datapp.xbeans;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface COLType
	extends XmlString
{
	public static final class Factory
	{

		public static COLType newInstance()
		{
			return (COLType)XmlBeans.getContextTypeLoader().newInstance(COLType.type, null);
		}

		public static COLType newInstance(XmlOptions xmloptions)
		{
			return (COLType)XmlBeans.getContextTypeLoader().newInstance(COLType.type, xmloptions);
		}

		public static COLType parse(String s)
			throws XmlException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(s, COLType.type, null);
		}

		public static COLType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(s, COLType.type, xmloptions);
		}

		public static COLType parse(File file)
			throws XmlException, IOException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(file, COLType.type, null);
		}

		public static COLType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(file, COLType.type, xmloptions);
		}

		public static COLType parse(URL url)
			throws XmlException, IOException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(url, COLType.type, null);
		}

		public static COLType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(url, COLType.type, xmloptions);
		}

		public static COLType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(inputstream, COLType.type, null);
		}

		public static COLType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(inputstream, COLType.type, xmloptions);
		}

		public static COLType parse(Reader reader)
			throws XmlException, IOException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(reader, COLType.type, null);
		}

		public static COLType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(reader, COLType.type, xmloptions);
		}

		public static COLType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, COLType.type, null);
		}

		public static COLType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, COLType.type, xmloptions);
		}

		public static COLType parse(Node node)
			throws XmlException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(node, COLType.type, null);
		}

		public static COLType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(node, COLType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static COLType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, COLType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static COLType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (COLType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, COLType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, COLType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, COLType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((COLType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E").resolveHandle("coltype7631type");

	public abstract String getId();

	public abstract XmlString xgetId();

	public abstract boolean isSetId();

	public abstract void setId(String s);

	public abstract void xsetId(XmlString xmlstring);

	public abstract void unsetId();

}
