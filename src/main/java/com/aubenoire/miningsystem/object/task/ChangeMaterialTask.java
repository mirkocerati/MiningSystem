package com.aubenoire.miningsystem.object.task;

import com.aubenoire.miningsystem.MiningSystem;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

@AllArgsConstructor
public class ChangeMaterialTask extends BukkitRunnable {

    private final Block block;
    private final Material newMaterial;

    @Override
    public void run() {
        this.block.setType(newMaterial);
    }
}
