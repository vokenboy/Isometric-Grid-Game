package main.java.engine.graphics;

import main.java.game.character.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class IsometricGridPanel extends JPanel {
    private ArrayList<Tile> tiles = new ArrayList<>();
    private Point hoveredTile = null;
    private Point dragStartPoint = null;
    private Point cameraOffset = new Point(0, 0);
    private Player player;
    private PlayerRenderer playerRenderer;
    private Tile playerTile;

    public IsometricGridPanel() {
        setPreferredSize(new Dimension(800, 600));
        player = new Player(0, 0);
        playerTile = new Tile(0, 0);
        playerRenderer = new PlayerRenderer(player, playerTile);
        updateGrid();

        MouseAdapter mouseHandler = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    updatePlayerPosition(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    dragStartPoint = e.getPoint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    dragStartPoint = null;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                updateHoveredTile(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                updateCameraPosition(e);
            }
        };

        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateGrid();
            }
        });

        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                updatePlayerMovement(e);
            }
        });
    }

    private void updatePlayerPosition(MouseEvent e) {
        Point mousePt = new Point(e.getPoint().x - cameraOffset.x, e.getPoint().y - cameraOffset.y);
        for (Tile tile : tiles) {
            if (tile.createIsometricTile().contains(mousePt)) {
                player.setPosition(new Point(tile.getX(), tile.getY())); // Assuming Player class has setPosition method
                playerTile = tile;
                playerRenderer = new PlayerRenderer(player, playerTile);
                repaint();
                break;
            }
        }
    }

    private void updatePlayerMovement(KeyEvent e) {
        int dx = 0, dy = 0;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dy = -1;
                break;
            case KeyEvent.VK_DOWN:
                dy = 1;
                break;
            case KeyEvent.VK_LEFT:
                dx = -1;
                break;
            case KeyEvent.VK_RIGHT:
                dx = 1;
                break;
        }
        Point newPos = new Point(player.getPosition().x + dx, player.getPosition().y + dy);
        player.setPosition(newPos); // Update player position
        // Update the playerTile to the new player position
        for (Tile tile : tiles) {
            if (tile.getX() == newPos.x && tile.getY() == newPos.y) {
                playerTile = tile;
                playerRenderer = new PlayerRenderer(player, playerTile);
                repaint();
                break;
            }
        }
    }

    private void updateGrid() {
        tiles.clear();
        int rows = getHeight() / Tile.getTileHeight() + 2;
        int cols = getWidth() / (Tile.getTileWidth() / 2) + 2;

        for (int row = -rows / 2; row < rows / 2; row++) {
            for (int col = -cols / 2; col < cols / 2; col++) {
                tiles.add(new Tile(col, row));
            }
        }

        cameraOffset.x = getWidth() / 2;
        cameraOffset.y = getHeight() / 2;
        repaint();
    }

    private void updateCameraPosition(MouseEvent e) {
        if (dragStartPoint != null) {
            Point currentPoint = e.getPoint();
            cameraOffset.x += currentPoint.x - dragStartPoint.x;
            cameraOffset.y += currentPoint.y - dragStartPoint.y;
            dragStartPoint = currentPoint;
            repaint();
        }
    }

    private void updateHoveredTile(MouseEvent e) {
        Point mousePoint = new Point(e.getPoint().x - cameraOffset.x, e.getPoint().y - cameraOffset.y);
        hoveredTile = null;
        for (Tile tile : tiles) {
            if (tile.createIsometricTile().contains(mousePoint)) {
                hoveredTile = new Point(tile.getX(), tile.getY());
                repaint();
                return;
            }
        }
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
