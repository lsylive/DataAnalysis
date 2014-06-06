
package com.liusy.analysis.template.model.dialogProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.SubDiagramParameter;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.vo.DataField;

public class SubDiagramNodeProperties
implements Cloneable, Serializable
{
 private static final long serialVersionUID = 1L;
 private List<DataField> fields = new ArrayList();
 private List<SubDiagramParameter> ParameterList = new ArrayList();
 private String[] multiParamName;
 private String[] multiParamCnName;
 private Diagram diagram;
 private String diagramName = "";

 private List<Metadata> MetadataList = new ArrayList();

 public List<DataField> getFields() {
   return this.fields;
 }

 public void setFields(List<DataField> fields) {
   this.fields = fields;
 }

 public List<Metadata> getMeta() {
   List mts = new ArrayList();

   mts.addAll(this.MetadataList);
   return mts;
 }

 public Diagram getDiagram() {
   return this.diagram;
 }

 public void setDiagram(Diagram diagram) {
   this.diagram = diagram;
 }

 public String[] getMultiParamName() {
   return this.multiParamName;
 }

 public void setMultiParamName(String[] multiParamName) {
   this.multiParamName = multiParamName;
 }

 public String[] getMultiParamCnName() {
   return this.multiParamCnName;
 }

 public void setMultiParamCnName(String[] multiParamCnName) {
   this.multiParamCnName = multiParamCnName;
 }

 public List<SubDiagramParameter> getParameterList() {
   return this.ParameterList;
 }

 public void setParameterList(List<SubDiagramParameter> parameterList) {
   this.ParameterList = parameterList;
 }

 public String getDiagramName() {
   return this.diagramName;
 }

 public void setDiagramName(String diagramName) {
   this.diagramName = diagramName;
 }

 public List<Metadata> getMetadataList() {
   return this.MetadataList;
 }

 public void setMetadataList(List<Metadata> metadataList) {
   this.MetadataList = metadataList;
 }

 public static long getSerialVersionUID() {
   return 1L;
 }
}
