



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ParameterType
	extends XmlObject
{
	public static final class Factory
	{

		public static ParameterType newInstance()
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().newInstance(ParameterType.type, null);
		}

		public static ParameterType newInstance(XmlOptions xmloptions)
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().newInstance(ParameterType.type, xmloptions);
		}

		public static ParameterType parse(String s)
			throws XmlException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(s, ParameterType.type, null);
		}

		public static ParameterType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(s, ParameterType.type, xmloptions);
		}

		public static ParameterType parse(File file)
			throws XmlException, IOException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(file, ParameterType.type, null);
		}

		public static ParameterType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(file, ParameterType.type, xmloptions);
		}

		public static ParameterType parse(URL url)
			throws XmlException, IOException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(url, ParameterType.type, null);
		}

		public static ParameterType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(url, ParameterType.type, xmloptions);
		}

		public static ParameterType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(inputstream, ParameterType.type, null);
		}

		public static ParameterType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(inputstream, ParameterType.type, xmloptions);
		}

		public static ParameterType parse(Reader reader)
			throws XmlException, IOException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(reader, ParameterType.type, null);
		}

		public static ParameterType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(reader, ParameterType.type, xmloptions);
		}

		public static ParameterType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, ParameterType.type, null);
		}

		public static ParameterType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, ParameterType.type, xmloptions);
		}

		public static ParameterType parse(Node node)
			throws XmlException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(node, ParameterType.type, null);
		}

		public static ParameterType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(node, ParameterType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static ParameterType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, ParameterType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static ParameterType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (ParameterType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, ParameterType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, ParameterType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, ParameterType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((ParameterType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("parametertypea2batype");

	public abstract String getName();

	public abstract XmlString xgetName();

	public abstract void setName(String s);

	public abstract void xsetName(XmlString xmlstring);

	public abstract String getCname();

	public abstract XmlString xgetCname();

	public abstract void setCname(String s);

	public abstract void xsetCname(XmlString xmlstring);

	public abstract String getType();

	public abstract XmlString xgetType();

	public abstract void setType(String s);

	public abstract void xsetType(XmlString xmlstring);

	public abstract String getCodeset();

	public abstract XmlString xgetCodeset();

	public abstract void setCodeset(String s);

	public abstract void xsetCodeset(XmlString xmlstring);

	public abstract String getIsbetween();

	public abstract XmlString xgetIsbetween();

	public abstract void setIsbetween(String s);

	public abstract void xsetIsbetween(XmlString xmlstring);


}
