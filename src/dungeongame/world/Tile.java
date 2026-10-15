package dungeongame.world;

public abstract class Tile {
    private final int x;
    private final int y;
    protected Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() { return x; }
    public int getY() { return y; }

    public abstract boolean isWalkable();
    public abstract String getSpritePath();

}
