package gg.kite.core.command;

import gg.kite.core.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TorchCommand implements TabExecutor {
    private final Main plugin;
    private final String prefix;

    /**
     * Constructor to initialize the TorchCommand with the plugin instance.
     * @param plugin The main plugin instance.
     */
    public TorchCommand(Main plugin) {
        this.plugin = plugin;
        this.prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("autotorch.admin.reload")) {
                plugin.reloadConfig();
                sender.sendMessage(prefix + " §aConfiguration reloaded!");
            } else {
                sender.sendMessage(prefix + " §cYou do not have permission to reload the plugin.");
            }
            return true;
        } else if (args.length > 0 && args[0].equalsIgnoreCase("toggle") && sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("autotorch.toggle")) {
                HashSet<UUID> enabledPlayers = plugin.getEnabledPlayers();
                if (enabledPlayers.contains(player.getUniqueId())) {
                    enabledPlayers.remove(player.getUniqueId());
                    player.sendMessage(prefix + " disabled.");
                } else {
                    enabledPlayers.add(player.getUniqueId());
                    player.sendMessage(prefix + " enabled.");
                }
            } else {
                player.sendMessage(prefix + " §cYou do not have permission to toggle AutoTorch.");
            }
            return true;
        }
        sender.sendMessage(prefix + " §cUsage: /autotorch [reload | toggle]");
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            if ("reload".startsWith(args[0].toLowerCase()) && sender.hasPermission("autotorch.admin.reload")) {
                completions.add("reload");
            }
            if ("toggle".startsWith(args[0].toLowerCase()) && sender.hasPermission("autotorch.toggle")) {
                completions.add("toggle");
            }
            return completions;
        }

        return List.of();
    }
}