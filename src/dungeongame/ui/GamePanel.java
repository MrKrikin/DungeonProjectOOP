package dungeongame.ui;

import dungeongame.entity.Character;
import dungeongame.game.Game;
import dungeongame.world.Room;
import dungeongame.world.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel {

    public static final int TILE = 32;

    private Game game;

    public GamePanel() {
        setBackground(new Color(15, 15, 20));
        setFocusable(true);
    }

    public void setGame(Game game) {
        this.game = game;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);

        if (game == null) return;

        Room room = game.getCurrentRoom();

        int roomPixelW = room.getWidth() * TILE;
        int roomPixelH = room.getHeight() * TILE;
        int offsetX = (getWidth() - roomPixelW) / 2;
        int offsetY = (getHeight() - roomPixelH) / 2;

        // 1. Клетки комнаты
        for (int y = 0; y < room.getHeight(); y++) {
            for (int x = 0; x < room.getWidth(); x++) {
                Tile tile = room.getTile(x, y);
                BufferedImage sprite = SpriteLoader.load(tile.getSpritePath());
                if (sprite != null) {
                    g2.drawImage(sprite,
                            offsetX + x * TILE,
                            offsetY + y * TILE,
                            TILE, TILE, null);
                }
            }
        }

        // 2. Герой — поверх комнаты
        Character hero = game.getHero();
        BufferedImage heroSprite = SpriteLoader.load(hero.getSpritePath());
        if (heroSprite != null) {
            g2.drawImage(heroSprite,
                    offsetX + hero.getX() * TILE,
                    offsetY + hero.getY() * TILE,
                    TILE, TILE, null);
        }
    }
}