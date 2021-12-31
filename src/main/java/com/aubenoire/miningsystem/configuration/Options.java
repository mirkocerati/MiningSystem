package com.aubenoire.miningsystem.configuration;

import com.aubenoire.miningsystem.MiningSystem;
import org.bukkit.Bukkit;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class Options {

    public static void loadOptions(MiningSystem plugin) {
        ALLOWED_WORLDS = plugin.getConfig().getStringList("worlds");

        ALLOWED_MATERIALS = new ArrayList<>();
        plugin.getConfig().getStringList("blocks").forEach(string -> {
            Material material = Material.getMaterial(string.toUpperCase());
            if(material == null) {
                plugin.getLogger().info("Block '" +string+ "' from config is not a valid material!");
            } else {
                ALLOWED_MATERIALS.add(material);
            }
        });

        String placeholderString = plugin.getConfig().getString("placeholder-block");
        Material placeholder = Material.getMaterial(plugin.getConfig().getString("placeholder").toUpperCase());
        if(placeholder == null) {
            PLACEHOLDER = Material.BEDROCK;
            plugin.getLogger().info("Placeholder block '" + placeholderString + "' is not a valid material, placeholder is set do BEDROCK as default!");
        } else {
            PLACEHOLDER = placeholder;
        }

        TIMER = plugin.getConfig().getLong("timer");

    }

    public static List<String> ALLOWED_WORLDS;

    public static List<Material> ALLOWED_MATERIALS;

    public static Material PLACEHOLDER;

    public static Long TIMER;

}
