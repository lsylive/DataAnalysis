package com.liusy.analysismodel.template.ui.dialog;

/**
 * 数据源对象
 * @author xing
 *
 */
public class DataSourceBean {
    private int id;
    private String dataSourceName;  //
    private String driver;     //
    private String url;       //
    private String userName;      //
    private String password;     //
    private String editFlg;
   public String getDataSourceName() {
      return dataSourceName;
   }
   public void setDataSourceName(String dataSourceName) {
      this.dataSourceName = dataSourceName;
   }
   public String getDriver() {
      return driver;
   }
   public void setDriver(String driver) {
      this.driver = driver;
   }
   public String getUrl() {
      return url;
   }
   public void setUrl(String url) {
      this.url = url;
   }
   public String getUserName() {
      return userName;
   }
   public void setUserName(String userName) {
      this.userName = userName;
   }
   public String getPassword() {
      return password;
   }
   public void setPassword(String password) {
      this.password = password;
   }
   public String getEditFlg() {
      return editFlg;
   }
   public void setEditFlg(String editFlg) {
      this.editFlg = editFlg;
   }
   public int getId() {
      return id;
   }
   public void setId(int id) {
      this.id = id;
   }
	
    
    
}
