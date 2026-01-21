package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {
    private final PapazCore plugin;
    public BroadcastCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player && !player.hasPermission("papaz.broadcast")) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.yetki-yok")); return true; }
        if (args.length < 1) { sender.sendMessage(plugin.getPrefix() + PapazCore.colorize("&cKullanım: /broadcast <mesaj>")); return true; }
        String message = String.join(" ", args);
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(PapazCore.colorize("&8&m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        Bukkit.broadcastMessage(PapazCore.colorize("      &6&l📢 DUYURU"));
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(PapazCore.colorize("   &f" + message));
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(PapazCore.colorize("&8&m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        Bukkit.broadcastMessage("");
        return true;
    }
}

