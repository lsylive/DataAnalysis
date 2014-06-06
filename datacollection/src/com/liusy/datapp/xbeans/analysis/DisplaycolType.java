



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans.analysis:
//			DcolType

public interface DisplaycolType
	extends XmlObject
{
	public static final class Factory
	{

		public static DisplaycolType newInstance()
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().newInstance(DisplaycolType.type, null);
		}

		public static DisplaycolType newInstance(XmlOptions xmloptions)
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().newInstance(DisplaycolType.type, xmloptions);
		}

		public static DisplaycolType parse(String s)
			throws XmlException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(s, DisplaycolType.type, null);
		}

		public static DisplaycolType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(s, DisplaycolType.type, xmloptions);
		}

		public static DisplaycolType parse(File file)
			throws XmlException, IOException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(file, DisplaycolType.type, null);
		}

		public static DisplaycolType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(file, DisplaycolType.type, xmloptions);
		}

		public static DisplaycolType parse(URL url)
			throws XmlException, IOException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(url, DisplaycolType.type, null);
		}

		public static DisplaycolType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(url, DisplaycolType.type, xmloptions);
		}

		public static DisplaycolType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(inputstream, DisplaycolType.type, null);
		}

		public static DisplaycolType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(inputstream, DisplaycolType.type, xmloptions);
		}

		public static DisplaycolType parse(Reader reader)
			throws XmlException, IOException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(reader, DisplaycolType.type, null);
		}

		public static DisplaycolType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(reader, DisplaycolType.type, xmloptions);
		}

		public static DisplaycolType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, DisplaycolType.type, null);
		}

		public static DisplaycolType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, DisplaycolType.type, xmloptions);
		}

		public static DisplaycolType parse(Node node)
			throws XmlException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(node, DisplaycolType.type, null);
		}

		public static DisplaycolType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(node, DisplaycolType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static DisplaycolType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, DisplaycolType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static DisplaycolType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (DisplaycolType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, DisplaycolType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, DisplaycolType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, DisplaycolType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((DisplaycolType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("displaycoltypecc81type");

	public abstract DcolType[] getDcolArray();

	public abstract DcolType getDcolArray(int i);

	public abstract int sizeOfDcolArray();

	public abstract void setDcolArray(DcolType adcoltype[]);

	public abstract void setDcolArray(int i, DcolType dcoltype);

	public abstract DcolType insertNewDcol(int i);

	public abstract DcolType addNewDcol();

	public abstract void removeDcol(int i);



}
