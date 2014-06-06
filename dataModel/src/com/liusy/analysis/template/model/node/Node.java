
package com.liusy.analysis.template.model.node;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Element;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import java.util.*;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public abstract class Node<T> extends Element
  implements IPropertySource
{
  private static final long serialVersionUID = 1L;
  public static final String PROP_LOCATION = "LOCATION";
  public static final String PROP_NAME = "NAME";
  public static final String PROP_VISIBLE = "VISIBLE";
  public static final String PROP_INPUTS = "INPUTS";
  public static final String PROP_OUTPUTS = "OUTPUTS";
  protected Point location = new Point(0, 0);
  protected int id = 0;
  protected String name = "";
  protected String description = "";
  protected Diagram diagram;
  protected boolean visible = true;
  protected T properties;
  protected List<Map<String, Object>> resultSet = null;

  protected boolean runFlag = false;

  protected List<Connection> outputs = new ArrayList<Connection>();
  protected List<Connection> inputs = new ArrayList<Connection>();

  protected Map<String, Metadata> getMeta(List<Metadata> metas)
  {
    Map<String, Metadata> map = new HashMap<String, Metadata>();
    for (Metadata mt : metas) {
      map.put(mt.getName(), mt);
    }
    return map;
  }

  public T getProperties() {
    return this.properties;
  }

  public void setProperties(T pro) {
    this.properties = pro;
  }

  public int getId()
  {
    return this.id;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    if (this.name.equals(name)) return;
    this.name = name;

    firePropertyChange("NAME", null, name);
  }

  public abstract List<Metadata> getMeta();

  public void addInput(Connection connection)
  {
    this.inputs.add(connection);

    fireStructureChange("INPUTS", connection);
  }

  public void addOutput(Connection connection)
  {
    this.outputs.add(connection);

    fireStructureChange("OUTPUTS", connection);
  }

  public List<Connection> getIncomingConnections()
  {
    return this.inputs;
  }

  public List<Connection> getOutgoingConnections()
  {
    return this.outputs;
  }

  public void removeInput(Connection connection)
  {
    this.inputs.remove(connection);
    fireStructureChange("INPUTS", connection);
  }

  public void removeOutput(Connection connection)
  {
    this.outputs.remove(connection);
    fireStructureChange("OUTPUTS", connection);
  }

  public boolean isVisible()
  {
    return this.visible;
  }

  public void setVisible(boolean visible)
  {
    if (this.visible == visible) return;
    this.visible = visible;
    firePropertyChange("VISIBLE", null, Boolean.valueOf(visible));
  }

	public void setLocation(Point paramPoint) {
	    if (this.location.equals(paramPoint)) return;
	    this.location = paramPoint;
	
	    firePropertyChange("LOCATION", null, paramPoint);
	}
  public Point getLocation()
  {
    return this.location;
  }
  

  public Object getEditableValue()
  {
    return this;
  }

  public Object getPropertyValue(Object id)
  {
    if ("NAME".equals(id)) return getName();
    if ("VISIBLE".equals(id)) return isVisible() ? new Integer(0) : new Integer(1);
    return null;
  }

  public boolean isPropertySet(Object id)
  {
    return true;
  }

  public void resetPropertyValue(Object id)
  {
  }

  public void setPropertyValue(Object id, Object value)
  {
    if ("NAME".equals(id)) setName((String)value);
    if ("VISIBLE".equals(id)) setVisible(((Integer)value).intValue() == 0);
  }

  public IPropertyDescriptor[] getPropertyDescriptors()
  {
    return null;
  }

  public Diagram getDiagram()
  {
    return this.diagram;
  }

  public void setDiagram(Diagram diagram)
  {
    this.diagram = diagram;
  }

  public String getDescription()
  {
    return this.description;
  }

  public void setDescription(String description)
  {
    this.description = description;
  }

  public boolean isRunFlag() {
    return this.runFlag;
  }

  public void setRunFlag(boolean runFlag) {
    this.runFlag = runFlag;
  }

  public List<Map<String, Object>> getResultSet() {
    return this.resultSet;
  }

  public void setResultSet(List<Map<String, Object>> resultSet) {
    this.resultSet = resultSet;
  }

  public boolean isEnable() {
    return true;
  }
  
}
