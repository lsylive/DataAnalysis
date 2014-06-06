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
 * <p>Title: Eclipse RCP Development</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Thunisoft</p>
 * @author xing
 * @version 1.0
 */

public class KeyMoveNodeCommand extends Command {
	//
	private String arrayKey = "";
	//�����Ӧ��ģ��
    private INode node;

    //ԭʼλ��
    private Point oldPos;

    //��λ��
    private Point newPos;

    //������λ��
//    public void setLocation(Point p) {
//        this.newPos = p;
//    }

    //���������Ӧ��ģ��
    public void setNode(INode node) {
        this.node = node;
    }

    //ִ������
    public void execute() {
    	//����ģ��ԭ����λ��
        oldPos = this.node.getLocation();
        newPos = oldPos.getCopy();
        if ("LEFT".equals(arrayKey)) {
      	  newPos.x = newPos.x - 5;
        } else if ("RIGHT".equals(arrayKey)) {
      	  newPos.x = newPos.x + 5;
        } else if ("UP".equals(arrayKey)) {
      	  newPos.y = newPos.y - 5;
        } else if ("DOWN".equals(arrayKey)) {
      	  newPos.y = newPos.y + 5;
        }
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

	public void setArrayKey(String arrayKey) {
		this.arrayKey = arrayKey;
	}
}