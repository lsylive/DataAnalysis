package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;

import com.liusy.analysismodel.template.figures.EndNodeFigure;

public class EndNodePart extends NodePart {
    //创建模型对应的视图
    protected IFigure createFigure() {
        return new EndNodeFigure();
    }
}
