package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StatsCommand implements CommandExecutor {
    private final PapazCore plugin;
    public StatsCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        Player target = player;
        if (args.length > 0) { target = Bukkit.getPlayer(args[0]); if (target == null) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.oyuncu-bulunamadi")); return true; }}
        int kills = plugin.getDataManager().getKills(target.getUniqueId());
        int deaths = plugin.getDataManager().getDeaths(target.getUniqueId());
        double kd = deaths > 0 ? (double) kills / deaths : kills;
        int level = plugin.getLevelManager().getLevel(target.getUniqueId());
        int money = plugin.getEconomyManager().getMoney(target.getUniqueId());
        String currency = plugin.getEconomyManager().getCurrency();
        player.sendMessage(PapazCore.colorize("&8&m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        player.sendMessage(plugin.getPrefix() + PapazCore.colorize("&6" + target.getName() + "'in İstatistikleri:"));
        player.sendMessage(PapazCore.colorize("  &8► &eÖldürmeler: &c" + kills));
        player.sendMessage(PapazCore.colorize("  &8► &eÖlümler: &c" + deaths));
        player.sendMessage(PapazCore.colorize("  &8► &eK/D Oranı: &a" + String.format("%.2f", kd)));
        player.sendMessage(PapazCore.colorize("  &8► &eSeviye: &e" + level));
        player.sendMessage(PapazCore.colorize("  &8► &ePara: &6" + money + " " + currency));
        player.sendMessage(PapazCore.colorize("&8&m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        return true;
    }
}

