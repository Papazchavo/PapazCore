package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParaGonderCommand implements CommandExecutor {
    private final PapazCore plugin;
    public ParaGonderCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        if (args.length < 2) { player.sendMessage(plugin.getPrefix() + PapazCore.colorize("&cKullanım: /paragonder <oyuncu> <miktar>")); return true; }
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.oyuncu-bulunamadi")); return true; }
        if (target.equals(player)) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("ekonomi.kendine-gonderemezsin")); return true; }
        int amount;
        try { amount = Integer.parseInt(args[1]); } catch (NumberFormatException e) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.gecersiz-miktar")); return true; }
        if (amount <= 0) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.gecersiz-miktar")); return true; }
        if (!plugin.getEconomyManager().hasMoney(player.getUniqueId(), amount)) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("ekonomi.yetersiz-bakiye")); return true; }
        String currency = plugin.getEconomyManager().getCurrency();
        plugin.getEconomyManager().removeMoney(player.getUniqueId(), amount);
        plugin.getEconomyManager().addMoney(target.getUniqueId(), amount);
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("ekonomi.gonderildi").replace("%alici%", target.getName()).replace("%miktar%", String.valueOf(amount)).replace("%birim%", currency));
        target.sendMessage(plugin.getPrefix() + plugin.getMessage("ekonomi.aldin").replace("%gonderen%", player.getName()).replace("%miktar%", String.valueOf(amount)).replace("%birim%", currency));
        return true;
    }
}

