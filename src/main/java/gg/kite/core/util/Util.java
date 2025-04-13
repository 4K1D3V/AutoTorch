package gg.kite.core.util;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.UUID;

public class Util {

    /**
     * Checks if the player has at least one torch in their inventory.
     *
     * @param player The player whose inventory is to be checked.
     * @return True if the player has a torch, false otherwise.
     */
    public static boolean hasTorch(@NotNull Player player) {
        return player.getInventory().contains(Material.TORCH);
    }

    /**
     * Removes one torch from the player's inventory.
     *
     * @param player The player whose inventory is to be modified.
     */
    public static void removeTorchFromInventory(@NotNull Player player) {
        ItemStack torch = new ItemStack(Material.TORCH, 1);
        player.getInventory().removeItem(torch);
    }

    /**
     * Finds a valid location to place a torch. This checks if the block below the given location is solid.
     *
     * @param location The location to check for valid placement.
     * @return A valid torch placement location, or null if no valid location is found.
     */
    public static @Nullable Location findTorchPlacementLocation(@NotNull Location location) {
        Location below = location.clone().subtract(0, 1, 0);

        if (below.getBlock().getType().isSolid()) {
            return location;
        }

        return null;
    }

    /**
     * Checks if the cooldown has expired for the player to place a torch.
     *
     * @param player    The player to check the cooldown for.
     * @param cooldowns A map storing the last torch placement timestamp for each player.
     * @return True if the player can place a torch, false otherwise.
     */
    public static boolean canPlaceTorch(@NotNull Player player, @NotNull HashMap<UUID, Long> cooldowns) {
        long now = System.currentTimeMillis();
        long lastUsed = cooldowns.getOrDefault(player.getUniqueId(), 0L);
        int cooldownSeconds = gg.kite.core.Main.getInstance().getConfig().getInt("placement-cooldown-seconds", 5);

        if (now - lastUsed < cooldownSeconds * 1000L) {
            return false;
        }

        cooldowns.put(player.getUniqueId(), now);
        return true;
    }
}