package com.liusy.analysismodel.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.vo.DataField;

public class DbManager {


   


   public static List<DataField> loadFieldsInfo(String id) {
      List<DataField> list = new ArrayList<DataField>();
      String sql = "select id,name,cn_name as cname,data_type as dataType from t_resource_column where table_id=" + id;
      Integer itmp;
      String stmp;
      Connection conn = null;
      Statement stat = null;
      ResultSet rs = null;

      try {
         conn = DbConnectionManager.getConnection();
         if (conn != null) {
            stat = conn.createStatement();
            rs = stat.executeQuery(sql);

            if (rs == null) return list;
            while (rs.next()) {
               DataField df = new DataField();

               itmp = rs.getInt("id");
               if (rs.wasNull()) df.setId("");
               else df.setId(itmp.toString());

               stmp = rs.getString("cname");
               if (rs.wasNull()) df.setCnName("");
               else df.setCnName(stmp);

               stmp = rs.getString("dataType");
               if (rs.wasNull()) df.setDataType("");
               else df.setDataType(stmp);

               stmp = rs.getString("name");
               if (rs.wasNull()) {
                  df.setName("");
                  df.setAliasName("");
               }
               else {
                  df.setName(stmp);
                  df.setAliasName(stmp);
               }
               df.setOutput(Consts.YES);
               df.setSortDirect("");
               df.setSortNo("");
               df.setAggregate("");
               list.add(df);
            }
            conn.commit();
         }
      }
      catch (Exception e) {
         DbConnectionManager.rollBack(conn);
         System.out.println("数据表访问出错：" + e.getMessage());
      }
      finally {
         DbConnectionManager.closeResultSet(rs);
         DbConnectionManager.closeStatement(stat);
      }
      return list;
   }
}
