



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface DcolType
	extends XmlObject
{
	public static final class Factory
	{

		public static DcolType newInstance()
		{
			return (DcolType)XmlBeans.getContextTypeLoader().newInstance(DcolType.type, null);
		}

		public static DcolType newInstance(XmlOptions xmloptions)
		{
			return (DcolType)XmlBeans.getContextTypeLoader().newInstance(DcolType.type, xmloptions);
		}

		public static DcolType parse(String s)
			throws XmlException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(s, DcolType.type, null);
		}

		public static DcolType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(s, DcolType.type, xmloptions);
		}

		public static DcolType parse(File file)
			throws XmlException, IOException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(file, DcolType.type, null);
		}

		public static DcolType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(file, DcolType.type, xmloptions);
		}

		public static DcolType parse(URL url)
			throws XmlException, IOException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(url, DcolType.type, null);
		}

		public static DcolType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(url, DcolType.type, xmloptions);
		}

		public static DcolType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(inputstream, DcolType.type, null);
		}

		public static DcolType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(inputstream, DcolType.type, xmloptions);
		}

		public static DcolType parse(Reader reader)
			throws XmlException, IOException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(reader, DcolType.type, null);
		}

		public static DcolType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(reader, DcolType.type, xmloptions);
		}

		public static DcolType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, DcolType.type, null);
		}

		public static DcolType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, DcolType.type, xmloptions);
		}

		public static DcolType parse(Node node)
			throws XmlException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(node, DcolType.type, null);
		}

		public static DcolType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(node, DcolType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static DcolType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, DcolType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static DcolType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (DcolType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, DcolType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, DcolType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, DcolType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((DcolType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("dcoltypedd5ftype");

	public abstract String getCname();

	public abstract XmlString xgetCname();

	public abstract void setCname(String s);

	public abstract void xsetCname(XmlString xmlstring);

	public abstract String getName();

	public abstract XmlString xgetName();

	public abstract void setName(String s);

	public abstract void xsetName(XmlString xmlstring);

	public abstract String getVisible();

	public abstract XmlString xgetVisible();

	public abstract void setVisible(String s);

	public abstract void xsetVisible(XmlString xmlstring);



}
