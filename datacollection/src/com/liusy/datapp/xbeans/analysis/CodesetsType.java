



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans.analysis:
//			CodesetType

public interface CodesetsType
	extends XmlObject
{
	public static final class Factory
	{

		public static CodesetsType newInstance()
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().newInstance(CodesetsType.type, null);
		}

		public static CodesetsType newInstance(XmlOptions xmloptions)
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().newInstance(CodesetsType.type, xmloptions);
		}

		public static CodesetsType parse(String s)
			throws XmlException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(s, CodesetsType.type, null);
		}

		public static CodesetsType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(s, CodesetsType.type, xmloptions);
		}

		public static CodesetsType parse(File file)
			throws XmlException, IOException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(file, CodesetsType.type, null);
		}

		public static CodesetsType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(file, CodesetsType.type, xmloptions);
		}

		public static CodesetsType parse(URL url)
			throws XmlException, IOException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(url, CodesetsType.type, null);
		}

		public static CodesetsType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(url, CodesetsType.type, xmloptions);
		}

		public static CodesetsType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(inputstream, CodesetsType.type, null);
		}

		public static CodesetsType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(inputstream, CodesetsType.type, xmloptions);
		}

		public static CodesetsType parse(Reader reader)
			throws XmlException, IOException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(reader, CodesetsType.type, null);
		}

		public static CodesetsType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(reader, CodesetsType.type, xmloptions);
		}

		public static CodesetsType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, CodesetsType.type, null);
		}

		public static CodesetsType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, CodesetsType.type, xmloptions);
		}

		public static CodesetsType parse(Node node)
			throws XmlException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(node, CodesetsType.type, null);
		}

		public static CodesetsType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(node, CodesetsType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static CodesetsType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, CodesetsType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static CodesetsType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (CodesetsType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, CodesetsType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, CodesetsType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, CodesetsType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader(CodesetsType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("codesetstype6901type");

	public abstract CodesetType[] getCodesetArray();

	public abstract CodesetType getCodesetArray(int i);

	public abstract int sizeOfCodesetArray();

	public abstract void setCodesetArray(CodesetType acodesettype[]);

	public abstract void setCodesetArray(int i, CodesetType codesettype);

	public abstract CodesetType insertNewCodeset(int i);

	public abstract CodesetType addNewCodeset();

	public abstract void removeCodeset(int i);

}
