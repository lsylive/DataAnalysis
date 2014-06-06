package com.liusy.analysismodel.template.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.IRunAlone;
import com.liusy.analysis.template.model.vo.DetailLinkField;
import com.liusy.analysis.template.model.vo.DiagramTable;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysis.template.model.vo.RelateParameter;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;

public class DetailLinkDialog extends Dialog {

   private Text                  txtDiagramName;
   private Table                 tblParams;
   private Combo                 cboNode;
   protected Shell               shell;
   private FieldConfig           dataField;
   private DetailLinkField       detailLinkField;
   private List<Integer>         nodeIds    = new ArrayList<Integer>();
   private List<String>          nodeNames  = new ArrayList<String>();
   private ComboViewer           cbvNode;
   private List<RelateParameter> configList = new ArrayList<RelateParameter>();
   private CheckboxTableViewer   chtParams;
   private int                   diagramId;
   private Button                btnLinkNode;
   private Button                btnLinkDigram;

   public DetailLinkDialog(Shell parent, int style) {
      super(parent, style);
   }

   private void fillParams(List<FieldConfig> config) {
      String[] params = detailLinkField.getParameters().split(";");
      if (detailLinkField.getRelateParameters() == null) detailLinkField.setRelateParameters("");
      String[] rparams = detailLinkField.getRelateParameters().split(";");
      for (int i = 0; i < config.size(); i++) {
         RelateParameter rp = new RelateParameter(config.get(i).getCnName(), config.get(i).getName());
         for (int j = 0; j < params.length; j++) {
            if (params[j].trim().equals(rp.getName())) {
               rp.setRelateName(rparams[j].trim());
               break;
            }
         }
         configList.add(rp);
      }
   }

   public DetailLinkDialog(Shell parent, FieldConfig dataField, List<FieldConfig> config, List<INode> nodes) {
      this(parent, SWT.NONE);
      this.dataField = dataField;

      if (dataField.getDetailLink() == null) this.detailLinkField = new DetailLinkField();
      else {
         this.detailLinkField = (DetailLinkField) dataField.getDetailLink().clone();
         if (this.detailLinkField == null) this.detailLinkField = new DetailLinkField();
         if (this.detailLinkField.getLinkType() == null) this.detailLinkField.setLinkType(Consts.DETAIL_LINKTYPE_NODE);
      }
      fillParams(config);

      nodeIds.add(0);
      nodeNames.add("");
      for (INode node : nodes) {
         if (node instanceof IRunAlone && node.getIncomingConnections().size() == 0 && node.getOutgoingConnections().size() == 0) {
            nodeIds.add(node.getId());
            nodeNames.add(node.getName());
         }
      }
   }

   private int getIndex(String value) {
      for (int i = 0; i < Consts.GENCOLUMN_LABEL.length; i++) {
         if (Consts.GENCOLUMN_LABEL[i].equalsIgnoreCase(value)) return i;
      }
      return -1;
   }

   public DetailLinkField open() {
      createContents();
      cbvNode.setSelection(new StructuredSelection(detailLinkField.getNodeId()));

      chtParams.setInput(configList);

      String[] params = detailLinkField.getParameters().split(";");
      for (int i = 0; i < params.length; i++) {
         for (RelateParameter f : configList) {
            if (params[i].trim().equals(f.getName())) {
               chtParams.setChecked(f, true);
            }
         }
      }
      diagramId = detailLinkField.getDiagramId();
      txtDiagramName.setText(detailLinkField.getDiagramName() == null ? "" : detailLinkField.getDiagramName());

      shell.setLocation(getParentLocation());
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) display.sleep();
      }
      return detailLinkField;
   }

   protected void createContents() {
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(471, 357);
      shell.setText("链接配置");

      final Composite composite = new Composite(shell, SWT.NONE);
      final FormData fd_composite = new FormData();
      fd_composite.left = new FormAttachment(0, 0);
      fd_composite.bottom = new FormAttachment(100, -35);
      fd_composite.top = new FormAttachment(0, 0);
      fd_composite.right = new FormAttachment(100, 0);
      composite.setLayoutData(fd_composite);
      composite.setLayout(new FormLayout());

      final Group group = new Group(composite, SWT.NONE);
      final FormData fd_group = new FormData();
      fd_group.height = 50;
      fd_group.right = new FormAttachment(100, -2);
      fd_group.left = new FormAttachment(0, 2);
      fd_group.top = new FormAttachment(0, -6);
      group.setLayoutData(fd_group);
      group.setLayout(new FormLayout());

      btnLinkNode = new Button(group, SWT.RADIO);
      final FormData fd_btnLinkNode = new FormData();
      fd_btnLinkNode.top = new FormAttachment(0, 0);
      fd_btnLinkNode.left = new FormAttachment(0, 2);
      btnLinkNode.setLayoutData(fd_btnLinkNode);
      btnLinkNode.setText("链接节点");

      btnLinkDigram = new Button(group, SWT.RADIO);
      final FormData fd_btnLinkDigram = new FormData();
      fd_btnLinkDigram.top = new FormAttachment(0, 0);
      fd_btnLinkDigram.left = new FormAttachment(50, 0);
      btnLinkDigram.setLayoutData(fd_btnLinkDigram);
      btnLinkDigram.setText("链接模版");

      if (this.detailLinkField.getLinkType().equals(Consts.DETAIL_LINKTYPE_NODE)) btnLinkNode.setSelection(true);
      else btnLinkDigram.setSelection(true);

      final Group group_1 = new Group(composite, SWT.NONE);
      group_1.setText(" 链接参数 ");
      group_1.setLayout(new FormLayout());
      final FormData fd_group_1 = new FormData();
      fd_group_1.top = new FormAttachment(group, 2);
      fd_group_1.bottom = new FormAttachment(100, -1);
      fd_group_1.right = new FormAttachment(100, -2);
      fd_group_1.left = new FormAttachment(0, 2);
      group_1.setLayoutData(fd_group_1);

      final Label lblType = new Label(group, SWT.RIGHT);
      final FormData fd_lblType = new FormData();
      fd_lblType.top = new FormAttachment(btnLinkNode, 8);
      fd_lblType.right = new FormAttachment(20, 0);
      fd_lblType.left = new FormAttachment(0, 0);
      lblType.setLayoutData(fd_lblType);
      if (this.detailLinkField.getLinkType().equals(Consts.DETAIL_LINKTYPE_NODE)) lblType.setText("数据节点：");
      else lblType.setText("模板名称：");

      cbvNode = new ComboViewer(group, SWT.BORDER | SWT.READ_ONLY);
      cboNode = cbvNode.getCombo();
      final FormData fd_combo = new FormData();
      fd_combo.right = new FormAttachment(100, -1);
      fd_combo.left = new FormAttachment(lblType, 0);
      fd_combo.top = new FormAttachment(btnLinkNode, 5);
      cboNode.setLayoutData(fd_combo);
      cbvNode.setContentProvider(new ArrayContentProvider());
      cbvNode.setInput(nodeIds);
      cbvNode.setLabelProvider(new LabelProvider() {
         public String getText(Object element) {
            Integer id = (Integer) element;
            for (int i = 0; i < nodeIds.size(); i++) {
               if (id == nodeIds.get(i)) { return nodeNames.get(i); }
            }
            return "";
         }
      });
      if (this.detailLinkField.getLinkType().equals(Consts.DETAIL_LINKTYPE_NODE)) cboNode.setVisible(true);
      else cboNode.setVisible(false);

      final Button btnSelect = new Button(group, SWT.NONE);
      btnSelect.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            DiagramTableDialog dataSoruceDlg = new DiagramTableDialog(shell);
            DiagramTable diagramTable = dataSoruceDlg.open();
            if (diagramTable != null) {
               txtDiagramName.setText(diagramTable.getName());
               diagramId = Integer.parseInt(diagramTable.getId());
            }
         }
      });
      btnSelect.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/plus1.png"));
      final FormData fd_btnSelect = new FormData();
      fd_btnSelect.top = new FormAttachment(cboNode, 0, SWT.TOP);
      fd_btnSelect.right = new FormAttachment(cboNode, 0, SWT.RIGHT);
      fd_btnSelect.height = 21;
      fd_btnSelect.width = 21;
      btnSelect.setLayoutData(fd_btnSelect);

      txtDiagramName = new Text(group, SWT.BORDER);
      final FormData fd_txtDiagramName = new FormData();
      fd_txtDiagramName.top = new FormAttachment(cboNode, 0, SWT.TOP);
      fd_txtDiagramName.left = new FormAttachment(cboNode, 0, SWT.LEFT);
      fd_txtDiagramName.right = new FormAttachment(btnSelect, 0);
      if (this.detailLinkField.getLinkType().equals(Consts.DETAIL_LINKTYPE_NODE)) txtDiagramName.setVisible(false);
      else txtDiagramName.setVisible(true);
      txtDiagramName.setLayoutData(fd_txtDiagramName);

      chtParams = CheckboxTableViewer.newCheckList(group_1, SWT.BORDER | SWT.FULL_SELECTION);
      tblParams = chtParams.getTable();
      final FormData fd_table = new FormData();
      fd_table.bottom = new FormAttachment(100, -1);
      fd_table.right = new FormAttachment(100, -1);
      fd_table.left = new FormAttachment(0, 0);
      fd_table.top = new FormAttachment(0, 0);
      tblParams.setLayoutData(fd_table);
      tblParams.setLinesVisible(true);
      tblParams.setHeaderVisible(true);
      chtParams.setContentProvider(new ViewContentProvider());
      chtParams.setLabelProvider(new ShowConfigLabelProvider());
      chtParams.setColumnProperties(DATAFIELDS);
      CellEditor[] cellEditor = new CellEditor[8];
      cellEditor[0] = new TextCellEditor(tblParams, SWT.READ_ONLY);
      cellEditor[1] = new TextCellEditor(tblParams, SWT.READ_ONLY);
      cellEditor[2] = new TextCellEditor(tblParams);
      chtParams.setCellEditors(cellEditor);
      chtParams.setCellModifier(new ShowConfigCellModifier(chtParams));

      final TableColumn colCnName = new TableColumn(tblParams, SWT.NONE);
      colCnName.setWidth(150);
      colCnName.setText("中文名");

      final TableColumn colFieldName = new TableColumn(tblParams, SWT.NONE);
      colFieldName.setWidth(150);
      colFieldName.setText("列名");

      final TableColumn colParam = new TableColumn(tblParams, SWT.NONE);
      colParam.setWidth(150);
      colParam.setText("对应参数");

      final Button btnOk = new Button(shell, SWT.NONE);
      btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
      final FormData fd_btnOk = new FormData();
      fd_btnOk.height = 25;
      fd_btnOk.width = 80;
      fd_btnOk.bottom = new FormAttachment(100, -5);
      fd_btnOk.right = new FormAttachment(50, -50);
      btnOk.setLayoutData(fd_btnOk);
      btnOk.setText("确定(&O)");
      btnOk.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            ok();
         }
      });

      final Button btnClear = new Button(shell, SWT.NONE);
      btnClear.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            detailLinkField = new DetailLinkField();
            shell.dispose();
         }
      });
      btnClear.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/arrow-circle-225.png"));
      final FormData fd_btnClear = new FormData();
      fd_btnClear.height = 25;
      fd_btnClear.width = 80;
      fd_btnClear.right = new FormAttachment(50, 40);
      fd_btnClear.bottom = new FormAttachment(100, -5);
      btnClear.setLayoutData(fd_btnClear);
      btnClear.setText("清除(&R)");

      final Button btnCancel = new Button(shell, SWT.NONE);
      btnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/cross.png"));
      final FormData fd_btnCancel = new FormData();
      fd_btnCancel.height = 25;
      fd_btnCancel.width = 80;
      fd_btnCancel.bottom = new FormAttachment(100, -5);
      fd_btnCancel.left = new FormAttachment(50, 50);
      btnCancel.setLayoutData(fd_btnCancel);
      btnCancel.setText("取消(&C)");
      btnCancel.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            detailLinkField = null;
            shell.dispose();
         }
      });

      btnLinkNode.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            lblType.setText("数据节点：");
            cboNode.setVisible(true);
            txtDiagramName.setVisible(false);
         }
      });
      btnLinkDigram.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            lblType.setText("模板名称：");
            cboNode.setVisible(false);
            txtDiagramName.setVisible(true);
         }
      });

   }

   private void ok() {
      if (btnLinkNode.getSelection()) {
         detailLinkField.setLinkType(Consts.DETAIL_LINKTYPE_NODE);
         detailLinkField.setNodeId(nodeIds.get(cboNode.getSelectionIndex()));
         detailLinkField.setNodeName(nodeNames.get(cboNode.getSelectionIndex()));
         detailLinkField.setDiagramId(0);
         detailLinkField.setDiagramName("");
      }
      else {
         detailLinkField.setLinkType(Consts.DETAIL_LINKTYPE_DIAGRAM);
         detailLinkField.setNodeId(0);
         detailLinkField.setNodeName("");
         detailLinkField.setDiagramId(diagramId);
         detailLinkField.setDiagramName(txtDiagramName.getText());
      }

      String strPara = "";
      String strLPara = "";
      for (Object o : chtParams.getCheckedElements()) {
         RelateParameter f = (RelateParameter) o;

         strPara += (f.getName() == null || f.getName().trim().length() == 0) ? " ;" : f.getName().trim() + ";";
         strLPara += (f.getRelateName() == null || f.getRelateName().trim().length() == 0) ? " ;" : f.getRelateName().trim() + ";";
      }
      detailLinkField.setParameters(strPara);
      detailLinkField.setRelateParameters(strLPara);
      shell.dispose();
   }

   public Point getParentLocation() {

      Shell shell = getParent();
      int x = shell.getLocation().x;
      int y = shell.getLocation().y;
      Rectangle rectParent = shell.getBounds();
      Point size = this.shell.getSize();
      int xx = x + rectParent.width / 2 - size.x / 2;
      int yy = y + rectParent.height / 2 - size.y / 2;
      return new Point(xx, yy);
   }

   class ShowConfigCellModifier extends BaseCellModifer implements ICellModifier {

      public ShowConfigCellModifier(TableViewer tv) {
         this.tv = tv;
      }

      public Object getValue(Object element, String property) {
         RelateParameter o = (RelateParameter) element;
         if (property.equals("name")) return o.getName();
         else if (property.equals("cnName")) return o.getCnName();
         else if (property.equals("param")) return o.getRelateName();
         else throw new RuntimeException("错误的列别名:" + property);
      }

      public void modify(Object element, String property, Object value) {
         TableItem item = (TableItem) element;
         RelateParameter o = (RelateParameter) item.getData();
         if (property.equals("name")) o.setName((String) value);
         else if (property.equals("cnName")) o.setCnName((String) value);
         else if (property.equals("param")) o.setRelateName((String) value);
         else throw new RuntimeException("错误的列别名:" + property);
         tv.update(o, null);
      }
   }

   class ShowConfigLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
      public String getColumnText(Object obj, int columnIndex) {
         RelateParameter dsi = (RelateParameter) obj;
         switch (columnIndex) {
            case 0:
               return dsi.getCnName() == null ? "" : dsi.getCnName();
            case 1:
               return dsi.getName() == null ? "" : dsi.getName();
            case 2:
               return dsi.getRelateName() == null ? "" : dsi.getRelateName();
         }
         return null;
      }
   }

   public static String[] DATAFIELDS = new String[] { "cnName", "name", "param" };
}
