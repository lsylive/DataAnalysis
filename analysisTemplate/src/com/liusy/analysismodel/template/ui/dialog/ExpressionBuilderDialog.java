package com.liusy.analysismodel.template.ui.dialog;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.jeval.function.Function;
import net.sourceforge.jeval.function.date.DateFunctions;
import net.sourceforge.jeval.function.math.MathFunctions;
import net.sourceforge.jeval.function.string.StringFunctions;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
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
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.swtdesigner.ResourceManager;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysis.template.model.DiagramParameter;
import com.liusy.analysis.template.model.base.Metadata;
import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.template.ui.labelProvider.DiagramParameterLabelProvider;
import com.liusy.analysismodel.template.ui.labelProvider.MetaFieldsLabelProvider;

public class ExpressionBuilderDialog extends Dialog {

   protected Shell                       shell;
   private List<INode>                   nodeList;
   // private final FormToolkit formToolkit = new
   // FormToolkit(Display.getDefault());
   private List<DiagramParameter>        parameters;
   private Diagram                       diagram;
   private Table                         table_params;
   public String[]                       params       = new String[] { "name", "cnName", "dataType", "length", "precision" };
   public static String[]                nodeFields   = new String[] { "cnName", "name", "dataType", "length", "precision" };
   private DiagramParameterLabelProvider diagramParameterLabelProvider;
   private List<Object>                  selectList   = new ArrayList<Object>();
   private Table                         table_nodes;
   private StringFunctions               sf;
   private MathFunctions                 mf;
   private DateFunctions                 df;
   private Text                          txt_function = null;
   private Text                          txtExpression;
   private Tree                          tree;
   private String                        expression;

   /**
    * Create the dialog.
    * 
    * @param parent
    * @param style
    */
   public ExpressionBuilderDialog(Shell parent, int style) {
      super(parent, style);
   }

   public ExpressionBuilderDialog(Shell parent, List<INode> nodeList, Diagram diagram, String exp) {
      super(parent, SWT.NONE);
      this.nodeList = nodeList;
      this.diagram = diagram;
      this.expression = exp;

   }

   /**
    * Open the dialog.
    * 
    * @return the result
    */
   public String open() {
      createContents();
      shell.setLocation(getParentLocation());
      shell.setLayout(new FormLayout());
      txtExpression.setText(expression);
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) {
            display.sleep();
         }
      }
      return expression;
   }

   /**
    * Create contents of the dialog.
    */
   private void createContents() {
      shell = new Shell(getParent(), SWT.DIALOG_TRIM);
      shell.setSize(686, 501);
      shell.setText("表达式生成器");
      Group group = new Group(shell, SWT.NONE);
      FormData fd_group = new FormData();
      fd_group.bottom = new FormAttachment(100, -5);
      fd_group.right = new FormAttachment(30, 0);
      fd_group.top = new FormAttachment(0, 0);
      fd_group.left = new FormAttachment(0, 5);
      group.setLayoutData(fd_group);
      group.setLayout(new FormLayout());

      // TreeViewer treeViewer = new TreeViewer(group, SWT.BORDER);
      // tree = treeViewer.getTree();
      tree = new Tree(group, SWT.BORDER);
      // Disable native tooltip
      tree.setToolTipText("");
      tree.addListener(SWT.Dispose, treeListener);
      tree.addListener(SWT.KeyDown, treeListener);
      tree.addListener(SWT.MouseMove, treeListener);
      tree.addListener(SWT.MouseHover, treeListener);

      FormData fd_tree_params = new FormData();
      fd_tree_params.bottom = new FormAttachment(0, -12);
      fd_tree_params.right = new FormAttachment(0, -3);
      fd_tree_params.top = new FormAttachment(0, -12);
      fd_tree_params.left = new FormAttachment(0, -3);
      tree.setLayoutData(fd_tree_params);
      FormData tree_paramsLayout = new FormData();
      tree_paramsLayout.top = new FormAttachment(0, 0);
      tree_paramsLayout.left = new FormAttachment(0, 0);
      tree_paramsLayout.right = new FormAttachment(100, 0);
      tree_paramsLayout.bottom = new FormAttachment(100, 0);
      tree.setLayoutData(tree_paramsLayout);
      // 参数树
      TreeItem parametersItem = new TreeItem(tree, 0);
      parametersItem.setText("参数列表");
      parameters = diagram.getParameterList();
      parametersItem.setData(parameters);
      if (parameters != null && parameters.size() != 0) {
         for (DiagramParameter parameter : parameters) {
            String parameterName = parameter.getCnName();
            TreeItem item0 = new TreeItem(parametersItem, 0);
            item0.setText(parameterName);
            item0.setData(parameter);
         }
      }
      // 结点树
      TreeItem nodesItem = new TreeItem(tree, 0);
      nodesItem.setText("元数据列表");
      if (nodeList != null && nodeList.size() != 0) {
         for (int i = 0; i < nodeList.size(); i++) {
            INode node = nodeList.get(i);
            TreeItem item0 = new TreeItem(nodesItem, 0);
            item0.setText(node.getName());
            item0.setData(node);
            List<Metadata> MetaList = node.getMeta();
            for (int j = 0; j < MetaList.size(); j++) {
               Metadata meta = MetaList.get(j);
               String metaName = meta.getCnName();
               TreeItem item1 = new TreeItem(item0, 0);
               item1.setText(metaName);
               item1.setData(meta);
            }
         }
      }
      // 函数列表
      TreeItem functionsItem = new TreeItem(tree, 0);
      functionsItem.setText("函数列表");
      // 字符
      TreeItem root_s = new TreeItem(functionsItem, 0);
      root_s.setText("字符函数");
      sf = new StringFunctions();
      for (Function function : sf.getFunctions()) {
         TreeItem item1 = new TreeItem(root_s, 0);
         item1.setText(function.getName());
         item1.setData(function);
      }
      // 数学
      TreeItem root_m = new TreeItem(functionsItem, 0);
      root_m.setText("数学函数");
      mf = new MathFunctions();
      for (Function function : mf.getFunctions()) {
         TreeItem item1 = new TreeItem(root_m, 0);
         item1.setText(function.getName());
         item1.setData(function);
      }
      // 日期
      TreeItem root_d = new TreeItem(functionsItem, 0);
      root_d.setText("日期函数");
      df = new DateFunctions();
      for (Function function : df.getFunctions()) {
         TreeItem item1 = new TreeItem(root_d, 0);
         item1.setText(function.getName());
         item1.setData(function);
      }
      // formToolkit.adapt(tree);
      // formToolkit.paintBordersFor(tree);

      // TAB页
      final TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
      FormData fd_tabFolder = new FormData();
      fd_tabFolder.top = new FormAttachment(0, 8);
      fd_tabFolder.left = new FormAttachment(group, 5);
      fd_tabFolder.bottom = new FormAttachment(50, 0);
      fd_tabFolder.right = new FormAttachment(100, -5);
      tabFolder.setLayoutData(fd_tabFolder);
      // formToolkit.adapt(tabFolder);
      // formToolkit.paintBordersFor(tabFolder);

      final TabItem tab_params = new TabItem(tabFolder, SWT.NONE);
      tab_params.setText("参数列表");

      Composite composite_params = new Composite(tabFolder, SWT.NONE);
      tab_params.setControl(composite_params);
      // formToolkit.paintBordersFor(composite_params);
      composite_params.setLayout(new FormLayout());

      final TableViewer tableViewer_params = new TableViewer(composite_params, SWT.BORDER | SWT.FULL_SELECTION);
      table_params = tableViewer_params.getTable();
      table_params.setLinesVisible(true);
      table_params.setHeaderVisible(true);
      FormData fd_table_params = new FormData();
      fd_table_params.top = new FormAttachment(0, 0);
      fd_table_params.left = new FormAttachment(0, 0);
      fd_table_params.bottom = new FormAttachment(100, 0);
      fd_table_params.right = new FormAttachment(100, 0);
      table_params.setLayoutData(fd_table_params);
      final TableColumn newColumnTableColumn = new TableColumn(table_params, SWT.NONE);
      newColumnTableColumn.setWidth(100);
      newColumnTableColumn.setText("参数名称");

      final TableColumn newColumnTableColumn_4 = new TableColumn(table_params, SWT.NONE);
      newColumnTableColumn_4.setWidth(120);
      newColumnTableColumn_4.setText("中文名称");

      final TableColumn newColumnTableColumn_1 = new TableColumn(table_params, SWT.NONE);
      newColumnTableColumn_1.setWidth(80);
      newColumnTableColumn_1.setText("数据类型");

      final TableColumn newColumnTableColumn_2 = new TableColumn(table_params, SWT.NONE);
      newColumnTableColumn_2.setWidth(50);
      newColumnTableColumn_2.setText("长度");

      final TableColumn newColumnTableColumn_5 = new TableColumn(table_params, SWT.NONE);
      newColumnTableColumn_5.setWidth(50);
      newColumnTableColumn_5.setText("精度");

      tableViewer_params.setContentProvider(new ViewContentProvider());
      diagramParameterLabelProvider = new DiagramParameterLabelProvider();
      tableViewer_params.setLabelProvider(diagramParameterLabelProvider);

      tableViewer_params.setColumnProperties(params);
      // formToolkit.paintBordersFor(table_params);
      // 结点
      final TabItem tab_nodes = new TabItem(tabFolder, SWT.NONE);
      tab_nodes.setText("元数据列表");

      Composite composite_nodes = new Composite(tabFolder, SWT.NONE);
      tab_nodes.setControl(composite_nodes);
      // formToolkit.paintBordersFor(composite_nodes);
      composite_nodes.setLayout(new FormLayout());

      final TableViewer tableViewer_nodes = new TableViewer(composite_nodes, SWT.BORDER | SWT.FULL_SELECTION);
      table_nodes = tableViewer_nodes.getTable();
      FormData fd_table_nodes = new FormData();
      fd_table_nodes.top = new FormAttachment(0, 0);
      fd_table_nodes.left = new FormAttachment(0, 0);
      fd_table_nodes.bottom = new FormAttachment(100, 0);
      fd_table_nodes.right = new FormAttachment(100, 0);
      table_nodes.setLayoutData(fd_table_nodes);
      table_nodes.setLinesVisible(true);
      table_nodes.setHeaderVisible(true);

      final TableColumn colCnName = new TableColumn(table_nodes, SWT.NONE);
      colCnName.setWidth(100);
      colCnName.setText("中文名");

      final TableColumn colFieldName = new TableColumn(table_nodes, SWT.NONE);
      colFieldName.setWidth(100);
      colFieldName.setText("字段名");

      final TableColumn colDataType = new TableColumn(table_nodes, SWT.NONE);
      colDataType.setWidth(100);
      colDataType.setText("数据类型");

      final TableColumn colLength = new TableColumn(table_nodes, SWT.NONE);
      colLength.setWidth(80);
      colLength.setText("长度");
      final TableColumn colPrecision = new TableColumn(table_nodes, SWT.NONE);
      colPrecision.setWidth(80);
      colPrecision.setText("精度");
      tableViewer_nodes.setContentProvider(new ViewContentProvider());
      tableViewer_nodes.setLabelProvider(new MetaFieldsLabelProvider());
      tableViewer_nodes.setColumnProperties(nodeFields);

      // formToolkit.paintBordersFor(table_nodes);
      // 函数
      final TabItem tab_functions = new TabItem(tabFolder, SWT.NONE);
      tab_functions.setText("函数列表");

      Composite composite_functions = new Composite(tabFolder, SWT.NONE);
      tab_functions.setControl(composite_functions);
      // formToolkit.paintBordersFor(composite_functions);
      composite_functions.setLayout(new FormLayout());

      txt_function = new Text(composite_functions, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
      FormData fd_txt_function = new FormData();
      fd_txt_function.top = new FormAttachment(0, 0);
      fd_txt_function.left = new FormAttachment(0, 0);
      fd_txt_function.right = new FormAttachment(100, 0);
      fd_txt_function.bottom = new FormAttachment(100, 0);
      txt_function.setLayoutData(fd_txt_function);

      Group group_1 = new Group(shell, SWT.NONE);
      group_1.setText("生成表达式");
      group_1.setLayout(new FormLayout());
      FormData fd_group_1 = new FormData();
      fd_group_1.top = new FormAttachment(tabFolder, 5);
      fd_group_1.left = new FormAttachment(group, 5);
      fd_group_1.bottom = new FormAttachment(100, -5);
      fd_group_1.right = new FormAttachment(100, -5);
      group_1.setLayoutData(fd_group_1);

      txtExpression = new Text(group_1, SWT.BORDER);
      FormData fd_txtExpression = new FormData();
      fd_txtExpression.top = new FormAttachment(0, 5);
      fd_txtExpression.left = new FormAttachment(0, 2);
      fd_txtExpression.right = new FormAttachment(100, -2);
      fd_txtExpression.bottom = new FormAttachment(80, 0);
      txtExpression.setLayoutData(fd_txtExpression);

      Button btnOk = new Button(group_1, SWT.NONE);
      btnOk.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(SelectionEvent e) {
            expression = txtExpression.getText().trim();
            shell.dispose();
         }
      });
      btnOk.setImage(ResourceManager.getPluginImage("DataAdminPlatform", "icons/tick.png"));
      FormData fd_btnOk = new FormData();
      fd_btnOk.top = new FormAttachment(txtExpression, 10);
      fd_btnOk.bottom = new FormAttachment(100, -10);
      fd_btnOk.left = new FormAttachment(25, 0);
      fd_btnOk.right = new FormAttachment(45, 0);
      btnOk.setLayoutData(fd_btnOk);
      // formToolkit.adapt(btnOk, true, true);
      btnOk.setText("确定(&O)");

      Button btnCancel = new Button(group_1, SWT.NONE);
      btnCancel.addSelectionListener(new SelectionAdapter() {
         @Override
         public void widgetSelected(SelectionEvent e) {
            shell.dispose();
         }
      });
      btnCancel.setImage(ResourceManager.getPluginImage("DataAdminPlatform", "icons/cross.png"));
      FormData fd_btnCancel = new FormData();
      fd_btnCancel.top = new FormAttachment(txtExpression, 10);
      fd_btnCancel.bottom = new FormAttachment(100, -10);
      fd_btnCancel.left = new FormAttachment(55, 0);
      fd_btnCancel.right = new FormAttachment(75, 0);
      btnCancel.setLayoutData(fd_btnCancel);
      btnCancel.setText("关闭(&C)");

      // 树—动作监听
      tree.addListener(SWT.Selection, new Listener() {
         public void handleEvent(Event e) {
            TreeItem[] selection = tree.getSelection();
            for (int i = 0; i < selection.length; i++) {
               // Tab激活
               for (TabItem tabs : tabFolder.getItems()) {
                  if (selection[i].getText().equals(tabs.getText())) {
                     tabFolder.setSelection(tabs);
                     break;
                  }
               }
               // 参数
               if (selection[i].getParentItem() != null && "参数列表".equals(selection[i].getParentItem().getText())) {
                  tabFolder.setSelection(tab_params);
                  selectList.clear();
                  selectList.add((DiagramParameter) selection[i].getData());
                  tableViewer_params.setInput(selectList);

               }
               if (selection[i].getText().equals("参数列表")) {
                  tableViewer_params.setInput((List<DiagramParameter>) selection[i].getData());
               }

               // 结点
               if (selection[i].getParentItem() != null && "元数据列表".equals(selection[i].getParentItem().getText())) {
                  tabFolder.setSelection(tab_nodes);
                  INode node = (INode) selection[i].getData();
                  tableViewer_nodes.setInput(node.getMeta());
               }
               if (selection[i].getParentItem() != null) {
                  TreeItem a = selection[i].getParentItem();
                  if (a != null && a.getParentItem() != null && a.getParentItem().getText().equals("元数据列表")) {
                     tabFolder.setSelection(tab_nodes);
                     Metadata data = (Metadata) selection[i].getData();
                     selectList.clear();
                     selectList.add(data);
                     tableViewer_nodes.setInput(selectList);
                  }
               }

               // 函数
               if (selection[i].getParentItem() != null && "函数列表".equals(selection[i].getParentItem().getText())) {
                  tabFolder.setSelection(tab_functions);
               }

               if (selection[i].getParentItem() != null) {
                  TreeItem a = selection[i].getParentItem();
                  if (a != null && a.getParentItem() != null && a.getParentItem().getText().equals("函数列表")) {
                     tabFolder.setSelection(tab_functions);
                     List<Function> functions = new ArrayList<Function>();
                     if (a.getText().equals("字符函数")) {
                        functions = sf.getFunctions();
                     }
                     if (a.getText().equals("数学函数")) {
                        functions = mf.getFunctions();
                     }
                     if (a.getText().equals("日期函数")) {
                        functions = df.getFunctions();
                     }
                     for (Function function : functions) {
                        if (function.getName().equals(selection[i].getText())) txt_function.setText(function.getDescription());
                     }
                  }
               }

            }
         }
      });

      // 树结点双击事件
      tree.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseDoubleClick(MouseEvent e) {
            TreeItem item = tree.getItem(new Point(e.x, e.y));
            String s = "";
            if (item.getParentItem() != null && "参数列表".equals(item.getParentItem().getText())) {
               DiagramParameter a = (DiagramParameter) item.getData();
               s = "{" + a.getName() + "}";
            }
            if (item.getParentItem().getParentItem() != null && "元数据列表".equals(item.getParentItem().getParentItem().getText())) {
               Metadata data = (Metadata) item.getData();
               s = "[" + item.getParentItem().getText() + "." + data.getName() + "]";
            }
            if (item.getParentItem().getParentItem() != null && "函数列表".equals(item.getParentItem().getParentItem().getText())) {
               s = item.getText() + "(argumments)";

            }
            txtExpression.append(s);
         }
      });

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

   final Listener labelListener = new Listener() {
                                   public void handleEvent(Event event) {
                                      Label label = (Label) event.widget;
                                      Shell lshell = label.getShell();
                                      switch (event.type) {
                                         case SWT.MouseDown:
                                            Event e = new Event();
                                            e.item = (TreeItem) label.getData("_TREEITEM");
                                            tree.setSelection(new TreeItem[] { (TreeItem) e.item });
                                            tree.notifyListeners(SWT.Selection, e);
                                            lshell.dispose();
                                            tree.setFocus();
                                            break;
                                         case SWT.MouseExit:
                                            lshell.dispose();
                                            break;
                                      }
                                   }
                                };
   Listener       treeListener  = new Listener() {
                                   Display display = getParent().getDisplay();
                                   Shell   tip     = null;
                                   Label   label   = null;

                                   public void handleEvent(Event event) {
                                      switch (event.type) {
                                         case SWT.Dispose:
                                         case SWT.KeyDown:
                                         case SWT.MouseMove: {
                                            if (tip == null) break;
                                            tip.dispose();
                                            tip = null;
                                            label = null;
                                            break;
                                         }
                                         case SWT.MouseHover: {
                                            TreeItem item = tree.getItem(new Point(event.x, event.y));
                                            if (item != null) {
                                               if (tip != null && !tip.isDisposed()) tip.dispose();
                                               tip = new Shell(shell, SWT.ON_TOP | SWT.NO_FOCUS | SWT.TOOL);
                                               tip.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                                               FillLayout layout = new FillLayout();
                                               layout.marginWidth = 2;
                                               tip.setLayout(layout);
                                               label = new Label(tip, SWT.NONE);
                                               label.setForeground(display.getSystemColor(SWT.COLOR_INFO_FOREGROUND));
                                               label.setBackground(display.getSystemColor(SWT.COLOR_INFO_BACKGROUND));
                                               label.setData("_TREEITEM", item);
                                               if (item.getParentItem().getParentItem().getText().equals("函数列表")) {
                                                  Function function = (Function) item.getData();
                                                  if (!function.getDescription().equals(" ")) label.setText(function.getDescription());
                                                  else label.setText(function.getName());
                                               }
                                               else label.setText(item.getText());
                                               label.addListener(SWT.MouseExit, labelListener);
                                               label.addListener(SWT.MouseDown, labelListener);
                                               Point size = tip.computeSize(SWT.DEFAULT, SWT.DEFAULT);
                                               Rectangle rect = item.getBounds(0);
                                               Point pt = tree.toDisplay(rect.x, rect.y);
                                               tip.setBounds(pt.x, pt.y, size.x, size.y);
                                               tip.setVisible(true);
                                            }
                                         }
                                      }
                                   }
                                };

}
