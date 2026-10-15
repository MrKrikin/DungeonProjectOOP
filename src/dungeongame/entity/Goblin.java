package dungeongame.entity;
import dungeongame.config.EnemyStats;
public class Goblin extends Enemy{
    public Goblin(int lvl) {
        super("Гоблин", lvl, EnemyStats.GOBLIN);
    }
    @Override
    public String getSymbol() {return "g";}
    @Override public String getSpritePath() {return "enemy/goblin/goblin_idle_anim_f0.png";}

}
