package com.aubenoire.miningsystem.object.task;

import com.aubenoire.miningsystem.MiningSystem;
import com.aubenoire.miningsystem.configuration.Options;
import com.aubenoire.miningsystem.object.MinedBlock;
import lombok.AllArgsConstructor;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class RegenTask extends BukkitRunnable {

    private final MiningSystem plugin;

    @Override
    public void run() {
        List<MinedBlock> trash = new ArrayList<>();
        this.plugin.getMinedBlocks().forEach(block -> {
            if(System.currentTimeMillis() - block.getTime() >= Options.TIMER) {
                block.regenerate();
                trash.add(block);
            }
        });
        trash.forEach(minedBlock -> this.plugin.getMinedBlocks().remove(minedBlock));
    }
}
