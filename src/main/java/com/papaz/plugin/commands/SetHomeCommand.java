package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {
    private final PapazCore plugin;
    public SetHomeCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        String homeName = args.length > 0 ? args[0] : "ev";
        plugin.getHomeManager().setHome(player.getUniqueId(), homeName, player.getLocation());
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("ev.ayarlandi").replace("%ev%", homeName));
        return true;
    }
}

