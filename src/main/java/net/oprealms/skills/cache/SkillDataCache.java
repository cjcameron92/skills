package net.oprealms.skills.cache;

public class SkillDataCache {

    private int level;
    private double exp;

    public SkillDataCache() {
        this.level = 1;
        this.exp = 0;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getExp() {
        return exp;
    }

    public void setExp(double exp) {
        this.exp = exp;
    }
}
