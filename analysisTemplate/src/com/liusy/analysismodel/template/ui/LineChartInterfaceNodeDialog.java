package com.liusy.analysismodel.template.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
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
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.template.commands.LineChartNodeBeanEditCommand;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.connection.Connection;
import com.liusy.analysis.template.model.dialogProperties.LineChartInterfaceProperties;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.node.LineChartInterfaceNode;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysis.template.model.vo.GrathFieldConfig;
import com.liusy.analysis.template.model.vo.LinearGrathConfig;
import com.liusy.analysismodel.template.ui.contentProvider.BaseCellModifer;
import com.liusy.analysismodel.template.ui.contentProvider.BaseLabelProvider;
import com.liusy.analysismodel.template.ui.contentProvider.NumberVerifier;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.util.MessageUtil;

public class LineChartInterfaceNodeDialog extends Dialog {

   private Text                         txtEndDate;
   private Text                         txtStartDate;
   private Combo                        comboStep;
   private Combo                        comboDataType;
   private Text                         txtStepLength;
   private Text                         txtEnd;
   private Text                         txtStart;
   private Combo                        comboColumnName;
   private Text                         txtTitle;
   private Text                         txtMaxCount;
   private Text                         txtGrathTitle;
   private List                         list;
   private TableViewer                  tvShowConfig;
   private Table                        tblShowConfig;
   private StyledText                   txtDes;
   private Text                         txtName;
   private int                          result          = 0;
   private LineChartInterfaceProperties nodeProperties;
   private LineChartInterfaceNode       node;
   private Diagram                      diagram;
   private List<GrathFieldConfig>       configList;
   private LinearGrathConfig            lineStaticGraphCfig;
   private String[]                     DATAFIELDS      = { "cnName", "name", "dataFormat", "align", "width", "visible", "codeSet", "axis" };
   private String[]                     GRATHDATAFIELDS = { "cnName", "name", "axis" };
   private String[]                     columnNames     = { "" };
   private LineChartNodeBeanEditCommand cmd;

   /**
    * Create the dialog
    * 
    * @param parent
    * @param style
    */
   public LineChartInterfaceNodeDialog(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * 
    * @param parent
    */
   public LineChartInterfaceNodeDialog(Shell parent, LineChartInterfaceNode node, LineChartNodeBeanEditCommand command) {

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
      // nodeProperties = node.getProperties();
      try {
         nodeProperties = (LineChartInterfaceProperties) node.getProperties().clone();
      }
      catch (CloneNotSupportedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
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

   // /**
   // * Create contents of the dialog
   // */
   protected void createContents() {
      cmd.setBean(nodeProperties);
      cmd.setNode(node);
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setLayout(new FormLayout());
      shell.setSize(800, 430);
      shell.setText("线型图配置属性");

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
            // staticGrathCfig.setXColumn(txtXColumn.getText().trim());
            // staticGrathCfig.setYColumns(Arrays.asList(listYColumn.getItems()));
            if (check()) {
               // staticGrathCfig.setTemplateType(cboType.getSelectionIndex());
               // staticGrathCfig.setRecordAllFlag(radAll.getSelection());

               lineStaticGraphCfig.setAxisTitle(txtTitle.getText().trim());
               lineStaticGraphCfig.setColumnName(comboColumnName.getText().trim());
               lineStaticGraphCfig.setRecordCount("".equals(txtMaxCount.getText().trim()) ? 0 : Integer.valueOf(txtMaxCount.getText().trim()));

               lineStaticGraphCfig.setGrathTitle(txtGrathTitle.getText().trim());
               if (comboDataType.getSelectionIndex() == 1) {
                  String tempEndDate = txtEnd.getText().trim();
                  String tempStartDate = txtStart.getText().trim();
                  if (!StringUtil.isNumeric(tempStartDate)) {
                     MessageBox messageBox = new MessageBox(shell, SWT.OK);
                     messageBox.setMessage("开始值不是合法数值！");
                     messageBox.open();
                     txtStart.setFocus();
                     return;
                  }
                  if (!StringUtil.isNumeric(tempEndDate)) {
                     MessageBox messageBox = new MessageBox(shell, SWT.OK);
                     messageBox.setMessage("结束值不是合法数值！");
                     messageBox.open();
                     txtEnd.setFocus();
                     return;
                  }
                  lineStaticGraphCfig.setAxisEnd(Integer.valueOf(txtEnd.getText().trim()));
                  lineStaticGraphCfig.setAxisStart(Integer.valueOf(txtStart.getText().trim()));
                  lineStaticGraphCfig.setStepLength(Integer.valueOf("".equals(txtStepLength.getText().trim()) ? "0" : txtStepLength.getText().trim()));
               }
               else if (comboDataType.getSelectionIndex() == 2) {
                  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                  try {
                     String tempEndDate = txtEndDate.getText().trim();
                     String tempStartDate = txtStartDate.getText().trim();
                     if (!StringUtil.isDate(tempStartDate)) {
                        MessageBox messageBox = new MessageBox(shell, SWT.OK);
                        messageBox.setMessage("开始日期值格式不对！");
                        messageBox.open();
                        txtStartDate.setFocus();
                        return;
                     }
                     if (!StringUtil.isDate(tempEndDate)) {
                        MessageBox messageBox = new MessageBox(shell, SWT.OK);
                        messageBox.setMessage("结束日期值格式不对！");
                        messageBox.open();
                        txtEndDate.setFocus();
                        return;
                     }
                     Date startDate = df.parse(txtStartDate.getText().trim());
                     Date endDate = df.parse(txtEndDate.getText().trim());
                     lineStaticGraphCfig.setAxisEndDate(endDate);
                     lineStaticGraphCfig.setAxisStartDate(startDate);
                     lineStaticGraphCfig
                           .setDateAxisStepLengthType(getValue(Consts.AXIS_DATE_STEPLENGTH_LABEL, Consts.AXIS_DATE_STEPLENGTH, comboStep.getText()));
                  }
                  catch (ParseException e1) {
                     // TODO Auto-generated catch block
                     e1.printStackTrace();
                  }

               }

               lineStaticGraphCfig.setDataType(comboDataType.getSelectionIndex() == -1 ? 0 : comboDataType.getSelectionIndex());

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

      // final Group group = new Group(shell, SWT.NONE);
      // group.setText("列表配置");
      // final FormData fd_group = new FormData();
      // fd_group.top = new FormAttachment(basicGroup, 5);
      // fd_group.left = new FormAttachment(0, 5);
      // fd_group.right = new FormAttachment(100, -5);
      // fd_group.bottom = new FormAttachment(100, -40);
      //
      // group.setLayoutData(fd_group);
      // group.setLayout(new FormLayout());

      final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
      final FormData fd_tabFolder = new FormData();
      fd_tabFolder.top = new FormAttachment(basicGroup, 4, SWT.DEFAULT);
      fd_tabFolder.bottom = new FormAttachment(100, -40);
      fd_tabFolder.right = new FormAttachment(100, -5);
      fd_tabFolder.left = new FormAttachment(0, 5);
      tabFolder.setLayoutData(fd_tabFolder);

      final TabItem tabItemFiledCfig = new TabItem(tabFolder, SWT.NONE);
      tabItemFiledCfig.setText("列表配置");

      final Composite compositeFiledCfig = new Composite(tabFolder, SWT.NONE);
      compositeFiledCfig.setLayout(new FormLayout());
      tabItemFiledCfig.setControl(compositeFiledCfig);

      final TabItem tabItemGrathic = new TabItem(tabFolder, SWT.NONE);
      tabItemGrathic.setText("图配置");

      final Composite compositeGrithic = new Composite(tabFolder, SWT.NONE);
      compositeGrithic.setLayout(new FormLayout());
      tabItemGrathic.setControl(compositeGrithic);
      CellEditor[] grathicCellEditors = new CellEditor[3];
      grathicCellEditors[0] = null;
      grathicCellEditors[1] = null;

      final Label labGrathTitle = new Label(compositeGrithic, SWT.NONE);
      labGrathTitle.setAlignment(SWT.RIGHT);
      final FormData fd_labGrathTitle = new FormData();
      fd_labGrathTitle.right = new FormAttachment(10, 0);
      fd_labGrathTitle.top = new FormAttachment(0, 18);
      fd_labGrathTitle.left = new FormAttachment(0, 5);
      labGrathTitle.setLayoutData(fd_labGrathTitle);
      labGrathTitle.setText("图标题：");

      txtGrathTitle = new Text(compositeGrithic, SWT.BORDER);
      final FormData fd_txtGrathTitle = new FormData();
      fd_txtGrathTitle.right = new FormAttachment(30, 0);
      fd_txtGrathTitle.left = new FormAttachment(10, 5);
      fd_txtGrathTitle.top = new FormAttachment(0, 15);
      txtGrathTitle.setLayoutData(fd_txtGrathTitle);

      final Label labMaxOuput = new Label(compositeGrithic, SWT.NONE);
      labMaxOuput.setAlignment(SWT.RIGHT);
      final FormData fd_labMaxOuput = new FormData();
      fd_labMaxOuput.right = new FormAttachment(45, 0);
      fd_labMaxOuput.left = new FormAttachment(35, 0);
      fd_labMaxOuput.top = new FormAttachment(0, 18);
      labMaxOuput.setLayoutData(fd_labMaxOuput);
      labMaxOuput.setText("最大输出数：");

      txtMaxCount = new Text(compositeGrithic, SWT.BORDER);
      final FormData fd_txtMaxCount = new FormData();
      fd_txtMaxCount.right = new FormAttachment(65, 0);
      fd_txtMaxCount.left = new FormAttachment(45, 0);
      fd_txtMaxCount.top = new FormAttachment(labMaxOuput, 0, SWT.TOP);
      txtMaxCount.setLayoutData(fd_txtMaxCount);

      Group group_1;
      group_1 = new Group(compositeGrithic, SWT.NONE);
      group_1.setLayout(new FormLayout());
      group_1.setText("连续轴");
      final FormData fd_group_1 = new FormData();
      fd_group_1.left = new FormAttachment(0, 5);
      fd_group_1.bottom = new FormAttachment(100, -5);
      fd_group_1.top = new FormAttachment(0, 49);
      fd_group_1.right = new FormAttachment(100, -5);
      group_1.setLayoutData(fd_group_1);

      final Label labTitle = new Label(group_1, SWT.NONE);
      labTitle.setAlignment(SWT.RIGHT);
      final FormData fd_labTitle = new FormData();
      // fd_labTitle.bottom = new FormAttachment(20, 0);
      fd_labTitle.right = new FormAttachment(10, 0);
      fd_labTitle.top = new FormAttachment(12, 0);
      fd_labTitle.left = new FormAttachment(0, 5);
      labTitle.setLayoutData(fd_labTitle);
      labTitle.setText("轴别名：");

      txtTitle = new Text(group_1, SWT.BORDER);
      final FormData fd_txtTitle = new FormData();
      fd_txtTitle.top = new FormAttachment(10, 0);
      fd_txtTitle.right = new FormAttachment(30, 0);
      fd_txtTitle.left = new FormAttachment(10, 0);
      txtTitle.setLayoutData(fd_txtTitle);

      final Label labColumnName = new Label(group_1, SWT.NONE);
      labColumnName.setAlignment(SWT.RIGHT);
      final FormData fd_labColumnName = new FormData();
      // fd_labColumnName.bottom = new FormAttachment(20, 0);
      fd_labColumnName.top = new FormAttachment(12, 0);
      fd_labColumnName.right = new FormAttachment(45, 0);
      fd_labColumnName.left = new FormAttachment(35, 0);
      labColumnName.setLayoutData(fd_labColumnName);
      labColumnName.setText("连续轴名：");

      comboColumnName = new Combo(group_1, SWT.NONE);
      final FormData fd_comboColumnName = new FormData();
      // fd_comboColumnName.bottom = new FormAttachment(20, 0);
      fd_comboColumnName.top = new FormAttachment(10, 0);
      fd_comboColumnName.left = new FormAttachment(45, 0);
      fd_comboColumnName.right = new FormAttachment(65, 0);
      comboColumnName.setLayoutData(fd_comboColumnName);

      final Label labDatatype = new Label(group_1, SWT.NONE);
      labDatatype.setAlignment(SWT.RIGHT);
      final FormData fd_labDatatype = new FormData();
      // fd_labDatatype.bottom = new FormAttachment(60, 0);
      fd_labDatatype.top = new FormAttachment(12, 0);
      fd_labDatatype.right = new FormAttachment(80, 0);
      fd_labDatatype.left = new FormAttachment(70, 0);
      labDatatype.setLayoutData(fd_labDatatype);
      labDatatype.setText("数据类型：");

      comboDataType = new Combo(group_1, SWT.NONE);
      final FormData fd_comboDataType = new FormData();
      fd_comboDataType.right = new FormAttachment(100, 0);
      // fd_comboDataType.bottom = new FormAttachment(60, 0);
      fd_comboDataType.top = new FormAttachment(10, 0);
      fd_comboDataType.left = new FormAttachment(80, 0);
      comboDataType.setLayoutData(fd_comboDataType);
      comboDataType.setItems(Consts.GRAPH_DATA_TYPE);
      comboDataType.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(SelectionEvent e) {
            System.out.println(comboDataType.getText());
            if (comboDataType.getSelectionIndex() == 1) {
               txtStepLength.setEnabled(true);
               txtStart.setEnabled(true);
               txtEnd.setEnabled(true);
               comboStep.setEnabled(false);
               txtStartDate.setEnabled(false);
               txtEndDate.setEnabled(false);

            }
            else if (comboDataType.getSelectionIndex() == 2) {
               txtStepLength.setEnabled(false);
               txtStart.setEnabled(false);
               txtEnd.setEnabled(false);
               comboStep.setEnabled(true);
               txtStartDate.setEnabled(true);
               txtEndDate.setEnabled(true);
            }
         }
      });
      final Label labStart = new Label(group_1, SWT.NONE);
      labStart.setAlignment(SWT.RIGHT);
      final FormData fd_labStart = new FormData();
      // fd_labStart.bottom = new FormAttachment(60, 0);
      fd_labStart.top = new FormAttachment(44, 0);
      fd_labStart.right = new FormAttachment(10, 0);
      fd_labStart.left = new FormAttachment(0, 5);
      labStart.setLayoutData(fd_labStart);
      labStart.setText("起始值：");

      txtStart = new Text(group_1, SWT.BORDER);
      final FormData fd_txtStart = new FormData();
      fd_txtStart.top = new FormAttachment(42, 0);
      fd_txtStart.right = new FormAttachment(30, 0);
      fd_txtStart.left = new FormAttachment(10, 0);
      txtStart.setLayoutData(fd_txtStart);

      final Label labEnd = new Label(group_1, SWT.NONE);
      labEnd.setAlignment(SWT.RIGHT);
      final FormData fd_labEnd = new FormData();
      // fd_labEnd.bottom = new FormAttachment(0, 117);76
      fd_labEnd.top = new FormAttachment(42, 0);
      fd_labEnd.right = new FormAttachment(45, 0);
      fd_labEnd.left = new FormAttachment(35, 5);
      labEnd.setLayoutData(fd_labEnd);
      labEnd.setText("结束值：");

      txtEnd = new Text(group_1, SWT.BORDER);
      final FormData fd_txtEnd = new FormData();
      // fd_txtEnd.bottom = new FormAttachment(0, 121);
      fd_txtEnd.top = new FormAttachment(42, 0);
      fd_txtEnd.right = new FormAttachment(65, 0);
      fd_txtEnd.left = new FormAttachment(45, 0);
      txtEnd.setLayoutData(fd_txtEnd);

      final Label labStepLength = new Label(group_1, SWT.NONE);
      labStepLength.setAlignment(SWT.RIGHT);
      final FormData fd_labStepLength = new FormData();
      fd_labStepLength.right = new FormAttachment(80, 0);
      fd_labStepLength.top = new FormAttachment(42, 0);
      // fd_labStepLength.bottom = new FormAttachment(labEnd, 0, SWT.BOTTOM);
      fd_labStepLength.left = new FormAttachment(70, 0);
      labStepLength.setLayoutData(fd_labStepLength);
      labStepLength.setText("步长：");

      txtStepLength = new Text(group_1, SWT.BORDER);
      //		fd_comboColumnName.right = new FormAttachment(txtStepLength, 0, SWT.RIGHT);
      final FormData fd_txtStepLength = new FormData();
      //		 fd_txtStepLength.bottom = new FormAttachment(90, 0);74
      fd_txtStepLength.top = new FormAttachment(42, 0);
      fd_txtStepLength.right = new FormAttachment(100, 0);
      fd_txtStepLength.left = new FormAttachment(80, 0);
      txtStepLength.setLayoutData(fd_txtStepLength);

      final Label labStartDate = new Label(group_1, SWT.NONE);
      labStartDate.setAlignment(SWT.RIGHT);
      final FormData fd_labStartDate = new FormData();
      fd_labStartDate.top = new FormAttachment(74, 0);
      fd_labStartDate.left = new FormAttachment(0, 5);
      fd_labStartDate.right = new FormAttachment(10, 0);
      labStartDate.setLayoutData(fd_labStartDate);
      labStartDate.setText("开始日期：");

      txtStartDate = new Text(group_1, SWT.BORDER);
      final FormData fd_txtStartDate = new FormData();
      fd_txtStartDate.top = new FormAttachment(74, 0);
      fd_txtStartDate.left = new FormAttachment(10, 0);
      fd_txtStartDate.right = new FormAttachment(30, 0);
      txtStartDate.setLayoutData(fd_txtStartDate);

      final Label labEndDate = new Label(group_1, SWT.NONE);
      labEndDate.setAlignment(SWT.RIGHT);
      final FormData fd_labEndDate = new FormData();
      fd_labEndDate.top = new FormAttachment(74, 0);
      fd_labEndDate.right = new FormAttachment(45, 0);
      fd_labEndDate.left = new FormAttachment(35, 0);
      labEndDate.setLayoutData(fd_labEndDate);
      labEndDate.setText("结束日期：");

      txtEndDate = new Text(group_1, SWT.BORDER);
      final FormData fd_txtEndDate = new FormData();
      fd_txtEndDate.bottom = new FormAttachment(txtStartDate, 19, SWT.TOP);
      //		fd_txtEndDate.top = new FormAttachment(txtStartDate, 0, SWT.TOP);
      //		fd_txtEndDate.right = new FormAttachment(txtEnd, 0, SWT.RIGHT);
      //		fd_txtEndDate.left = new FormAttachment(txtEnd, 0, SWT.LEFT);

      fd_txtEndDate.top = new FormAttachment(74, 0);
      fd_txtEndDate.right = new FormAttachment(65, 0);
      fd_txtEndDate.left = new FormAttachment(45, 0);
      txtEndDate.setLayoutData(fd_txtEndDate);

      final Label labDateStepLength = new Label(group_1, SWT.NONE);
      labDateStepLength.setAlignment(SWT.RIGHT);
      final FormData fd_labDateStepLength = new FormData();
      fd_labDateStepLength.bottom = new FormAttachment(txtEndDate, 13, SWT.TOP);
      //		fd_labDateStepLength.top = new FormAttachment(txtEndDate, 0, SWT.TOP);
      //		fd_labDateStepLength.right = new FormAttachment(labStepLength, 0,SWT.RIGHT);
      //		fd_labDateStepLength.left = new FormAttachment(labStepLength, 0,SWT.LEFT);

      fd_labDateStepLength.top = new FormAttachment(74, 0);
      fd_labDateStepLength.right = new FormAttachment(80, 0);
      fd_labDateStepLength.left = new FormAttachment(70, 0);
      labDateStepLength.setLayoutData(fd_labDateStepLength);
      labDateStepLength.setText("日期步长：");

      comboStep = new Combo(group_1, SWT.NONE);
      //		comboStep.setVisible(false);
      comboStep.setItems(Consts.AXIS_DATE_STEPLENGTH_LABEL);
      final FormData fd_comboStep = new FormData();
      //		fd_comboStep.top = new FormAttachment(labDateStepLength, 0, SWT.TOP);
      //		fd_comboStep.left = new FormAttachment(txtStepLength, 0, SWT.LEFT);
      //		fd_comboStep.right = new FormAttachment(txtStepLength, 0, SWT.RIGHT);

      fd_comboStep.top = new FormAttachment(74, 0);
      fd_comboStep.left = new FormAttachment(80, 0);
      fd_comboStep.right = new FormAttachment(100, 0);

      comboStep.setLayoutData(fd_comboStep);

      //
      tvShowConfig = new TableViewer(compositeFiledCfig, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
      tvShowConfig.setContentProvider(new ViewContentProvider());
      tvShowConfig.setLabelProvider(new GrathListLabelProvider());
      tvShowConfig.setColumnProperties(DATAFIELDS);
      tblShowConfig = tvShowConfig.getTable();
      final FormData fd_tblShowConfig = new FormData();
      fd_tblShowConfig.right = new FormAttachment(100, -5);
      fd_tblShowConfig.bottom = new FormAttachment(100, -30);
      fd_tblShowConfig.top = new FormAttachment(0, 0);
      fd_tblShowConfig.left = new FormAttachment(0, 5);
      tblShowConfig.setLayoutData(fd_tblShowConfig);

      CellEditor[] cellEditor = new CellEditor[8];
      cellEditor[0] = new TextCellEditor(tblShowConfig);
      cellEditor[1] = new TextCellEditor(tblShowConfig);
      cellEditor[2] = new TextCellEditor(tblShowConfig);
      cellEditor[3] = new ComboBoxCellEditor(tblShowConfig, Consts.ALIGN_LABEL, SWT.READ_ONLY);
      cellEditor[4] = new TextCellEditor(tblShowConfig);
      cellEditor[5] = new ComboBoxCellEditor(tblShowConfig, Consts.YESNO_LABEL, SWT.READ_ONLY);
      cellEditor[6] = new TextCellEditor(tblShowConfig);
      cellEditor[7] = new ComboBoxCellEditor(tblShowConfig, Consts.X_Y_LABEL, SWT.READ_ONLY);
      Text text1 = (Text) cellEditor[4].getControl();
      text1.addVerifyListener(new NumberVerifier());

      tvShowConfig.setCellEditors(cellEditor);
      tvShowConfig.setCellModifier(new GrathicConfigCellModifier(tvShowConfig));
      tblShowConfig.setLinesVisible(true);
      tblShowConfig.setHeaderVisible(true);

      final TableColumn colCnName = new TableColumn(tblShowConfig, SWT.NONE);
      colCnName.setWidth(150);
      colCnName.setText("中文名");

      final TableColumn colFieldName = new TableColumn(tblShowConfig, SWT.NONE);
      colFieldName.setWidth(100);
      colFieldName.setText("列名");

      final TableColumn colDataType = new TableColumn(tblShowConfig, SWT.NONE);
      colDataType.setWidth(80);
      colDataType.setText("数据格式");

      final TableColumn colAlign = new TableColumn(tblShowConfig, SWT.NONE);
      colAlign.setWidth(80);
      colAlign.setText("对齐方式");

      final TableColumn colWidth = new TableColumn(tblShowConfig, SWT.NONE);
      colWidth.setWidth(80);
      colWidth.setText("宽度");

      final TableColumn colVisible = new TableColumn(tblShowConfig, SWT.NONE);
      colVisible.setWidth(80);
      colVisible.setText("是否显示");
      final TableColumn colCodeSet = new TableColumn(tblShowConfig, SWT.NONE);
      colCodeSet.setWidth(120);
      colCodeSet.setText("代码集");

      final TableColumn colAxis = new TableColumn(tblShowConfig, SWT.NONE);
      colAxis.setWidth(80);
      colAxis.setText("坐标轴");

      //
      final Button btnLoad = new Button(compositeFiledCfig, SWT.NONE);
      final FormData fd_btnLoad = new FormData();
      fd_btnLoad.bottom = new FormAttachment(100, -1);
      fd_btnLoad.top = new FormAttachment(100, -21);
      fd_btnLoad.width = 20;
      fd_btnLoad.left = new FormAttachment(0, 1);
      fd_btnLoad.width = 20;
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
               grathInit();
               tvShowConfig.refresh();
            }
         }
      });
      btnLoad.setToolTipText("从上一环节导入数据结构");
      btnLoad.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/table-import.png"));

      final Button btnDel = new Button(compositeFiledCfig, SWT.NONE);
      final FormData fd_btnDel = new FormData();
      fd_btnDel.bottom = new FormAttachment(100, -1);
      fd_btnDel.top = new FormAttachment(100, -21);
      fd_btnDel.width = 20;
      fd_btnDel.left = new FormAttachment(btnLoad, 1);
      fd_btnDel.width = 20;
      btnDel.setLayoutData(fd_btnDel);
      btnDel.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            TableItem[] selectItems = tblShowConfig.getSelection();
            if (selectItems != null && selectItems.length > 0) {
               for (TableItem ti : selectItems) {
                  GrathFieldConfig o = (GrathFieldConfig) ti.getData();
                  configList.remove(o);
               }
               grathInit();
               tvShowConfig.refresh();
            }
         }
      });
      btnDel.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/minus.png"));

      final Button btnUp = new Button(compositeFiledCfig, SWT.NONE);
      final FormData fd_btnUp = new FormData();
      fd_btnUp.bottom = new FormAttachment(100, -1);
      fd_btnUp.top = new FormAttachment(100, -21);
      fd_btnUp.width = 20;
      fd_btnUp.left = new FormAttachment(btnDel, 1);
      fd_btnUp.width = 20;
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
      btnUp.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/arrow-090.png"));

      final Button btnDown = new Button(compositeFiledCfig, SWT.NONE);
      final FormData fd_btnDown = new FormData();
      fd_btnDown.bottom = new FormAttachment(100, -1);
      fd_btnDown.top = new FormAttachment(100, -21);
      fd_btnDown.width = 20;
      fd_btnDown.left = new FormAttachment(btnUp, 1);
      fd_btnDown.width = 20;
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
      btnDown.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "icons/arrow-270.png"));

      //

   }

   private void init() {
      txtName.setText(node.getName());
      txtDes.setText(node.getDescription());

      configList = new ArrayList<GrathFieldConfig>();
      for (int i = 0; i < nodeProperties.getFieldConfigs2().size(); i++) {
         configList.add((GrathFieldConfig) nodeProperties.getFieldConfigs2().get(i).clone());
      }
      tvShowConfig.setInput(configList);
      // 图形配置
      lineStaticGraphCfig = nodeProperties.getLinearGraphCfig();
      //
      this.txtGrathTitle.setText(lineStaticGraphCfig.getGrathTitle());
      this.txtMaxCount.setText(String.valueOf(lineStaticGraphCfig.getRecordCount()));
      this.txtTitle.setText(lineStaticGraphCfig.getAxisTitle());

      // this.comboDataType.select(getIndex(Consts.GRATH_DATA_TYPE,staticGrathCfig.getDataType()));
      // int datType = staticGrathCfig.getDataType();

      this.comboDataType.select(Integer.valueOf(lineStaticGraphCfig.getDataType()));
      this.txtStart.setText(String.valueOf(lineStaticGraphCfig.getAxisStart()));
      this.txtEnd.setText(String.valueOf(lineStaticGraphCfig.getAxisEnd()));
      this.txtStepLength.setText(String.valueOf(lineStaticGraphCfig.getStepLength()));

      String tempStartDate = StringUtil.formateDate(lineStaticGraphCfig.getAxisStartDate(), "yyyy-MM-dd");
      String tempEndDate = StringUtil.formateDate(lineStaticGraphCfig.getAxisEndDate(), "yyyy-MM-dd");
      this.txtStartDate.setText(tempStartDate);
      this.txtEndDate.setText(tempEndDate);
      String dateAxisStepLength = lineStaticGraphCfig.getDateAxisStepLengthType();
      if (null != dateAxisStepLength) {
         this.comboStep.select(getIndex(Consts.AXIS_DATE_STEPLENGTH, dateAxisStepLength));

      }
      if (lineStaticGraphCfig.getDataType() == 1) {
         txtStepLength.setEnabled(true);
         txtStart.setEnabled(true);
         txtEnd.setEnabled(true);
         comboStep.setEnabled(false);
         txtStartDate.setEnabled(false);
         txtEndDate.setEnabled(false);
      }
      else if (lineStaticGraphCfig.getDataType() == 2) {
         comboStep.setEnabled(true);
         txtStartDate.setEnabled(true);
         txtEndDate.setEnabled(true);
         txtStepLength.setEnabled(false);
         txtStart.setEnabled(false);
         txtEnd.setEnabled(false);
      }

      grathInit();
      this.comboColumnName.select(getIndex(columnNames, lineStaticGraphCfig.getColumnName()));
   }

   public void grathInit() {

      //
      columnNames = new String[configList.size() + 1];
      // columnNames[0] = "";
      for (int i = 0; i < configList.size(); i++) {
         columnNames[i] = configList.get(i).getName();
      }
      for (int j = configList.size(); j > 0; j--) {
         columnNames[j] = columnNames[j - 1];
      }
      columnNames[0] = "";
      comboColumnName.setItems(columnNames);
   }

   private boolean check() {
      int xAxisCount = 0;
      int yAxiscount = 0;
      String xAxisIndex = "";
      for (GrathFieldConfig grathCfig : configList) {
         if ("X轴".equals(grathCfig.getAxis())) {
            xAxisCount++;
            xAxisIndex = grathCfig.getName();
         }
         if ("Y轴".equals(grathCfig.getAxis())) {
            yAxiscount++;
         }

      }
      if (xAxisCount == 0 && comboColumnName.getSelectionIndex() < 1) {
         MessageBox messageBox = new MessageBox(this.shell, SWT.OK);
         messageBox.setMessage("要选定一个X轴！");
         messageBox.open();
         return false;
      }
      if (xAxisCount == 1 && comboColumnName.getSelectionIndex() > 0) {
         String colName = comboColumnName.getText().trim();

         if (!xAxisIndex.equals(colName)) {
            MessageBox messageBox = new MessageBox(this.shell, SWT.OK);
            messageBox.setMessage("不能选定两种轴！");
            messageBox.open();
            return false;
         }
      }
      if (xAxisCount > 1) {
         MessageBox messageBox = new MessageBox(this.shell, SWT.OK);
         messageBox.setMessage("不能选定多个X轴！");
         messageBox.open();
         return false;
      }
      if (yAxiscount == 0) {

      }
      return true;
   }

   private void save() {
      node.setName(txtName.getText().trim());
      node.setDescription(txtDes.getText().trim());
      nodeProperties.setFieldConfigs2(configList);
      nodeProperties.setLinearGraphCfig(lineStaticGraphCfig);
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

   // class ShowConfigLabelProvider extends BaseLabelProvider implements
   // ITableLabelProvider {
   // public String getColumnText(Object obj, int columnIndex) {
   // GrathFieldConfig dsi = (GrathFieldConfig) obj;
   // switch (columnIndex) {
   // case 0:
   // return dsi.getCnName() == null ? "" : dsi.getCnName();
   // case 1:
   // return dsi.getName() == null ? "" : dsi.getName();
   // case 2:
   // return dsi.getDataFormat() == null ? "" : dsi.getDataFormat();
   // case 3:
   // return dsi.getAlign() == null ? "" : getLabel(Consts.ALIGN_LABEL,
   // Consts.ALIGN, dsi.getAlign());
   // case 4:
   // return dsi.getWidth() == null ? "" : dsi.getWidth();
   // case 5:
   // return dsi.getVisible() == null ? Consts.NO : getLabel(Consts.YESNO_LABEL,
   // Consts.YESNO, dsi.getVisible());
   // case 6:
   // return dsi.getCodeSet() == null ? "" : dsi.getCodeSet();
   // }
   // return null;
   // }
   // }
   class GrathListLabelProvider extends BaseLabelProvider implements ITableLabelProvider {
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
               return dsi.getCodeSet() == null ? "" : dsi.getCodeSet();
            case 7:
               return dsi.getAxis() == null ? "" : dsi.getAxis();
         }
         return null;
      }

   }

   class GrathicConfigCellModifier extends BaseCellModifer implements ICellModifier {

      public GrathicConfigCellModifier(TableViewer tv) {
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
         else if (property.equals("codeSet")) return o.getCodeSet();
         else if (property.equals("axis")) return getIndex(Consts.X_Y_AXIS, o.getAxis());
         else throw new RuntimeException("错误的列别名:" + property);
      }

      public void modify(Object element, String property, Object value) {
         TableItem item = (TableItem) element;
         GrathFieldConfig o = (GrathFieldConfig) item.getData();
         if (property.equals("name")) {
            if (!o.getName().equals((String) value)) {
               o.setName((String) value);
               grathInit();
            }

         }
         else if (property.equals("cnName")) o.setCnName((String) value);
         else if (property.equals("dataFormat")) o.setDataFormat((String) value);
         else if (property.equals("width")) o.setWidth((String) value);
         else if (property.equals("align")) o.setAlign(Consts.ALIGN[(Integer) value]);
         else if (property.equals("visible")) o.setVisible(Consts.YESNO[(Integer) value]);
         else if (property.equals("codeSet")) o.setCodeSet((String) value);
         else if (property.equals("axis")) o.setAxis(Consts.X_Y_AXIS[(Integer) value]);
         else throw new RuntimeException("错误的列别名:" + property);
         tv.update(o, null);
      }

   }

   protected String getValue(String[] labels, String[] values, String name) {
      for (int i = 0; i < values.length; i++) {
         if (labels[i].equalsIgnoreCase(name)) return values[i];
      }
      return "";
   }

   // class ShowConfigCellModifier extends BaseCellModifer implements
   // ICellModifier {
   //
   // public ShowConfigCellModifier(TableViewer tv) {
   // this.tv = tv;
   // }
   //
   // public Object getValue(Object element, String property) {
   // GrathFieldConfig o = (GrathFieldConfig) element;
   // if (property.equals("name")) return o.getName();
   // else if (property.equals("cnName")) return o.getCnName();
   // else if (property.equals("dataFormat")) return o.getDataFormat();
   // else if (property.equals("align")) return getIndex(Consts.ALIGN,
   // o.getAlign());
   // else if (property.equals("visible")) return getIndex(Consts.YESNO,
   // o.getVisible());
   // else if (property.equals("width")) return o.getWidth();
   // else if (property.equals("codeSet")) return o.getCodeSet();
   // else throw new RuntimeException("错误的列别名:" + property);
   // }
   //
   // public void modify(Object element, String property, Object value) {
   // TableItem item = (TableItem) element;
   // GrathFieldConfig o = (GrathFieldConfig) item.getData();
   // if (property.equals("name")) {
   // if (!o.getName().equals((String) value)) {
   // o.setName((String) value);
   // grathInit();
   // }
   //				
   // }
   // else if (property.equals("cnName")) o.setCnName((String) value);
   // else if (property.equals("dataFormat")) o.setDataFormat((String) value);
   // else if (property.equals("width")) o.setWidth((String) value);
   // else if (property.equals("align")) o.setAlign(Consts.ALIGN[(Integer)
   // value]);
   // else if (property.equals("visible")) o.setVisible(Consts.YESNO[(Integer)
   // value]);
   // else if (property.equals("codeSet")) o.setCodeSet((String) value);
   // else throw new RuntimeException("错误的列别名:" + property);
   //			tv.update(o, null);
   //		}
   //	}
}
