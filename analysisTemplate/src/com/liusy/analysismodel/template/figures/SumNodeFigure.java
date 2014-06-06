package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class SumNodeFigure extends BaseFigure {
	public SumNodeFigure() {
		Image img = SWTResourceManager.getImage(SumNodeFigure.class, BASEPATH+"AGG.png");
        super.setImgFigure(img);
	}
}
