package dungeongame.world;

public class Room {

    private final int width;
    private final int height;
    private final Tile[][] tiles;

    public Room(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
        initFloorRoom();
        initBorderWallsRoom();
        initColumns();
    }
    private void addColumn(int x, int y) {
        if (!inBounds(x, y)) return;
        tiles[x][y] = new ColumnTile(x, y);
    }
    private void initColumns() {
        addColumn(3, 2);
        addColumn(11, 2);
        addColumn(3, 8);
        addColumn(11, 8);
    }
    private void initFloorRoom() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new FloorTile(x, y);
            }
        }
    }

    private void initBorderWallsRoom() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                boolean onBorder =
                        x == 0 || x == width - 1 ||
                                y == 0 || y == height - 1;
                if (onBorder) {
                    String sprite = chooseWallSprite(x, y);
                    tiles[x][y] = new WallTile(x, y, sprite);
                }
            }
        }
    }

    private String chooseWallSprite(int x, int y) {
        boolean left   = x == 0;
        boolean right  = x == width - 1;
        boolean top    = y == 0;
        boolean bottom = y == height - 1;

        if (left && top)     return "wall/wall_outer_mid_left.png";
        if (right && top)    return "wall/wall_outer_mid_right.png";
        if (left && bottom)  return "wall/wall_outer_front_left.png";
        if (right && bottom) return "wall/wall_outer_front_right.png";

        if (top)    return "wall/wall_mid.png";
        if (bottom) return "wall/wall_mid.png";
        if (left)   return "wall/wall_outer_mid_left.png";
        if (right)  return "wall/wall_outer_mid_right.png";

        return "wall/wall_mid.png";
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public boolean inBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public boolean isWalkable(int x, int y) {
        return inBounds(x, y) && tiles[x][y].isWalkable();
    }

    public int getWidth()  { return width; }
    public int getHeight() { return height; }

}