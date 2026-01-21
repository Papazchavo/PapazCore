package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KitCommand implements CommandExecutor {
    private final PapazCore plugin;
    public KitCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        Player player = (Player) sender;
        
        if (args.length < 1) { 
            player.sendMessage(plugin.getMessage("kit.liste").replace("%prefix%", plugin.getPrefix())); 
            return true; 
        }
        
        String kitName = args[0].toLowerCase();
        
        if (kitName.equals("baslangic") || kitName.equals("başlangıç")) {
            if (!plugin.getKitManager().canUseKit(player.getUniqueId(), "baslangic")) {
                long remaining = plugin.getKitManager().getRemainingCooldown(player.getUniqueId(), "baslangic");
                player.sendMessage(plugin.getPrefix() + plugin.getMessage("kit.bekleme").replace("%sure%", plugin.getKitManager().formatCooldown(remaining))); 
                return true;
            }
            plugin.getKitManager().giveStarterKit(player); 
            plugin.getKitManager().setKitUsed(player.getUniqueId(), "baslangic");
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("kit.aldin").replace("%kit%", "Baslangic"));
        } else if (kitName.equals("savasci") || kitName.equals("savaşçı")) {
            if (!player.hasPermission("papaz.kit.vip")) { 
                player.sendMessage(plugin.getPrefix() + plugin.getMessage("kit.yetki-yok")); 
                return true; 
            }
            if (!plugin.getKitManager().canUseKit(player.getUniqueId(), "savasci")) {
                long remaining = plugin.getKitManager().getRemainingCooldown(player.getUniqueId(), "savasci");
                player.sendMessage(plugin.getPrefix() + plugin.getMessage("kit.bekleme").replace("%sure%", plugin.getKitManager().formatCooldown(remaining))); 
                return true;
            }
            plugin.getKitManager().giveWarriorKit(player); 
            plugin.getKitManager().setKitUsed(player.getUniqueId(), "savasci");
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("kit.aldin").replace("%kit%", "Savasci"));
        } else if (kitName.equals("elmas")) {
            if (!player.hasPermission("papaz.kit.admin")) { 
                player.sendMessage(plugin.getPrefix() + plugin.getMessage("kit.yetki-yok")); 
                return true; 
            }
            plugin.getKitManager().giveDiamondKit(player);
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("kit.aldin").replace("%kit%", "Elmas"));
        } else {
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("kit.bulunamadi"));
        }
        
        return true;
    }
}
