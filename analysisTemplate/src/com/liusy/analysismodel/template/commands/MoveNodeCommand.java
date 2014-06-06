/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.commands;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.liusy.analysis.template.model.node.INode;
import com.liusy.analysis.template.model.util.StringUtil;
/**
 * <p>Title: Eclipse Plugin Development</p>
 * <p>Description: Free download</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Free</p>
 * @author gan.shu.man
 * @version 1.0
 */

public class MoveNodeCommand extends Command {
	//�����Ӧ��ģ��
    private INode node;

    //ԭʼλ��
    private Point oldPos;

    //��λ��
    private Point newPos;

    //������λ��
    public void setLocation(Point p) {
        this.newPos = p;
    }

    //���������Ӧ��ģ��
    public void setNode(INode node) {
        this.node = node;
    }

    //ִ������
    public void execute() {
    	//����ģ��ԭ����λ��
        oldPos = this.node.getLocation();
        node.setLocation(newPos);
    }

    public String getLabel() {
        return StringUtil.moveNodeCmd;
    }

    //�ظ���һ����
    public void redo() {
        this.node.setLocation(newPos);
    }

    //����������λ�õĲ���
    public void undo() {
        this.node.setLocation(oldPos);
    }
}