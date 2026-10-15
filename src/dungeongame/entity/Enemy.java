package dungeongame.entity;

import dungeongame.config.EnemyStats;

public abstract class Enemy extends Entity {
    private final int lvl;
    private final EnemyStats stats;
    protected Enemy(String name, int lvl, EnemyStats stats) {
        super(name, stats.hpAtLvl(lvl), stats.atkAtLvl(lvl), stats.defAtLvl(lvl));
        this.lvl = lvl;
        this.stats = stats;
    }
    public int getLvl() {return lvl;}
    public int getExpReward() {return stats.expAtLvl(lvl);}
    @Override
    public String getDescription() {
        return String.format("%s (lvl: %d, HP: %d/%d, ATK: %d, DEF: %d)",
                getName(), lvl, getHp(), getMaxHp(), getAttack(), getDefense());
    }
    @Override
    public abstract String getSymbol();
    public abstract String getSpritePath();
}
