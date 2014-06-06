package com.liusy.analysismodel.template.ui.labelProvider;

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableItem;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.DetailLinkField;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;

public class ShowConfigCellModifier extends BaseCellModifer implements ICellModifier {

   private String[] codeSetNames;
   private String[] codeSetValues;

   public ShowConfigCellModifier(TableViewer tv, String[] codeSetNames, String[] codeSetValues) {
      this.tv = tv;
      this.codeSetNames = codeSetNames;
      this.codeSetValues = codeSetValues;
   }

   public Object getValue(Object element, String property) {
      FieldConfig o = (FieldConfig) element;
      if (property.equals("name")) return o.getName();
      else if (property.equals("cnName")) return o.getCnName();
      else if (property.equals("dataFormat")) return o.getDataFormat();
      else if (property.equals("align")) return getIndex(Consts.ALIGN, o.getAlign());
      else if (property.equals("visible")) return getIndex(Consts.YESNO, o.getVisible());
      else if (property.equals("width")) return o.getWidth();
      else if (property.equals("codeset")) return getIndex(codeSetValues, o.getCodeSet());
      else if (property.equals("link")) {
         if (o.getDetailLink().getLinkType().equals(Consts.DETAIL_LINKTYPE_NODE)) return o.getDetailLink().getNodeName();
         else return o.getDetailLink().getDiagramName();
      }

      else throw new RuntimeException("错误的列别名:" + property);
   }

   public void modify(Object element, String property, Object value) {
      TableItem item = (TableItem) element;
      FieldConfig o = (FieldConfig) item.getData();
      if (property.equals("name")) o.setName((String) value);
      else if (property.equals("cnName")) o.setCnName((String) value);
      else if (property.equals("dataFormat")) o.setDataFormat((String) value);
      else if (property.equals("width")) o.setWidth((String) value);
      else if (property.equals("codeset")) o.setCodeSet(codeSetValues[(Integer) value]);
      else if (property.equals("link")) {
         if (value instanceof DetailLinkField) o.setDetailLink((DetailLinkField) value);
      }
      else if (property.equals("align")) o.setAlign(Consts.ALIGN[(Integer) value]);
      else if (property.equals("visible")) o.setVisible(Consts.YESNO[(Integer) value]);
      else throw new RuntimeException("错误的列别名:" + property);
      tv.update(o, null);
   }

}
