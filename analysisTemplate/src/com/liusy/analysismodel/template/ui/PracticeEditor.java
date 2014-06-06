/*
 * Created on 2005-1-24 TODO To change the template for this generated file go
 * to Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.ui;

import java.io.File;
import java.io.FileInputStream;
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
import java.sql.Statement;
import java.util.EventObject;

import oracle.sql.BLOB;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.commands.CommandStackListener;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import com.liusy.analysismodel.Activator;
import com.liusy.analysismodel.template.TemplateView;
import com.liusy.analysismodel.template.actions.KeyMoveNodeAction;
import com.liusy.analysismodel.template.actions.SchemaContextMenuProvider;
import com.liusy.analysismodel.template.actions.SelfCommandStack;
import com.liusy.analysismodel.template.actions.DeleteAction;
import com.liusy.analysismodel.template.actions.RedoAction;
import com.liusy.analysismodel.template.actions.SelfSaveAction;
import com.liusy.analysismodel.template.actions.UndoAction;
import com.liusy.analysis.template.model.Diagram;
import com.liusy.analysismodel.template.parts.PartFactory;
import com.liusy.analysismodel.template.parts.TreePartFactory;
import com.liusy.analysismodel.template.tools.PaletteFactory;
import com.liusy.analysismodel.util.DbConnectionManager;

public class PracticeEditor extends GraphicalEditorWithPalette {

   private Diagram            diagram = new Diagram();

   private PaletteRoot        paletteRoot;

   private Action             saveAction;
   private Action             closeAction;
   private KeyHandler sharedKeyHandler;
   private static Logger      log     = Logger.getLogger(PracticeEditor.class);
   public static final String ID      = "DataAdminPlatform.PracticeEditor";

   public Diagram getDiagram() {
      return this.diagram;
   }

   public PracticeEditor() {
	   SelfCommandStack stack = new SelfCommandStack();
      LiusyEditDomain editDomain = new LiusyEditDomain(this);
      CommandStackListener commandStackListener = new CommandStackListener() {
			public void commandStackChanged(EventObject event) {
				// TODO Auto-generated method stub
				 firePropertyChange(PROP_DIRTY);
			}
      };
      stack.addCommandStackListener(commandStackListener);
      editDomain.setCommandStack(stack);
      setEditDomain(editDomain);
   }

   protected void configureGraphicalViewer() {
      super.configureGraphicalViewer();
      getGraphicalViewer().setRootEditPart(new ScalableFreeformRootEditPart());
      getGraphicalViewer().setEditPartFactory(new PartFactory());
      //        getGraphicalViewer().
      
//      getGraphicalViewer().setKeyHandler(new GraphicalViewerKeyHandler(
//      		getGraphicalViewer()).setParent(keyHandler));
      
      

   }

   protected void initializeGraphicalViewer() {
      getGraphicalViewer().setContents(this.diagram);
      ActionRegistry actionRegistry = getActionRegistry();
      //		actionRegistry.removeAction(actionRegistry.getAction(key));
      IAction action = new UndoAction(this);
      actionRegistry.registerAction(action);
      super.getStackActions().add(action.getId());
      IAction redoAction = new RedoAction(this);
      actionRegistry.registerAction(redoAction);
      super.getStackActions().add(redoAction.getId());
      IAction deleteAction = new DeleteAction(this);
      actionRegistry.registerAction(deleteAction);
      super.getSelectionActions().add(deleteAction.getId());
      IAction keyRightMoveAction = new KeyMoveNodeAction(this,"RIGHT");
      actionRegistry.registerAction(keyRightMoveAction);
      super.getSelectionActions().add(keyRightMoveAction.getId());
      IAction keyLeftMoveAction = new KeyMoveNodeAction(this,"LEFT");
      actionRegistry.registerAction(keyLeftMoveAction);
      super.getSelectionActions().add(keyLeftMoveAction.getId());
      IAction keyUpMoveAction = new KeyMoveNodeAction(this,"UP");
      actionRegistry.registerAction(keyUpMoveAction);
      super.getSelectionActions().add(keyUpMoveAction.getId());
      IAction keyDownMoveAction = new KeyMoveNodeAction(this,"DOWN");
      actionRegistry.registerAction(keyDownMoveAction);
      super.getSelectionActions().add(keyDownMoveAction.getId());
      
      ContextMenuProvider provider = new SchemaContextMenuProvider(getGraphicalViewer(), actionRegistry);
      getGraphicalViewer().setContextMenu(provider);
      getSite().registerContextMenu("com.realpersist.gef.editor.contextmenu", provider, getGraphicalViewer());
      //		getSite().getPage().getPerspective().
      
      //
      KeyHandler keyHandler = new KeyHandler();
      keyHandler.put(
      KeyStroke.getPressed(SWT.DEL, 127, 0),deleteAction);
      keyHandler.put(
      KeyStroke.getPressed(SWT.ARROW_RIGHT, 0),
      getActionRegistry().getAction(keyRightMoveAction.getId()));
      keyHandler.put(
            KeyStroke.getPressed(SWT.ARROW_LEFT, 0),
            getActionRegistry().getAction(keyLeftMoveAction.getId()));
      keyHandler.put(
            KeyStroke.getPressed(SWT.ARROW_UP, 0),
            getActionRegistry().getAction(keyUpMoveAction.getId()));
      keyHandler.put(
            KeyStroke.getPressed(SWT.ARROW_DOWN, 0),
            getActionRegistry().getAction(keyDownMoveAction.getId()));
      //ctrl+z
      keyHandler.put(KeyStroke.getPressed((char) 0x1a, 122, SWT.CTRL), getActionRegistry().getAction(
            ActionFactory.UNDO.getId()));
      //ctrl+y
      keyHandler.put(KeyStroke.getPressed((char) 0x19, 121, SWT.CTRL), getActionRegistry().getAction(
            ActionFactory.REDO.getId()));
      //ctrl+s
      keyHandler.put(KeyStroke.getPressed((char) 0x13, 115, SWT.CTRL), new SelfSaveAction(this));
      //ctrl+a
//      keyHandler.put(KeyStroke.getPressed((char) 0x01,97,SWT.CTRL), new ThunisoftSaveAction(this));
      getGraphicalViewer().setKeyHandler(keyHandler);
      
   }

   public void doSave(IProgressMonitor monitor) {

      FileOutputStream f = null;
      ObjectOutputStream objectOut = null;
      //
      boolean treeRefleshFlg = false;
      boolean deleteOldFileFlg = false;
      
      File file = null;
      String oldFilePaht = "";
      int diagramId = diagram.getId();
      try {

         //写文件到数据库中
         //	    	Clob data = objectOut.
         Connection con = null;
         Statement stat = null;
         Statement stat1 = null;
         ResultSet resultSet = null;
         ResultSet resultSet0 = null;
         PreparedStatement pstmt = null;
         PreparedStatement pstmt1 = null;
         PreparedStatement pstmt2 = null;
         PreparedStatement pstmt3 = null;
         BLOB blob = null;
         con = DbConnectionManager.getConnection();
         boolean defaultCommit = con.getAutoCommit();
         //
         String sql1 = "select name from t_analysis_diagram where id=" + String.valueOf(diagramId);
         stat1 = con.createStatement();
         resultSet0 = stat1.executeQuery(sql1);
         while (resultSet0.next()) {
            String oldName = resultSet0.getString("name");
            URI uriFile = ((IURIEditorInput) getEditorInput()).getURI();
            oldFilePaht = uriFile.getPath();
            if (!oldName.equals(diagram.getName())) {
            	treeRefleshFlg = true;
            	//
            	int index = uriFile.getPath().lastIndexOf("/");
            	file = new File(uriFile.getPath().substring(0, index)+"/"+diagram.getName());
            	deleteOldFileFlg = true;
            } else {
            	file = new File(uriFile.getPath());
            }
            
            f = new FileOutputStream(file);
            objectOut = new ObjectOutputStream(f);
            objectOut.writeObject(diagram);
            objectOut.writeObject(null);
            //			objectOut.close();
         }
         //	 	    String sql = "update t_analysis_diagram set name ="+"'+diagram.getName()+'"+" type="
         //	 	    +"'+String.valueOf(diagram.getType())+'"+ " description="+
         //	 	    "'+diagram.getDescription()+'"+" where id="+ "'+String.valueOf(diagramId)+'";
         try {
            //获取数据库最新的主键号
            if (diagramId == 0) {
               String sql = "select max(id) as id from t_analysis_diagram";
               stat = con.createStatement();
               resultSet = stat.executeQuery(sql);
               while (resultSet.next()) {
                  diagramId = resultSet.getInt("id") + 1;
               }
               //那图对象插进数据库
               pstmt3 = con.prepareStatement("insert into t_analysis_diagram(id,name,type,description,body) values(?,?,?,?,EMPTY_BLOB())");
               pstmt3.setInt(1, diagramId);
               pstmt3.setString(2, diagram.getName());
               pstmt3.setInt(3, diagram.getType());
               pstmt3.setString(4, diagram.getDescription());
               pstmt3.executeUpdate();
               pstmt3.close();
            }
            else {
               // 写diagrm对象到数据库中
               pstmt = con.prepareStatement("update t_analysis_diagram set name=? , type=?, description=?, visiable=? where id= ?");
               pstmt.setString(1, diagram.getName());
               pstmt.setInt(2, diagram.getType());
               pstmt.setString(3, diagram.getDescription());
               pstmt.setString(4, diagram.getVisiable());
               pstmt.setInt(5, diagramId);
               pstmt.executeUpdate();
               pstmt.close();
            }
            /* 查询CLOB对象并锁定 */
            pstmt1 = con.prepareStatement("select body from t_analysis_diagram where id =? for update");
            pstmt1.setInt(1, diagramId);
            oracle.jdbc.OracleResultSet rs = (oracle.jdbc.OracleResultSet) pstmt1.executeQuery();
            while (rs.next()) {
               /* 获取此CLOB对象 */
               blob = (BLOB) rs.getBlob("body");
            }
            /* 进行覆盖式修改 */
            FileInputStream fin = new FileInputStream(file);
            if (blob != null) {
               OutputStream out = blob.getBinaryOutputStream();
               byte[] data = new byte[(int) fin.available()];
               fin.read(data);
               out.write(data);
               fin.close();
               out.close();
               pstmt2 = con.prepareStatement("update t_analysis_diagram set body =? where id=?");
               pstmt2.setBlob(1, blob);
               pstmt2.setInt(2, diagramId);
               pstmt2.executeUpdate();
               pstmt2.close();
            }
            /* 正式提交 */
            con.commit();
         }
         catch (Exception e) {
            con.rollback();
            log.error(e);
         }
         finally {
            con.setAutoCommit(defaultCommit);
            DbConnectionManager.closeStatement(stat);
            //				con.commit();
         }
         //关闭流
         f.close();
         objectOut.close();
         //	    	s.flush();
      }
      catch (Exception e) {
         try {
            f.close();
         }
         catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         try {
            objectOut.close();
         }
         catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
         }
         e.printStackTrace();

      }
      getCommandStack().markSaveLocation();
      //
      if (treeRefleshFlg) {

      	IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
         //关闭编辑器
         IEditorPart[] parts = page.getEditors();
         for (int i = 0; i < parts.length; i++) {
            IEditorPart part = parts[i];
            IURIEditorInput input = (IURIEditorInput) part.getEditorInput();
            URI editorUriFile = input.getURI();
            String filePath = editorUriFile.toString().substring(5);
            int idCloseEditor = 0;
            try {
					idCloseEditor = getDiagramIdByPath(filePath);
					if (diagramId == idCloseEditor) {
						page.closeEditor(part, false);
						break;
					}
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
         }
         try {
				IDE.openEditor(page, file.toURI(), "DataAdminPlatform.PracticeEditor", true);
			}
			catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (deleteOldFileFlg) {
				File oldFile = new File(oldFilePaht);
				oldFile.delete();
			}
      	IWorkbenchPage workbenchPage = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage();
      	TemplateView view = (TemplateView) workbenchPage.findView("DataAdminPlatform.view2");
      	view.getRoot().clear();
      	view.createDictory(view.getInitTreePath());
      	org.eclipse.jface.viewers.TreeViewer treeViewer = view.getTreeViewer();
      	treeViewer.refresh();
      }
   }

   public void doSaveAs() {
   }

   public boolean isDirty() {
      return getCommandStack().isDirty();
   }

   public boolean isSaveAsAllowed() {
      return true;
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
   protected void setInput(IEditorInput input) {

      super.setInput(input);
      //***********************
      URI uriFile = ((IURIEditorInput) input).getURI();
      File file = new File(uriFile.getPath());
      if (file.exists()) {
         try {
            setPartName(file.getName());
            //				setPartProperty(IWorkbenchPartConstants.PROP_TITLE,file.getName());
            //				setContentDescription(file.getName());
//            System.out.println(file.getName());
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            Object obj = ois.readObject();
            if (obj != null) {
               diagram = (Diagram) obj;
            }
            is.close();
            ois.close();
         }
         catch (Exception e) {
            e.printStackTrace();
            diagram = new Diagram();
         }
      }
      //********************
   }

   public Object getAdapter(Class type) {
      if (type == IContentOutlinePage.class) return new OutlinePage();
      return super.getAdapter(type);
   }

   protected PaletteRoot getPaletteRoot() {
      if (this.paletteRoot == null) {
         this.paletteRoot = PaletteFactory.createPalette();
      }
      return this.paletteRoot;
   }

   protected void initializePaletteViewer() {
      super.initializePaletteViewer();
      //		setPartName("sff");
      //		setTitle("fsf");
      //		setDefaultPartName();
   }

   //在此方法中创建Editor中的界面组件
   public void createPartControl(Composite parent) {
      super.createPartControl(parent);
//      System.out.println("createPartControl");
      //      		closeAction = new Action("close",ImageDescriptor.createFromImage(
      //    				SWTResourceManager.getImage(ApplayLogView.class, "/com/thunisoft/dataplatform/image/delete.png"))) {
      //    			public void run() {
      //    				System.out.println("close");
      //                    IWorkbenchPage page = PlatformUI.getWorkbench()
      //                    .getActiveWorkbenchWindow().getActivePage();
      //                    page.closeEditor(page.getActiveEditor(), true);
      //    		}
      //    		};
      //    		saveAction = new Action("save",ImageDescriptor.createFromImage(
      //    				SWTResourceManager.getImage(ApplayLogView.class, "/com/thunisoft/dataplatform/image/save.png"))) {
      //    			public void run() {
      //                  System.out.println("save");
      //                  IWorkbenchPage page = PlatformUI.getWorkbench()
      //                  .getActiveWorkbenchWindow().getActivePage();
      //                  page.getActiveEditor().doSave(null);
      //    		}
      //    		};
      //    		IActionBars bars = this.getEditorSite().getActionBars();
      //    		IToolBarManager iToolBar = bars.getToolBarManager();
      //    		iToolBar.add(saveAction);
      //    		iToolBar.add(closeAction);
   }

   class OutlinePage extends ContentOutlinePage {

      private Control outline;

      public OutlinePage() {
         //创建大纲视图的EditPart Viewer
         super(new TreeViewer());
      }

      public void init(IPageSite pageSite) {
         super.init(pageSite);
         //            ActionRegistry registry = getActionRegistry();
         //            IActionBars bars = pageSite.getActionBars();
         //            String id = IWorkbenchActionConstants.UNDO;
         //            bars.setGlobalActionHandler(id, registry.getAction(id));
         //            id = IWorkbenchActionConstants.REDO;
         //            bars.setGlobalActionHandler(id, registry.getAction(id));
         //            id = IWorkbenchActionConstants.DELETE;
         //            bars.setGlobalActionHandler(id, registry.getAction(id));
         //            bars.updateActionBars();
      }

      public void createControl(Composite parent) {
         //创建大纲
         outline = getViewer().createControl(parent);
         getSelectionSynchronizer().addViewer(getViewer());
         getViewer().setEditDomain(getEditDomain());
         //添加大纲EditPart Factory
         getViewer().setEditPartFactory(new TreePartFactory());
         //设置大纲的内容
         getViewer().setContents(getDiagram());
      }

      public Control getControl() {
         return outline;
      }

      public void dispose() {
         getSelectionSynchronizer().removeViewer(getViewer());
         super.dispose();
      }
   }
   
}
