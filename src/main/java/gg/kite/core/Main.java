package gg.kite.core;

import gg.kite.core.listeners.PlayerLightListener;
import gg.kite.core.command.TorchCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Logger;

public class Main extends JavaPlugin {

    private static Main instance;
    private final Logger LOGGER = getLogger();
    private final HashSet<UUID> enabledPlayers = new HashSet<>();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new PlayerLightListener(), this);
        getCommand("autotorch").setExecutor(new TorchCommand(this));

        LOGGER.info("AutoTorch enabled!");
    }

    public static Main getInstance() {
        return instance;
    }

    public HashSet<UUID> getEnabledPlayers() {
        return enabledPlayers;
    }
}