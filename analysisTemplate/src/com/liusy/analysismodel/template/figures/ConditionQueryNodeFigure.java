package com.liusy.analysismodel.template.figures;

import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;

public class ConditionQueryNodeFigure extends BaseFigure {
	public ConditionQueryNodeFigure() {
		Image img = SWTResourceManager.getImage(ConditionQueryNodeFigure.class, BASEPATH+"SQL.png");
        super.setImgFigure(img);
	}
}
