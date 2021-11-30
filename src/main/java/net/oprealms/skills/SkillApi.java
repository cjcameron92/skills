package net.oprealms.skills;


import net.oprealms.skills.cache.SkillCache;
import net.oprealms.skills.cache.SkillCacheResult;
import net.oprealms.skills.type.SkillType;

import java.text.DecimalFormat;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

public class SkillApi {

    private static final Map<SkillType, SkillCache> skillCache = new EnumMap<>(SkillType.class);
    private static final DecimalFormat decimalFormat = new DecimalFormat("##.#");

    public static void cacheSkills() {
        for (var type : SkillType.values()) {
            skillCache.put(type, new SkillCache(type));
        }
    }

    public static String getFormattedPercentage(SkillType type, UUID uuid) {
        return decimalFormat.format(getPercentage(type, uuid) * 100);
    }

    public static double getPercentage(SkillType type, UUID uuid) {
        return getExp(type, uuid) / getRequiredExp(type, uuid);
    }

    public static long getRequiredExp(SkillType type, UUID uuid) {
        return skillCache.get(type).getProgression().getRequired(getLevel(type, uuid));
    }

    public static SkillCacheResult incrementExp(SkillType type, UUID uuid, double amount) {
       return skillCache.get(type).incrementExp(uuid, amount);
    }

    public static void decrementExp(SkillType type, UUID uuid, double amount) {
        skillCache.get(type).decrementExp(uuid, amount);
    }

    public static SkillCacheResult setExp(SkillType type, UUID uuid, double exp) {
       return skillCache.get(type).setExp(uuid, exp);
    }

    public static double getExp(SkillType type, UUID uuid) {
        return skillCache.get(type).getExp(uuid);
    }

    public static void decrementLevel(SkillType type, UUID uuid, int amount) {
        skillCache.get(type).decrementLevel(uuid, amount);
    }

    public static void incrementLevel(SkillType type, UUID uuid, int amount) {
        skillCache.get(type).incrementLevel(uuid, amount);
    }

    public static void setLevel(SkillType type, UUID uuid, int level) {
        skillCache.get(type).setLevel(uuid, level);
    }

    public static int getLevel(SkillType type, UUID uuid) {
        return skillCache.get(type).getLevel(uuid);
    }

    private SkillApi() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }
}
