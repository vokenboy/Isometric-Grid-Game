package main.java;

import main.java.engine.graphics.IsometricGridPanel;
import main.java.game.GameController;
import main.java.game.character.Player;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Isometric Game");
            Player player = new Player(0, 0); // Assume Player has a constructor
            IsometricGridPanel gridPanel = new IsometricGridPanel();
//            GameController gameController = new GameController(gridPanel, player);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gridPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

//            Timer gameTimer = new Timer(100, e -> gameController.gameUpdate());
//            gameTimer.start();
        });
    }
}
