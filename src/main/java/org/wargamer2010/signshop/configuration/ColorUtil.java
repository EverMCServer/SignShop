
package org.wargamer2010.signshop.configuration;

import java.util.HashMap;
import java.util.Map;

import com.meowj.langutils.lang.LanguageHelper;

import org.bukkit.Color;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.wargamer2010.signshop.util.signshopUtil;

public class ColorUtil {
    private static Map<Integer, String> colorLookup = new HashMap<Integer, String>();

    private ColorUtil() {

    }

    public static void init() {
        // Stock Minecraft colors
        colorLookup.put(8339378 , "purple");
        colorLookup.put(12801229 , "magenta");
        colorLookup.put(8073150 , "purple");
        colorLookup.put(6719955 , "light_blue");
        colorLookup.put(2651799 , "cyan");
        colorLookup.put(4408131 , "gray");
        colorLookup.put(11250603 , "light_gray");
        colorLookup.put(15892389 , "pink");
        colorLookup.put(15435844 , "orange");
        colorLookup.put(4312372 , "lime");
        colorLookup.put(11743532 , "red");
        colorLookup.put(2437522 , "blue");
        colorLookup.put(14602026 , "yellow");
        colorLookup.put(11743532 , "red");
        colorLookup.put(1973019 , "black");
        colorLookup.put(6704179 , "brown");
        colorLookup.put(3887386 , "green");
        colorLookup.put(15790320 , "white");
        colorLookup.put(2437522 , "blue");
        colorLookup.put(1973019 , "black");
        colorLookup.put(14188952 , "pink");
        colorLookup.put(14602026, "yellow");
        colorLookup.put(5320730, "brown");

        // Load colors that will help guessing custom colornames
        FileConfiguration config = new YamlConfiguration();
        config = configUtil.loadYMLFromJar(config, "colors.yml");
        if(config == null)
            return;
        for(Map.Entry<String, String> entry : configUtil.fetchStringStringHashMap("colors", config).entrySet()) {
            try {
                int hex = Integer.parseInt(entry.getKey(), 16);
                if(!colorLookup.containsKey(hex))
                    colorLookup.put(hex, entry.getValue());
            } catch(NumberFormatException ex) {
                continue;
            }
        }
    }

    public static String getColorAsString(Color color) {
        int rgb = color.asRGB();
        if(colorLookup.containsKey(rgb)) {
            return LanguageHelper.translateToLocal("item.minecraft.firework_star."+ colorLookup.get(rgb), SignShopConfig.getPreferredLanguage());
        } else {
            double diff = -1;
            String last = "";
            for(int val : colorLookup.keySet()) {
                double currentdiff = getDifferenceBetweenColors(rgb, val);
                if(diff == -1 || currentdiff < diff) {
                    diff = currentdiff;
                    last = colorLookup.get(val);
                }
            }
            return LanguageHelper.translateToLocal("item.minecraft.firework_star."+ colorLookup.get(last), SignShopConfig.getPreferredLanguage()) + 
            "[#"+ Integer.toHexString(color.getRed()).toUpperCase() + Integer.toHexString(color.getGreen()).toUpperCase() + Integer.toHexString(color.getBlue()).toUpperCase() +"]";
        }
    }

    public static double getDifferenceBetweenColors(int colorone, int colortwo) {
        java.awt.Color a = new java.awt.Color(colorone);
        java.awt.Color b = new java.awt.Color(colortwo);
        int comboa = (a.getRed() + a.getGreen() + a.getBlue());
        int combob = (b.getRed() + b.getGreen() + b.getBlue());
        return Math.abs(comboa - combob);
    }
}
