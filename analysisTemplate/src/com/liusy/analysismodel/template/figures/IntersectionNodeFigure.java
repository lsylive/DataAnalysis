/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.figures;

import org.eclipse.draw2d.KeyEvent;
import org.eclipse.draw2d.KeyListener;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.swt.graphics.Image;

import com.swtdesigner.SWTResourceManager;


public class IntersectionNodeFigure extends BaseFigure {
	public IntersectionNodeFigure() {
//        this.rectangleFigure = new RectangleFigure();
        Image img = SWTResourceManager.getImage(IntersectionNodeFigure.class, BASEPATH+"MRG.png");
        super.setImgFigure(img);
        this.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent ke) {
				// TODO Auto-generated method stub
				
			}

			public void keyReleased(KeyEvent ke) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        this.addMouseListener(new MouseListener(){

			public void mouseDoubleClicked(MouseEvent me) {
				// TODO Auto-generated method stub
				
			}

			public void mousePressed(MouseEvent me) {
				// TODO Auto-generated method stub
				if (me.button == 3) {
					System.out.println("right btton.");
				}
			}

			public void mouseReleased(MouseEvent me) {
				// TODO Auto-generated method stub
				
			}
        	
        	
        });
        
    }
}