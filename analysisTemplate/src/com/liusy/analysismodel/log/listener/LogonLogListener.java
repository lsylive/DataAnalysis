package com.liusy.analysismodel.log.listener;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Text;
import com.liusy.analysismodel.log.dao.formBean.LogonBean;
import com.liusy.analysismodel.log.dialog.LogonLogDetailDlg;
import com.liusy.analysismodel.log.model.log.LogonLog;
import com.liusy.analysismodel.log.view.LogonLogView;
import com.liusy.analysismodel.util.DateUtil;
import com.liusy.analysismodel.ui.STCalendar;

public class LogonLogListener {
   private LogonLogView viewPart;

   public LogonLogListener(LogonLogView viewPart) {
      super();
      this.viewPart = viewPart;
   }

   public boolean check() {
      if (!viewPart.getStartDate().getText().trim().equals(viewPart.getStartDateHd())
            || !viewPart.getEndDate().getText().trim().equals(viewPart.getEndDateHd())) {
         return true;
      }
      else {
         return false;
      }
   }

   public void addListener() {
      viewPart.getViewer().addDoubleClickListener(new IDoubleClickListener() {
         public void doubleClick(DoubleClickEvent doubleclickevent) {
            // TODO Auto-generated method stub
            IStructuredSelection obj = (IStructuredSelection) doubleclickevent.getSelection();
            LogonLog logBean = (LogonLog) obj.getFirstElement();
            if (logBean != null) {
               LogonLogDetailDlg dlg = new LogonLogDetailDlg(viewPart.getShell());
               LogonLogDetailListener applyLogDetialDlg = new LogonLogDetailListener(dlg, logBean);
               applyLogDetialDlg.init();
               dlg.open();
            }
         };
      });
      viewPart.setLogonBean(new LogonBean(viewPart.getUserAcount(), viewPart.getUserName(), viewPart.getDeptNameCombo(), viewPart.getOrgNameCombo(), viewPart
            .getStartDate(), viewPart.getEndDate()));
      //下拉列表
      viewPart.getGetComboContentAction().run();
      viewPart.getOrgNameCombo().addModifyListener(new ModifyListener() {
         public void modifyText(ModifyEvent modifyevent) {
            viewPart.getGetDeptComboContentAction().run();
         }
      });
      //查询按钮
      viewPart.getSearchBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            viewPart.getSearchAction().run();
         }
      });
      viewPart.getClearBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            viewPart.getClearAction().run();
         }
      });
      //导航条按钮
      viewPart.getDesPage().addVerifyListener(new VerifyListener() {
         public void verifyText(VerifyEvent verifyevent) {
            // TODO Auto-generated method stub
            verifyevent.doit = false;
            char myChar = verifyevent.character;
            String text = ((Text) verifyevent.widget).getText();
            if (Character.isDigit(myChar) || myChar == '\b') verifyevent.doit = true;

         }
      });
      viewPart.getGoBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            viewPart.getGoAction().run();
         }
      });
      viewPart.getStartBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            viewPart.getStartAction().run();
         }
      });
      viewPart.getNextBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            viewPart.getNextAction().run();
         }
      });
      viewPart.getPreviewBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            viewPart.getPreviewAction().run();
         }
      });
      viewPart.getEndBtn().addSelectionListener(new SelectionAdapter() {
         public void widgetSelected(final SelectionEvent e) {
            viewPart.getEndAction().run();
         }
      });

      //查询条件改变监听
      viewPart.getUserAcount().addFocusListener(viewPart.new FocusListioner());
      viewPart.getUserName().addFocusListener(viewPart.new FocusListioner());
      viewPart.getDeptNameCombo().addFocusListener(viewPart.new FocusListioner());
      viewPart.getOrgNameCombo().addFocusListener(viewPart.new FocusListioner());
      viewPart.getStartDate().addModifyListener(new ModifyListener() {
         public void modifyText(ModifyEvent modifyevent) {
            viewPart.setReSearchFlg(check());
         }
      });
      viewPart.getEndDate().addModifyListener(new ModifyListener() {
         public void modifyText(ModifyEvent modifyevent) {
            viewPart.setReSearchFlg(check());
         }
      });
      //日期控件监听
      viewPart.getStartDateBtn().addMouseListener(new MouseAdapter() {
         public void mouseDown(final MouseEvent e) {
            DateUtil.dateLocationInit();
            viewPart.setX(DateUtil.getXlocation(viewPart.getStartDateBtn()));
            ;
            viewPart.setY(DateUtil.getYlocation(viewPart.getStartDateBtn(), true));
            ;
            STCalendar stc = new STCalendar(viewPart.getShell(), viewPart.getStartDate(), viewPart.getX(), viewPart.getY());
            stc.open();
         }
      });
      viewPart.getEndDateBtn().addMouseListener(new MouseAdapter() {
         public void mouseDown(final MouseEvent e) {
            DateUtil.dateLocationInit();
            viewPart.setX(DateUtil.getXlocation(viewPart.getEndDateBtn()));
            ;
            viewPart.setY(DateUtil.getYlocation(viewPart.getEndDateBtn(), true));
            ;
            STCalendar stc = new STCalendar(viewPart.getShell(), viewPart.getEndDate(), viewPart.getX(), viewPart.getY());
            stc.open();
         }
      });

   }

}
