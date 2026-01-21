package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpAcceptCommand implements CommandExecutor {
    private final PapazCore plugin;
    public TpAcceptCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        if (!plugin.getTpaManager().hasRequest(player.getUniqueId())) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("tpa.istek-yok")); return true; }
        Player requester = plugin.getTpaManager().getRequester(player.getUniqueId());
        if (requester == null || !requester.isOnline()) {
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("tpa.oyuncu-cevrimdisi"));
            plugin.getTpaManager().removeRequest(player.getUniqueId()); return true;
        }
        requester.teleport(player);
        requester.sendMessage(plugin.getPrefix() + plugin.getMessage("tpa.kabul-edildi").replace("%oyuncu%", player.getName()));
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("tpa.sana-isinlandi").replace("%oyuncu%", requester.getName()));
        requester.playSound(requester.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f);
        plugin.getTpaManager().removeRequest(player.getUniqueId());
        return true;
    }
}

