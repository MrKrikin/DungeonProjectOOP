package dungeongame.ui;

import dungeongame.game.Game;

import javax.swing.*;
import java.awt.*;

public class GameWindow {

    public static final int WINDOW_W = 1280;
    public static final int WINDOW_H = 720;

    private final JFrame frame;
    private final GamePanel gamePanel;

    public GameWindow(String title) {
        this.gamePanel = new GamePanel();
        this.frame = new JFrame(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(gamePanel, BorderLayout.CENTER);

        frame.setSize(WINDOW_W, WINDOW_H);
        frame.setLocationRelativeTo(null);
    }

    public void setGame(Game game) {
        gamePanel.setGame(game);
    }

    public void show() {
        frame.setVisible(true);
        gamePanel.requestFocusInWindow();
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }
}