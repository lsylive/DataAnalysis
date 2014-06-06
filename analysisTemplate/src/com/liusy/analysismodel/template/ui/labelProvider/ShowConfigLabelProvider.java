package com.liusy.analysismodel.template.ui.labelProvider;

import org.eclipse.jface.viewers.ITableLabelProvider;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;

public class ShowConfigLabelProvider extends BaseLabelProvider implements ITableLabelProvider {

   private String[] codeSetNames;
   private String[] codeSetValues;

   public ShowConfigLabelProvider(String[] codeSetNames, String[] codeSetValues) {
      super();
      this.codeSetNames = codeSetNames;
      this.codeSetValues = codeSetValues;
   }

   public String getColumnText(Object obj, int columnIndex) {
      FieldConfig dsi = (FieldConfig) obj;
      switch (columnIndex) {
         case 0:
            return dsi.getCnName() == null ? "" : dsi.getCnName();
         case 1:
            return dsi.getName() == null ? "" : dsi.getName();
         case 2:
            return dsi.getDataFormat() == null ? "" : dsi.getDataFormat();
         case 3:
            return dsi.getAlign() == null ? "" : getLabel(Consts.ALIGN_LABEL, Consts.ALIGN, dsi.getAlign());
         case 4:
            return dsi.getWidth() == null ? "" : dsi.getWidth();
         case 5:
            return dsi.getVisible() == null ? Consts.NO : getLabel(Consts.YESNO_LABEL, Consts.YESNO, dsi.getVisible());
         case 6:
            return dsi.getCodeSet() == null ? "" : getLabel(codeSetNames, codeSetValues, dsi.getCodeSet());
         case 7:
            if (dsi.getDetailLink().getLinkType().equals(Consts.DETAIL_LINKTYPE_NODE)) {
               if (dsi.getDetailLink().getNodeName() == null) return "";
               else return dsi.getDetailLink().getNodeName();
            }
            else {
               if (dsi.getDetailLink().getDiagramName() == null) return "";
               else return dsi.getDetailLink().getDiagramName();
            }
      }
      return null;
   }
}
