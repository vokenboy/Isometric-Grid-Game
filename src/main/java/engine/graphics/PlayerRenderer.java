package main.java.engine.graphics;

import main.java.game.characters.Player;
import java.awt.*;

public class PlayerRenderer {
    public Player Player;

    public PlayerRenderer(Player player) {
        this.Player = player;
    }

    public void draw(Graphics g) {
        Point gridPosition = Player.getPosition();
        Point screenPosition = convertToScreenCoordinates(gridPosition);
        g.setColor(Color.RED);
        g.fillOval(screenPosition.x - 47, screenPosition.y - 50, 30, 60);
    }

    private Point convertToScreenCoordinates(Point gridPosition) {
        int screenX = gridPosition.x * Tile.getTileWidth() / 2 - gridPosition.y * Tile.getTileWidth() / 2;
        int screenY = gridPosition.y * Tile.getTileHeight() / 2 + gridPosition.x * Tile.getTileHeight() / 2;
        screenX += 30;
        screenY += 15;
        return new Point(screenX, screenY);
    }

}
