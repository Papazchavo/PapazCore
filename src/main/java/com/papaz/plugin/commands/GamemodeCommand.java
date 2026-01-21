package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {
    private final PapazCore plugin;
    public GamemodeCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        Player player = (Player) sender;
        if (!player.hasPermission("papaz.gamemode")) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.yetki-yok")); return true; }
        if (args.length < 1) { player.sendMessage(plugin.getPrefix() + PapazCore.colorize("&cKullanim: /gm <0/1/2/3> [oyuncu]")); return true; }
        
        Player target = player;
        if (args.length > 1) { 
            target = Bukkit.getPlayer(args[1]); 
            if (target == null) { 
                player.sendMessage(plugin.getPrefix() + plugin.getMessage("genel.oyuncu-bulunamadi")); 
                return true; 
            }
        }
        
        GameMode mode;
        String modeName;
        String arg = args[0].toLowerCase();
        
        if (arg.equals("0") || arg.equals("s") || arg.equals("survival")) {
            mode = GameMode.SURVIVAL;
            modeName = "Survival";
        } else if (arg.equals("1") || arg.equals("c") || arg.equals("creative")) {
            mode = GameMode.CREATIVE;
            modeName = "Creative";
        } else if (arg.equals("2") || arg.equals("a") || arg.equals("adventure")) {
            mode = GameMode.ADVENTURE;
            modeName = "Adventure";
        } else if (arg.equals("3") || arg.equals("sp") || arg.equals("spectator")) {
            mode = GameMode.SPECTATOR;
            modeName = "Spectator";
        } else {
            player.sendMessage(plugin.getPrefix() + PapazCore.colorize("&cGecersiz mod! (0/1/2/3)"));
            return true;
        }
        
        target.setGameMode(mode);
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("admin.gamemode").replace("%oyuncu%", target.getName()).replace("%mod%", modeName));
        return true;
    }
}
