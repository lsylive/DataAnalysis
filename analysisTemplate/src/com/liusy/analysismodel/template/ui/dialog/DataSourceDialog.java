package com.liusy.analysismodel.template.ui.dialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.liusy.analysismodel.log.view.labelProvider.DataSourceLabelProvider;
import com.liusy.analysis.template.model.dialogProperties.DataSource;
import com.liusy.analysis.template.model.util.StringUtil;
import com.liusy.analysismodel.template.ui.contentProvider.ViewContentProvider;
import com.liusy.analysismodel.util.XmlEditUtil;

public class DataSourceDialog extends Dialog {
   private Text txtPass;
   private Text txtUserName;
   private Text txtUrl;
   private Text txtDriver;
   private Table table;
   private Text txtSourceName;
   private Label labTest;
   protected Shell  shell;
   protected Text text;
   protected DataSource dataSource;
   private TableViewer tableViewer;
   private Vector<DataSourceBean> beanList;
   private List<DataSourceBean> addList = new ArrayList<DataSourceBean>(5);
   private List<DataSourceBean> updateList = new ArrayList<DataSourceBean>(5);
   private List<DataSourceBean> deleteList = new ArrayList<DataSourceBean>(5);
   private int idCount = 0;

   /**
    * Create the dialog
    * @param parent
    * @param style
    */
   public DataSourceDialog(Shell parent, int style) {
      super(parent, style);
   }

   /**
    * Create the dialog
    * @param parent
    */
   public DataSourceDialog(Shell parent) {
      this(parent, SWT.NONE);
   }

   /**
    * Open the dialog
    * @return the text
    */
   public Object open() {
      createContents();
      shell.setLocation(getParentLocation());
      shell.open();
      shell.layout();
      Display display = getParent().getDisplay();
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) display.sleep();
      }
      return null;
   }

   /**
    * Create contents of the dialog
    */
   protected void createContents() {
      shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
      shell.setSize(556, 454);
      shell.setText("数据源配置");

      final Group conditionGroup = new Group(shell, SWT.NONE);
      conditionGroup.setText("编辑");
      conditionGroup.setBounds(10, 73, 530, 149);

      final Label label = new Label(conditionGroup, SWT.NONE);
      label.setText("数据源名称：");
      label.setBounds(23, 30, 68, 12);

      txtSourceName = new Text(conditionGroup, SWT.BORDER);
      txtSourceName.setBounds(97, 27, 140, 20);

      final Label label_1 = new Label(conditionGroup, SWT.NONE);
      label_1.setText("驱动：");
      label_1.setBounds(23, 60, 30, 12);

      txtDriver = new Text(conditionGroup, SWT.BORDER);
      txtDriver.setBounds(97, 57, 350, 20);

      final Label urlLabel = new Label(conditionGroup, SWT.NONE);
      urlLabel.setText("URL：");
      urlLabel.setBounds(23, 92, 30, 12);

      txtUrl = new Text(conditionGroup, SWT.BORDER);
      txtUrl.setBounds(97, 89, 350, 20);

      final Label label_2 = new Label(conditionGroup, SWT.NONE);
      label_2.setText("用户名：");
      label_2.setBounds(23, 127, 48, 12);

      final Label label_3 = new Label(conditionGroup, SWT.NONE);
      label_3.setText("密码：");
      label_3.setBounds(265, 122, 30, 12);

      txtUserName = new Text(conditionGroup, SWT.BORDER);
      txtUserName.setBounds(97, 119, 140, 20);

      txtPass = new Text(conditionGroup, SWT.BORDER);
      txtPass.setBounds(307, 119, 140, 20);

      final Button testBtn = new Button(conditionGroup, SWT.NONE);
      testBtn.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            String dirver = txtDriver.getText().trim();
            String url = txtUrl.getText().trim();
            String userName = txtUserName.getText().trim();
            String pass = txtPass.getText().trim();
            Connection conn=null;
            try{
               Class.forName(dirver);
               conn=DriverManager.getConnection(url,userName,pass);
            }catch(Exception e1){
               e1.printStackTrace();
            }
            if (conn != null) {
               labTest.setText("测试结果: 成功！");
            } else {
               labTest.setText("测试结果: 失败！");
            }
         }
      });
      testBtn.setText("测试");
      testBtn.setBounds(472, 20, 48, 22);

      labTest = new Label(conditionGroup, SWT.NONE);
      labTest.setText("测试结果:");
      labTest.setBounds(265, 30, 182, 12);

      final Group group = new Group(shell, SWT.NONE);
      group.setText("查询结果");
      group.setBounds(10, 228, 530, 184);

      tableViewer = new TableViewer(group, SWT.FULL_SELECTION | SWT.BORDER);
      tableViewer.addPostSelectionChangedListener(new ISelectionChangedListener() {
         public void selectionChanged(final SelectionChangedEvent arg0) {
            // TODO Auto-generated method stub
            IStructuredSelection obj = (IStructuredSelection)arg0.getSelection();
            DataSourceBean bean = (DataSourceBean) obj.getFirstElement();
            if (bean != null) {
               txtSourceName.setText(bean.getDataSourceName());
               txtDriver.setText(bean.getDriver());
               txtUrl.setText(bean.getUrl());
               txtUserName.setText(bean.getUserName());
               txtPass.setText(bean.getPassword());
            }
         }
      });
      table = tableViewer.getTable();
      table.setLinesVisible(true);
      table.setHeaderVisible(true);
      table.setBounds(10, 22, 510, 152);

      final TableColumn tableColumn = new TableColumn(table, SWT.NONE);
      tableColumn.setWidth(100);
      tableColumn.setText("数据源名称");

      final TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
      newColumnTableColumn.setWidth(100);
      newColumnTableColumn.setText("驱动");

      final TableColumn newColumnTableColumn_1 = new TableColumn(table, SWT.NONE);
      newColumnTableColumn_1.setWidth(100);
      newColumnTableColumn_1.setText("URL");

      final TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
      newColumnTableColumn_2.setWidth(100);
      newColumnTableColumn_2.setText("用户名");

      final TableColumn newColumnTableColumn_3 = new TableColumn(table, SWT.NONE);
      newColumnTableColumn_3.setWidth(100);
      newColumnTableColumn_3.setText("密码");

      final Group group_1 = new Group(shell, SWT.NONE);
      group_1.setText("操作");
      group_1.setBounds(10, 5, 530, 63);

      final Button searchBtn = new Button(group_1, SWT.NONE);
      searchBtn.setBounds(10, 25,48, 22);
      searchBtn.setText("查询");

      final Button addBtn = new Button(group_1, SWT.NONE);
      addBtn.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            try {
               DataSourceBean bean = new DataSourceBean();
               idCount++;
               bean.setId(idCount);
               bean.setDataSourceName(txtSourceName.getText().trim());
               bean.setDriver(txtDriver.getText().trim());
               bean.setUrl(txtUrl.getText().trim());
               bean.setUserName(txtUserName.getText().trim());
               bean.setPassword(txtPass.getText().trim());
               bean.setEditFlg(StringUtil.addFlg);
               addList.add(bean);
               beanList.add(bean);
//               tableViewer.refresh(true);
               tableViewer.refresh();
               System.out.println("add");
            } catch(Exception e2) {
               e2.printStackTrace();
            }
            
         }
      });
      addBtn.setText("添加");
      addBtn.setBounds(84, 25, 48, 22);

      final Button editBtn = new Button(group_1, SWT.NONE);
      editBtn.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            int index = tableViewer.getTable().getSelectionIndex();
            DataSourceBean bean = beanList.get(index);
            bean.setDataSourceName(txtSourceName.getText().trim());
            bean.setDriver(txtDriver.getText().trim());
            bean.setUrl(txtUrl.getText().trim());
            bean.setUserName(txtUserName.getText().trim());
            bean.setPassword(txtPass.getText().trim());
//            updateList.add(bean);
            if (bean.getEditFlg() == null ||!bean.getEditFlg().equals(StringUtil.addFlg)) {
               bean.setEditFlg(StringUtil.updateFlg);
               updateList.add(bean);
            }
            tableViewer.refresh(true);
         }
      });
      editBtn.setText("修改");
      editBtn.setBounds(165, 25, 48, 22);

      final Button delBtn = new Button(group_1, SWT.NONE);
      delBtn.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            int index = tableViewer.getTable().getSelectionIndex();
            DataSourceBean bean = beanList.get(index);
            beanList.remove(index);
            deleteList.add(bean);
//            if (bean.getEditFlg() == null || !bean.getEditFlg().equals(StringUtil.addFlg)) {
//               
//            }
            tableViewer.refresh();
         }
      });
      delBtn.setText("删除");
      delBtn.setBounds(249, 25, 48, 22);

      final Button okBtn = new Button(group_1, SWT.NONE);
      okBtn.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            try{
               if (updateList.size()>0) {
                  XmlEditUtil.updateDataSourceXml(updateList);
                  updateList.clear();
               }
               if (addList.size()>0) {
                  XmlEditUtil.addDataSourceXml(addList);
                  addList.clear();
               }
               if (deleteList.size()>0) {
                  XmlEditUtil.deleteDataSourceXml(deleteList);
                  deleteList.clear();
               }
               beanList = XmlEditUtil.readDataSourceXml();
               tableViewer.setInput(beanList);
               tableViewer.refresh();
//               tableViewer.refresh(true);
               System.out.println("OK");
            }catch(Exception e1){
               e1.printStackTrace();
            }
            
         }
      });
      okBtn.setText("确定");
      okBtn.setBounds(393, 25, 48, 22);

      final Button closeBtn = new Button(group_1, SWT.NONE);
      closeBtn.addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            shell.close();
         }
      });
      closeBtn.setText("关闭");
      closeBtn.setBounds(472, 25, 48, 22);
      //
      setTableData();
   }
   public void setTableData() {
      try {
         beanList = XmlEditUtil.readDataSourceXml();
         idCount = XmlEditUtil.getMaxID();
      }
      catch (Exception e) {
         // TODO Auto-generated catch block
         beanList = new Vector<DataSourceBean>();
         e.printStackTrace();
      }
      
      tableViewer.setContentProvider(new ViewContentProvider());
      tableViewer.setLabelProvider(new DataSourceLabelProvider());
      tableViewer.setInput(beanList);
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
}
