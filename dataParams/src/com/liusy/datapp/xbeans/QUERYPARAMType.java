



package com.liusy.datapp.xbeans;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans:
//			COMMBATCHQUERYType, ADVANCEQUERYType

public interface QUERYPARAMType
	extends XmlObject
{
	public static final class Factory
	{

		public static QUERYPARAMType newInstance()
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().newInstance(QUERYPARAMType.type, null);
		}

		public static QUERYPARAMType newInstance(XmlOptions xmloptions)
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().newInstance(QUERYPARAMType.type, xmloptions);
		}

		public static QUERYPARAMType parse(String s)
			throws XmlException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(s, QUERYPARAMType.type, null);
		}

		public static QUERYPARAMType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(s, QUERYPARAMType.type, xmloptions);
		}

		public static QUERYPARAMType parse(File file)
			throws XmlException, IOException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(file, QUERYPARAMType.type, null);
		}

		public static QUERYPARAMType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(file, QUERYPARAMType.type, xmloptions);
		}

		public static QUERYPARAMType parse(URL url)
			throws XmlException, IOException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(url, QUERYPARAMType.type, null);
		}

		public static QUERYPARAMType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(url, QUERYPARAMType.type, xmloptions);
		}

		public static QUERYPARAMType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(inputstream, QUERYPARAMType.type, null);
		}

		public static QUERYPARAMType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(inputstream, QUERYPARAMType.type, xmloptions);
		}

		public static QUERYPARAMType parse(Reader reader)
			throws XmlException, IOException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(reader, QUERYPARAMType.type, null);
		}

		public static QUERYPARAMType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(reader, QUERYPARAMType.type, xmloptions);
		}

		public static QUERYPARAMType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, QUERYPARAMType.type, null);
		}

		public static QUERYPARAMType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, QUERYPARAMType.type, xmloptions);
		}

		public static QUERYPARAMType parse(Node node)
			throws XmlException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(node, QUERYPARAMType.type, null);
		}

		public static QUERYPARAMType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(node, QUERYPARAMType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static QUERYPARAMType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, QUERYPARAMType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static QUERYPARAMType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (QUERYPARAMType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, QUERYPARAMType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, QUERYPARAMType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, QUERYPARAMType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((QUERYPARAMType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E").resolveHandle("queryparamtype6048type");

	public abstract COMMBATCHQUERYType getCOMMBATCHQUERY();

	public abstract boolean isSetCOMMBATCHQUERY();

	public abstract void setCOMMBATCHQUERY(COMMBATCHQUERYType commbatchquerytype);

	public abstract COMMBATCHQUERYType addNewCOMMBATCHQUERY();

	public abstract void unsetCOMMBATCHQUERY();

	public abstract ADVANCEQUERYType getADVANCEQUERY();

	public abstract boolean isSetADVANCEQUERY();

	public abstract void setADVANCEQUERY(ADVANCEQUERYType advancequerytype);

	public abstract ADVANCEQUERYType addNewADVANCEQUERY();

	public abstract void unsetADVANCEQUERY();


}
