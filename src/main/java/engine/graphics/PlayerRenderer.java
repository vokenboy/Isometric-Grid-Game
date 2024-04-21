package main.java.engine.graphics;

import main.java.game.characters.Player;

import java.awt.*;

public class PlayerRenderer {
    private final Tile tile;

    public PlayerRenderer(Tile tile) {
        this.tile = tile;
    }

    public void draw(Graphics g) {
        Point position = tile.getCenter();
        g.setColor(Color.RED);
        g.fillOval(position.x - 47, position.y - 50, 30, 60);
    }
}
