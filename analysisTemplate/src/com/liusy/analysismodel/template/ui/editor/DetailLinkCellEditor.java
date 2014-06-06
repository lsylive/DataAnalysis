package com.liusy.analysismodel.template.ui.editor;

import java.util.List;

import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.vo.DetailLinkField;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysismodel.template.ui.dialog.DetailLinkDialog;

public class DetailLinkCellEditor extends DialogCellEditor {
   private Table             table;
   private List<FieldConfig> configList;
   private List<INode>       nodes;

   public DetailLinkCellEditor(Composite parent, List<FieldConfig> configList, List<INode> nodes) {
      super(parent);
      this.table = (Table) parent;
      this.configList = configList;
      this.nodes = nodes;
   }

   protected Object openDialogBox(Control cellEditorWindow) {
      TableItem[] selectItems = table.getSelection();
      FieldConfig dlf = null;
      if (selectItems != null && selectItems.length > 0) dlf = (FieldConfig) selectItems[0].getData();

      DetailLinkDialog gcd = new DetailLinkDialog(cellEditorWindow.getShell(), dlf, configList, nodes);
      DetailLinkField res = gcd.open();
      return res;
   }
}
