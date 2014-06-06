



package com.liusy.datapp.xbeans;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans:
//			COLType

public interface COLSType
	extends XmlObject
{
	public static final class Factory
	{

		public static COLSType newInstance()
		{
			return (COLSType)XmlBeans.getContextTypeLoader().newInstance(COLSType.type, null);
		}

		public static COLSType newInstance(XmlOptions xmloptions)
		{
			return (COLSType)XmlBeans.getContextTypeLoader().newInstance(COLSType.type, xmloptions);
		}

		public static COLSType parse(String s)
			throws XmlException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(s, COLSType.type, null);
		}

		public static COLSType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(s, COLSType.type, xmloptions);
		}

		public static COLSType parse(File file)
			throws XmlException, IOException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(file, COLSType.type, null);
		}

		public static COLSType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(file, COLSType.type, xmloptions);
		}

		public static COLSType parse(URL url)
			throws XmlException, IOException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(url, COLSType.type, null);
		}

		public static COLSType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(url, COLSType.type, xmloptions);
		}

		public static COLSType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(inputstream, COLSType.type, null);
		}

		public static COLSType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(inputstream, COLSType.type, xmloptions);
		}

		public static COLSType parse(Reader reader)
			throws XmlException, IOException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(reader, COLSType.type, null);
		}

		public static COLSType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(reader, COLSType.type, xmloptions);
		}

		public static COLSType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, COLSType.type, null);
		}

		public static COLSType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, COLSType.type, xmloptions);
		}

		public static COLSType parse(Node node)
			throws XmlException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(node, COLSType.type, null);
		}

		public static COLSType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(node, COLSType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static COLSType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, COLSType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static COLSType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (COLSType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, COLSType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, COLSType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, COLSType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((COLSType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E").resolveHandle("colstype8a36type");

	public abstract COLType[] getCOLArray();

	public abstract COLType getCOLArray(int i);

	public abstract int sizeOfCOLArray();

	public abstract void setCOLArray(COLType acoltype[]);

	public abstract void setCOLArray(int i, COLType coltype);

	public abstract COLType insertNewCOL(int i);

	public abstract COLType addNewCOL();

	public abstract void removeCOL(int i);

}
