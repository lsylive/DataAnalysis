



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans.analysis:
//			CodeType

public interface CodesetType
	extends XmlObject
{
	public static final class Factory
	{

		public static CodesetType newInstance()
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().newInstance(CodesetType.type, null);
		}

		public static CodesetType newInstance(XmlOptions xmloptions)
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().newInstance(CodesetType.type, xmloptions);
		}

		public static CodesetType parse(String s)
			throws XmlException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(s, CodesetType.type, null);
		}

		public static CodesetType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(s, CodesetType.type, xmloptions);
		}

		public static CodesetType parse(File file)
			throws XmlException, IOException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(file, CodesetType.type, null);
		}

		public static CodesetType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(file, CodesetType.type, xmloptions);
		}

		public static CodesetType parse(URL url)
			throws XmlException, IOException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(url, CodesetType.type, null);
		}

		public static CodesetType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(url, CodesetType.type, xmloptions);
		}

		public static CodesetType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(inputstream, CodesetType.type, null);
		}

		public static CodesetType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(inputstream, CodesetType.type, xmloptions);
		}

		public static CodesetType parse(Reader reader)
			throws XmlException, IOException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(reader, CodesetType.type, null);
		}

		public static CodesetType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(reader, CodesetType.type, xmloptions);
		}

		public static CodesetType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, CodesetType.type, null);
		}

		public static CodesetType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, CodesetType.type, xmloptions);
		}

		public static CodesetType parse(Node node)
			throws XmlException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(node, CodesetType.type, null);
		}

		public static CodesetType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(node, CodesetType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static CodesetType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, CodesetType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static CodesetType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (CodesetType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, CodesetType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, CodesetType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, CodesetType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((CodesetType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("codesettype8626type");

	public abstract CodeType[] getCodeArray();

	public abstract CodeType getCodeArray(int i);

	public abstract int sizeOfCodeArray();

	public abstract void setCodeArray(CodeType acodetype[]);

	public abstract void setCodeArray(int i, CodeType codetype);

	public abstract CodeType insertNewCode(int i);

	public abstract CodeType addNewCode();

	public abstract void removeCode(int i);

	public abstract String getName();

	public abstract XmlString xgetName();

	public abstract boolean isSetName();

	public abstract void setName(String s);

	public abstract void xsetName(XmlString xmlstring);

	public abstract void unsetName();

}
