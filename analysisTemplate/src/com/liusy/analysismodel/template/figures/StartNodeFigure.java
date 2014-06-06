/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;


public class StartNodeFigure extends BaseFigure {

	public StartNodeFigure() {
        Image img = SWTResourceManager.getImage(StartNodeFigure.class, BASEPATH+"start.png");
        super.setImgFigure(img);
    }

}