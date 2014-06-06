package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class SyncContrastNodeFigure extends BaseFigure {
	public SyncContrastNodeFigure() {
		Image img = SWTResourceManager.getImage(SyncContrastNodeFigure.class, BASEPATH+"BFC.png");
        super.setImgFigure(img);
	}
}
