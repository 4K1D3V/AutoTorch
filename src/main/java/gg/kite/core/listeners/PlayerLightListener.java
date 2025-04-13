package gg.kite.core.listeners;

import gg.kite.core.Main;
import gg.kite.core.util.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.UUID;

public class PlayerLightListener implements Listener {

    private final HashMap<UUID, Long> cooldowns = new HashMap<>();

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Main plugin = Main.getInstance();
        if (!plugin.getConfig().getBoolean("enabled")) {
            return;
        }

        Player player = event.getPlayer();

        if (!plugin.getEnabledPlayers().contains(player.getUniqueId())) {
            return;
        }

        Location location = player.getLocation();
        int lightLevelThreshold = plugin.getConfig().getInt("light-level-threshold");

        Block block = location.getBlock();
        int lightLevel = block.getLightLevel();

        if (!Util.canPlaceTorch(player, cooldowns)) {
            return;
        }

        if (lightLevel < lightLevelThreshold && Util.hasTorch(player)) {
            Location torchLocation = Util.findTorchPlacementLocation(location);

            if (torchLocation != null) {
                location.getWorld().getBlockAt(torchLocation).setType(Material.TORCH);
                Util.removeTorchFromInventory(player);
                player.sendMessage("§6AutoTorch: A torch has been placed!");
            }
        }
    }
}