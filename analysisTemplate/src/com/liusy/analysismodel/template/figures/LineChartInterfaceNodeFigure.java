/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;


public class LineChartInterfaceNodeFigure extends BaseFigure {
    

	public LineChartInterfaceNodeFigure() {
        Image img = SWTResourceManager.getImage(LineChartInterfaceNodeFigure.class,  BASEPATH+"UPD.png");
        super.setImgFigure(img);
    }

   
}