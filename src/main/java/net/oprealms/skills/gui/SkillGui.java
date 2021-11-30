package net.oprealms.skills.gui;

import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.scheme.MenuPopulator;
import me.lucko.helper.menu.scheme.MenuScheme;
import net.oprealms.skills.SkillApi;
import net.oprealms.skills.type.SkillType;
import net.oprealms.skills.type.TextUtil;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class SkillGui extends Gui {

    private static final MenuScheme BUTTONS = new MenuScheme()
            .mask("000000000")
            .mask("000000000")
            .mask("001111100")
            .mask("000000000")
            .mask("000010000")
            .mask("000000000");


    public SkillGui(Player player) {
        super(player, 6, "&8Skills");
    }

    @Override public void redraw() {
        MenuPopulator populate = BUTTONS.newPopulator(this);
        Player player = getPlayer();
        for (int i = 0; i < 54; i++) {
            setItem(i, ItemStackBuilder.of(Material.BLACK_STAINED_GLASS_PANE).buildItem().build());
        }

        for (var type : SkillType.values()) {

            populate.accept(ItemStackBuilder.of(type.getMaterial())
                            .name(type.getChatColor().toString() + WordUtils.capitalizeFully(type.name().toLowerCase()) + " Skill " +  TextUtil.intToRoman(SkillApi.getLevel(type, player.getUniqueId())))
                                    .lore(String.join("\n", type.getDescription()),
                                    "",
                                    TextUtil.createProgressBar(SkillApi.getPercentage(type, player.getUniqueId()), 30) + " &7" + SkillApi.getFormattedPercentage(type, player.getUniqueId()) + "%")
                    .build(() -> {
                        var skillPreview = new SkillPreviewGui(player, type);
                        skillPreview.open();
            }));
        }

        populate.accept(ItemStackBuilder.of(Material.RED_STAINED_GLASS_PANE).name("&cClose").build(player::closeInventory));
    }
}
