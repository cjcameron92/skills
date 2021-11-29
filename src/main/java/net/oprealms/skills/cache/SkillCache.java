package net.oprealms.skills.cache;

import com.google.common.collect.Maps;
import net.oprealms.skills.SkillPlugin;
import net.oprealms.skills.progression.LinearProgression;
import net.oprealms.skills.progression.Progression;
import net.oprealms.skills.type.SkillType;
import org.bukkit.Bukkit;

import java.util.Map;
import java.util.UUID;

public class SkillCache {

    private final SkillType type;
    private final Progression progression;
    private final Map<UUID, SkillDataCache> dataCache;

    public SkillCache(SkillType type) {
        this.type = type;
        this.progression = new LinearProgression(type.getConstant(), type.getMultiplier());
        this.dataCache = Maps.newConcurrentMap();
        SkillPlugin.get().getSql().queryAsync("SELECT * FROM " + type.name().toLowerCase(), $ -> {}, resultSet -> {
            while (resultSet.next()) {
                UUID uuid = new UUID(resultSet.getLong(2), resultSet.getLong(1));
                int level = resultSet.getInt(3);
                double exp = resultSet.getInt(4);
                SkillDataCache skillDataCache = new SkillDataCache(uuid);
                skillDataCache.setLevel(level);
                skillDataCache.setExp(exp);
                dataCache.put(uuid, skillDataCache);
                Bukkit.getLogger().info("CACHED PLAYER " + uuid);
            }
            return null;
        });
    }

    public void incrementExp(UUID uuid, double exp) {
        setExp(uuid, getExp(uuid) + exp);
    }

    public void decrementExp(UUID uuid, double exp) {
        setExp(uuid, Math.max(0, getExp(uuid) - exp));
    }

    public void setExp(UUID uuid, double exp) {
        var data = dataCache.getOrDefault(uuid, new SkillDataCache(uuid));
        data.setExp(exp);
        saveData(uuid);
    }

    public double getExp(UUID uuid) {
        return dataCache.getOrDefault(uuid, new SkillDataCache(uuid)).getExp();
    }

    public void incrementLevel(UUID uuid, int level) {
        setLevel(uuid,getLevel(uuid) + level);
    }

    public void decrementLevel(UUID uuid, int level) {
        setLevel(uuid, Math.max(1, getLevel(uuid) - level));
    }

    public void setLevel(UUID uuid, int level) {
        var data = dataCache.getOrDefault(uuid, new SkillDataCache(uuid));
        data.setLevel(Math.min(level, 50));
        saveData(uuid);
    }

    public int getLevel(UUID uuid) {
        return dataCache.getOrDefault(uuid, new SkillDataCache(uuid)).getLevel();
    }

    public void saveData(UUID uuid) {
        SkillPlugin.get().getSql().queryAsync("SELECT * FROM " + type.name().toLowerCase() +" WHERE uuid_least = ? AND uuid_most = ?;", statement -> {
            statement.setLong(1, uuid.getLeastSignificantBits());
            statement.setLong(2, uuid.getMostSignificantBits());
        }, resultSet -> {
            if (!resultSet.next()) {
                SkillPlugin.get().getSql().executeAsync("INSERT INTO " + type.name().toLowerCase() + "(uuid_least, uuid_most, level, exp) VALUES (?, ?, ?, ?);", statement -> {
                    statement.setLong(1, uuid.getLeastSignificantBits());
                    statement.setLong(2, uuid.getMostSignificantBits());
                    statement.setInt(3, getLevel(uuid));
                    statement.setDouble(4, getExp(uuid));
                });
            } else {
                SkillPlugin.get().getSql().executeAsync("UPDATE " + type.name().toLowerCase() + " SET level = ?, SET exp = ? WHERE uuid_least = ? AND uuid_most = ?;", statement -> {
                    statement.setInt(1, getLevel(uuid));
                    statement.setDouble(2, getExp(uuid));
                    statement.setLong(3, uuid.getLeastSignificantBits());
                    statement.setLong(4, uuid.getMostSignificantBits());
                });
            }
            return null;
        });
    }

    public Progression getProgression() {
        return progression;
    }
}
