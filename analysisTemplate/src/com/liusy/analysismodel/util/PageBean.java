package com.liusy.analysismodel.util;

import com.liusy.analysis.template.model.util.StringUtil;

public class PageBean {
   private int totlRecord      = 0;
   private int pageRecordCount = 100;
   private int totlPage        = 0;
   private int currentPage     = 1;
   private int start           = 0;
   private int end             = 0;

   public int getTotlRecord() {
      return totlRecord;
   }

   public void setTotlRecord(int totlRecord) {
      this.totlRecord = totlRecord;
   }

   public int getPageRecordCount() {
      return pageRecordCount;
   }

   public void setPageRecordCount(int pageRecordCount) {
      this.pageRecordCount = pageRecordCount;
   }

   public int getTotlPage() {
      return (int) ((totlRecord % pageRecordCount == 0) ? (totlRecord / pageRecordCount) : (totlRecord / pageRecordCount) + 1);
   }

   public int getCurrentPage() {
      return currentPage;
   }

   public void setCurrentPage(int currentPage) {
      if (currentPage <= 0) currentPage = 1;
      if (currentPage > getTotlPage()) {
         currentPage = getTotlPage();
      }
      this.currentPage = currentPage;
   }

   public int getStart() {
      return (getCurrentPage() - 1) * pageRecordCount + 1;
   }

   public void setStart(int start) {
      this.start = start;
   }

   public int getEnd() {
      return getCurrentPage() * pageRecordCount;
   }

   public void setEnd(int end) {
      this.end = end;
   }

   public void next() {
      int currenPage = this.getCurrentPage();
      this.setCurrentPage(currenPage + 1);
   }

   public void preview() {
      int currenPage = this.getCurrentPage();
      this.setCurrentPage(currenPage - 1);
   }

   public String bounderCheck() {
      String result = "";
      if (currentPage == getTotlPage()) {
         result = StringUtil.lastPageWarnMessage;
      }
      else if (currentPage == 1) {
         result = StringUtil.firstPageWarnMessage;
      }
      else {
         result = "";
      }
      return result;

   }
}
