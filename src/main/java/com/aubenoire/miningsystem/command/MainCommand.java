package com.aubenoire.miningsystem.command;

import com.aubenoire.miningsystem.MiningSystem;
import com.aubenoire.miningsystem.configuration.Options;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class MainCommand implements CommandExecutor {

    private final MiningSystem plugin;

    public MainCommand(MiningSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("miningsystem").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(sender.hasPermission("miningsystem.admin")) {
            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("regen")) {
                    long start = System.currentTimeMillis();
                    this.plugin.getRegenTask().run();
                    sender.sendMessage("§aAll mined blocks have been regenerated in " + (System.currentTimeMillis() - start) + "ms");
                } else if (strings[0].equalsIgnoreCase("reload")) {
                    plugin.reloadConfig();
                    Options.loadOptions(plugin);
                    sender.sendMessage("§aThe configuration file has been reloaded!");
                }
            } else {
                sender.sendMessage("§eThere are §6§l" + this.plugin.getMinedBlocks().size() + "§e mined blocks in queue.");
                sender.sendMessage("§aUse §2/miningsystem regen§a to regenerate them right now!");
            }
        } else {
            sender.sendMessage("§cYou do not have the permission for this!");
        }
        return false;
    }
}
