package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvseeCommand implements CommandExecutor {
    private final PapazCore plugin;
    public InvseeCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        if (!player.hasPermission("papaz.invsee")) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.yetki-yok")); return true; }
        if (args.length < 1) { player.sendMessage(plugin.getPrefix() + PapazCore.colorize("&cKullanım: /invsee <oyuncu>")); return true; }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.oyuncu-bulunamadi")); return true; }
        player.openInventory(target.getInventory());
        return true;
    }
}

