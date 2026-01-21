package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    private final PapazCore plugin;
    public HealCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        Player player = (Player) sender;
        if (!player.hasPermission("papaz.heal")) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.yetki-yok")); return true; }
        
        Player target = player;
        if (args.length > 0) { 
            target = Bukkit.getPlayer(args[0]); 
            if (target == null) { 
                player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.oyuncu-bulunamadi")); 
                return true; 
            }
        }
        
        target.setHealth(target.getMaxHealth());
        target.setFoodLevel(20);
        target.setSaturation(20f);
        
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("admin.heal").replace("%oyuncu%", target.getName()));
        return true;
    }
}
