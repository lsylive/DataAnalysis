

package com.liusy.analysis.template.model.base;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
public abstract class Element
implements Cloneable, Serializable
{
private static final long serialVersionUID = 1L;
PropertyChangeSupport listeners = new PropertyChangeSupport(this);

public void addPropertyChangeListener(PropertyChangeListener l)
{
  this.listeners.addPropertyChangeListener(l);
}

protected void firePropertyChange(String prop, Object old, Object newValue)
{
  this.listeners.firePropertyChange(prop, old, newValue);
}

protected void fireStructureChange(String prop, Object child)
{
  this.listeners.firePropertyChange(prop, null, child);
}

public void removePropertyChangeListener(PropertyChangeListener l)
{
  this.listeners.removePropertyChangeListener(l);
}
}