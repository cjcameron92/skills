package net.oprealms.skills.cache;

import java.util.UUID;

public class SkillDataCache {

    private final UUID uuid;
    private int level;
    private double exp;

    public SkillDataCache(UUID uuid) {
        this.uuid = uuid;
        this.level = 1;
        this.exp = 0;
    }

    public UUID getUuid() {
        return uuid;
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
