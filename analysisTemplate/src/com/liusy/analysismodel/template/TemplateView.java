package com.liusy.analysismodel.template;

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
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.sql.BLOB;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.ViewPart;

import com.swtdesigner.SWTResourceManager;
import com.liusy.analysismodel.DataAdminPlatform;
import com.liusy.analysis.template.model.Consts;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysismodel.template.ui.dialog.AnalysisModleNameDlg;
import com.liusy.analysismodel.util.DbConnectionManager;

// *******************
// import com.free.viewer.FileTreeContentProvider;
// import com.free.viewer.FileTreeLabelProvider;
public class TemplateView extends ViewPart implements ISelectionListener {
   private static Logger      log          = Logger.getLogger(TemplateView.class);
   private Tree               tree;
   private TreeViewer         treeViewer;
   private Action             newAction;
   private Action             openAction;
   private Action             reNameAction;
   private Action             deleteAction;
   private Action             copyAction;
   private Action             refleshAction;
   public static final String ID           = "DataAdminPlatform.view2";
   private String             newFileName  = "";
   private String             initTreePath = Platform.getInstanceLocation().getURL().getPath().concat("analisis");
   private List<ITreeElement> root         = new ArrayList<ITreeElement>();

   /**
    * This is a callback that will allow us to create the viewer and initialize
    * it.
    */
   public void createPartControl(Composite parent) {
      // String initTreePath = Platform.getLocation().toOSString();
      //    introductionLab.setFont(new Font()); //$NON-NLS-1$
      log.info(initTreePath);
      createDictory(initTreePath);
      parent.setLayout(new FormLayout());
      //
      initializeToolBar();
      treeViewer = new TreeViewer(parent, SWT.NONE);
      addContent(initTreePath);
      createActions();
      createContextMenu(parent);

      tree = treeViewer.getTree();
      final FormData fd_tree = new FormData();
      fd_tree.bottom = new FormAttachment(100, 0);
      fd_tree.top = new FormAttachment(0, 0);
      fd_tree.right = new FormAttachment(100, 0);
      fd_tree.left = new FormAttachment(0, 0);
      tree.setLayoutData(fd_tree);

   }

   private void createContextMenu(Composite parent) {
      MenuManager mgr = new MenuManager();
      mgr.setRemoveAllWhenShown(true);
      mgr.addMenuListener(new IMenuListener() {
         public void menuAboutToShow(IMenuManager manager) {
            fillContextMenu(manager);
         }
      });
      Menu menu = mgr.createContextMenu(treeViewer.getControl());
      treeViewer.getControl().setMenu(menu);
      getSite().registerContextMenu(mgr, treeViewer);
   }

   protected void fillContextMenu(IMenuManager manager) {
      manager.add(newAction);
      manager.add(openAction);
      manager.add(reNameAction);
      manager.add(deleteAction);
      manager.add(copyAction);
      manager.add(refleshAction);
      //      manager.add(deleteLocalAction);
   }

   public void createActions() {
      openAction = new Action("打开(&O)") {
         public void run() {
            File file2 = null;
            Object obj = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
            NavigatorEntityElement item = (NavigatorEntityElement) obj;
            file2 = item.getFileInput();
            if (file2.isFile()) {
               IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
               try {
                  IDE.openEditor(page, file2.toURI(), "DataAdminPlatform.PracticeEditor", true);
               }
               catch (PartInitException e) {
                  log.error(e.getMessage());
               }
            }
         }
      };

      refleshAction = new Action("刷新(&R)") {
         public void run() {
            root.clear();
            createDictory(initTreePath);
            treeViewer.refresh();
         }
      };
      copyAction = new Action("复制添加(&C)") {
         public void run() {
            Object obj = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
            if (obj != null) {
               NavigatorEntityElement item = (NavigatorEntityElement) obj;
               NavigatorEntityElement pItem = item.getParent();
               File delFile = item.getFileInput();
               String derectoryName = pItem.getName();
               if (delFile.isFile()) {
                  int templateId = 0;
                  String oldName = delFile.getName();
                  String copyName = "CopyOf" + oldName;
                  Point cursorLoc = treeViewer.getControl().getDisplay().getCursorLocation();
                  AnalysisModleNameDlg fileNameDlg = new AnalysisModleNameDlg(getShell(), copyName, cursorLoc, 3);
                  String newFileName = (String) fileNameDlg.open();
                  //检查重复文件名
                  File[] files = pItem.getFileInput().listFiles();
                  for (File file : files) {
                     if (file.getName().equals(newFileName)) { return;// 文件名重复
                     }
                  }
                  if (!"".equals(newFileName) && !oldName.equals(newFileName)) {
                     NavigatorEntityElement newItem = new NavigatorEntityElement();

                     InputStream inputSteam;
                     Connection con = null;
                     try {
                        //获取模板类型
                        int type = 0;
                        for (int i = 0; i < Consts.TEMP_LABEL.length; i++) {
                           if (derectoryName.equals(Consts.TEMP_LABEL[i])) {
                              type = i;
                              break;
                           }
                        }
                        // 按确定按钮后，上传到数据库

                        Statement stat = null;
                        PreparedStatement pstmt = null;
                        ResultSet rs = null;
                        con = DbConnectionManager.getConnection();
                        if (con != null) {
                           String sql = "select max(id) as id from t_analysis_diagram";
                           stat = con.createStatement();
                           rs = stat.executeQuery(sql);
                           while (rs.next()) {
                              templateId = rs.getInt("id") + 1;
                           }
                           stat.close();
                           //那图对象插进数据库
                           pstmt = con.prepareStatement("insert into t_analysis_diagram(id,name,type,body) values(?,?,?,EMPTY_BLOB())");
                           pstmt.setInt(1, templateId);
                           pstmt.setString(2, newFileName);
                           pstmt.setInt(3, type);
                           pstmt.executeUpdate();
                           pstmt.close();
                        }

                        // 复制DIAGRAM模型
                        inputSteam = new FileInputStream(delFile);
                        ObjectInputStream oisEditor = new ObjectInputStream(inputSteam);
                        Object objEditor = oisEditor.readObject();
                        Diagram diagramEditor = (Diagram) objEditor;
                        diagramEditor.setName(newFileName);
                        diagramEditor.setId(templateId);
                        inputSteam.close();
                        oisEditor.close();

                        // 按确定按钮后，生成本地文件
                        File tempFile = new File(delFile.getParent() + "\\" + newFileName);
                        FileOutputStream f = new FileOutputStream(tempFile);
                        ObjectOutputStream objectOut = new ObjectOutputStream(f);
                        objectOut.writeObject(diagramEditor);
                        objectOut.writeObject(null);
                        objectOut.close();

                        //进行覆盖式修改
                        OutputStream out;
                        BLOB blob = null;
                        PreparedStatement pstmt1 = null;
                        PreparedStatement pstmt2 = null;
                        pstmt1 = con.prepareStatement("select body from t_analysis_diagram where id =? for update");
                        pstmt1.setInt(1, templateId);
                        oracle.jdbc.OracleResultSet result = (oracle.jdbc.OracleResultSet) pstmt1.executeQuery();
                        while (result.next()) {
                           /* 获取此CLOB对象 */
                           blob = (BLOB) result.getBlob("body");
                        }
                        FileInputStream fin = new FileInputStream(tempFile);
                        if (blob != null) {
                           OutputStream outputStream = blob.getBinaryOutputStream();
                           byte[] data = new byte[(int) fin.available()];
                           fin.read(data);
                           outputStream.write(data);
                           fin.close();
                           outputStream.close();
                           pstmt2 = con.prepareStatement("update t_analysis_diagram set body =? where id=?");
                           pstmt2.setBlob(1, blob);
                           pstmt2.setInt(2, templateId);
                           pstmt2.executeUpdate();
                           pstmt2.close();
                        }
                        //
                        newItem.setName(newFileName);
                        newItem.setParent(pItem);
                        newItem.setFileInput(tempFile);
                        newItem.setTemplateId(templateId);
                        pItem.addChild(newItem);

                     }
                     catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }
                     catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }
                     catch (ClassNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }
                     catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }
                     finally {
                        treeViewer.refresh();
                        try {
                           con.commit();
                        }
                        catch (SQLException e) {
                           // TODO Auto-generated catch block
                           e.printStackTrace();
                        }
                        //								DbConnectionManager.closeResultSet(rs);
                        //								DbConnectionManager.closeStatement(stat);
                     }
                  }

                  // 生成ID

                  // 按取消后，关闭对话框

               }
            }

         }
      };
      deleteAction = new Action("删除(&D)") {
         public void run() {
            Object obj = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
            if (obj != null) {
               NavigatorEntityElement item = (NavigatorEntityElement) obj;
               NavigatorEntityElement pItem = item.getParent();
               File delFile = item.getFileInput();
               if (delFile.isFile()) {
                  // 获取图ID
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
      };
      reNameAction = new Action("重命名(&N)") {
         public void run() {
            Object obj = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
            if (obj != null) {
               NavigatorEntityElement item = (NavigatorEntityElement) obj;
               File desFile = item.getFileInput();
               if (desFile.isFile()) {
                  boolean fileOpenFlag = false;
                  String oldName = desFile.getName();
                  //                  Point cursorLoc = new Point(0,0);
                  Point cursorLoc = treeViewer.getControl().getDisplay().getCursorLocation();
                  AnalysisModleNameDlg fileNameDlg = new AnalysisModleNameDlg(getShell(), oldName, cursorLoc, 2);
                  newFileName = (String) fileNameDlg.open();

                  
                  if (!oldName.equals(newFileName) && !"".equals(newFileName)) {
                     File tempFile = new File(desFile.getParent() + "\\" + newFileName);

                     //
                     IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                     try {
                        InputStream inputSteam = new FileInputStream(desFile);
                        ObjectInputStream oisEditor = new ObjectInputStream(inputSteam);
                        Object objEditor = oisEditor.readObject();
                        Diagram diagramEditor = (Diagram) objEditor;
                        int idEditor = diagramEditor.getId();
                        //
                        int diagramType = diagramEditor.getType();
                        Connection con0 = null;
                        PreparedStatement pstmt0 = null;
                        ResultSet rs1 = null;
                        List nameList = new ArrayList<String>();
                        con0 = DbConnectionManager.getConnection();
                        pstmt0 = con0.prepareStatement("select name from t_analysis_diagram where type=? and id!=?");
            				pstmt0.setInt(1, diagramType);
            				pstmt0.setInt(2, idEditor);
            				rs1 = pstmt0.executeQuery();
            				while (rs1.next()) {
            					nameList.add(rs1.getString(1));
            				}
            				rs1.close();
            				pstmt0.close();
            				if (nameList.contains(newFileName)) {
            					MessageBox messageBox= new MessageBox(getShell(),SWT.OK);
            					messageBox.setText("模板名称");
            					messageBox.setMessage("模板名字重复！请重新设置模板名字。");
            					int result = messageBox.open();
//            					txtName.setFocus();
            					return;
            				}
                        diagramEditor.setName(newFileName);
                        //设置树节点名称
                        item.setName(newFileName);
                        inputSteam.close();
                        oisEditor.close();
                        FileOutputStream f = new FileOutputStream(tempFile);
                        ObjectOutputStream objectOut = new ObjectOutputStream(f);
                        objectOut.writeObject(diagramEditor);
                        objectOut.writeObject(null);
                        objectOut.close();
                        //关闭编辑器
                        IEditorPart[] parts = page.getEditors();
                        for (int i = 0; i < parts.length; i++) {
                           IEditorPart part = parts[i];
                           IURIEditorInput input = (IURIEditorInput) part.getEditorInput();
                           URI uriFile = input.getURI();
                           String filePath = uriFile.toString().substring(5);
                           int idCloseEditor = 0;
                           idCloseEditor = getDiagramIdByPath(filePath);
                           if (idEditor == idCloseEditor) {
                              page.closeEditor(part, false);
                              fileOpenFlag = true;
                              //										page.closeEditor(part, true);
                              break;
                           }
                        }
                        desFile.delete();
                        if (fileOpenFlag) {
                           IDE.openEditor(page, tempFile.toURI(), "DataAdminPlatform.PracticeEditor", true);
                        }

                        //在数据库更新图
                        setDiagramName(tempFile);
                        Connection con = null;
                        con = DbConnectionManager.getConnection();
                        PreparedStatement pstmt = null;
                        pstmt = con.prepareStatement("update t_analysis_diagram set name=? where id= ?");
                        pstmt.setString(1, newFileName);
                        pstmt.setInt(2, idEditor);
                        pstmt.executeUpdate();
                        pstmt.close();
                        //更新内容
                        PreparedStatement pstmt1 = null;
                        PreparedStatement pstmt2 = null;
                        BLOB blob = null;
                        pstmt1 = con.prepareStatement("select body from t_analysis_diagram where id =? for update");
                        pstmt1.setInt(1, idEditor);
                        oracle.jdbc.OracleResultSet result = (oracle.jdbc.OracleResultSet) pstmt1.executeQuery();
                        while (result.next()) {
                           /* 获取此CLOB对象 */
                           blob = (BLOB) result.getBlob("body");
                        }
                        /* 进行覆盖式修改 */
                        FileInputStream fin = new FileInputStream(tempFile);
                        if (blob != null) {
                           OutputStream outputStream = blob.getBinaryOutputStream();
                           byte[] data = new byte[(int) fin.available()];
                           fin.read(data);
                           outputStream.write(data);
                           fin.close();
                           outputStream.close();
                           pstmt2 = con.prepareStatement("update t_analysis_diagram set body =? where id=?");
                           pstmt2.setBlob(1, blob);
                           pstmt2.setInt(2, idEditor);
                           pstmt2.executeUpdate();
                           pstmt2.close();
                        }
                        /* 正式提交 */
                        con.commit();
                        //
                        item.setFileInput(tempFile);
                        item.setName(newFileName);
                     }
                     catch (PartInitException e) {

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
                        treeViewer.refresh();
                     }

                     //							boolean reNameFlg = desFile.renameTo(tempFile);
                     //							if (reNameFlg) {}
                  }
               }
            }

         }
      };
      IWorkbenchWindow window = this.getSite().getWorkbenchWindow();
      newAction = new Action("添加(&A)") {
         public void run() {
            Object obj = ((IStructuredSelection) treeViewer.getSelection()).getFirstElement();
            NavigatorEntityElement item = (NavigatorEntityElement) obj;
            String parentName = item.getName();
            File desFile = item.getFileInput();
            if (desFile.isDirectory()) {
               newFileName = "";
               Point cursorLoc = treeViewer.getControl().getDisplay().getCursorLocation();
               AnalysisModleNameDlg fileNameDlg = new AnalysisModleNameDlg(getShell(), newFileName, cursorLoc, 1);
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
                  //                  IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
                  //                  try {
                  //                     log.info("prepare to open editor by" + uri.toString());
                  //                     IDE.openEditor(page, uri, "DataAdminPlatform.PracticeEditor", true);
                  //                     log.info("open editor by" + uri.toString() + "file success!");
                  //                  }
                  //                  catch (PartInitException e) {
                  //                     log.info(e.getStackTrace());
                  //                  }

               }
               else {
                  return;// 没有文件名
               }
            }

         }
      };
      //      ImageDescriptor imageDesNew = Activator.imageDescriptorFromPlugin("DataAdminPlatform","/src/com/thunisoft/dataplatform/template/image/save.png");
      //      newAction.setImageDescriptor(imageDesNew);
      treeViewer.addDoubleClickListener(new IDoubleClickListener() {
         public void doubleClick(DoubleClickEvent doubleclickevent) {
            openAction.run();
         }
      });
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

   public void setDiagramName(File file) throws IOException, ClassNotFoundException {
      InputStream inputSteam = new FileInputStream(file);
      ObjectInputStream oisEditor = new ObjectInputStream(inputSteam);
      Object objEditor = oisEditor.readObject();
      Diagram diagramEditor = (Diagram) objEditor;
      diagramEditor.setName(file.getName());
      inputSteam.close();
      oisEditor.close();
   }

   //   public int getDiagramIdByFile(File file) throws IOException, ClassNotFoundException {
   //      InputStream inputSteam = new FileInputStream(file);
   //      ObjectInputStream oisEditor = new ObjectInputStream(inputSteam);
   //      Object objEditor = oisEditor.readObject();
   //      Diagram diagramEditor = (Diagram) objEditor;
   //      int idEditor = diagramEditor.getId();
   //      inputSteam.close();
   //      oisEditor.close();
   //      return idEditor;
   //   }

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

   /**
    * Passing the focus request to the viewer's control.
    */
   public void setFocus() {
   }

   public Shell getShell() {
      return treeViewer.getControl().getShell();
   }

   private void initializeToolBar() {
      IToolBarManager toolBarManager = getViewSite().getActionBars().getToolBarManager();
   }

   public void addContent(String initPath) {
      treeViewer.setContentProvider(new TreeViewerContentProvider());
      treeViewer.setLabelProvider(new TreeViewerLabelProvider());
      //      File file = new File(initPath);
      //      if (!file.exists()) {
      //         file.mkdir();
      //      }
      treeViewer.setInput(root);

   }

   public void selectionChanged(IWorkbenchPart iworkbenchpart, ISelection iselection) {
   }

   private void throwCoreException(String message) throws CoreException {
      IStatus status = new Status(IStatus.ERROR, "test22", IStatus.OK, message, null);
      throw new CoreException(status);
   }

   public void createDictory(String basePath) {
      Connection con = null;
      Statement stat = null;
      ResultSet rs = null;
      con = DbConnectionManager.getConnection();
      deletefile(basePath);
      File baseDir = new File(basePath);
      baseDir.mkdir();
      if (con != null) {
         try {
            stat = con.createStatement();
            int start = 1;
            int end = 6;
            for (int i = start; i < end; i++) {
               //创建目录
               String directory = Consts.TEMP_LABEL[i];
               searchAndCreatFile(0, directory, null, basePath, true, null);
               String sql = "select id,name,body from t_analysis_diagram where type =" + String.valueOf(i);
               rs = stat.executeQuery(sql);
               while (rs.next()) {
                  int id = rs.getInt("id");
                  String name = rs.getString("name");
                  Blob data = rs.getBlob("body");
                  searchAndCreatFile(id, name, directory, basePath, false, data);
               }
            }
         }
         catch (Exception e) {
            log.error(e);
         }
         finally {
            DbConnectionManager.closeResultSet(rs);
            DbConnectionManager.closeStatement(stat);
         }
      }
   }

   private void deletefile(String delpath) {
      // TODO Auto-generated method stub

      try {
         File file = new File(delpath);
         if (!file.isDirectory()) {
            file.delete();
         }
         else if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
               File delfile = new File(delpath + "\\" + filelist[i]);
               if (!delfile.isDirectory()) delfile.delete();
               else if (delfile.isDirectory()) deletefile(delpath + "\\" + filelist[i]);
            }
            file.delete();
         }
      }
      catch (Exception e) {
         log.error("delete file exception:" +e.getMessage());
      }

   }

   public void searchAndCreatFile(int id, String name, String directory, String basePath, boolean isDikFlg, Blob source) {
      if (isDikFlg) {//目录
         File directoryFile = new File(basePath + "/" + name);
         log.info(basePath + "/" + name);
         //			System.out.println(basePath + "/" + name);
         if (!directoryFile.exists()) {
            boolean result = directoryFile.mkdir();

         }
         NavigatorEntityElement item = new NavigatorEntityElement();
         item.setName(name);
         item.setFileInput(directoryFile);
         item.setTemplateId(0);
         root.add(item);
      }
      else {
         File file = new File(basePath + "//" + directory + "//" + name);
         java.io.FileOutputStream fileWrite = null;
         java.io.InputStream input = null;
         if (!file.exists() && source != null) {
            try {
               file.createNewFile();
               //写文件
               fileWrite = new java.io.FileOutputStream(file);
               input = source.getBinaryStream();
               byte[] cbuf = new byte[1024];
               Integer iRead = input.read(cbuf, 0, 1024);
               while (iRead.compareTo(-1) != 0) {
                  fileWrite.write(cbuf, 0, iRead);
                  iRead = input.read(cbuf, 0, 1024);
               }
               input.close();
               fileWrite.close();

            }
            catch (Exception e) {
               log.error(e.getMessage());
            }
            //
            NavigatorEntityElement item = new NavigatorEntityElement();
            item.setName(name);
            item.setFileInput(file);
            item.setTemplateId(id);
            NavigatorEntityElement directoryEntity = new NavigatorEntityElement();
            for (ITreeElement derectory : root) {
               if (directory.equals(derectory.getName())) {
                  directoryEntity = (NavigatorEntityElement) derectory;
                  break;
               }
            }
            directoryEntity.addChild(item);
            item.setParent(directoryEntity);

         }
         else if (file.exists()) {
            try {
               //删除本地文件
               boolean deleteFlag = file.delete();
               //新建文件
               if (deleteFlag) {
                  File localeFile = new File(basePath + "//" + directory + "//" + name);
                  java.io.FileOutputStream remoteFileWrite = null;
                  java.io.InputStream fileInput = null;
                  localeFile.createNewFile();
                  //写文件
                  remoteFileWrite = new java.io.FileOutputStream(localeFile);
                  fileInput = source.getBinaryStream();
                  byte[] cbuf = new byte[1024];
                  Integer iRead = fileInput.read(cbuf, 0, 1024);
                  while (iRead.compareTo(-1) != 0) {
                     remoteFileWrite.write(cbuf, 0, iRead);
                     iRead = fileInput.read(cbuf, 0, 1024);
                  }
                  fileInput.close();
                  remoteFileWrite.close();
                  //
                  NavigatorEntityElement item = new NavigatorEntityElement();
                  item.setName(name);
                  item.setFileInput(localeFile);
                  item.setTemplateId(id);
                  NavigatorEntityElement directoryEntity = new NavigatorEntityElement();
                  for (ITreeElement derectory : root) {
                     if (directory.equals(derectory.getName())) {
                        directoryEntity = (NavigatorEntityElement) derectory;
                        break;
                     }
                  }
                  directoryEntity.addChild(item);
                  item.setParent(directoryEntity);
               }
            }
            catch (Exception e) {
               log.error(e.getMessage());
            }
         }
      }
   }

	public TreeViewer getTreeViewer() {
		return treeViewer;
	}

	public List<ITreeElement> getRoot() {
		return root;
	}

	public String getInitTreePath() {
		return initTreePath;
	}
}

/**
 * This class provides the content for the tree in FileTree
 */

class FileTreeContentProvider implements ITreeContentProvider {

   /**
    * Gets the children of the specified object
    * 
    * @param arg0
    *           the parent object
    * @return Object[]
    */
   public Object[] getChildren(Object arg0) {
      if (arg0 == null || !(arg0 instanceof File)) { return new Object[0]; }
      return ((File) arg0).listFiles();

   }

   /**
    * Gets the parent of the specified object
    * 
    * @param arg0
    *           the object
    * @return Object
    */
   public Object getParent(Object arg0) {
      // Return this file's parent file
      // 返回树的上一级结点
      return ((File) arg0).getParentFile();
   }

   /**
    * Returns whether the passed object has children
    * 
    * @param arg0
    *           the parent object
    * @return boolean
    */
   public boolean hasChildren(Object arg0) {
      File file = (File) arg0;
      if (file.isDirectory() && file.listFiles().length > 0) return true;
      return false;
   }

   /**
    * Gets the root element(s) of the tree
    * 
    * @param arg0
    *           the input data
    * @return Object[]
    */
   public Object[] getElements(Object arg0) {
      File file = (File) arg0;
      if (!file.exists()) return new Object[0];
      return file.listFiles();
   }

   /**
    * Disposes any created resources
    */
   public void dispose() {
      // Nothing to dispose
   }

   /**
    * Called when the input changes
    * 
    * @param arg0
    *           the viewer
    * @param arg1
    *           the old input
    * @param arg2
    *           the new input
    */
   public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
      // Nothing to change
   }

}

class FileTreeLabelProvider implements ILabelProvider {
   private static Logger                log          = Logger.getLogger(FileTreeLabelProvider.class);

   private List<ILabelProviderListener> listeners;

   // Images for tree nodes
   private Image                        file;

   private Image                        dir;

   // Label provider state: preserve case of file names/directories
   boolean                              preserveCase = true;

   /**
    * Constructs a FileTreeLabelProvider
    */
   public FileTreeLabelProvider() {
      // Create the list to hold the listeners
      listeners = new ArrayList<ILabelProviderListener>();

      // 添加文件和目录的图标
      dir = SWTResourceManager.getImage(FileTreeLabelProvider.class, "/com/thunisoft/dataplatform/template/image/directory.gif");
      file = SWTResourceManager.getImage(FileTreeLabelProvider.class, "/com/thunisoft/dataplatform/template/image/file.gif");
   }

   /**
    * Gets the image to display for a node in the tree
    * 
    * @param arg0
    *           the node
    * @return Image
    */
   public Image getImage(Object arg0) {
      if (((File) arg0).isDirectory()) {
         return dir;
      }
      else {
         return file;
      }
   }

   /**
    * Gets the text to display for a node in the tree
    * 
    * @param arg0
    *           the node
    * @return String
    */
   public String getText(Object arg0) {
      String text = ((File) arg0).getName();

      // If name is blank, get the path
      if (text.length() == 0) {
         text = ((File) arg0).getPath();
      }

      // Check the case settings before returning the text
      // 返回目录或文件的文件名
      return preserveCase ? text : text.toUpperCase();
   }

   /**
    * Adds a listener to this label provider
    * 
    * @param arg0
    *           the listener
    */
   public void addListener(ILabelProviderListener arg0) {
      // 添加监听器
      listeners.add(arg0);
   }

   /**
    * Called when this LabelProvider is being disposed
    */
   public void dispose() {
      // Dispose the images
      if (dir != null) dir.dispose();
      if (file != null) file.dispose();
   }

   /**
    * Returns whether changes to the specified property on the specified element
    * would affect the label for the element
    * 
    * @param arg0
    *           the element
    * @param arg1
    *           the property
    * @return boolean
    */
   public boolean isLabelProperty(Object arg0, String arg1) {
      return false;
   }

   /**
    * Removes the listener
    * 
    * @param arg0
    *           the listener to remove
    */
   public void removeListener(ILabelProviderListener arg0) {
      // 删除监听器
      listeners.remove(arg0);
   }

   public boolean deletefile(String delpath) throws FileNotFoundException, IOException {
      try {
         File file = new File(delpath);
         if (!file.isDirectory()) file.delete();
         else if (file.isDirectory()) {
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
               File delfile = new File(delpath + "\\" + filelist[i]);
               if (!delfile.isDirectory()) delfile.delete();
               else if (delfile.isDirectory()) deletefile(delpath + "\\" + filelist[i]);
            }
            file.delete();
         }
      }
      catch (FileNotFoundException e) {
         log.error("delete file exception:" + e.getMessage());
      }
      return true;
   }
}
