package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;

import com.liusy.analysismodel.template.figures.StartNodeFigure;

public class StartNodePart extends NodePart {
    //创建模型对应的视图
    protected IFigure createFigure() {
        return new StartNodeFigure();
    }

}
