package com.liusy.analysismodel.template.ui.contentProvider;

import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;

import com.liusy.analysis.template.model.util.StringUtil;

public class NumberVerifier implements VerifyListener {
   
   public NumberVerifier() {
   }

   public void verifyText(VerifyEvent e) {
      String str = e.text;
      if (str != null && str.length() > 0) e.doit = StringUtil.isInteger(str);//只能输入数字
   }
}
