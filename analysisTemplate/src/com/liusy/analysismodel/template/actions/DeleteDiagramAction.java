package com.liusy.analysismodel.template.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IURIEditorInput;
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

public class DeleteDiagramAction extends Action {
	private static Logger      log          = Logger.getLogger(DeleteDiagramAction.class);
	private final IWorkbenchWindow window;
	public DeleteDiagramAction(IWorkbenchWindow window, String label) {
		this.window = window;
		setText(label);
		setId("33");
//		String fileImage = ISharedImages.IMG_OBJ_PROJECT_CLOSED;
		
		ImageDescriptor imageDes = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.getDefault().PLUGIN_ID,"/src/com/thunisoft/dataplatform/template/image/delete.png");
//		ImageDescriptor imageDes = PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(fileImage);
		this.setImageDescriptor(imageDes);
	}
	public void run() {
		TemplateView view = (TemplateView)window.getActivePage().findView("DataAdminPlatform.view2");
		TreeViewer treeViewer = view.getTreeViewer();
      Object obj = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
      if (obj != null) {
         NavigatorEntityElement item = (NavigatorEntityElement) obj;
         NavigatorEntityElement pItem = item.getParent();
         File delFile = item.getFileInput();
         if (delFile.isFile()) {
            // 获取图ID
         	boolean comfirm = MessageDialog.openConfirm(window.getShell(), "询问", "确定要进行删除模板吗？");
            if (!comfirm) return ;
         	int id = 0;
            int sqlDeleteResult = 0;
            boolean fileDeleteResult = true;
            try {
               id = item.getTemplateId();
               // 在数据库删除图
               Connection con = null;
               Statement stat = null;
               Statement stat2 = null;
               PreparedStatement pstmt = null;
               con = DbConnectionManager.getConnection();
               stat = con.createStatement();
               stat2 = con.createStatement();
               String selectSql = "select * from t_analysis_diagram where id=" + String.valueOf(id);
               ResultSet rs = stat.executeQuery(selectSql);
               if (rs.next()) {
                  stat.close();
                  String sql = "delete from t_analysis_diagram where id=" + String.valueOf(id);
                  //								stat2 = con.prepareStatement(sql);
                  //								stat2.setString(2, delFile.getName());
                  sqlDeleteResult = stat2.executeUpdate(sql);
                  stat2.close();
                  // 关闭编辑器
                  IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                  IEditorPart[] parts = page.getEditors();
                  for (int i = 0; i < parts.length; i++) {
                     IEditorPart part = parts[i];
                     IURIEditorInput input = (IURIEditorInput) part.getEditorInput();
                     URI uriFile = input.getURI();
                     String filePath = uriFile.toString().substring(5);
                     int idEditor = getDiagramIdByPath(filePath);
                     // 如果ID相等的话就关闭
                     if (id == idEditor && sqlDeleteResult > 0) {
                        page.closeEditor(part, false);
                     }
                  }
               }
               con.commit();

            }
            catch (FileNotFoundException e) {

               e.printStackTrace();
            }
            catch (IOException e) {

               e.printStackTrace();
            }
            catch (ClassNotFoundException e) {

               e.printStackTrace();
            }
            catch (SQLException e) {

               e.printStackTrace();
            }
            finally {
               // 关闭编辑器
               IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
               IEditorPart[] parts = page.getEditors();
               for (int i = 0; i < parts.length; i++) {
                  IEditorPart part = parts[i];
                  IURIEditorInput input = (IURIEditorInput) part.getEditorInput();
                  URI uriFile = input.getURI();
                  String filePath = uriFile.toString().substring(5);
                  int idEditor = 0;
                  try {
                     idEditor = getDiagramIdByPath(filePath);
                     // 如果ID相等的话就关闭
                     if (id == idEditor) {
                        page.closeEditor(part, false);
                     }
                  }
                  catch (IOException e) {

                     e.printStackTrace();
                  }
                  catch (ClassNotFoundException e) {

                     e.printStackTrace();
                  }
               }
               //							System.out.println(delFlg);
               boolean delFlg = delFile.delete();
               pItem.delete(item);
               treeViewer.refresh();
            }
         }
      }
      //				;
   
	}
   public int getDiagramIdByPath(String path) throws IOException, ClassNotFoundException {
      InputStream inputSteam = new FileInputStream(path);
      ObjectInputStream oisEditor = new ObjectInputStream(inputSteam);
      Object objEditor = oisEditor.readObject();
      Diagram diagramEditor = (Diagram) objEditor;
      int idEditor = diagramEditor.getId();
      inputSteam.close();
      oisEditor.close();
      return idEditor;
   }
}
