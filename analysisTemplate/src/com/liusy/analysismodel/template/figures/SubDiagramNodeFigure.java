package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class SubDiagramNodeFigure extends BaseFigure {
	public SubDiagramNodeFigure() {
		Image img = SWTResourceManager.getImage(SubDiagramNodeFigure.class, BASEPATH+"MBL.png");
        super.setImgFigure(img);
	}
}
