package dungeongame.world;

public class WallTile extends Tile {
    private final String spritePath;
    public WallTile(int x, int y, String spritePath) {
        super(x, y);
        this.spritePath = spritePath;
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public String getSpritePath() {
        return spritePath;
    }
}