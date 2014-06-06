



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans.analysis:
//			ColType

public interface MetaType
	extends XmlObject
{
	public static final class Factory
	{

		public static MetaType newInstance()
		{
			return (MetaType)XmlBeans.getContextTypeLoader().newInstance(MetaType.type, null);
		}

		public static MetaType newInstance(XmlOptions xmloptions)
		{
			return (MetaType)XmlBeans.getContextTypeLoader().newInstance(MetaType.type, xmloptions);
		}

		public static MetaType parse(String s)
			throws XmlException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(s, MetaType.type, null);
		}

		public static MetaType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(s, MetaType.type, xmloptions);
		}

		public static MetaType parse(File file)
			throws XmlException, IOException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(file, MetaType.type, null);
		}

		public static MetaType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(file, MetaType.type, xmloptions);
		}

		public static MetaType parse(URL url)
			throws XmlException, IOException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(url, MetaType.type, null);
		}

		public static MetaType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(url, MetaType.type, xmloptions);
		}

		public static MetaType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(inputstream, MetaType.type, null);
		}

		public static MetaType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(inputstream, MetaType.type, xmloptions);
		}

		public static MetaType parse(Reader reader)
			throws XmlException, IOException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(reader, MetaType.type, null);
		}

		public static MetaType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(reader, MetaType.type, xmloptions);
		}

		public static MetaType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, MetaType.type, null);
		}

		public static MetaType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, MetaType.type, xmloptions);
		}

		public static MetaType parse(Node node)
			throws XmlException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(node, MetaType.type, null);
		}

		public static MetaType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(node, MetaType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static MetaType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, MetaType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static MetaType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (MetaType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, MetaType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, MetaType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, MetaType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((MetaType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("metatype2048type");

	public abstract ColType[] getColArray();

	public abstract ColType getColArray(int i);

	public abstract int sizeOfColArray();

	public abstract void setColArray(ColType acoltype[]);

	public abstract void setColArray(int i, ColType coltype);

	public abstract ColType insertNewCol(int i);

	public abstract ColType addNewCol();

	public abstract void removeCol(int i);



}
