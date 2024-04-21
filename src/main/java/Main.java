package main.java;

import main.java.engine.graphics.IsometricGridPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Isometric Game");
            IsometricGridPanel gridPanel = new IsometricGridPanel();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(gridPanel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
