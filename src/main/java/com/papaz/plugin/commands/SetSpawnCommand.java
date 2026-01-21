package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    private final PapazCore plugin;
    public SetSpawnCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        if (!player.hasPermission("papaz.admin")) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.yetki-yok")); return true; }
        plugin.getDataManager().setSpawnLocation(player.getLocation());
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("spawn.ayarlandi"));
        return true;
    }
}

