package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SeviyeCommand implements CommandExecutor {
    private final PapazCore plugin;
    public SeviyeCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        Player target = player;
        if (args.length > 0) { target = Bukkit.getPlayer(args[0]); if (target == null) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.oyuncu-bulunamadi")); return true; }}
        int level = plugin.getLevelManager().getLevel(target.getUniqueId());
        int xp = plugin.getLevelManager().getXp(target.getUniqueId());
        int required = plugin.getLevelManager().getRequiredXp(target.getUniqueId());
        player.sendMessage(plugin.getMessage("seviye.bilgi").replace("%oyuncu%", target.getName()).replace("%seviye%", String.valueOf(level)).replace("%xp%", String.valueOf(xp)).replace("%gerekli%", String.valueOf(required)).replace("%prefix%", plugin.getPrefix()));
        return true;
    }
}

