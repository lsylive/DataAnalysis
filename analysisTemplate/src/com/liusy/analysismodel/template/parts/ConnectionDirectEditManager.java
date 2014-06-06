/*
 * Created on 2005-1-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.liusy.analysismodel.template.parts;

import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Text;

import com.liusy.analysis.template.model.connection.Connection;


public class ConnectionDirectEditManager extends DirectEditManager {

    Font scaledFont;

    protected VerifyListener verifyListener;

    protected Figure nodeFigure;

    /**
     * Creates a new ActivityDirectEditManager with the given attributes.
     * @param source the source EditPart
     * @param editorType type of editor
     * @param locator the CellEditorLocator
     */
    public ConnectionDirectEditManager(GraphicalEditPart source, Class editorType, CellEditorLocator locator) {
        super(source, editorType, locator);
        //    	this.nodeFigure = nodeFigure;
        this.nodeFigure = (Figure) source.getFigure();
    }

    /**
     * @see org.eclipse.gef.tools.DirectEditManager#initCellEditor()
     */
    protected void initCellEditor() {
        Text text = (Text) getCellEditor().getControl();
        getCellEditor().setValue(((Connection) getEditPart().getModel()).getName());
        IFigure figure = ((GraphicalEditPart) getEditPart()).getFigure();
        scaledFont = figure.getFont();
        FontData data = scaledFont.getFontData()[0];
        Dimension fontSize = new Dimension(0, data.getHeight());
        nodeFigure.translateToAbsolute(fontSize);
        data.setHeight(fontSize.height);
        scaledFont = new Font(null, data);

        text.setFont(scaledFont);
        text.selectAll();
    }

    /**
     * @see org.eclipse.gef.tools.DirectEditManager#unhookListeners()
     */
//    protected void unhookListeners() {
//        super.unhookListeners();
//        Text text = (Text) getCellEditor().getControl();
//        text.removeVerifyListener(verifyListener);
//        verifyListener = null;
//    }
}