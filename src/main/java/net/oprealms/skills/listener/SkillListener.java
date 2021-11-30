package net.oprealms.skills.listener;

import me.lucko.helper.Events;
import net.oprealms.skills.SkillApi;
import net.oprealms.skills.cache.SkillCacheResult;
import net.oprealms.skills.type.SkillType;
import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Mob;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashMap;
import java.util.Map;

public class SkillListener {

    private final Map<EntityType, Double> ENTITY_EXP = new HashMap<>() {{
        put(EntityType.CREEPER, 5D);
    }};

    public SkillListener() {
        Events.subscribe(EntityDeathEvent.class).filter(event -> event.getEntity() instanceof Mob).filter(event -> ENTITY_EXP.containsKey(event.getEntityType())).filter(event -> event.getEntity().getKiller() != null).handler(event -> {
            var exp = ENTITY_EXP.getOrDefault(event.getEntityType(), 0D);
            if (exp <= 0) return;
            var killer = event.getEntity().getKiller();
            assert killer != null;
            if (SkillApi.incrementExp(SkillType.COMBAT, killer.getUniqueId(), exp) == SkillCacheResult.LEVEL_UP) {
                killer.sendMessage(ChatColor.GREEN + "You have increased your level for skill " + WordUtils.capitalizeFully(SkillType.COMBAT.name().toLowerCase()));
            }
        });
    }
}
