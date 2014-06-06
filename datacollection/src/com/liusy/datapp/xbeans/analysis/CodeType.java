



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface CodeType
	extends XmlString
{
	public static final class Factory
	{

		public static CodeType newInstance()
		{
			return (CodeType)XmlBeans.getContextTypeLoader().newInstance(CodeType.type, null);
		}

		public static CodeType newInstance(XmlOptions xmloptions)
		{
			return (CodeType)XmlBeans.getContextTypeLoader().newInstance(CodeType.type, xmloptions);
		}

		public static CodeType parse(String s)
			throws XmlException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(s, CodeType.type, null);
		}

		public static CodeType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(s, CodeType.type, xmloptions);
		}

		public static CodeType parse(File file)
			throws XmlException, IOException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(file, CodeType.type, null);
		}

		public static CodeType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(file, CodeType.type, xmloptions);
		}

		public static CodeType parse(URL url)
			throws XmlException, IOException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(url, CodeType.type, null);
		}

		public static CodeType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(url, CodeType.type, xmloptions);
		}

		public static CodeType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(inputstream, CodeType.type, null);
		}

		public static CodeType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(inputstream, CodeType.type, xmloptions);
		}

		public static CodeType parse(Reader reader)
			throws XmlException, IOException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(reader, CodeType.type, null);
		}

		public static CodeType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(reader, CodeType.type, xmloptions);
		}

		public static CodeType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, CodeType.type, null);
		}

		public static CodeType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, CodeType.type, xmloptions);
		}

		public static CodeType parse(Node node)
			throws XmlException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(node, CodeType.type, null);
		}

		public static CodeType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(node, CodeType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static CodeType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, CodeType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static CodeType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (CodeType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, CodeType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, CodeType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, CodeType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((CodeType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("codetype8430type");

	public abstract String getLabel();

	public abstract XmlString xgetLabel();

	public abstract boolean isSetLabel();

	public abstract void setLabel(String s);

	public abstract void xsetLabel(XmlString xmlstring);

	public abstract void unsetLabel();

	public abstract String getData();

	public abstract XmlString xgetData();

	public abstract boolean isSetData();

	public abstract void setData(String s);

	public abstract void xsetData(XmlString xmlstring);

	public abstract void unsetData();



}
