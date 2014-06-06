package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class MultiTableDataNodeFigure extends BaseFigure {
	public MultiTableDataNodeFigure() {
		Image img = SWTResourceManager.getImage(MultiTableDataNodeFigure.class, BASEPATH+"DBJ.png");
        super.setImgFigure(img);
	}
}
