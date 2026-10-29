package dungeongame.world;

public class ColumnTile extends Tile {

    public ColumnTile(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public String getSpritePath() {
        return "wall/column_wall.png";
    }
}