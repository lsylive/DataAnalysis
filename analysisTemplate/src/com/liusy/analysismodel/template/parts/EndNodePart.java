package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.IFigure;

import com.liusy.analysismodel.template.figures.EndNodeFigure;

public class EndNodePart extends NodePart {
    //����ģ�Ͷ�Ӧ����ͼ
    protected IFigure createFigure() {
        return new EndNodeFigure();
    }
}
