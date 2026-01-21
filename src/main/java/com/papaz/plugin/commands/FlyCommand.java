package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    private final PapazCore plugin;
    public FlyCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        if (!player.hasPermission("papaz.fly")) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.yetki-yok")); return true; }
        Player target = player;
        if (args.length > 0 && player.hasPermission("papaz.admin")) { target = Bukkit.getPlayer(args[0]); if (target == null) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.oyuncu-bulunamadi")); return true; }}
        target.setAllowFlight(!target.getAllowFlight());
        if (target.getAllowFlight()) target.sendMessage(plugin.getPrefix() + plugin.getMessage("admin.fly-acildi"));
        else { target.setFlying(false); target.sendMessage(plugin.getPrefix() + plugin.getMessage("admin.fly-kapandi")); }
        return true;
    }
}

