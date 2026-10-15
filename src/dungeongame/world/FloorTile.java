package dungeongame.world;

public class FloorTile extends Tile {
    public FloorTile(int x, int y) {
        super(x, y);
    }
    @Override
    public boolean isWalkable() {
        return true;
    }
    @Override
    public String getSpritePath() {
        return "floor/floor_1.png";
    }
}
