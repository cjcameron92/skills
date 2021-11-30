package net.oprealms.skills.type;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum SkillType {

    COMBAT(50, 100, 5, Material.DIAMOND_SWORD, ChatColor.RED,"&7Battle mobs to earn combat exp"),
    FARMING(50, 100, 5, Material.WHEAT, ChatColor.YELLOW, "&7Harvest and Plant crops to earn farming exp"),
    FISHING(50, 100, 5, Material.FISHING_ROD, ChatColor.AQUA,"&7Venture the seas and fish them to earn", "&7 fishing exp"),
    ENCHANTING(50, 100, 5, Material.ENCHANTED_BOOK, ChatColor.LIGHT_PURPLE,"&7Enchant your gear and weapons to", "&7earn enchanting exp"),
    MINING(50, 100, 5, Material.DIAMOND_PICKAXE, ChatColor.GOLD,"&7Explore the mines to earn mining exp");

    private final int maxLevel, constant, multiplier;
    private final Material material;
    private final ChatColor chatColor;
    private final String[] description;


    SkillType(int maxLevel, int constant, int multiplier, Material material, ChatColor chatColor, String... description) {
        this.maxLevel = maxLevel;
        this.constant = constant;
        this.multiplier = multiplier;
        this.material = material;
        this.chatColor = chatColor;
        this.description = description;

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

    public Material getMaterial() {
        return material;
    }

    public ChatColor getChatColor() {
        return chatColor;
    }

    public List<String> getDescription() {
        return Arrays.asList(description);
    }


}
