package com.liusy.analysismodel.template.ui;

import java.lang.reflect.Method;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.liusy.analysismodel.template.commands.OperateNodeBeanEditCommand;
import com.liusy.analysis.template.model.dialogProperties.OperateNodeBean;
import com.liusy.analysis.template.model.node.OperateNode;

public class OperateNodeDialog extends Dialog {

	private Combo combo;
	private Text text;
	private Group methodGroup;

	public Group getMethodGroup() {
		return methodGroup;
	}

	public Group getMethodGroup2() {
		return methodGroup2;
	}

	private Group methodGroup2;
	private OperateNode node;
	private OperateNodeBean bean = new OperateNodeBean();
	protected int result;
	protected Shell shell;
	private OperateNodeBeanEditCommand cmd;
	private String[] strs = { "", "集合操作", "统计分析" };
	private String[] groupStrs = { "", "methodGroup", "methodGroup2" };

	/**
	 * Create the dialog
	 * 
	 * @param parent
	 * @param style
	 */
	public OperateNodeDialog(Shell parent, int style) {
		super(parent, style);
	}

	/**
	 * Create the dialog
	 * 
	 * @param parent
	 */
	public OperateNodeDialog(Shell parent, OperateNode node,
			OperateNodeBeanEditCommand cmd) {
		this(parent, SWT.NONE);
		this.node = node;
		this.cmd = cmd;
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
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;
	}

	/**
	 * Create contents of the dialog
	 */
	protected void createContents() {
		bean = new OperateNodeBean();
		cmd.setNode(node);
		cmd.setBean(bean);
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(500, 375);
		shell.setText("SWT Dialog");

		final Group group = new Group(shell, SWT.NONE);
		group.setText("操作节点信息");
		group.setBounds(10, 10, 474, 56);

		final Label label = new Label(group, SWT.NONE);
		label.setText("操作节点名称：");
		label.setBounds(10, 26, 84, 12);

		text = new Text(group, SWT.BORDER);
		text.setBounds(98, 23, 129, 20);

		final Label label_1 = new Label(group, SWT.NONE);
		label_1.setText("算法类型：");
		label_1.setBounds(250, 26, 60, 12);

		combo = new Combo(group, SWT.NONE);
		combo.setBounds(317, 23, 129, 20);
		combo.setItems(strs);
		combo.setData("1", "methodGroup");
		combo.setData("2", "methodGroup2");
		combo.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent arg0) {
				int selectedIndex = combo.getSelectionIndex();
				String selectedName = (String)combo.getData(String.valueOf(selectedIndex));
				if (methodGroup.getData("name").equals(selectedName)) {
					methodGroup.setVisible(true);
					methodGroup2.setVisible(false);
				} else if (methodGroup2.getData("name").equals(selectedName)) {
					methodGroup.setVisible(false);
					methodGroup2.setVisible(true);

				}
			}
		});
		methodGroup = new Group(shell, SWT.NONE);
		methodGroup.setText("操作方法");
		methodGroup.setBounds(10, 72, 474, 195);
		methodGroup.setVisible(false);
		methodGroup.setData("name", "methodGroup");
		// methodGroup.setd
		methodGroup2 = new Group(shell, SWT.NONE);
		methodGroup2.setText("操作方法");
		methodGroup2.setBounds(10, 72, 474, 195);
		methodGroup2.setVisible(false);
		methodGroup2.setData("name", "methodGroup2");
		createMethodWedige1();
		createMethodWedige2();

		final Group group_2 = new Group(shell, SWT.NONE);
		group_2.setText("操作");
		group_2.setBounds(10, 273, 474, 60);

		final Button button_6 = new Button(group_2, SWT.NONE);
		button_6.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {

				bean.setOperateType(String.valueOf(combo.getSelectionIndex()));
				try {
					bean.setSubOperareType(String.valueOf(getSelectedButton(getGroup(combo.getSelectionIndex()))));
				} catch (NoSuchMethodException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				result = 1;
				shell.close();
			}
		});
		button_6.setText("确定");
		button_6.setBounds(271, 28, 48, 22);

		final Button button_7 = new Button(group_2, SWT.NONE);
		button_7.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				result = 0;
				shell.close();
			}
		});
		button_7.setText("取消");
		button_7.setBounds(363, 28, 48, 22);
		//
		init();
	}

	public void createMethodWedige1() {
		final Button button = new Button(methodGroup, SWT.CHECK);
		button.setText("合并");
		button.setBounds(10, 26, 93, 16);
		button.setData("order", 1);

		final Button button_1 = new Button(methodGroup, SWT.CHECK);
		button_1.setText("抽取");
		button_1.setBounds(250, 26, 93, 16);
		button_1.setData("order", 2);

		final Button button_2 = new Button(methodGroup, SWT.CHECK);
		button_2.setText("拆分");
		button_2.setBounds(10, 67, 93, 16);
		button_2.setData("order", 3);

		final Button button_3 = new Button(methodGroup, SWT.CHECK);
		button_3.setText("Check Button");
		button_3.setBounds(250, 67, 93, 16);
		button_3.setData("order", 4);

		final Button button_4 = new Button(methodGroup, SWT.CHECK);
		button_4.setText("Check Button");
		button_4.setBounds(10, 117, 93, 16);
		button_4.setData("order", 5);

		final Button button_5 = new Button(methodGroup, SWT.CHECK);
		button_5.setText("Check Button");
		button_5.setBounds(250, 117, 93, 16);
		button_5.setData("order", 6);
	}

	public void createMethodWedige2() {
		final Button button = new Button(methodGroup2, SWT.CHECK);
		button.setText("聚类");
		button.setBounds(10, 26, 93, 16);
		button.setData("order", 1);

		final Button button_1 = new Button(methodGroup2, SWT.CHECK);
		button_1.setText("回归分析");
		button_1.setBounds(250, 26, 93, 16);
		button_1.setData("order", 2);

		final Button button_2 = new Button(methodGroup2, SWT.CHECK);
		button_2.setText("方差分析");
		button_2.setBounds(10, 67, 93, 16);
		button_2.setData("order", 3);

		final Button button_3 = new Button(methodGroup2, SWT.CHECK);
		button_3.setText("Check Button");
		button_3.setBounds(250, 67, 93, 16);
		button_3.setData("order", 4);

		final Button button_4 = new Button(methodGroup2, SWT.CHECK);
		button_4.setText("Check Button");
		button_4.setBounds(10, 117, 93, 16);
		button_4.setData("order", 5);

		final Button button_5 = new Button(methodGroup2, SWT.CHECK);
		button_5.setText("Check Button");
		button_5.setBounds(250, 117, 93, 16);
		button_5.setData("order", 6);
	}
	public Group getGroup(int index) throws Exception, NoSuchMethodException {
		String field = groupStrs[index];
		String methodPrefix = field.toString().substring(0, 1)
		.toUpperCase();
		String methodSuffix = field.toString().substring(1);
		
//		String methodPrefix = field.getName().toString().substring(0, 1)
//				.toUpperCase();
//		String methodSuffix = field.getName().toString().substring(1);
		Method methodGet = this.getClass().getMethod(
				"get" + methodPrefix + methodSuffix, null);
		Group group = (Group) methodGet.invoke(this, null);
		return group;
	}
	public int getSelectedButton (Group group) {
		Control[] contls = group.getChildren();
		for (Control contr : contls) {
			if (contr.getClass() == Button.class
					&& (((Button)contr).getStyle() & SWT.CHECK) != 0) {
				 if(((Button) contr).getSelection()) {
					 return (Integer)((Button) contr).getData("order");
				 }
			}
		}
		return 0;
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

	public void init() {
		OperateNodeBean initBean = this.node.getExtrProperties();
		
		int operateType = "".equals(initBean.getOperateType())?0:Integer.valueOf(initBean.getOperateType());
		int subOperateType = "".equals(initBean.getSubOperareType())?0:Integer.valueOf(initBean.getSubOperareType());
		combo.select(operateType);

		try {
			Group group = null;
			if (operateType == 0) {
				group = methodGroup;
				combo.select(1);
			} else {
				group = getGroup(operateType);
			}
			group.setVisible(true);
			if (subOperateType == 0) {
				return;
			}
			Control[] contls = group.getChildren();
			for (Control contr : contls) {
				if (contr.getClass() == Button.class
						&& (contr.getStyle() & SWT.CHECK) != 0) {
					 if((Integer)((Button) contr).getData("order") == subOperateType) {
						 ((Button) contr).setSelection(true);
					 }
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// nodeNameTxt.setText(this.node.getPropertyValue(Node.PROP_NAME).toString());
		// tableNameTxt.setText(initBean.getTableName());
		// String[] fieldArray = new String[initBean.getFields().size()];
		// fieldLst.setItems(initBean.getFields().toArray(fieldArray));
		// String[] pramArray = new String[initBean.getPrams().size()];
		// pramLst.setItems(initBean.getPrams().toArray(pramArray));
		// String[] orderArray = new String[initBean.getOrderFields().size()];
		// orderLst.setItems(initBean.getOrderFields().toArray(orderArray));
		// String[] groupArray = new String[initBean.getGroupFields().size()];
		// groupLst.setItems(initBean.getGroupFields().toArray(groupArray));
	}
}
