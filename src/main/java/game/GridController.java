package main.java.game;

import main.java.engine.graphics.IsometricGridPanel;
import main.java.engine.graphics.Tile;
import main.java.game.characters.Player;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class GridController {
    private final ArrayList<Tile> tiles;
    private Point dragStartPoint = null;
    private final Point cameraOffset;
    private final Player player;
    private IsometricGridPanel panel;

    public GridController(ArrayList<Tile> tiles, Point cameraOffset, Player player, IsometricGridPanel panel) {
        this.tiles = tiles;
        this.cameraOffset = cameraOffset;
        this.player = player;
        this.panel = panel;
    }
    public void updatePlayerPosition(MouseEvent e) {
        Point mousePt = new Point(e.getPoint().x - cameraOffset.x, e.getPoint().y - cameraOffset.y);
        for (Tile tile : tiles) {
            if (tile.createIsometricTile().contains(mousePt)) {
                player.setPosition(new Point(tile.getX(), tile.getY()));
                panel.repaint();
                break;
            }
        }
    }

    public void setDragStartPoint(Point point) {
        this.dragStartPoint = point;
    }
    public void updateHoveredTile(MouseEvent e) {
        Point mousePoint = new Point(e.getPoint().x - cameraOffset.x, e.getPoint().y - cameraOffset.y);
        Point newHoveredTile = null;
        for (Tile tile : tiles) {
            if (tile.createIsometricTile().contains(mousePoint)) {
                newHoveredTile = new Point(tile.getX(), tile.getY());
                break;
            }
        }
        panel.setHoveredTile(newHoveredTile);
    }
    public void updateCameraPosition(MouseEvent e) {
        if (dragStartPoint != null) {
            Point currentPoint = e.getPoint();
            cameraOffset.x += currentPoint.x - dragStartPoint.x;
            cameraOffset.y += currentPoint.y - dragStartPoint.y;
            dragStartPoint = currentPoint;
            panel.repaint();
        }
    }
    public void updateGrid() {
        tiles.clear();
        int rows = panel.getHeight() / Tile.getTileHeight() + 2;
        int cols = panel.getWidth() / (Tile.getTileWidth() / 2) + 2;

        for (int row = -rows / 2; row < rows / 2; row++) {
            for (int col = -cols / 2; col < cols / 2; col++) {
                tiles.add(new Tile(col, row));
            }
        }

        cameraOffset.x = panel.getWidth() / 2;
        cameraOffset.y = panel.getHeight() / 2;
        panel.repaint();
    }
}