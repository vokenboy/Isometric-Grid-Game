package main.java.engine.input;

import main.java.engine.graphics.IsometricGridPanel;
import java.awt.event.*;

public class MouseHandler extends MouseAdapter {
    private final IsometricGridPanel panel;

    public MouseHandler(IsometricGridPanel panel) {
        this.panel = panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            panel.getGridController().updatePlayerPosition(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            panel.getGridController().setDragStartPoint(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            panel.getGridController().setDragStartPoint(null);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        panel.getGridController().updateHoveredTile(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        panel.getGridController().updateCameraPosition(e);
    }
}