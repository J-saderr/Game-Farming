package main;

import javax.swing.*;
import Entity.Player;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Better Farming");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); //cause window to size to fit the preferred size of gamePanel

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
        gamePanel.setupGame();
    }

}
