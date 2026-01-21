package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor {
    private final PapazCore plugin;
    public DelHomeCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        String homeName = args.length > 0 ? args[0] : "ev";
        if (!plugin.getHomeManager().hasHome(player.getUniqueId(), homeName)) {
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("ev.bulunamadi").replace("%ev%", homeName)); return true;
        }
        plugin.getHomeManager().deleteHome(player.getUniqueId(), homeName);
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("ev.silindi").replace("%ev%", homeName));
        return true;
    }
}

