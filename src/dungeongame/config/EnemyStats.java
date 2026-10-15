package dungeongame.config;
public final class EnemyStats {
    private final int baseHp, baseAttack, baseDef, hpPerLvl, atkPerLvl, defPerLvl, baseExpReward, expPerLvlReward;
    public EnemyStats(int baseHp, int baseAttack, int baseDef,
                      int hpPerLvl, int atkPerLvl, int defPerLvl,
                      int baseExpReward, int expPerLvlReward) {
        this.baseHp = baseHp;   this.baseAttack = baseAttack;   this.baseDef = baseDef;
        this.hpPerLvl = hpPerLvl;   this.atkPerLvl = atkPerLvl; this.defPerLvl = defPerLvl;
        this.baseExpReward = baseExpReward; this.expPerLvlReward = expPerLvlReward;
    }
    public int hpAtLvl(int lvl) {return baseHp + (lvl - 1) * hpPerLvl;}
    public int atkAtLvl(int lvl) { return baseAttack + (lvl - 1) * atkPerLvl; }
    public int defAtLvl(int lvl) { return baseDef + (lvl - 1) * defPerLvl; }
    public int expAtLvl(int lvl) { return baseExpReward + (lvl - 1) * expPerLvlReward; }




    public static final EnemyStats GOBLIN = new EnemyStats(
            20, 5, 1,
            5, 2, 1,
            25, 10
    );

}
