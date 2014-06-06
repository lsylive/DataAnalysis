



package com.liusy.datapp.xbeans.analysis;

import java.io.*;
import java.net.URL;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.*;
import org.apache.xmlbeans.xml.stream.XMLInputStream;
import org.apache.xmlbeans.xml.stream.XMLStreamException;
import org.w3c.dom.Node;

// Referenced classes of package com.liusy.datapp.xbeans.analysis:
//			ParameterType

public interface ParametersType
	extends XmlObject
{
	public static final class Factory
	{

		public static ParametersType newInstance()
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().newInstance(ParametersType.type, null);
		}

		public static ParametersType newInstance(XmlOptions xmloptions)
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().newInstance(ParametersType.type, xmloptions);
		}

		public static ParametersType parse(String s)
			throws XmlException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(s, ParametersType.type, null);
		}

		public static ParametersType parse(String s, XmlOptions xmloptions)
			throws XmlException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(s, ParametersType.type, xmloptions);
		}

		public static ParametersType parse(File file)
			throws XmlException, IOException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(file, ParametersType.type, null);
		}

		public static ParametersType parse(File file, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(file, ParametersType.type, xmloptions);
		}

		public static ParametersType parse(URL url)
			throws XmlException, IOException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(url, ParametersType.type, null);
		}

		public static ParametersType parse(URL url, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(url, ParametersType.type, xmloptions);
		}

		public static ParametersType parse(InputStream inputstream)
			throws XmlException, IOException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(inputstream, ParametersType.type, null);
		}

		public static ParametersType parse(InputStream inputstream, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(inputstream, ParametersType.type, xmloptions);
		}

		public static ParametersType parse(Reader reader)
			throws XmlException, IOException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(reader, ParametersType.type, null);
		}

		public static ParametersType parse(Reader reader, XmlOptions xmloptions)
			throws XmlException, IOException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(reader, ParametersType.type, xmloptions);
		}

		public static ParametersType parse(XMLStreamReader xmlstreamreader)
			throws XmlException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, ParametersType.type, null);
		}

		public static ParametersType parse(XMLStreamReader xmlstreamreader, XmlOptions xmloptions)
			throws XmlException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(xmlstreamreader, ParametersType.type, xmloptions);
		}

		public static ParametersType parse(Node node)
			throws XmlException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(node, ParametersType.type, null);
		}

		public static ParametersType parse(Node node, XmlOptions xmloptions)
			throws XmlException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(node, ParametersType.type, xmloptions);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static ParametersType parse(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, ParametersType.type, null);
		}

		/**
		 * @deprecated Method parse is deprecated
		 */

		public static ParametersType parse(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return (ParametersType)XmlBeans.getContextTypeLoader().parse(xmlinputstream, ParametersType.type, xmloptions);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, ParametersType.type, null);
		}

		/**
		 * @deprecated Method newValidatingXMLInputStream is deprecated
		 */

		public static XMLInputStream newValidatingXMLInputStream(XMLInputStream xmlinputstream, XmlOptions xmloptions)
			throws XmlException, XMLStreamException
		{
			return XmlBeans.getContextTypeLoader().newValidatingXMLInputStream(xmlinputstream, ParametersType.type, xmloptions);
		}

		private Factory()
		{
		}
	}


	public static final SchemaType type = (SchemaType)XmlBeans.typeSystemForClassLoader((ParametersType.class).getClassLoader(), "schemaorg_apache_xmlbeans.system.sAEB37D062E62EBB615FDCE391F095E81").resolveHandle("parameterstypedeedtype");

	public abstract ParameterType[] getParameterArray();

	public abstract ParameterType getParameterArray(int i);

	public abstract int sizeOfParameterArray();

	public abstract void setParameterArray(ParameterType aparametertype[]);

	public abstract void setParameterArray(int i, ParameterType parametertype);

	public abstract ParameterType insertNewParameter(int i);

	public abstract ParameterType addNewParameter();

	public abstract void removeParameter(int i);



}
