package com.liusy.analysismodel.log.dao.formBean;

import org.eclipse.swt.widgets.Text;

public class LogExportBean {
   private String fromDate;
   private String toDate;
   private String tableName;
   private Text   txtFromDate;
   private Text   txtToDate;

   public String getFromDate() {
      return txtFromDate.getText().trim();
   }

   public void setFromDate(String fromDate) {
      this.fromDate = fromDate;
   }

   public String getToDate() {
      return txtToDate.getText().trim();
   }

   public void setToDate(String toDate) {
      this.toDate = toDate;
   }

   public String getTableName() {
      return tableName;
   }

   public void setTableName(String tableName) {
      this.tableName = tableName;
   }

   public LogExportBean(Text txtFromDate, Text txtToDate) {
      super();
      this.txtFromDate = txtFromDate;
      this.txtToDate = txtToDate;
   }

}
