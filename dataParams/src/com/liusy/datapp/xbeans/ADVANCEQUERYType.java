



package com.liusy.datapp.xbeans;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans:
//			LINESType

public interface ADVANCEQUERYType
	extends XmlObject
{
	public static final class Factory
	{

		public static ADVANCEQUERYType newInstance()
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().newInstance(ADVANCEQUERYType.type, null);
		}

		public static ADVANCEQUERYType newInstance(XmlOptions xmloptions)
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().newInstance(ADVANCEQUERYType.type, xmloptions);
		}

		public static ADVANCEQUERYType parse(String s)
			throws XmlException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(s, ADVANCEQUERYType.type, null);
		}

		public static ADVANCEQUERYType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(s, ADVANCEQUERYType.type, xmloptions);
		}

		public static ADVANCEQUERYType parse(File file)
			throws XmlException, IOException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(file, ADVANCEQUERYType.type, null);
		}

		public static ADVANCEQUERYType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(file, ADVANCEQUERYType.type, xmloptions);
		}

		public static ADVANCEQUERYType parse(URL url)
			throws XmlException, IOException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(url, ADVANCEQUERYType.type, null);
		}

		public static ADVANCEQUERYType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(url, ADVANCEQUERYType.type, xmloptions);
		}

		public static ADVANCEQUERYType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(inputstream, ADVANCEQUERYType.type, null);
		}

		public static ADVANCEQUERYType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(inputstream, ADVANCEQUERYType.type, xmloptions);
		}

		public static ADVANCEQUERYType parse(Reader reader)
			throws XmlException, IOException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(reader, ADVANCEQUERYType.type, null);
		}

		public static ADVANCEQUERYType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(reader, ADVANCEQUERYType.type, xmloptions);
		}

		public static ADVANCEQUERYType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, ADVANCEQUERYType.type, null);
		}

		public static ADVANCEQUERYType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, ADVANCEQUERYType.type, xmloptions);
		}

		public static ADVANCEQUERYType parse(Node node)
			throws XmlException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(node, ADVANCEQUERYType.type, null);
		}

		public static ADVANCEQUERYType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(node, ADVANCEQUERYType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static ADVANCEQUERYType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, ADVANCEQUERYType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static ADVANCEQUERYType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (ADVANCEQUERYType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, ADVANCEQUERYType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, ADVANCEQUERYType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, ADVANCEQUERYType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((ADVANCEQUERYType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E").resolveHandle("advancequerytype4249type");

	public abstract String getATABLEID();

	public abstract XmlString xgetATABLEID();

	public abstract void setATABLEID(String s);

	public abstract void xsetATABLEID(XmlString xmlstring);

	public abstract LINESType[] getLINESArray();

	public abstract LINESType getLINESArray(int i);

	public abstract int sizeOfLINESArray();

	public abstract void setLINESArray(LINESType alinestype[]);

	public abstract void setLINESArray(int i, LINESType linestype);

	public abstract LINESType insertNewLINES(int i);

	public abstract LINESType addNewLINES();

	public abstract void removeLINES(int i);


}
