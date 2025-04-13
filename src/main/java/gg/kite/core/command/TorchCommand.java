package gg.kite.core.command;

import gg.kite.core.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;

public class TorchCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("autotorch.admin.reload")) {
                Main.getInstance().reloadConfig();
                sender.sendMessage("§aAutoTorch configuration reloaded!");
            } else {
                sender.sendMessage("§cYou do not have permission to reload the plugin.");
            }
            return true;
        } else if (args.length > 0 && args[0].equalsIgnoreCase("toggle") && sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("autotorch.toggle")) {
                HashSet<UUID> enabledPlayers = Main.getInstance().getEnabledPlayers();
                if (enabledPlayers.contains(player.getUniqueId())) {
                    enabledPlayers.remove(player.getUniqueId());
                    player.sendMessage("§cAutoTorch has been disabled for you.");
                } else {
                    enabledPlayers.add(player.getUniqueId());
                    player.sendMessage("§aAutoTorch has been enabled for you.");
                }
            } else {
                player.sendMessage("§cYou do not have permission to toggle AutoTorch.");
            }
            return true;
        }
        sender.sendMessage("§cUsage: /autotorch [reload|toggle]");
        return true;
    }
}