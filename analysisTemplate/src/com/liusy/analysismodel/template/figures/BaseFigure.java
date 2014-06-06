package com.liusy.analysismodel.template.figures;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.ImageFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.ToolbarLayout;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class BaseFigure extends Figure {

   public static String BASEPATH = "/com/liusy/analysismodel/template/image/png32/";

   private String       name;

   public String getName() {
      return name;
   }

   private Label label = new Label();

   public Label getLabel() {
      return label;
   }

   private ImageFigure imgFigure = null;

   public ImageFigure getImgFigure() {
      return imgFigure;
   }

   public void setImgFigure(Image img) {
      this.imgFigure.setImage(img);
   }

   public BaseFigure() {
      ToolbarLayout layoutThis = new ToolbarLayout();
      layoutThis.setSpacing(5);
      this.setLayoutManager(layoutThis);
      
      Image img = SWTResourceManager.getImage(BaseFigure.class, BASEPATH+"TRN.png");
      imgFigure = new ImageFigure(img);
      this.add(imgFigure);
      this.add(label);
      this.setOpaque(true);
   }

   public String getText() {
      return this.label.getText();
   }

   public Rectangle getTextBounds() {
      return this.label.getTextBounds();
   }

   public void setName(String name) {
      this.name = name;
      this.label.setText(name);
      this.repaint();
   }

   //------------------------------------------------------------------------
   // Overridden methods from Figure

   public void setBounds(Rectangle rect) {
      super.setBounds(rect);

      //        this.rectangleFigure.setBounds(rect);
      //        this.label.setBounds(rect);
   }

}
