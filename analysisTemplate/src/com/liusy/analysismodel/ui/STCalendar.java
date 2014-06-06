package com.liusy.analysismodel.ui;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import com.swtdesigner.ResourceManager;
import com.liusy.analysismodel.Activator;

/**
 * SWT日历控件
 * 
 * @author 
 * @version 1.00
 */
public class STCalendar extends Dialog implements MouseListener {
	private Combo combo_month;
	private Spinner spinner_year;
	private GridData gridData;
	private CLabel sunday;
	private GridData gridData_6;
	private CLabel friday;
	private GridData gridData_5;
	private CLabel thursday;
	private GridData gridData_4;
	private CLabel wednesday;
	private GridData gridData_3;
	private CLabel tuesday;
	private GridData gridData_2;
	private CLabel saturday;
	private GridData gridData_1;
	private CLabel monday;
	private Display display = null;
	// 当前的日期
	private Date nowDate = null;
	// 用户选择的日期
	private String selectedDate = null;
	private Text desDate=null;
	private Shell shell = null;
	private GridLayout gridLayout = null;
	// 星期标签
	// 切换按钮
	public CLabel[] days = new CLabel[42];
	private int j;
	private int x,y;
	private String[] months =new String[12];
	private String[] monthDignit =new String[12];
	public STCalendar(Shell parent, int style) {
		super(parent, style);
	}

	public STCalendar(Shell parent,Text sourceText,int x,int y) {
		this(parent, 0);
		this.desDate = sourceText;
		this.x =x;
		this.y = y;
	}

	/**
	 * 获取某年某月的最后一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return 最后一天
	 */
	private int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeapYear(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}

	/**
	 * 是否闰年
	 * 
	 * @param year
	 *            年
	 * @return
	 */
	public boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	private void moveTo(int type, int value) {
		Calendar now = Calendar.getInstance();
		now.setTime(nowDate);
		now.set(type, value);
		
//		nowDate = new Date(now.getTimeInMillis());
//		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
//		nowLabel.setText(formatter.format(nowDate));
		
		setDayForDisplay(now);
	}

	private void setDayForDisplay(Calendar now) {
		int currentDay = now.get(Calendar.DATE);
		now.add(Calendar.DAY_OF_MONTH, -(now.get(Calendar.DATE) - 1));
		int startIndex = now.get(Calendar.DAY_OF_WEEK) - 1;
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int lastDay = this.getLastDayOfMonth(year, month);
		int endIndex = startIndex + lastDay - 1;
		int startday = 1;
		for (int i = 0; i < 42; i++) {
			Color temp = days[i].getBackground();
			if (temp.equals(display.getSystemColor(SWT.COLOR_BLUE))) {
				days[i].setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			}
		}
		final CLabel tempDays[] = new CLabel[42];
		for ( j = 0; j < 42; j++) {
			if (j >= startIndex && j <= endIndex) {
				days[j].setText("" + startday);
				if (startday == currentDay) {
					days[j].setBackground(display
							.getSystemColor(SWT.COLOR_BLUE));
				} else {
					tempDays[j] = days[j];
					final CLabel tempDay = tempDays[j];
					tempDay.addMouseTrackListener(new MouseTrackAdapter() {
						public void mouseEnter(MouseEvent mouseevent)
			            {
							tempDay.setBackground(display
									.getSystemColor(SWT.COLOR_GRAY));
			            }
			            public void mouseExit(MouseEvent mouseevent)
			            {
			            	tempDay.setBackground(display
									.getSystemColor(SWT.COLOR_WHITE));
			            }
					});
				}
				startday++;
			} else {
				days[j].setText("");
				tempDays[j] = days[j];
				final CLabel tempDay = tempDays[j];
				tempDay.removeMouseTrackListener(new MouseTrackAdapter() {
					public void mouseEnter(MouseEvent mouseevent)
		            {
						tempDay.setBackground(display
								.getSystemColor(SWT.COLOR_GRAY));
		            }
		            public void mouseExit(MouseEvent mouseevent)
		            {
		            	tempDay.setBackground(display
								.getSystemColor(SWT.COLOR_WHITE));
		            }
				});
			}
		}
	}

	public void mouseDoubleClick(MouseEvent e) {
		CLabel day = (CLabel) e.getSource();
		if (!day.getText().equals("")) {
			String year = String.valueOf(this.spinner_year.getSelection());
			String month = monthDignit[this.combo_month.getSelectionIndex()];
			this.selectedDate = year+"-"+month + "-" + day.getText();
			this.desDate.setText(selectedDate);
			this.shell.close();
		}
	}

	public void mouseDown(MouseEvent e) {
	}

	public void mouseUp(MouseEvent e) {
	}

	/**
	 * 返回用户选择的年月日信息
	 * 
	 * @return
	 */
	public void open() {
		Shell parent = getParent();

		display = Display.getDefault();
		shell = new Shell(parent, SWT.TITLE | SWT.PRIMARY_MODAL |SWT.CLOSE);
		shell.setImage(ResourceManager.getPluginImage(Activator.getDefault(), "src/com/thunisoft/dataplatform/image/oldDate.png"));
		shell.setText("日期选择");
		shell.setSize(230, 228);
		
		//*****************
		
		//让窗口居中
//        Rectangle screenSize = Display.getDefault().getClientArea();
//        Point frameSize2 = this.desDate.getLocation();
//        Rectangle frameSize = this.getParent().getBounds();
		
		//****************12-03
//        shell.setLocation(this.x+130,this.y+55);
		shell.setLocation(this.x,this.y);
		//****************12-03
        
		gridLayout = new GridLayout();
		gridLayout.numColumns = 7;
		shell.setLayout(gridLayout);

		months = new String[] {"一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"};
		monthDignit = new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		combo_month = new Combo(shell, SWT.NONE);
		combo_month.setItems(months);
		combo_month.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent modifyevent) {
				int index = combo_month.getSelectionIndex();
				moveTo(Calendar.MONTH,index);
			}
		});
		combo_month.setVisibleItemCount(12);
		
		final GridData gd_combo_month = new GridData(SWT.LEFT, SWT.CENTER, true, false, 4, 1);
		gd_combo_month.widthHint = 80;
		combo_month.setLayoutData(gd_combo_month);

		spinner_year = new Spinner(shell, SWT.BORDER);
		spinner_year.setMaximum(2999);
		spinner_year.setMinimum(1980);
		final GridData gd_spinner_year = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
		gd_spinner_year.widthHint = 50;
		spinner_year.setLayoutData(gd_spinner_year);
		spinner_year.addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent modifyevent) {
				int index = spinner_year.getSelection();
				moveTo(Calendar.YEAR,index);
			}
		});
		
		sunday = new CLabel(shell, SWT.SHADOW_OUT | SWT.CENTER);
		gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData.heightHint = 20;
		gridData.widthHint = 70;
		sunday.setLayoutData(gridData);
		sunday.setText("日");

		monday = new CLabel(shell, SWT.SHADOW_OUT | SWT.CENTER);
		gridData_1 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData_1.heightHint = 20;
		gridData_1.widthHint = 70;
		monday.setLayoutData(gridData_1);
		monday.setText("一");

		tuesday = new CLabel(shell, SWT.SHADOW_OUT | SWT.CENTER);
		gridData_3 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData_3.heightHint = 20;
		gridData_3.widthHint = 70;
		tuesday.setLayoutData(gridData_3);
		tuesday.setText("二");

		wednesday = new CLabel(shell, SWT.SHADOW_OUT | SWT.CENTER);
		gridData_4 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData_4.heightHint = 20;
		gridData_4.widthHint = 70;
		wednesday.setLayoutData(gridData_4);
		wednesday.setText("三");

		thursday = new CLabel(shell, SWT.SHADOW_OUT | SWT.CENTER);
		gridData_5 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData_5.heightHint = 20;
		gridData_5.widthHint = 70;
		thursday.setLayoutData(gridData_5);
		thursday.setText("四");

		friday = new CLabel(shell, SWT.SHADOW_OUT | SWT.CENTER);
		gridData_6 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData_6.heightHint = 20;
		gridData_6.widthHint = 70;
		friday.setLayoutData(gridData_6);
		friday.setText("五");

		saturday = new CLabel(shell, SWT.SHADOW_OUT | SWT.CENTER);
		gridData_2 = new GridData(SWT.FILL, SWT.FILL, true, true);
		gridData_2.heightHint = 20;
		gridData_2.widthHint = 70;
		saturday.setLayoutData(gridData_2);
		saturday.setText("六");
		for (int i = 0; i < 42; i++) {
			days[i] = new CLabel(shell, SWT.FLAT | SWT.CENTER);
			gridData = new GridData(GridData.FILL_HORIZONTAL
					| GridData.FILL_VERTICAL);
			days[i].setLayoutData(gridData);
			days[i].setBackground(display.getSystemColor(SWT.COLOR_WHITE));
			days[i].addMouseListener(this);
			days[i].setToolTipText("双击返回.");
		}
		Calendar now = Calendar.getInstance();
		nowDate = new Date(now.getTimeInMillis());
		
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		this.spinner_year.setSelection(year);
//		this.combo_month.setText(months[month]);
		this.combo_month.select(month);
		setDayForDisplay(now);
		shell.open();
		Display display = parent.getDisplay();
		//**********************
		
//		//让窗口居中
////		Shell shell = getWindowConfigurer().getWindow().getShell();
//        Rectangle screenSize = Display.getDefault().getClientArea();
////        Rectangle frameSize = parent.getBounds();
//        Rectangle frameSize = this.desDate.getParent().getBounds();
//        shell.setLocation((screenSize.width - frameSize.width) / 2,(
//                           screenSize.height - frameSize.height) / 2);
        
        //************************
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
//		return selectedDate;
	}

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		Display display = new Display();
//		final Shell shell = new Shell(display);
//		shell.setText("日历");
//		FillLayout fl = new FillLayout();
//		shell.setLayout(fl);
//		final Text text = new Text(shell, SWT.PUSH);
//		Button open = new Button(shell, SWT.PUSH);
//		open.setText("日历");
//		open.addSelectionListener(new SelectionAdapter() {
//			public void widgetSelected(SelectionEvent e) {
//				STCalendar stc = new STCalendar(shell,text);
//				// 输出用户选择的年月日信息
//				System.out.println(stc.open());
//			}
//		});
//		shell.pack();
//		shell.open();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//	}
}
