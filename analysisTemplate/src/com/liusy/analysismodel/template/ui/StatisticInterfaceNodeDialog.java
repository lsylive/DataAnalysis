package com.liusy.analysismodel.template.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.template.commands.StatisticNodeBeanEditCommand;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.dialogProperties.StatisticInterfaceProperties;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.StatisticInterfaceNode;
import com.liusy.analysis.template.model.vo.CodeSet;
import com.liusy.analysis.template.model.vo.DetailLinkField;
import com.liusy.analysis.template.model.vo.FieldConfig;
import com.liusy.analysis.template.model.vo.GrathFieldConfig;
import com.liusy.analysis.template.model.vo.StaticGrathConfig;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.NumberVerifier;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.dialog.DetailLinkDialog;
import com.liusy.analysismodel.util.MessageUtil;

public class StatisticInterfaceNodeDialog extends Dialog {

   private Text                         txtMaxCount;
   private Text                         txtGraphTitle;
   private TableViewer                  tvShowConfig;
   private Table                        tblShowConfig;
   private List                         list;
   private Combo                        cboType;
   private StyledText                   txtDes;
   private Text                         txtName;
   private int                          result          = 0;
   private StatisticInterfaceProperties nodeProperties;
   private StatisticInterfaceNode       node;
   private Diagram                      diagram;
   private List<GrathFieldConfig>       configList;
   private StaticGrathConfig            staticGrathCfig;
   private String[]                     DATAFIELDS      = { "cnName", "name", "dataFormat", "align", "width", "visible", "codeset", "axis", "link" };
   private String[]                     GRATHDATAFIELDS = { "cnName", "name", "axis" };
   private String[]                     columnNames     = { "" };
   private StatisticNodeBeanEditCommand cmd;

   /**
    * Create the dialog
    * 
    * @param parent
    * @param style
    */
   public StatisticInterfaceNodeDialog(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * 
    * @param parent
    */
   public StatisticInterfaceNodeDialog(Shell parent, StatisticInterfaceNode node, StatisticNodeBeanEditCommand command) {

      this(parent, SWT.NONE);
      this.node = node;
      this.diagram = node.getDiagram();
      this.cmd = command;
   }

   /**
    * Open the dialog
    * 
    * @return the result
    */
   public int open() {
      //      nodeProperties = node.getProperties();
      try {
         nodeProperties = (StatisticInterfaceProperties) node.getProperties().clone();
      }
      catch (CloneNotSupportedException e) {
         e.printStackTrace();
      }

      List<CodeSet> cs = diagram.getCodeSetList();
      codeSetNames = new String[cs.size() + 1];
      codeSetValues = new String[cs.size() + 1];
      codeSetNames[0] = "";
      codeSetValues[0] = "";
      for (int i = 0; i < cs.size(); i++) {
         codeSetNames[i + 1] = cs.get(i).getCnName();
         codeSetValues[i + 1] = cs.get(i).getName();
      }
      
      configList = new ArrayList<GrathFieldConfig>();
      for (int i = 0; i < nodeProperties.getFieldConfigs2().size(); i++) {
         configList.add((GrathFieldConfig) nodeProperties.getFieldConfigs2().get(i).clone());
      }
      
      createContents();
      init();
      shell.setLocation(getParentLocation());
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) display.sleep();
      }
      return result;
   }

   //   /**
   //    * Create contents of the dialog
   //    */
   protected void createContents() {
      cmd.setBean(nodeProperties);
      cmd.setNode(node);
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(835, 500);
      shell.setText("统计图配置属性");

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
      fd_txtName.right = new FormAttachment(65, 0);
      fd_txtName.left = new FormAttachment(label, 0);
      txtName.setLayoutData(fd_txtName);
      txtName.setEditable(true);

      final Label label_6 = new Label(basicGroup, SWT.RIGHT);
      final FormData fd_label_6 = new FormData();
      fd_label_6.top = new FormAttachment(0, 0);
      fd_label_6.right = new FormAttachment(75, 0);
      fd_label_6.left = new FormAttachment(txtName, 0, SWT.DEFAULT);
      label_6.setLayoutData(fd_label_6);
      label_6.setText("图表类型：");

      cboType = new Combo(basicGroup, SWT.NONE | SWT.READ_ONLY);
      final FormData fd_cboType = new FormData();
      fd_cboType.top = new FormAttachment(0, 0);
      fd_cboType.right = new FormAttachment(100, 0);
      fd_cboType.left = new FormAttachment(label_6, 0, SWT.DEFAULT);
      cboType.setLayoutData(fd_cboType);
      cboType.setVisibleItemCount(10);

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

      final Group sdsdGroup = new Group(shell, SWT.NONE);
      final FormData fd_sdsdGroup = new FormData();
      fd_sdsdGroup.bottom = new FormAttachment(100, -40);
      fd_sdsdGroup.right = new FormAttachment(100, -5);
      fd_sdsdGroup.left = new FormAttachment(0, 5);
      fd_sdsdGroup.top = new FormAttachment(basicGroup, -1, SWT.DEFAULT);
      sdsdGroup.setLayoutData(fd_sdsdGroup);
      sdsdGroup.setLayout(new FormLayout());

      final Label labGraphTitle = new Label(sdsdGroup, SWT.RIGHT);
      final FormData fd_labGraphTitle = new FormData();
      fd_labGraphTitle.right = new FormAttachment(15, 0);
      fd_labGraphTitle.top = new FormAttachment(0, 0);
      fd_labGraphTitle.left = new FormAttachment(0, 1);
      labGraphTitle.setLayoutData(fd_labGraphTitle);
      labGraphTitle.setText("图标题：");

      txtGraphTitle = new Text(sdsdGroup, SWT.BORDER);
      final FormData fd_txtGraphTitle = new FormData();
      fd_txtGraphTitle.left = new FormAttachment(labGraphTitle, 0);
      fd_txtGraphTitle.top = new FormAttachment(0, 0);
      fd_txtGraphTitle.right = new FormAttachment(65, 0);
      txtGraphTitle.setLayoutData(fd_txtGraphTitle);

      final Label lblMaxOutput = new Label(sdsdGroup, SWT.NONE);
      final FormData fd_lblMaxOutput = new FormData();
      fd_lblMaxOutput.top = new FormAttachment(0, 0);
      fd_lblMaxOutput.right = new FormAttachment(75, 0);
      fd_lblMaxOutput.left = new FormAttachment(txtGraphTitle, 0);
      lblMaxOutput.setLayoutData(fd_lblMaxOutput);
      lblMaxOutput.setAlignment(SWT.RIGHT);
      lblMaxOutput.setText("最大输出数：");

      txtMaxCount = new Text(sdsdGroup, SWT.BORDER);
      final FormData fd_txtMaxCount = new FormData();
      fd_txtMaxCount.top = new FormAttachment(0, 0);
      fd_txtMaxCount.right = new FormAttachment(100, -1);
      fd_txtMaxCount.left = new FormAttachment(lblMaxOutput, 0);
      txtMaxCount.setLayoutData(fd_txtMaxCount);

      tvShowConfig = new TableViewer(sdsdGroup, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
      tvShowConfig.setLabelProvider(new GraphListLabelProvider());
      tvShowConfig.setContentProvider(new ViewContentProvider());
      tblShowConfig = tvShowConfig.getTable();
      CellEditor[] cellEditor = new CellEditor[9];
      cellEditor[0] = new TextCellEditor(tblShowConfig);
      cellEditor[1] = new TextCellEditor(tblShowConfig);
      cellEditor[2] = new TextCellEditor(tblShowConfig);
      cellEditor[3] = new ComboBoxCellEditor(tblShowConfig, Consts.ALIGN_LABEL, SWT.READ_ONLY);
      cellEditor[4] = new TextCellEditor(tblShowConfig);
      cellEditor[5] = new ComboBoxCellEditor(tblShowConfig, Consts.YESNO_LABEL, SWT.READ_ONLY);
      cellEditor[6] = new ComboBoxCellEditor(tblShowConfig, codeSetNames, SWT.READ_ONLY);
      cellEditor[7] = new ComboBoxCellEditor(tblShowConfig, Consts.X_Y_LABEL, SWT.READ_ONLY);
      cellEditor[8] = new DetailLinkCellEditor(tblShowConfig);
      Text text1 = (Text) cellEditor[4].getControl();
      text1.addVerifyListener(new NumberVerifier());

      tvShowConfig.setCellEditors(cellEditor);
      tvShowConfig.setCellModifier(new GraphConfigCellModifier(tvShowConfig));
      tvShowConfig.setColumnProperties(DATAFIELDS);
      final FormData fd_tblShowConfig = new FormData();
      fd_tblShowConfig.bottom = new FormAttachment(100, -21);
      fd_tblShowConfig.top = new FormAttachment(txtGraphTitle, 5);
      fd_tblShowConfig.right = new FormAttachment(100, -1);
      fd_tblShowConfig.left = new FormAttachment(0, 1);
      tblShowConfig.setLayoutData(fd_tblShowConfig);
      tblShowConfig.setLinesVisible(true);
      tblShowConfig.setHeaderVisible(true);

      final TableColumn colCnName = new TableColumn(tblShowConfig, SWT.NONE);
      colCnName.setWidth(100);
      colCnName.setText("中文名");

      final TableColumn colFieldName = new TableColumn(tblShowConfig, SWT.NONE);
      colFieldName.setWidth(100);
      colFieldName.setText("列名");

      final TableColumn colDataType = new TableColumn(tblShowConfig, SWT.NONE);
      colDataType.setWidth(80);
      colDataType.setText("数据格式");

      final TableColumn colAlign = new TableColumn(tblShowConfig, SWT.NONE);
      colAlign.setWidth(70);
      colAlign.setText("对齐方式");

      final TableColumn colWidth = new TableColumn(tblShowConfig, SWT.NONE);
      colWidth.setWidth(60);
      colWidth.setText("宽度");

      final TableColumn colVisible = new TableColumn(tblShowConfig, SWT.NONE);
      colVisible.setWidth(70);
      colVisible.setText("是否显示");

      final TableColumn colCodeSet = new TableColumn(tblShowConfig, SWT.NONE);
      colCodeSet.setWidth(100);
      colCodeSet.setText("代码集");

      final TableColumn colAxis = new TableColumn(tblShowConfig, SWT.NONE);
      colAxis.setWidth(70);
      colAxis.setText("坐标轴");

      final TableColumn colLink = new TableColumn(tblShowConfig, SWT.NONE);
      colLink.setWidth(150);
      colLink.setText("链接");

      final Button btnLoad = new Button(sdsdGroup, SWT.NONE);
      btnLoad.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/table-import.png"));
      final FormData fd_btnLoad = new FormData();
      fd_btnLoad.height = 20;
      fd_btnLoad.width = 20;
      fd_btnLoad.bottom = new FormAttachment(100, -1);
      fd_btnLoad.top = new FormAttachment(100, -21);
      fd_btnLoad.left = new FormAttachment(0, 1);
      btnLoad.setLayoutData(fd_btnLoad);
      btnLoad.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            int res = MessageUtil.comfirm(shell, "导入", "是否从上一环节导入字段信息?\r\n注意：已有字段信息将被覆盖!");
            if (res == SWT.YES) {
               List<Connection> list = node.getIncomingConnections();
               if (list == null || list.size() == 0) {
                  MessageUtil.alert(shell, "提示", "找不到节点。");
                  return;
               }
               else if (list.size() > 1) {
                  MessageUtil.alert(shell, "提示", "上一环节的节点不能多于一个。");
                  return;
               }
               INode node = list.get(0).getSource();
               List<Metadata> listMeta = node.getMeta();
               configList.clear();
               for (Metadata md : listMeta) {
                  configList.add(new GrathFieldConfig(md));
               }
               //					grathInit();
               tvShowConfig.refresh();
            }
         }
      });
      btnLoad.setToolTipText("从上一环节导入数据结构");

      final Button btnDel = new Button(sdsdGroup, SWT.NONE);
      btnDel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));
      final FormData fd_btnDel = new FormData();
      fd_btnDel.height = 20;
      fd_btnDel.width = 20;
      fd_btnDel.bottom = new FormAttachment(100, -1);
      fd_btnDel.top = new FormAttachment(100, -21);
      fd_btnDel.left = new FormAttachment(btnLoad, 1, SWT.DEFAULT);
      btnDel.setLayoutData(fd_btnDel);
      btnDel.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            TableItem[] selectItems = tblShowConfig.getSelection();
            if (selectItems != null && selectItems.length > 0) {
               for (TableItem ti : selectItems) {
                  GrathFieldConfig o = (GrathFieldConfig) ti.getData();
                  configList.remove(o);
               }
               tvShowConfig.refresh();
            }
         }
      });
      final Button btnUp = new Button(sdsdGroup, SWT.NONE);
      btnUp.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/arrow-090.png"));
      final FormData fd_btnUp = new FormData();
      fd_btnUp.height = 20;
      fd_btnUp.width = 20;
      fd_btnUp.bottom = new FormAttachment(100, -1);
      fd_btnUp.top = new FormAttachment(100, -21);
      fd_btnUp.left = new FormAttachment(btnDel, 1, SWT.DEFAULT);
      btnUp.setLayoutData(fd_btnUp);
      btnUp.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            if (tblShowConfig.getSelectionCount() == 1) {
               int idx = tblShowConfig.getSelectionIndex();
               if (idx > 0) {
                  GrathFieldConfig o = (GrathFieldConfig) tblShowConfig.getSelection()[0].getData();
                  configList.remove(o);
                  configList.add(idx - 1, o);
                  tvShowConfig.refresh();
               }
            }
         }
      });
      final Button btnDown = new Button(sdsdGroup, SWT.NONE);
      btnDown.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/arrow-270.png"));
      final FormData fd_btnDown = new FormData();
      fd_btnDown.height = 20;
      fd_btnDown.width = 20;
      fd_btnDown.bottom = new FormAttachment(100, -1);
      fd_btnDown.top = new FormAttachment(100, -21);
      fd_btnDown.left = new FormAttachment(btnUp, 1, SWT.DEFAULT);
      btnDown.setLayoutData(fd_btnDown);
      btnDown.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            if (tblShowConfig.getSelectionCount() == 1) {
               int idx = tblShowConfig.getSelectionIndex();
               if (idx < tblShowConfig.getItemCount()) {
                  GrathFieldConfig o = (GrathFieldConfig) tblShowConfig.getSelection()[0].getData();
                  configList.remove(o);
                  configList.add(idx + 1, o);
                  tvShowConfig.refresh();
               }
            }
         }
      });

      final Button btnOk = new Button(shell, SWT.NONE);
      btnOk.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/tick.png"));
      final FormData fd_btnOk = new FormData();
      fd_btnOk.height = 28;
      fd_btnOk.width = 80;
      fd_btnOk.bottom = new FormAttachment(100, -5);
      fd_btnOk.right = new FormAttachment(50, -10);
      btnOk.setLayoutData(fd_btnOk);
      btnOk.setText("确定(&O)");
      btnOk.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            if (check()) {
               staticGrathCfig.setTemplateType(cboType.getSelectionIndex());
               staticGrathCfig.setRecordCount("".equals(txtMaxCount.getText().trim()) ? 0 : Integer.valueOf(txtMaxCount.getText().trim()));
               staticGrathCfig.setGrathTitle(txtGraphTitle.getText().trim());
               save();
               result = 1;
               close();
            }
         }
      });

      final Button btnCancel = new Button(shell, SWT.NONE);
      btnCancel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/cross.png"));
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

      //
      cboType.setItems(Consts.CHART_LABEL);
      cboType.setVisibleItemCount(10);

   }

   private void init() {
      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());


      tvShowConfig.setInput(configList);
      //图形配置
      staticGrathCfig = nodeProperties.getStaticGrathCfig();
      //
      cboType.select(staticGrathCfig.getTemplateType());
      txtMaxCount.setText(String.valueOf(staticGrathCfig.getRecordCount()));
      txtGraphTitle.setText(staticGrathCfig.getGrathTitle());
      //		grathInit();
   }

   private boolean check() {
      int xAxisCount = 0;
      int yAxiscount = 0;
      for (GrathFieldConfig grathCfig : configList) {
         if ("X轴".equals(grathCfig.getAxis())) {
            xAxisCount++;
         }
         if ("Y轴".equals(grathCfig.getAxis())) {
            yAxiscount++;
         }

      }
      if (xAxisCount == 0) {
         MessageBox messageBox = new MessageBox(this.shell, SWT.OK);
         messageBox.setMessage("要选择一个X轴！");
         messageBox.open();
         return false;
      }
      if (xAxisCount > 1) {
         MessageBox messageBox = new MessageBox(this.shell, SWT.OK);
         messageBox.setMessage("不能选定多个X轴！");
         messageBox.open();
         return false;
      }
      return true;
   }

   private void save() {
      node.setName(txtName.getText().trim());
      node.setDescription(txtDes.getText().trim());
      nodeProperties.setFieldConfigs2(configList);
      nodeProperties.setStaticGrathCfig(staticGrathCfig);
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

   public int getIndex(String[] strs, String name) {
      for (int i = 0; i < strs.length; i++) {
         if (name.equals(strs[i])) { return i; }
      }
      return 0;
   }

   public void close() {
      shell.dispose();
   }

   private String[] codeSetNames;
   private String[] codeSetValues;

   class GraphListLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
      public String getColumnText(Object obj, int columnIndex) {
         GrathFieldConfig dsi = (GrathFieldConfig) obj;
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
               return dsi.getAxis() == null ? "" : dsi.getAxis();
            case 8:
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

   class GraphConfigCellModifier extends BaseCellModifer implements ICellModifier {

      public GraphConfigCellModifier(TableViewer tv) {
         this.tv = tv;
      }

      public Object getValue(Object element, String property) {
         GrathFieldConfig o = (GrathFieldConfig) element;
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
         else if (property.equals("axis")) return getIndex(Consts.X_Y_AXIS, o.getAxis());
         else throw new RuntimeException("错误的列别名:" + property);
      }

      public void modify(Object element, String property, Object value) {
         TableItem item = (TableItem) element;
         GrathFieldConfig o = (GrathFieldConfig) item.getData();
         if (property.equals("name")) o.setName((String) value);
         else if (property.equals("cnName")) o.setCnName((String) value);
         else if (property.equals("dataFormat")) o.setDataFormat((String) value);
         else if (property.equals("width")) o.setWidth((String) value);
         else if (property.equals("align")) o.setAlign(Consts.ALIGN[(Integer) value]);
         else if (property.equals("visible")) o.setVisible(Consts.YESNO[(Integer) value]);
         else if (property.equals("codeset")) o.setCodeSet(codeSetValues[(Integer) value]);
         else if (property.equals("link")) {
            if (value instanceof DetailLinkField) o.setDetailLink((DetailLinkField) value);
         }
         else if (property.equals("axis")) o.setAxis(Consts.X_Y_AXIS[(Integer) value]);
         else throw new RuntimeException("错误的列别名:" + property);
         tv.update(o, null);
      }
   }

   class DetailLinkCellEditor extends DialogCellEditor {

      private Table table;

      public DetailLinkCellEditor(Composite parent) {
         super(parent);
         this.table = (Table) parent;
      }

      protected Object openDialogBox(Control cellEditorWindow) {
         TableItem[] selectItems = table.getSelection();
         FieldConfig dlf = null;
         if (selectItems != null && selectItems.length > 0) dlf = (FieldConfig) selectItems[0].getData();

         List<FieldConfig> list = new ArrayList<FieldConfig>();
         for (GrathFieldConfig g : configList) {
            list.add((FieldConfig) g);
         }

         DetailLinkDialog gcd = new DetailLinkDialog(cellEditorWindow.getShell(), dlf, list, diagram.getNodes());
         DetailLinkField res = gcd.open();
         return res;
      }
   }
}
