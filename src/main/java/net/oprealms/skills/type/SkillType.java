package net.oprealms.skills.type;

public enum SkillType {

    COMBAT(50, 100, 5),
    FARMING(50, 100, 5),
    FISHING(50, 100, 5),
    ENCHANTING(50, 100, 5),
    MINING(50, 100, 5);

    private final int maxLevel, constant, multiplier;


    SkillType(int maxLevel, int constant, int multiplier) {
        this.maxLevel = maxLevel;
        this.constant = constant;
        this.multiplier = multiplier;

    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getConstant() {
        return constant;
    }

    public int getMultiplier() {
        return multiplier;
    }
}
