/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class PathSearchNodeFigure extends BaseFigure {
    

	public PathSearchNodeFigure() {
        Image img = SWTResourceManager.getImage(PathSearchNodeFigure.class, BASEPATH+"MAP.png");
        super.setImgFigure(img);
    }

   
}