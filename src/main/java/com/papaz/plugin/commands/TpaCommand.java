package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpaCommand implements CommandExecutor {
    private final PapazCore plugin;
    public TpaCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        if (args.length < 1) { player.sendMessage(plugin.getPrefix() + PapazCore.colorize("&cKullanım: /tpa <oyuncu>")); return true; }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.oyuncu-bulunamadi")); return true; }
        if (target.equals(player)) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("tpa.kendine-gonderemezsin")); return true; }
        plugin.getTpaManager().sendRequest(player, target);
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("tpa.gonderildi").replace("%oyuncu%", target.getName()));
        target.sendMessage(plugin.getPrefix() + plugin.getMessage("tpa.istek-aldin").replace("%oyuncu%", player.getName()));
        target.sendMessage(plugin.getPrefix() + plugin.getMessage("tpa.kabul-reddet"));
        return true;
    }
}

