package main.java.engine.graphics;

import main.java.game.character.Player;

import java.awt.*;

public class PlayerRenderer {
    private Player player;
    private Tile tile;

    public PlayerRenderer(Player player, Tile tile) {
        this.player = player;
        this.tile = tile;
    }

    public void draw(Graphics g) {
        Point position = tile.getCenter();
        g.setColor(Color.RED);
        g.fillOval(position.x - 47, position.y - 50, 30, 60);
    }
}
