/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;


public class QueryInterfaceNodeFigure extends BaseFigure {
    

	public QueryInterfaceNodeFigure() {
        Image img = SWTResourceManager.getImage(QueryInterfaceNodeFigure.class,  BASEPATH+"DLU.png");
        super.setImgFigure(img);
    }

   
}