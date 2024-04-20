package main.java.game.character;

import java.awt.*;

public class Player {
    private Point position;

    public Player(int x, int y) {
        this.position = new Point(x, y);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }
}