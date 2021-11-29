package net.oprealms.skills;

import me.lucko.helper.plugin.ap.Plugin;
import me.lucko.helper.sql.DatabaseCredentials;
import me.lucko.helper.sql.Sql;
import me.lucko.helper.sql.plugin.HelperSql;
import net.oprealms.skills.type.SkillType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

@Plugin(name = "Skills", version = "1.1")
public class SkillPlugin extends JavaPlugin {

    private static SkillPlugin i;

    private Sql sql;

    public SkillPlugin() {
        SkillPlugin.i = this;
    }

    @Override public void onEnable() {
        saveDefaultConfig();
        sql = new HelperSql(DatabaseCredentials.fromConfig(Objects.requireNonNull(getConfig().getConfigurationSection("sql"))));


        for (var type : SkillType.values()) {
            sql.executeAsync(String.format("CREATE TABLE IF NOT EXISTS %s(uuid_least BIGINT NOT NULL, uuid_most BIGINT NOT NULL, level INT NOT NULL, exp DOUBLE NOT NULL, PRIMARY KEY(uuid_least, uuid_most));", type.name().toLowerCase()));
        }
    }

    public Sql getSql() {
        return sql;
    }

    public static SkillPlugin get() {
        return i;
    }
}
