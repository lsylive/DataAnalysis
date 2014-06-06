package com.liusy.analysismodel.template.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import oracle.sql.BLOB;

import org.apache.log4j.Logger;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.template.ITreeElement;
import com.liusy.analysismodel.template.NavigatorEntityElement;
import com.liusy.analysismodel.template.TemplateView;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysismodel.template.ui.dialog.AnalysisModleNameDlg;
import com.liusy.analysismodel.util.DbConnectionManager;

public class NewDiagramAction extends Action {
	private static Logger      log          = Logger.getLogger(NewDiagramAction.class);
	private final IWorkbenchWindow window;
	public NewDiagramAction(IWorkbenchWindow window, String label) {
		this.window = window;
		setText(label);
		setId("33");
		String fileImage = ISharedImages.IMG_OBJ_FILE;
		
//		ImageDescriptor imageDes = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.getDefault().PLUGIN_ID,"/src/com/thunisoft/dataplatform/template/image/save.png");
		ImageDescriptor imageDes = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(fileImage);
		this.setImageDescriptor(imageDes);
	}
	public void run() {
//		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
//		page.getActiveEditor().doSave(null);
		TemplateView view = (TemplateView)window.getActivePage().findView("DataAdminPlatform.view2");
		TreeViewer treeViewer = view.getTreeViewer();
      Object obj = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
      NavigatorEntityElement item = (NavigatorEntityElement) obj;
      String parentName = item.getName();
      File desFile = item.getFileInput();
      if (desFile.isDirectory()) {
         String newFileName = "";
         int winX = window.getShell().getLocation().x;
         int winY = window.getShell().getLocation().y;
         int x = treeViewer.getTree().getSelection()[0].getBounds().x+11;
         float hei = treeViewer.getTree().getFont().getFontData()[0].height;
         int itemH = treeViewer.getTree().getItemHeight();
         int h = Math.round(hei*3);
         int base = 67;
         
         int y = treeViewer.getTree().getSelection()[0].getBounds().y+base+h+itemH;
         
         Point dd = new Point(x+winX,y+winY);
//         Point cursorLoc = treeViewer.getControl().getDisplay().getCursorLocation();
         AnalysisModleNameDlg fileNameDlg = new AnalysisModleNameDlg(window.getShell(), newFileName, dd, 1);
         newFileName = (String) fileNameDlg.open();
         if (!"".equals(newFileName)) {
            File[] files = desFile.listFiles();
            for (File file : files) {
               if (file.getName().equals(newFileName)) { return;// 文件名重复
               }
            }
            String fileName = newFileName;
            String derectoryName = desFile.getName();
            StringBuffer newTemplateId = new StringBuffer();
            InputStream stream = openContentStream(newFileName, derectoryName, newTemplateId);

            File file = new File(desFile.getAbsolutePath() + "/" + fileName);
            final URI uri = file.toURI();
            OutputStream out;
            try {
               //写进文件系统
               out = new FileOutputStream(file);
               byte buf[] = new byte[1024];
               int len;
               while ((len = stream.read(buf)) > 0)
                  out.write(buf, 0, len);
               out.close();
               stream.close();
               treeViewer.refresh();
               //写进数据库
               Connection con = null;
               Statement stat = null;
               PreparedStatement pstmt1 = null;
               PreparedStatement pstmt2 = null;
               ResultSet rs = null;
               BLOB blob = null;
               con = DbConnectionManager.getConnection();
               /* 查询CLOB对象并锁定 */
               pstmt1 = con.prepareStatement("select body from t_analysis_diagram where id =? for update");
               pstmt1.setInt(1, Integer.valueOf(newTemplateId.toString()));
               oracle.jdbc.OracleResultSet result = (oracle.jdbc.OracleResultSet) pstmt1.executeQuery();
               while (result.next()) {
                  /* 获取此CLOB对象 */
                  blob = (BLOB) result.getBlob("body");
               }
               /* 进行覆盖式修改 */
               FileInputStream fin = new FileInputStream(file);
               if (blob != null) {
                  OutputStream outputStream = blob.getBinaryOutputStream();
                  byte[] data = new byte[(int) fin.available()];
                  fin.read(data);
                  outputStream.write(data);
                  fin.close();
                  outputStream.close();
                  pstmt2 = con.prepareStatement("update t_analysis_diagram set body =? where id=?");
                  pstmt2.setBlob(1, blob);
                  pstmt2.setInt(2, Integer.valueOf(newTemplateId.toString()));
                  pstmt2.executeUpdate();
                  pstmt2.close();
               }
               /* 正式提交 */
               con.commit();
            }

            catch (Exception e) {

               e.printStackTrace();
            }
            //构建目录

            NavigatorEntityElement pItem = new NavigatorEntityElement();
            List<ITreeElement> root = (List<ITreeElement>)treeViewer.getInput();
            for (ITreeElement roopItem : root) {
               if (parentName.equals(roopItem.getName())) {
                  pItem = (NavigatorEntityElement) roopItem;
                  break;
               }
            }
            NavigatorEntityElement newItem = new NavigatorEntityElement();
            newItem.setTemplateId(Integer.valueOf(newTemplateId.toString()));
            newItem.setName(newFileName);
            newItem.setFileInput(file);
            newItem.setParent(pItem);
            pItem.addChild(newItem);
            treeViewer.refresh();
         }
         else {
            return;// 没有文件名
         }
      }

   
	}
   private InputStream openContentStream(String name, String directory, StringBuffer templateId) {
      Diagram ld = new Diagram();
      //设置属性
      int type = 0;
      for (int i = 0; i < Consts.TEMP_LABEL.length; i++) {
         if (directory.equals(Consts.TEMP_LABEL[i])) {
            type = i;
            break;
         }
      }
      int id = 0;
      Connection con = null;
      Statement stat = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      con = DbConnectionManager.getConnection();
      if (con != null) {
         boolean defaultCommit = true;
         String sql = "select max(id) as id from t_analysis_diagram";
         try {
            stat = con.createStatement();
            rs = stat.executeQuery(sql);
            while (rs.next()) {
               id = rs.getInt("id") + 1;
            }
            ld.setId(id);
            //那图对象插进数据库
            pstmt = con.prepareStatement("insert into t_analysis_diagram(id,name,type,body) values(?,?,?,EMPTY_BLOB())");
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, type);
            //         pstmt.setBlob(4, id);
            pstmt.executeUpdate();
            pstmt.close();
         }
         catch (Exception e) {
            log.error(e);
         }
         finally {

            DbConnectionManager.closeResultSet(rs);
            DbConnectionManager.closeStatement(stat);
         }

      }
      templateId.append(id);
      ld.setName(name);
      ld.setType(type);
      ByteArrayInputStream bais = null;
      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream();
         ObjectOutputStream oos = new ObjectOutputStream(baos);
         oos.writeObject(ld);
         oos.writeObject(null);
         oos.flush();
         oos.close();
         baos.close();
         bais = new ByteArrayInputStream(baos.toByteArray());
         bais.close();
      }
      catch (Exception e) {
         e.printStackTrace();

      }
      finally {
         try {
            con.commit();
         }
         catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         return bais;
      }
   }
}
