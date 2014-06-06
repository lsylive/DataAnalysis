package com.liusy.analysismodel.template.figures;

import org.eclipse.draw2d.BendpointConnectionRouter;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.FlowLayout;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;

public class ConnectionFigure extends Figure {
   private Label lab;

   public ConnectionFigure(Label lab) {

      super();
      FlowLayout layoutThis = new FlowLayout();
      layoutThis.setMinorAlignment(FlowLayout.ALIGN_CENTER);
      layoutThis.setMajorAlignment(FlowLayout.ALIGN_CENTER);
      layoutThis.setMajorSpacing(15);
      layoutThis.setMinorSpacing(15);
      layoutThis.setHorizontal(false);
      this.setLayoutManager(layoutThis);
      PolylineConnection conn = new PolylineConnection();
      conn.setTargetDecoration(new PolygonDecoration());
      conn.setConnectionRouter(new BendpointConnectionRouter());
      conn.setForegroundColor(ColorConstants.blue);
      this.lab = lab;
      this.add(lab);
      this.add(conn);
   }

   public Label getLab() {
      return lab;
   }

   public void setLab(Label lab) {
      this.lab = lab;
   }
}
