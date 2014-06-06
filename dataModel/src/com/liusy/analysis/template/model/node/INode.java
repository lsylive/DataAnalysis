
package com.liusy.analysis.template.model.node;

import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.vo.DataSet;
import com.liusy.analysis.template.model.vo.FieldConfig;
public abstract interface INode
{
  public abstract int getId();

  public abstract void setId(int paramInt);

  public abstract String getName();

  public abstract void setName(String paramString);

  public abstract List<Metadata> getMeta();

  public abstract List<Map<String, Object>> run(List<DataSet> paramList);

  public abstract void addInput(Connection paramConnection);

  public abstract void addOutput(Connection paramConnection);

  public abstract List<Connection> getIncomingConnections();

  public abstract List<Connection> getOutgoingConnections();

  public abstract void removeInput(Connection paramConnection);

  public abstract void removeOutput(Connection paramConnection);

  public abstract boolean isVisible();

  public abstract void setVisible(boolean paramBoolean);

  public abstract void setLocation(Point paramPoint);

  public abstract Point getLocation();

  public abstract Object getEditableValue();

  public abstract Object getPropertyValue(Object paramObject);

  public abstract boolean isPropertySet(Object paramObject);

  public abstract void resetPropertyValue(Object paramObject);

  public abstract void setPropertyValue(Object paramObject1, Object paramObject2);

  public abstract IPropertyDescriptor[] getPropertyDescriptors();

  public abstract Diagram getDiagram();

  public abstract void setDiagram(Diagram paramDiagram);

  public abstract String getDescription();

  public abstract void setDescription(String paramString);

  public abstract void addPropertyChangeListener(PropertyChangeListener paramPropertyChangeListener);

  public abstract void removePropertyChangeListener(PropertyChangeListener paramPropertyChangeListener);

  public abstract List<FieldConfig> getFieldConfigs();

  public abstract String getExtraConfigs();

  public abstract boolean isRunFlag();

  public abstract boolean isEnable();

  public abstract List<Map<String, Object>> getResultSet();
}