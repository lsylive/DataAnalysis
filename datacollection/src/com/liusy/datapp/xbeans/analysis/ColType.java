



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface ColType
	extends XmlObject
{
	public static final class Factory
	{

		public static ColType newInstance()
		{
			return (ColType)XmlBeans.getContextTypeLoader().newInstance(ColType.type, null);
		}

		public static ColType newInstance(XmlOptions xmloptions)
		{
			return (ColType)XmlBeans.getContextTypeLoader().newInstance(ColType.type, xmloptions);
		}

		public static ColType parse(String s)
			throws XmlException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(s, ColType.type, null);
		}

		public static ColType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(s, ColType.type, xmloptions);
		}

		public static ColType parse(File file)
			throws XmlException, IOException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(file, ColType.type, null);
		}

		public static ColType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(file, ColType.type, xmloptions);
		}

		public static ColType parse(URL url)
			throws XmlException, IOException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(url, ColType.type, null);
		}

		public static ColType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(url, ColType.type, xmloptions);
		}

		public static ColType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(inputstream, ColType.type, null);
		}

		public static ColType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(inputstream, ColType.type, xmloptions);
		}

		public static ColType parse(Reader reader)
			throws XmlException, IOException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(reader, ColType.type, null);
		}

		public static ColType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(reader, ColType.type, xmloptions);
		}

		public static ColType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, ColType.type, null);
		}

		public static ColType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, ColType.type, xmloptions);
		}

		public static ColType parse(Node node)
			throws XmlException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(node, ColType.type, null);
		}

		public static ColType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(node, ColType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static ColType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, ColType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static ColType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (ColType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, ColType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, ColType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, ColType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((ColType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("coltypee251type");

	public abstract String getCname();

	public abstract XmlString xgetCname();

	public abstract void setCname(String s);

	public abstract void xsetCname(XmlString xmlstring);

	public abstract String getName();

	public abstract XmlString xgetName();

	public abstract void setName(String s);

	public abstract void xsetName(XmlString xmlstring);

	public abstract String getType();

	public abstract XmlString xgetType();

	public abstract void setType(String s);

	public abstract void xsetType(XmlString xmlstring);

	public abstract String getCodeset();

	public abstract XmlString xgetCodeset();

	public abstract void setCodeset(String s);

	public abstract void xsetCodeset(XmlString xmlstring);

	public abstract String getIndicator();

	public abstract XmlString xgetIndicator();

	public abstract void setIndicator(String s);

	public abstract void xsetIndicator(XmlString xmlstring);

	public abstract String getId();

	public abstract XmlString xgetId();

	public abstract boolean isSetId();

	public abstract void setId(String s);

	public abstract void xsetId(XmlString xmlstring);

	public abstract void unsetId();


}
