package dungeongame.ui;

import dungeongame.entity.Character;
import dungeongame.entity.Enemy;
import dungeongame.game.Game;
import dungeongame.world.ColumnTile;
import dungeongame.world.Room;
import dungeongame.world.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel {

    public static final int TILE = 32;

    private Game game;
    private final javax.swing.Timer stepTimer;

    public GamePanel() {
        setBackground(new Color(15, 15, 20));
        setFocusable(true);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (game == null) return;

                Room room = game.getCurrentRoom();
                int roomPixelW = room.getWidth() * TILE;
                int roomPixelH = room.getHeight() * TILE;
                int offsetX = (getWidth() - roomPixelW) / 2;
                int offsetY = (getHeight() - roomPixelH) / 2;

                int cellX = (e.getX() - offsetX) / TILE;
                int cellY = (e.getY() - offsetY) / TILE;

                game.setTarget(cellX, cellY);
            }
        });

        stepTimer = new javax.swing.Timer(150, e -> {
            if (game != null) {
                game.moveHero();
                repaint();
            }
        });
        stepTimer.start();
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

        // 1. Рисуем пол для ВСЕХ клеток (включая колонны, чтобы не было черных дыр)
        for (int y = 0; y < room.getHeight(); y++) {
            for (int x = 0; x < room.getWidth(); x++) {
                Tile tile = room.getTile(x, y);

                String spritePath = tile.getSpritePath();
                if (tile instanceof ColumnTile) {
                    spritePath = "floor/floor_1.png"; // Под колонной всегда рисуем пол
                }

                BufferedImage sprite = SpriteLoader.load(spritePath);
                if (sprite != null) {
                    g2.drawImage(sprite,
                            offsetX + x * TILE,
                            offsetY + y * TILE,
                            TILE, TILE, null);
                }
            }
        }

        // 2. Собираем элементы для отрисовки
        List<RenderItem> items = new ArrayList<>();

        for (int y = 0; y < room.getHeight(); y++) {
            for (int x = 0; x < room.getWidth(); x++) {
                Tile tile = room.getTile(x, y);
                if (tile instanceof ColumnTile) {
                    items.add(new RenderItem(x, y, tile.getSpritePath(), true));
                }
            }
        }

        Character hero = game.getHero();
        items.add(new RenderItem(hero.getX(), hero.getY(), hero.getSpritePath(), false));

        for (Enemy enemy : game.getEnemies()) {
            items.add(new RenderItem(enemy.getX(), enemy.getY(), enemy.getSpritePath(), false));
        }

        // Сортируем по Y для правильного наложения
        items.sort((a, b) -> Integer.compare(a.y, b.y));

        for (RenderItem item : items) {
            BufferedImage sprite = SpriteLoader.load(item.spritePath);
            if (sprite == null) continue;

            int cellX = offsetX + item.x * TILE;
            int cellY = offsetY + item.y * TILE;

            int sprW = sprite.getWidth();
            int sprH = sprite.getHeight();

            int drawW = TILE;
            int drawH = sprH * drawW / sprW;

            int drawX = cellX + (TILE - drawW) / 2;
            int drawY;

            if (item.isColumn) {
                // Сдвигаем колонну вверх на разницу между ее реальной высотой и тайлом,
                // чтобы основание стояло на полу своей клетки, а верхушка уходила на клетку выше.
                drawY = cellY - (drawH - TILE);
            } else {
                // Персонажи и враги привязываются к низу клетки
                drawY = cellY + TILE - drawH;
            }

            g2.drawImage(sprite, drawX, drawY, drawW, drawH, null);
        }
    }

    private static class RenderItem {
        int x, y;
        String spritePath;
        boolean isColumn;

        RenderItem(int x, int y, String spritePath, boolean isColumn) {
            this.x = x;
            this.y = y;
            this.spritePath = spritePath;
            this.isColumn = isColumn;
        }
    }
}