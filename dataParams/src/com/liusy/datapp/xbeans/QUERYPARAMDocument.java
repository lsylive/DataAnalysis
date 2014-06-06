



package com.liusy.datapp.xbeans;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans:
//			QUERYPARAMType

public interface QUERYPARAMDocument
	extends XmlObject
{
	public static final class Factory
	{

		public static QUERYPARAMDocument newInstance()
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().newInstance(QUERYPARAMDocument.type, null);
		}

		public static QUERYPARAMDocument newInstance(XmlOptions xmloptions)
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().newInstance(QUERYPARAMDocument.type, xmloptions);
		}

		public static QUERYPARAMDocument parse(String s)
			throws XmlException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(s, QUERYPARAMDocument.type, null);
		}

		public static QUERYPARAMDocument parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(s, QUERYPARAMDocument.type, xmloptions);
		}

		public static QUERYPARAMDocument parse(File file)
			throws XmlException, IOException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(file, QUERYPARAMDocument.type, null);
		}

		public static QUERYPARAMDocument parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(file, QUERYPARAMDocument.type, xmloptions);
		}

		public static QUERYPARAMDocument parse(URL url)
			throws XmlException, IOException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(url, QUERYPARAMDocument.type, null);
		}

		public static QUERYPARAMDocument parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(url, QUERYPARAMDocument.type, xmloptions);
		}

		public static QUERYPARAMDocument parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(inputstream, QUERYPARAMDocument.type, null);
		}

		public static QUERYPARAMDocument parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(inputstream, QUERYPARAMDocument.type, xmloptions);
		}

		public static QUERYPARAMDocument parse(Reader reader)
			throws XmlException, IOException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(reader, QUERYPARAMDocument.type, null);
		}

		public static QUERYPARAMDocument parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(reader, QUERYPARAMDocument.type, xmloptions);
		}

		public static QUERYPARAMDocument parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, QUERYPARAMDocument.type, null);
		}

		public static QUERYPARAMDocument parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, QUERYPARAMDocument.type, xmloptions);
		}

		public static QUERYPARAMDocument parse(Node node)
			throws XmlException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(node, QUERYPARAMDocument.type, null);
		}

		public static QUERYPARAMDocument parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(node, QUERYPARAMDocument.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static QUERYPARAMDocument parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(xmlinputstream, QUERYPARAMDocument.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static QUERYPARAMDocument parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (QUERYPARAMDocument)XmlBeans.getContextTypeLoader().parse(xmlinputstream, QUERYPARAMDocument.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, QUERYPARAMDocument.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, QUERYPARAMDocument.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((QUERYPARAMDocument.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E").resolveHandle("queryparamadfedoctype");

	public abstract QUERYPARAMType getQUERYPARAM();

	public abstract void setQUERYPARAM(QUERYPARAMType queryparamtype);

	public abstract QUERYPARAMType addNewQUERYPARAM();


}
