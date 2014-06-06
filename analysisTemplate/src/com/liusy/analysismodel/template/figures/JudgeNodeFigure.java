package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class JudgeNodeFigure extends BaseFigure {
	public JudgeNodeFigure() {
		Image img = SWTResourceManager.getImage(JudgeNodeFigure.class, BASEPATH+"JDG.png");
        super.setImgFigure(img);
	}
}
