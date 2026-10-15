package dungeongame.entity;

public class Character extends Entity {
    private int lvl;
    private int exp;
    private int expToLvl;

    public Character(String name) {
        super(name, 25, 5, 2);
        this.lvl = 1;
        this.exp = 0;
        this.expToLvl = 100;
    }
    @Override
    public String getSymbol() {
        return "@";
    }
    @Override
    public String getDescription() {
        return String.format("Герой %s (lvl: %d, HP: %d/%d, ATK: %d, DEF: %d)",getName(), lvl, getHp(), getMaxHp(), getAttack(), getDefense());
    }
    public String getSpritePath() {
        return "player/knight_f_idle_anim_f0.png";
    }
    private void applyLevelup() {
        lvl++;
        expToLvl = (int) (expToLvl*1.5);
        setMaxHp(getMaxHp() + (5 * getLvl()));
        setHp(getHp() + (10 * getLvl()));
        setAttack(getAttack() + ((int) (1.5 * getLvl())));
        setDefense(getDefense() + ((int) (1.25 * getLvl())));

        System.out.println(getName() + " Достиг уровня:" + getLvl() + "!");
    }
    public void giveExp(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Опыт не может быть отрицательным!");
        }
        exp += amount;
        while (exp >= expToLvl) {
            exp -= expToLvl;
            applyLevelup();
        }
    }
    public int getLvl() {
        return lvl;
    }
    public int getExp() {return exp;}
    public int getExpToLvl(){return expToLvl;}



}