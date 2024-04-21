package main.java.engine.graphics;

import main.java.engine.input.MouseHandler;
import main.java.game.GridController;
import main.java.game.characters.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class IsometricGridPanel extends JPanel {
    private final ArrayList<Tile> tiles = new ArrayList<>();
    private Point hoveredTile = null;
    private final Point cameraOffset = new Point(0, 0);
    private final PlayerRenderer playerRenderer;
    private final GridController gridController;

    public IsometricGridPanel() {
        setPreferredSize(new Dimension(800, 600));
        Player player = new Player(0, 0);
        Tile playerTile = new Tile(0, 0);
        playerRenderer = new PlayerRenderer(playerTile, player);

        gridController = new GridController(tiles, cameraOffset, player, this);

        MouseHandler mouseHandler = new MouseHandler(this);
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                gridController.updateGrid();
                repaint();
            }
        });
    }

    public GridController getGridController() {
        return gridController;
    }

    public void setHoveredTile(Point hoveredTile) {
        this.hoveredTile = hoveredTile;
        repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(cameraOffset.x, cameraOffset.y);

        for (Tile tile : tiles) {
            Polygon poly = tile.createIsometricTile();
            if (hoveredTile != null && tile.getX() == hoveredTile.x && tile.getY() == hoveredTile.y) {
                g.setColor(Color.YELLOW);
                g.fillPolygon(poly);
            } else {
                g.setColor(Color.BLACK);
                g.drawPolygon(poly);
            }
        }

        playerRenderer.draw(g);
    }
}
