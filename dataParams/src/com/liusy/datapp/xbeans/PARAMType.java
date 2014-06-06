



package com.liusy.datapp.xbeans;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans:
//			COLSType

public interface PARAMType
	extends XmlObject
{
	public static final class Factory
	{

		public static PARAMType newInstance()
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().newInstance(PARAMType.type, null);
		}

		public static PARAMType newInstance(XmlOptions xmloptions)
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().newInstance(PARAMType.type, xmloptions);
		}

		public static PARAMType parse(String s)
			throws XmlException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(s, PARAMType.type, null);
		}

		public static PARAMType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(s, PARAMType.type, xmloptions);
		}

		public static PARAMType parse(File file)
			throws XmlException, IOException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(file, PARAMType.type, null);
		}

		public static PARAMType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(file, PARAMType.type, xmloptions);
		}

		public static PARAMType parse(URL url)
			throws XmlException, IOException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(url, PARAMType.type, null);
		}

		public static PARAMType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(url, PARAMType.type, xmloptions);
		}

		public static PARAMType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(inputstream, PARAMType.type, null);
		}

		public static PARAMType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(inputstream, PARAMType.type, xmloptions);
		}

		public static PARAMType parse(Reader reader)
			throws XmlException, IOException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(reader, PARAMType.type, null);
		}

		public static PARAMType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(reader, PARAMType.type, xmloptions);
		}

		public static PARAMType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, PARAMType.type, null);
		}

		public static PARAMType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, PARAMType.type, xmloptions);
		}

		public static PARAMType parse(Node node)
			throws XmlException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(node, PARAMType.type, null);
		}

		public static PARAMType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(node, PARAMType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static PARAMType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, PARAMType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static PARAMType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (PARAMType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, PARAMType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, PARAMType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, PARAMType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((PARAMType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E").resolveHandle("paramtypec0fetype");

	public abstract COLSType[] getCOLSArray();

	public abstract COLSType getCOLSArray(int i);

	public abstract int sizeOfCOLSArray();

	public abstract void setCOLSArray(COLSType acolstype[]);

	public abstract void setCOLSArray(int i, COLSType colstype);

	public abstract COLSType insertNewCOLS(int i);

	public abstract COLSType addNewCOLS();

	public abstract void removeCOLS(int i);


}
