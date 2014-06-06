



package schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.xmlbeans.SchemaTypeSystem;

public class TypeSystemHolder
{

	public static final SchemaTypeSystem typeSystem = loadTypeSystem();

	private TypeSystemHolder()
	{
	}

	private static final SchemaTypeSystem loadTypeSystem()
	{
		try {
			return (SchemaTypeSystem)Class.forName("org.apache.xmlbeans.impl.schema.SchemaTypeSystemImpl", true, (schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E.TypeSystemHolder.class).getClassLoader()).getConstructor(new Class[] {
				java.lang.Class.class
			}).newInstance(new Object[] {
				schemaorg_apache_xmlbeans.system.sE579F271F594572CFC8F36D29A7C261E.TypeSystemHolder.class
			});
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Cannot load org.apache.xmlbeans.impl.SchemaTypeSystemImpl: make sure xbean.jar is on the classpath.", ((Throwable) (e)));
		} catch (Exception e) {
			throw new RuntimeException("Could not instantiate SchemaTypeSystemImpl (" + ((Exception) (e)).toString() + "): is the version of xbean.jar correct?", ((Throwable) (e)));
		}
	}

}
