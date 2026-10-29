package dungeongame.game;

import dungeongame.entity.Character;
import dungeongame.entity.Enemy;
import dungeongame.entity.Goblin;
import dungeongame.world.Room;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final Character hero;
    private final Room currentRoom;
    private final List<Enemy> enemies = new ArrayList<>();

    private int targetX;
    private int targetY;
    private boolean hasTarget;

    public Game() {
        this.hero = new Character("Артём");
        this.currentRoom = new Room(15, 11);

        this.hero.setPosition(
                currentRoom.getWidth() / 2,
                currentRoom.getHeight() / 2
        );

        Goblin goblin = new Goblin(1);
        goblin.setPosition(10, 5);
        enemies.add(goblin);
    }

    public void setTarget(int x, int y) {
        if (!currentRoom.inBounds(x, y)) return;
        if (!currentRoom.isWalkable(x, y)) return;

        this.targetX = x;
        this.targetY = y;
        this.hasTarget = true;
    }

    public boolean hasTarget() {
        return hasTarget;
    }
    private Enemy enemyAt(int x, int y) {
        for (Enemy enemy : enemies) {
            if (enemy.getX() == x && enemy.getY() == y) {
                return enemy;
            }
        }
        return null;
    }
    public void moveHero() {
        if (!hasTarget) return;

        int heroX = hero.getX();
        int heroY = hero.getY();

        if (heroX == targetX && heroY == targetY) {
            hasTarget = false;
            return;
        }

        int stepX = Integer.signum(targetX - heroX);
        int stepY = Integer.signum(targetY - heroY);

        int newHeroX = heroX + stepX;
        int newHeroY = heroY + stepY;

        if (!currentRoom.isWalkable(newHeroX, newHeroY) || enemyAt(newHeroX, newHeroY) != null) {
            hasTarget = false;
            return;
        }

        hero.setPosition(newHeroX, newHeroY);
    }

    public Character getHero() { return hero; }
    public Room getCurrentRoom() { return currentRoom; }
    public List<Enemy> getEnemies() { return enemies; }
}