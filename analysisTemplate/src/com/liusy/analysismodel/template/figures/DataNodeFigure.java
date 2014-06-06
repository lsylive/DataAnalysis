package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class DataNodeFigure extends BaseFigure {
	public DataNodeFigure() {
		Image img = SWTResourceManager.getImage(DataNodeFigure.class, BASEPATH+"PRC.png");
        super.setImgFigure(img);
	}
}
