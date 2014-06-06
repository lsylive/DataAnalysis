



package com.liusy.datapp.xbeans;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

public interface LINESType
	extends XmlObject
{
	public static final class Factory
	{

		public static LINESType newInstance()
		{
			return (LINESType)XmlBeans.getContextTypeLoader().newInstance(LINESType.type, null);
		}

		public static LINESType newInstance(XmlOptions xmloptions)
		{
			return (LINESType)XmlBeans.getContextTypeLoader().newInstance(LINESType.type, xmloptions);
		}

		public static LINESType parse(String s)
			throws XmlException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(s, LINESType.type, null);
		}

		public static LINESType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(s, LINESType.type, xmloptions);
		}

		public static LINESType parse(File file)
			throws XmlException, IOException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(file, LINESType.type, null);
		}

		public static LINESType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(file, LINESType.type, xmloptions);
		}

		public static LINESType parse(URL url)
			throws XmlException, IOException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(url, LINESType.type, null);
		}

		public static LINESType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(url, LINESType.type, xmloptions);
		}

		public static LINESType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(inputstream, LINESType.type, null);
		}

		public static LINESType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(inputstream, LINESType.type, xmloptions);
		}

		public static LINESType parse(Reader reader)
			throws XmlException, IOException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(reader, LINESType.type, null);
		}

		public static LINESType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(reader, LINESType.type, xmloptions);
		}

		public static LINESType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, LINESType.type, null);
		}

		public static LINESType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, LINESType.type, xmloptions);
		}

		public static LINESType parse(Node node)
			throws XmlException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(node, LINESType.type, null);
		}

		public static LINESType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(node, LINESType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static LINESType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, LINESType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static LINESType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (LINESType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, LINESType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, LINESType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, LINESType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((LINESType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E").resolveHandle("linestype6e70type");

	public abstract String getLINKOPER();

	public abstract XmlString xgetLINKOPER();

	public abstract void setLINKOPER(String s);

	public abstract void xsetLINKOPER(XmlString xmlstring);

	public abstract String getPREVOPER();

	public abstract XmlString xgetPREVOPER();

	public abstract void setPREVOPER(String s);

	public abstract void xsetPREVOPER(XmlString xmlstring);

	public abstract String getCOLID();

	public abstract XmlString xgetCOLID();

	public abstract void setCOLID(String s);

	public abstract void xsetCOLID(XmlString xmlstring);

	public abstract String getOPER();

	public abstract XmlString xgetOPER();

	public abstract void setOPER(String s);

	public abstract void xsetOPER(XmlString xmlstring);

	public abstract String getPARAMVALUE1();

	public abstract XmlString xgetPARAMVALUE1();

	public abstract void setPARAMVALUE1(String s);

	public abstract void xsetPARAMVALUE1(XmlString xmlstring);

	public abstract String getPARAMVALUE2();

	public abstract XmlString xgetPARAMVALUE2();

	public abstract void setPARAMVALUE2(String s);

	public abstract void xsetPARAMVALUE2(XmlString xmlstring);

	public abstract String getNEXTOPER();

	public abstract XmlString xgetNEXTOPER();

	public abstract void setNEXTOPER(String s);

	public abstract void xsetNEXTOPER(XmlString xmlstring);

}
