package com.liusy.analysismodel.template.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.grouplayout.Activator;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.template.commands.NodePropertyEditCommand;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.DiagramParameter;
import com.liusy.analysis.template.model.SubDiagramParameter;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.dialogProperties.SubDiagramNodeProperties;
import com.liusy.analysis.template.model.node.SubDiagramNode;
import com.liusy.analysis.template.model.vo.CodeSet;
import com.liusy.analysis.template.model.vo.DiagramTable;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.dialog.DiagramTableDialog;
import com.liusy.analysismodel.util.DbConnectionManager;
import com.liusy.analysismodel.util.MessageUtil;

public class SubDiagramNodeDialog extends Dialog {
   private TableViewer                tvSubParameters;
   private Table                      tblSubParameters;
   private TableViewer                tvMetaField;
   private Table                      tblDataField;
   private StyledText                 txtDes;
   private Text                       txtDiagramName;
   private Text                       txtName;
   private int                        result              = 0;
   protected SubDiagramNodeProperties nodeProperties;
   private SubDiagramNode             node;
   private Diagram                    diagram;
   private Diagram                    fatherDiagram;
   private List<Metadata>             MetadataList;
   private List<SubDiagramParameter>  ParameterList;
   private NodePropertyEditCommand    cmd;
   private DiagramTable               diagramTable;
   private String[]                   fatherCodeSets      = new String[] { "" };
   private String[]                   fatherCodeSetLabels = new String[] { "" };

   /**
    * Create the dialog
    * 
    * @param parent
    * @param style
    */
   public SubDiagramNodeDialog(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * 
    * @param parent
    */
   public SubDiagramNodeDialog(Shell parent, SubDiagramNode node, NodePropertyEditCommand command) {

      this(parent, SWT.NONE);
      this.node = node;
      this.fatherDiagram = node.getDiagram();
      this.cmd = command;
   }

   /**
    * Open the dialog
    * 
    * @return the result
    */
   public int open() {
      createContents();
      shell.setLocation(getParentLocation());
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) display.sleep();
      }
      return result;
   }

   /**
    * Create contents of the dialog
    */
   protected void createContents() {
      nodeProperties = node.getProperties();
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(663, 429);
      shell.setText("子图属性");

      final Group basicGroup = new Group(shell, SWT.NONE);
      basicGroup.setLayout(new FormLayout());
      final FormData fd_basicGroup = new FormData();
      fd_basicGroup.height = 70;
      fd_basicGroup.top = new FormAttachment(0, 1);
      fd_basicGroup.right = new FormAttachment(100, -5);
      fd_basicGroup.left = new FormAttachment(0, 5);
      basicGroup.setLayoutData(fd_basicGroup);

      final Label label = new Label(basicGroup, SWT.RIGHT);
      final FormData fd_label = new FormData();
      fd_label.top = new FormAttachment(0, 0);
      fd_label.right = new FormAttachment(15, 0);
      fd_label.left = new FormAttachment(0, 0);
      label.setLayoutData(fd_label);
      label.setText("节点名称：");

      txtName = new Text(basicGroup, SWT.BORDER);
      final FormData fd_txtName = new FormData();
      fd_txtName.top = new FormAttachment(0, 0);
      fd_txtName.right = new FormAttachment(40, 0);
      fd_txtName.left = new FormAttachment(label, 0);
      txtName.setLayoutData(fd_txtName);
      txtName.setEditable(true);

      final Label label_1 = new Label(basicGroup, SWT.RIGHT);
      final FormData fd_label_1 = new FormData();
      fd_label_1.top = new FormAttachment(0, 0);
      fd_label_1.right = new FormAttachment(55, 0);
      fd_label_1.left = new FormAttachment(txtName, 0);
      label_1.setLayoutData(fd_label_1);
      label_1.setText("子图名称：");

      txtDiagramName = new Text(basicGroup, SWT.BORDER);
      txtDiagramName.setEditable(false);
      final FormData fd_txtDiagramName = new FormData();
      fd_txtDiagramName.top = new FormAttachment(0, 0);
      fd_txtDiagramName.right = new FormAttachment(90, 0);
      fd_txtDiagramName.left = new FormAttachment(label_1, 0);
      txtDiagramName.setLayoutData(fd_txtDiagramName);

      final Button btnSelectTable = new Button(basicGroup, SWT.NONE);
      final FormData fd_btnSelectTable = new FormData();
      fd_btnSelectTable.top = new FormAttachment(0, 0);
      fd_btnSelectTable.right = new FormAttachment(100, -1);
      fd_btnSelectTable.left = new FormAttachment(txtDiagramName, 0);
      fd_btnSelectTable.height = 23;
      btnSelectTable.setLayoutData(fd_btnSelectTable);
      btnSelectTable.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            DiagramTableDialog dataSoruceDlg = new DiagramTableDialog(shell);
            diagramTable = dataSoruceDlg.open();
            if (diagramTable != null) {
               txtDiagramName.setText(diagramTable.getName());
               nodeProperties.setDiagramName(diagramTable.getName());
               int res = MessageUtil.comfirm(shell, "导入", "是否导入字段信息?\r\n注意：已有字段信息将被覆盖!");
               if (res == SWT.YES) {
                  // 初始化子图diagram
                  LoadMeta_ParamInfo(diagramTable);
                  // 初始化读取子图中的meta
                  MetadataList = new ArrayList<Metadata>();
                  for (Metadata df : diagram.getMetaData()) {
                     MetadataList.add(df);
                  }
                  tvMetaField.setInput(MetadataList);
                  // 初始化子图参数列表
                  ParameterList.clear();
                  List<DiagramParameter> dpList = diagram.getParameterList();
                  for (int i = 0; i < dpList.size(); i++) {
                     DiagramParameter dp = (DiagramParameter) dpList.get(i).clone();
                     SubDiagramParameter sdp = new SubDiagramParameter(dp);
                     sdp.setFatherParameter("");
                     ParameterList.add(sdp);
                  }
                  tvSubParameters.setInput(ParameterList);
               }
            }
         }
      });
      btnSelectTable.setText("选择表");

      final Label lblDescription = new Label(basicGroup, SWT.RIGHT);
      final FormData fd_lblDescription = new FormData();
      fd_lblDescription.top = new FormAttachment(label, 10);
      fd_lblDescription.bottom = new FormAttachment(100, -2);
      fd_lblDescription.left = new FormAttachment(0, 0);
      fd_lblDescription.right = new FormAttachment(15, 0);
      lblDescription.setLayoutData(fd_lblDescription);
      lblDescription.setText("说明：");

      txtDes = new StyledText(basicGroup, SWT.BORDER);
      final FormData fd_txtDes = new FormData();
      fd_txtDes.top = new FormAttachment(txtName, 5);
      fd_txtDes.left = new FormAttachment(15, 0);
      fd_txtDes.right = new FormAttachment(100, -2);
      fd_txtDes.bottom = new FormAttachment(100, -2);
      txtDes.setLayoutData(fd_txtDes);

      final Button btnOk = new Button(shell, SWT.NONE);
      btnOk.setImage(ResourceManager.getPluginImage("DataAdminPlatform", "icons/tick.png"));
      final FormData fd_btnOk = new FormData();
      fd_btnOk.height = 28;
      fd_btnOk.width = 80;
      fd_btnOk.bottom = new FormAttachment(100, -5);
      fd_btnOk.right = new FormAttachment(50, -10);
      btnOk.setLayoutData(fd_btnOk);
      btnOk.setText("确定(&O)");
      btnOk.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            save();
            result = 1;
            close();
         }
      });

      final Button btnCancel = new Button(shell, SWT.NONE);
      btnCancel.setImage(ResourceManager.getPluginImage("DataAdminPlatform", "icons/cross.png"));
      final FormData fd_btnCancel = new FormData();
      fd_btnCancel.height = 28;
      fd_btnCancel.width = 80;
      fd_btnCancel.bottom = new FormAttachment(100, -5);
      fd_btnCancel.left = new FormAttachment(50, 10);
      btnCancel.setLayoutData(fd_btnCancel);
      btnCancel.setText("取消(&C)");
      btnCancel.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            close();
         }
      });

      final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
      tabFolder.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            TabItem ti = (TabItem) e.item;
            if (ti.getText().startsWith("子图参数")) {
               // 初始化子图参数表中combox中的父参数数组
               String[] fatherParams = null;
               List<DiagramParameter> fdp = new ArrayList<DiagramParameter>();
               fdp = fatherDiagram.getParameterList();
               if (fdp != null && fdp.size() > 0) {
                  int index = fdp.size() + 1;
                  fatherParams = new String[index];
                  fatherParams[0] = new String("");
                  for (int i = 1; i < fatherParams.length; i++) {
                     fatherParams[i] = fdp.get(i - 1).getName();
                  }
               }
               else fatherParams = new String[] { "" };
               // tvSubParameters.setCellModifier(new
               // subDiagramParameterCellModifier(tvSubParameters,
               // fatherParams));
               tvSubParameters.getCellEditors()[3] = new ComboBoxCellEditor(tblSubParameters, fatherParams, SWT.READ_ONLY);

               // 初始化子图表中combox中的父代码集数组
               if (fatherDiagram.getCodeSetList() == null) {
                  fatherCodeSetLabels = new String[] { "" };
                  fatherCodeSets = new String[] { "" };
               }
               else {
                  int size = fatherDiagram.getCodeSetList().size();
                  fatherCodeSets = new String[size + 1];
                  fatherCodeSetLabels = new String[size + 1];
                  fatherCodeSets[0] = "";
                  fatherCodeSetLabels[0] = "";
                  for (int i = 0; i < size; i++) {
                     CodeSet desBean = (CodeSet) fatherDiagram.getCodeSetList().get(i);
                     fatherCodeSets[i + 1] = desBean.getName();
                     fatherCodeSetLabels[i + 1] = desBean.getCnName();
                  }
               }
               tvSubParameters.setLabelProvider(new SubDiagramParamsLabelProvider(fatherCodeSets, fatherCodeSetLabels));
               tvSubParameters.getCellEditors()[5] = new ComboBoxCellEditor(tblSubParameters, fatherCodeSetLabels, SWT.READ_ONLY);
               tvSubParameters.setCellModifier(new subDiagramParameterCellModifier(tvSubParameters, fatherParams, fatherCodeSets));

            }
         }
      });
      final FormData fd_tabFolder = new FormData();
      fd_tabFolder.bottom = new FormAttachment(100, -40);
      fd_tabFolder.top = new FormAttachment(basicGroup, 3, SWT.BOTTOM);
      fd_tabFolder.right = new FormAttachment(100, -5);
      fd_tabFolder.left = new FormAttachment(0, 5);
      tabFolder.setLayoutData(fd_tabFolder);

      final TabItem tabFields = new TabItem(tabFolder, SWT.NONE);
      tabFields.setText("子图元数据字段");

      final Group group_1 = new Group(tabFolder, SWT.NONE);
      group_1.setLayout(new FormLayout());
      tabFields.setControl(group_1);

      tvMetaField = new TableViewer(group_1, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
      tvMetaField.setContentProvider(new ViewContentProvider());
      tvMetaField.setLabelProvider(new SubDiagramMetaFieldLabelProvider());
      tvMetaField.setColumnProperties(SubDiagramMetaFieldLabelProvider.METAFIELDS);
      tblDataField = tvMetaField.getTable();

      final FormData fd_table_1 = new FormData();
      fd_table_1.bottom = new FormAttachment(100, 0);
      fd_table_1.top = new FormAttachment(0, -6);
      fd_table_1.right = new FormAttachment(100, 0);
      fd_table_1.left = new FormAttachment(0, 0);
      tblDataField.setLayoutData(fd_table_1);
      tblDataField.setLinesVisible(true);
      tblDataField.setHeaderVisible(true);

      final TableColumn colCnName = new TableColumn(tblDataField, SWT.NONE);
      colCnName.setWidth(150);
      colCnName.setText("中文名");

      final TableColumn colFieldName = new TableColumn(tblDataField, SWT.NONE);
      colFieldName.setWidth(150);
      colFieldName.setText("英文名");

      final TableColumn colDataType = new TableColumn(tblDataField, SWT.NONE);
      colDataType.setWidth(150);
      colDataType.setText("数据类型");

      final TabItem tabFilter = new TabItem(tabFolder, SWT.NONE);
      tabFilter.setText("子图参数设置");

      final Composite composite = new Composite(tabFolder, SWT.NONE);
      composite.setLayout(new FormLayout());
      tabFilter.setControl(composite);

      final Group group_2 = new Group(composite, SWT.NO_RADIO_GROUP);
      final FormData fd_group_2 = new FormData();
      fd_group_2.left = new FormAttachment(0, 0);
      fd_group_2.right = new FormAttachment(100, 0);
      fd_group_2.top = new FormAttachment(0, -6);
      fd_group_2.bottom = new FormAttachment(100, 0);
      group_2.setLayoutData(fd_group_2);
      group_2.setLayout(new FormLayout());

      tvSubParameters = new TableViewer(group_2, SWT.FULL_SELECTION | SWT.BORDER);
      tvSubParameters.setLabelProvider(new SubDiagramParamsLabelProvider(fatherCodeSets, fatherCodeSetLabels));
      tvSubParameters.setContentProvider(new ViewContentProvider());
      tvSubParameters.setColumnProperties(SubDiagramParamsLabelProvider.DATAFIELDS);
      tblSubParameters = tvSubParameters.getTable();
      // 只有父节点参数可以手动设置
      CellEditor[] cellEditor1 = new CellEditor[6];
      String[] fatherParams = new String[] { "" };
      String[] fatherCodeSetLabels = new String[] { "" };
      cellEditor1[0] = null;
      cellEditor1[1] = null;
      cellEditor1[2] = null;
      cellEditor1[3] = new ComboBoxCellEditor(tblSubParameters, fatherParams, SWT.READ_ONLY);
      cellEditor1[4] = null;
      cellEditor1[5] = new ComboBoxCellEditor(tblSubParameters, fatherCodeSetLabels, SWT.READ_ONLY);
      tvSubParameters.setCellEditors(cellEditor1);
      tvSubParameters.setCellModifier(new subDiagramParameterCellModifier(tvSubParameters, fatherParams, fatherCodeSets));

      final FormData fd_table_2 = new FormData();
      fd_table_2.bottom = new FormAttachment(100, 0);
      fd_table_2.top = new FormAttachment(0, -5);
      fd_table_2.right = new FormAttachment(100, -1);
      fd_table_2.left = new FormAttachment(0, 1);
      tblSubParameters.setLayoutData(fd_table_2);
      tblSubParameters.setLinesVisible(true);
      tblSubParameters.setHeaderVisible(true);

      final TableColumn newColumnTableColumn = new TableColumn(tblSubParameters, SWT.NONE);
      newColumnTableColumn.setWidth(100);
      newColumnTableColumn.setText("参数名称");

      final TableColumn newColumnTableColumn_4 = new TableColumn(tblSubParameters, SWT.NONE);
      newColumnTableColumn_4.setWidth(120);
      newColumnTableColumn_4.setText("中文名称");

      final TableColumn newColumnTableColumn_1 = new TableColumn(tblSubParameters, SWT.NONE);
      newColumnTableColumn_1.setWidth(80);
      newColumnTableColumn_1.setText("数据类型");

      final TableColumn newColumnTableColumn_2 = new TableColumn(tblSubParameters, SWT.NONE);
      newColumnTableColumn_2.setWidth(150);
      newColumnTableColumn_2.setText("参数值来自父结点");

      final TableColumn newColumnTableColumn_3 = new TableColumn(tblSubParameters, SWT.NONE);
      newColumnTableColumn_3.setWidth(80);
      newColumnTableColumn_3.setText("支持多行");

      final TableColumn newColumnTableColumn_10 = new TableColumn(tblSubParameters, SWT.NONE);
      newColumnTableColumn_10.setWidth(80);
      newColumnTableColumn_10.setText("代码集");

      /*
       * final Button btnFilterUp = new Button(group_2, SWT.NONE);
       * btnFilterUp.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
       * "icons/arrow-090.png")); final FormData fd_btnFilterUp = new
       * FormData(); fd_btnFilterUp.left = new FormAttachment(0, 43);
       * fd_btnFilterUp.bottom = new FormAttachment(100, -1);
       * fd_btnFilterUp.height = 20; fd_btnFilterUp.width = 20;
       * btnFilterUp.setLayoutData(fd_btnFilterUp);
       * btnFilterUp.setVisible(false); final Button btnFilterDown = new
       * Button(group_2, SWT.NONE);
       * btnFilterDown.setImage(ResourceManager.getPluginImage(Activator.getDefault(),
       * "icons/arrow-270.png")); final FormData fd_btnFilterDown = new
       * FormData(); fd_btnFilterDown.bottom = new FormAttachment(100, -1);
       * fd_btnFilterDown.left = new FormAttachment(btnFilterUp, 1,
       * SWT.DEFAULT); fd_btnFilterDown.height = 20; fd_btnFilterDown.width =
       * 20; btnFilterDown.setLayoutData(fd_btnFilterDown);
       * btnFilterDown.setVisible(false);
       */
      init();
   }

   private void LoadMeta_ParamInfo(DiagramTable dt) {
      String sql = "select t.body from t_analysis_diagram t where id=" + dt.getId();
      Blob diagramData = null;
      Connection conn = null;
      Statement stat = null;
      ResultSet rs = null;

      try {
         conn = DbConnectionManager.getConnection();
         if (conn != null) {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);

            if (rs == null) return;
            while (rs.next()) {

               diagramData = rs.getBlob("body");
               if (rs.wasNull()) dt.setBody(null);
               else dt.setBody(diagramData);
            }
            conn.commit();
         }
         InputStream inputSteam = diagramData.getBinaryStream();
         ObjectInputStream oisEditor = new ObjectInputStream(inputSteam);
         Object objEditor = oisEditor.readObject();
         diagram = (Diagram) objEditor;
      }
      catch (SQLException e) {
         DbConnectionManager.rollBack(conn);
         System.out.println("数据表访问出错：" + e.getMessage());
      }
      catch (ClassNotFoundException ex) {
         System.out.print("反序列化错误:" + ex.getMessage());
      }
      catch (IOException ex) {
         System.out.print("读取对象流错误:" + ex.getMessage());
      }
      finally {
         DbConnectionManager.closeResultSet(rs);
         DbConnectionManager.closeStatement(stat);
      }
   }

   private void init() {

      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());
      txtDiagramName.setText(nodeProperties.getDiagramName());
      diagram = nodeProperties.getDiagram();
      MetadataList = new ArrayList<Metadata>();
      for (Metadata meta : nodeProperties.getMetadataList()) {
         MetadataList.add((Metadata) meta.clone());
      }

      tvMetaField.setInput(MetadataList);

      ParameterList = new ArrayList<SubDiagramParameter>();
      for (SubDiagramParameter df : nodeProperties.getParameterList()) {
         ParameterList.add((SubDiagramParameter) df.clone());
      }
      tvSubParameters.setInput(ParameterList);
   }

   private void save() {
      node.setName(txtName.getText().trim());
      node.setDescription(txtDes.getText());
      nodeProperties.setDiagramName(txtDiagramName.getText().trim());
      nodeProperties.setMetadataList(MetadataList);
      nodeProperties.setParameterList(ParameterList);
      nodeProperties.setDiagram(diagram);
      node.setProperties(nodeProperties);
   }

   protected Shell shell;

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

   public void close() {
      shell.dispose();
   }
}

class SubDiagramMetaFieldLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
   public String getColumnText(Object obj, int columnIndex) {
      Metadata dsi = (Metadata) obj;
      switch (columnIndex) {
         case 0:
            return dsi.getCnName() == null ? "" : dsi.getCnName();
         case 1:
            return dsi.getName() == null ? "" : dsi.getName();
         case 2:
            return dsi.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, dsi.getDataType());
      }
      return null;
   }

   public static String[] METAFIELDS = new String[] { "cnName", "name", "dataType" };
}

class SubDiagramParamsLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
   private String[] codeSets;
   private String[] codeSetLabels;

   public SubDiagramParamsLabelProvider(String[] codeSets, String[] codeSetLabels) {
      this.codeSets = codeSets;
      this.codeSetLabels = codeSetLabels;
   }

   public String getColumnText(Object obj, int columnIndex) {
      SubDiagramParameter sdp = (SubDiagramParameter) obj;
      switch (columnIndex) {
         case 0:
            return sdp.getName() == null ? "" : sdp.getName();
         case 1:
            return sdp.getCnName() == null ? "" : sdp.getCnName();
         case 2:
            return sdp.getDataType() == null ? "" : getLabel(Consts.DATATYPE_LABEL, Consts.DATATYPE, sdp.getDataType());
         case 3:
            return sdp.getFatherParameter() == null ? "" : sdp.getFatherParameter().toString();
         case 4:
            return sdp.getPolyLineFlag() == null ? "" : getLabel(Consts.YESNO_LABEL, Consts.YESNO, sdp.getPolyLineFlag());
         case 5:
            return sdp.getCodeSet() == null ? "" : getLabel(codeSetLabels, codeSets, sdp.getCodeSet());
      }
      return null;
   }

   public static String[] DATAFIELDS = new String[] { "name", "cnName", "dataType", "fatherParameter", "polyLineFlag", "codeSet" };

   public void setCodeSets(String[] codeSets) {
      this.codeSets = codeSets;
   }

   public void setCodeSetLabels(String[] codeSetLabels) {
      this.codeSetLabels = codeSetLabels;
   }
}

class subDiagramParameterCellModifier implements ICellModifier {
   private TableViewer tv;
   private String[]    fatherDParameter;
   private String[]    fatherCodeSets;

   public subDiagramParameterCellModifier(TableViewer tv, String[] fatherDParameter, String[] fatherCodeSets) {
      this.tv = tv;
      this.fatherDParameter = fatherDParameter;
      this.fatherCodeSets = fatherCodeSets;
   }

   public boolean canModify(Object element, String property) {
      return true;
   }

   public Object getValue(Object element, String property) {
      SubDiagramParameter o = (SubDiagramParameter) element;
      if (property.equals("fatherParameter")) return getIndex(fatherDParameter, o.getFatherParameter());
      if (property.equals("codeSet")) return getIndex(fatherCodeSets, o.getCodeSet());
      throw new RuntimeException("错误的列别名:" + property);
   }

   private int getIndex(String[] labels, String name) {
      for (int i = 0; i < labels.length; i++) {
         if (labels[i].equalsIgnoreCase(name)) return i;
      }
      return 0;
   }

   public void modify(Object element, String property, Object value) {
      TableItem item = (TableItem) element;
      SubDiagramParameter o = (SubDiagramParameter) item.getData();

      if (property.equals("fatherParameter")) {
         Integer itmp = (Integer) value;
         o.setFatherParameter(fatherDParameter[itmp]);
      }
      else if (property.equals("codeSet")) {
         Integer itmp = (Integer) value;
         o.setCodeSet(fatherCodeSets[itmp]);
      }
      else {
         throw new RuntimeException("错误的列别名:" + property);
      }
      tv.update(o, null);

   }
}
