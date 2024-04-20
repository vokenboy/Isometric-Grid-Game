package main.java.engine.graphics;

import java.awt.*;

public class Tile {
    private static final int TILE_WIDTH = 64;
    private static final int TILE_HEIGHT = 32;
    private final int x, y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int getTileWidth() {
        return TILE_WIDTH;
    }

    public static int getTileHeight() {
        return TILE_HEIGHT;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point getCenter() {
        int centerX = (x - y) * TILE_WIDTH / 2 + TILE_WIDTH / 2;
        int centerY = (x + y) * TILE_HEIGHT / 2 + TILE_HEIGHT / 2;
        return new Point(centerX, centerY);
    }

    public Polygon createIsometricTile() {
        int isoX = (x - y) * TILE_WIDTH / 2;
        int isoY = (x + y) * TILE_HEIGHT / 2;

        Polygon tile = new Polygon();
        tile.addPoint(isoX, isoY);
        tile.addPoint(isoX + TILE_WIDTH / 2, isoY + TILE_HEIGHT / 2);
        tile.addPoint(isoX, isoY + TILE_HEIGHT);
        tile.addPoint(isoX - TILE_WIDTH / 2, isoY + TILE_HEIGHT / 2);

        return tile;
    }
}
