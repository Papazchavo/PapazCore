package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HomeCommand implements CommandExecutor {
    private final PapazCore plugin;
    public HomeCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        String homeName = args.length > 0 ? args[0] : "ev";
        Location home = plugin.getHomeManager().getHome(player.getUniqueId(), homeName);
        if (home == null) {
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("ev.bulunamadi").replace("%ev%", homeName));
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("ev.ayarla-bilgi").replace("%ev%", homeName));
            return true;
        }
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("ev.isinlanma"));
        new BukkitRunnable() { @Override public void run() {
            player.teleport(home);
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("ev.isinlandin").replace("%ev%", homeName));
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
        }}.runTaskLater(plugin, 60L);
        return true;
    }
}

