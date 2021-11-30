package net.oprealms.skills.type;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class TextUtil {

    private static final Map<Character, Integer> SYMBOLS = new HashMap<>();

    static {
        SYMBOLS.put('M', 1000);
        SYMBOLS.put('D', 500);
        SYMBOLS.put('C', 100);
        SYMBOLS.put('L', 50);
        SYMBOLS.put('X', 10);
        SYMBOLS.put('V', 5);
        SYMBOLS.put('I', 1);
    }


    public static String createProgressBar(double percent, int lines) {
        percent = Math.min(percent, 1);
        percent = Math.max(percent, 0);

        int index = (int) (lines * percent);

        String bar = StringUtils.repeat("|", lines);
        bar = bar.substring(0, index) + ChatColor.RED + bar.substring(index);

        return ChatColor.GREEN + bar;
    }

    public static String intToRoman(int num) {
        if (num == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        int times = 0;

        String[] romans = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] ints = new int[]{1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

        for (int i = ints.length - 1; i >= 0; i--) {
            times = num / ints[i];
            num %= ints[i];
            while (times > 0) {
                sb.append(romans[i]);
                times--;
            }
        }
        return sb.toString();
    }
}
