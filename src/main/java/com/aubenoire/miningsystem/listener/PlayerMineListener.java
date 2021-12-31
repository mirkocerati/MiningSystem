package com.aubenoire.miningsystem.listener;

import com.aubenoire.miningsystem.MiningSystem;
import com.aubenoire.miningsystem.configuration.Options;
import com.aubenoire.miningsystem.object.MinedBlock;
import com.aubenoire.miningsystem.object.task.ChangeMaterialTask;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public record PlayerMineListener(MiningSystem plugin) implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material originalMaterial = block.getType();
        if(Options.ALLOWED_MATERIALS.contains(originalMaterial) && Options.ALLOWED_WORLDS.contains(player.getWorld().getName()) && !event.isCancelled() && player.getGameMode() != GameMode.CREATIVE) {
            new ChangeMaterialTask(block, Options.PLACEHOLDER).runTaskLater(plugin, 1L);
            this.plugin.getMinedBlocks().add(new MinedBlock(originalMaterial, block.getLocation(), System.currentTimeMillis()));
        }
    }

}
