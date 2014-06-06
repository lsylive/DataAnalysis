package com.liusy.analysismodel.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageUtil {

   public static int alert(Shell shell, String title, String message) {
      MessageBox dialog = new MessageBox(shell, SWT.ICON_WARNING);
      dialog.setText(title);
      dialog.setMessage(message);
      return dialog.open();
   }

   public static int comfirm(Shell shell, String title, String message) {
      MessageBox dialog = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
      dialog.setText(title);
      dialog.setMessage(message);
      return dialog.open();
   }
}
