package dungeongame.entity;

public abstract class Entity {
    private String name;
    private int maxHp;
    private int hp;
    private int attack;
    private int defense;
    private int x;
    private int y;

    protected Entity(String name, int maxHp, int attack, int defense) {
        this.name = name;
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.attack = attack;
        this.defense = defense;
    }
    public abstract String getSymbol();

    public abstract String getDescription();

    public boolean isAlive() {
        if (hp > 0) {
            return true;
        }
        return false;
    }
    public void takeDamage(int expectedDamage) {
        int actualDamage = Math.max(1, expectedDamage - defense);
        hp = Math.max(0, (hp - actualDamage));
    }
    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
    }
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getX() { return x; }
    public int getY() { return y; }


    protected void setHp(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Hp не может быть отрицательным!");
        }
        this.hp = Math.min(value, maxHp);
    }
    protected void setMaxHp(int value) {
        if (value < 1) {
            throw new IllegalArgumentException("maxHp не может быть отрицательным!");
        }
        this.maxHp = value;
        if (hp > maxHp) {
            hp = maxHp;
        }
    }
    protected void setAttack(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("attack не может быть отрицательным!");
        }
        this.attack = value;
    }
    protected void setDefense(int value) {
        this.defense = value;
    }

    public abstract String getSpritePath();
}
