



package com.liusy.datapp.xbeans;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans:
//			PARAMType

public interface PARAMSType
	extends XmlObject
{
	public static final class Factory
	{

		public static PARAMSType newInstance()
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().newInstance(PARAMSType.type, null);
		}

		public static PARAMSType newInstance(XmlOptions xmloptions)
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().newInstance(PARAMSType.type, xmloptions);
		}

		public static PARAMSType parse(String s)
			throws XmlException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(s, PARAMSType.type, null);
		}

		public static PARAMSType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(s, PARAMSType.type, xmloptions);
		}

		public static PARAMSType parse(File file)
			throws XmlException, IOException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(file, PARAMSType.type, null);
		}

		public static PARAMSType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(file, PARAMSType.type, xmloptions);
		}

		public static PARAMSType parse(URL url)
			throws XmlException, IOException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(url, PARAMSType.type, null);
		}

		public static PARAMSType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(url, PARAMSType.type, xmloptions);
		}

		public static PARAMSType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(inputstream, PARAMSType.type, null);
		}

		public static PARAMSType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(inputstream, PARAMSType.type, xmloptions);
		}

		public static PARAMSType parse(Reader reader)
			throws XmlException, IOException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(reader, PARAMSType.type, null);
		}

		public static PARAMSType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(reader, PARAMSType.type, xmloptions);
		}

		public static PARAMSType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, PARAMSType.type, null);
		}

		public static PARAMSType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, PARAMSType.type, xmloptions);
		}

		public static PARAMSType parse(Node node)
			throws XmlException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(node, PARAMSType.type, null);
		}

		public static PARAMSType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(node, PARAMSType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static PARAMSType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, PARAMSType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static PARAMSType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (PARAMSType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, PARAMSType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, PARAMSType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, PARAMSType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((PARAMSType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E").resolveHandle("paramstype9909type");

	public abstract PARAMType getPARAM();

	public abstract void setPARAM(PARAMType paramtype);

	public abstract PARAMType addNewPARAM();


}
