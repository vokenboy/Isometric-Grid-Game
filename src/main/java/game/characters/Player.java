package main.java.game.characters;
import java.awt.*;

public class Player {
    public Point position;

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