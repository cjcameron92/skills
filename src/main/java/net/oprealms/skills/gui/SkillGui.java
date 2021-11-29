package net.oprealms.skills.gui;

import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.scheme.MenuPopulator;
import me.lucko.helper.menu.scheme.MenuScheme;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SkillGui extends Gui {

    private static final MenuScheme BUTTONS = new MenuScheme()
            .mask("000000000")
            .mask("000000000")
            .mask("001234500")
            .mask("000000000")
            .mask("0000@0000")
            .mask("000000000");


    public SkillGui(Player player) {
        super(player, 6, "&8Skills");
    }

    @Override public void redraw() {
        MenuPopulator populate = BUTTONS.newPopulator(this);
        populate.accept(ItemStackBuilder.of(Material.BLACK_STAINED_GLASS_PANE).buildItem().build());
        populate.accept(ItemStackBuilder.of(Material.DIAMOND_SWORD).name("&c&lCombat Skill").buildItem().build());
        populate.accept(ItemStackBuilder.of(Material.WHEAT).name("&e&lFarming Skill").buildItem().build());
        populate.accept(ItemStackBuilder.of(Material.FISHING_ROD).name("&b&lFishing Skill").buildItem().build());
        populate.accept(ItemStackBuilder.of(Material.ENCHANTED_BOOK).name("&d&lEnchanting Skill").buildItem().build());
        populate.accept(ItemStackBuilder.of(Material.DIAMOND_PICKAXE).name("&6&lMining Skill").buildItem().build());
    }
}
