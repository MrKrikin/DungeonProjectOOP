package dungeongame.game;

import dungeongame.entity.Character;
import dungeongame.world.Room;

public class Game {

    private final Character hero;
    private final Room currentRoom;

    public Game() {
        this.hero = new Character("Артём");
        this.currentRoom = new Room(10, 8);
        this.hero.setPosition(
                currentRoom.getWidth() / 2,
                currentRoom.getHeight() / 2
        );
    }

    public Character getHero() { return hero; }
    public Room getCurrentRoom() { return currentRoom; }
}
