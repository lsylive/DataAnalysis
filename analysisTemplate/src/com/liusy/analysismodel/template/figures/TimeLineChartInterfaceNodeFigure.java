/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class TimeLineChartInterfaceNodeFigure extends BaseFigure {

	public TimeLineChartInterfaceNodeFigure() {
		Image img = SWTResourceManager.getImage(TimeLineChartInterfaceNodeFigure.class, BASEPATH + "LIP.png");
		super.setImgFigure(img);
	}

}