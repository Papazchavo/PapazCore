package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParaCommand implements CommandExecutor {
    private final PapazCore plugin;
    public ParaCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        Player target = player;
        if (args.length > 0) { target = Bukkit.getPlayer(args[0]); if (target == null) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.oyuncu-bulunamadi")); return true; }}
        int money = plugin.getEconomyManager().getMoney(target.getUniqueId());
        String currency = plugin.getEconomyManager().getCurrency();
        player.sendMessage(plugin.getMessage("ekonomi.bakiye").replace("%oyuncu%", target.getName()).replace("%para%", String.valueOf(money)).replace("%birim%", currency).replace("%prefix%", plugin.getPrefix()));
        return true;
    }
}

