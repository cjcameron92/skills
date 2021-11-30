package net.oprealms.skills.gui;

import me.lucko.helper.item.ItemStackBuilder;
import me.lucko.helper.menu.Gui;
import me.lucko.helper.menu.Item;
import me.lucko.helper.menu.paginated.PageInfo;
import me.lucko.helper.menu.paginated.PaginatedGui;
import me.lucko.helper.menu.paginated.PaginatedGuiBuilder;
import me.lucko.helper.menu.scheme.MenuScheme;
import me.lucko.helper.menu.scheme.StandardSchemeMappings;
import net.oprealms.skills.SkillApi;
import net.oprealms.skills.type.SkillType;
import net.oprealms.skills.type.TextUtil;
import org.apache.commons.lang.WordUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SkillPreviewGui extends PaginatedGui {

    public static final int DEFAULT_LINES = 6;

    public static final int DEFAULT_NEXT_PAGE_SLOT = new MenuScheme()
            .maskEmpty(5)
            .mask("000000010")
            .getMaskedIndexes().get(0);

    public static final int DEFAULT_PREVIOUS_PAGE_SLOT = new MenuScheme()
            .maskEmpty(5)
            .mask("010000000")
            .getMaskedIndexes().get(0);

    public static final List<Integer> DEFAULT_ITEM_SLOTS = new MenuScheme()
            .mask("011111110")
            .mask("011111110")
            .mask("011111110")
            .mask("011111110")
            .mask("011111110")
            .getMaskedIndexesImmutable();

    public static final MenuScheme DEFAULT_SCHEME = new MenuScheme(StandardSchemeMappings.STAINED_GLASS)
            .mask("100000001")
            .mask("100000001")
            .mask("100000001")
            .mask("100000001")
            .mask("100000001")
            .mask("100000001")
            .scheme(10, 10)
            .scheme(10, 10)
            .scheme(10, 10)
            .scheme(10, 10)
            .scheme(10,10)
            .scheme(10, 10);

    public static final Function<PageInfo, ItemStack> DEFAULT_NEXT_PAGE_ITEM = pageInfo -> ItemStackBuilder.of(Material.ARROW)
            .name("&7Next Page ->")
            .lore("&fSwitch to the next page.").build();

    public static final Function<PageInfo, ItemStack> DEFAULT_PREVIOUS_PAGE_ITEM = pageInfo -> ItemStackBuilder.of(Material.ARROW)
            .name("&7Last Page <-")
            .lore("&fSwitch to the previous page.")
            .build();

    public SkillPreviewGui(Player player, SkillType type) {
        super(paginatedGui -> {
            List<Item> items = new ArrayList<>();
            int level = SkillApi.getLevel(type, player.getUniqueId());
            for (int i = 1; i <= type.getMaxLevel(); i++) {
                int amount = Math.min(64, i);
                if (level >= i) {
                    items.add(ItemStackBuilder.of(Material.LIME_STAINED_GLASS_PANE).name("&aUnlocked " + TextUtil.intToRoman(i)).amount(amount).buildItem().build());
                } else if (level + 1 == i) {
                    items.add(ItemStackBuilder.of(Material.YELLOW_STAINED_GLASS_PANE).name("&cLocked " + TextUtil.intToRoman(i)).amount(amount).buildItem().build());
                } else {
                    items.add(ItemStackBuilder.of(Material.RED_STAINED_GLASS_PANE).name("&cLocked " + TextUtil.intToRoman(i)).amount(amount).buildItem().build());
                }
            }
            return items;

        }, player, PaginatedGuiBuilder.create().title("&8Skill " + WordUtils.capitalizeFully(type.name()))
                .itemSlots(DEFAULT_ITEM_SLOTS)
                .scheme(DEFAULT_SCHEME)
                .nextPageItem(DEFAULT_NEXT_PAGE_ITEM)
                .previousPageItem(DEFAULT_PREVIOUS_PAGE_ITEM));

        setFallbackGui(SkillGui::new);
    }
}
