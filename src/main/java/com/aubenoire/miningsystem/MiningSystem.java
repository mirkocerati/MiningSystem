package com.aubenoire.miningsystem;

import com.aubenoire.miningsystem.command.MainCommand;
import com.aubenoire.miningsystem.configuration.Options;
import com.aubenoire.miningsystem.listener.PlayerMineListener;
import com.aubenoire.miningsystem.object.MinedBlock;
import com.aubenoire.miningsystem.object.task.RegenTask;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class MiningSystem extends JavaPlugin {

    @Getter
    private List<MinedBlock> minedBlocks;

    @Getter
    private RegenTask regenTask;

    @Override
    public void onEnable() {
        this.minedBlocks = new ArrayList<>();
        this.regenTask = new RegenTask(this);
        getServer().getPluginManager().registerEvents(new PlayerMineListener(this), this);

        new MainCommand(this);

        saveDefaultConfig();
        Options.loadOptions(this);

        this.regenTask.runTaskTimer(this, 60L, 20L);
    }

}
