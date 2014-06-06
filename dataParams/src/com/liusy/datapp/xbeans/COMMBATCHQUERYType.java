



package com.liusy.datapp.xbeans;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans:
//			PARAMSType

public interface COMMBATCHQUERYType
	extends XmlObject
{
	public static final class Factory
	{

		public static COMMBATCHQUERYType newInstance()
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().newInstance(COMMBATCHQUERYType.type, null);
		}

		public static COMMBATCHQUERYType newInstance(XmlOptions xmloptions)
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().newInstance(COMMBATCHQUERYType.type, xmloptions);
		}

		public static COMMBATCHQUERYType parse(String s)
			throws XmlException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(s, COMMBATCHQUERYType.type, null);
		}

		public static COMMBATCHQUERYType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(s, COMMBATCHQUERYType.type, xmloptions);
		}

		public static COMMBATCHQUERYType parse(File file)
			throws XmlException, IOException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(file, COMMBATCHQUERYType.type, null);
		}

		public static COMMBATCHQUERYType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(file, COMMBATCHQUERYType.type, xmloptions);
		}

		public static COMMBATCHQUERYType parse(URL url)
			throws XmlException, IOException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(url, COMMBATCHQUERYType.type, null);
		}

		public static COMMBATCHQUERYType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(url, COMMBATCHQUERYType.type, xmloptions);
		}

		public static COMMBATCHQUERYType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(inputstream, COMMBATCHQUERYType.type, null);
		}

		public static COMMBATCHQUERYType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(inputstream, COMMBATCHQUERYType.type, xmloptions);
		}

		public static COMMBATCHQUERYType parse(Reader reader)
			throws XmlException, IOException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(reader, COMMBATCHQUERYType.type, null);
		}

		public static COMMBATCHQUERYType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(reader, COMMBATCHQUERYType.type, xmloptions);
		}

		public static COMMBATCHQUERYType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, COMMBATCHQUERYType.type, null);
		}

		public static COMMBATCHQUERYType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, COMMBATCHQUERYType.type, xmloptions);
		}

		public static COMMBATCHQUERYType parse(Node node)
			throws XmlException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(node, COMMBATCHQUERYType.type, null);
		}

		public static COMMBATCHQUERYType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(node, COMMBATCHQUERYType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static COMMBATCHQUERYType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, COMMBATCHQUERYType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static COMMBATCHQUERYType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (COMMBATCHQUERYType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, COMMBATCHQUERYType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, COMMBATCHQUERYType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, COMMBATCHQUERYType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((COMMBATCHQUERYType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E").resolveHandle("commbatchquerytype125dtype");

	public abstract String getSUBJECTID();

	public abstract XmlString xgetSUBJECTID();

	public abstract void setSUBJECTID(String s);

	public abstract void xsetSUBJECTID(XmlString xmlstring);

	public abstract String getSELTABS();

	public abstract XmlString xgetSELTABS();

	public abstract void setSELTABS(String s);

	public abstract void xsetSELTABS(XmlString xmlstring);

	public abstract String getTABLEID();

	public abstract XmlString xgetTABLEID();

	public abstract void setTABLEID(String s);

	public abstract void xsetTABLEID(XmlString xmlstring);

	public abstract PARAMSType getPARAMS();

	public abstract void setPARAMS(PARAMSType paramstype);

	public abstract PARAMSType addNewPARAMS();


}
