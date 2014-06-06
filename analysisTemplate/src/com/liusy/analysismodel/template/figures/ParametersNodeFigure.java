package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class ParametersNodeFigure extends BaseFigure {
	public ParametersNodeFigure() {
		Image img = SWTResourceManager.getImage(ParametersNodeFigure.class, BASEPATH+"RGE.png");
        super.setImgFigure(img);
	}
}
