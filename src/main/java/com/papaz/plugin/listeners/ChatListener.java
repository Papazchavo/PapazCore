package com.papaz.plugin.listeners;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {
    private final PapazCore plugin;
    public ChatListener(PapazCore plugin) { this.plugin = plugin; }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        if (!player.hasPermission("papaz.admin")) {
            String lowerMessage = message.toLowerCase();
            if (lowerMessage.contains(".com") || lowerMessage.contains(".net") || 
                lowerMessage.contains(".org") || lowerMessage.contains(".gg") || lowerMessage.contains("discord")) {
                event.setCancelled(true);
                player.sendMessage(plugin.getPrefix() + PapazCore.colorize("&cReklam yapmak yasaktır!"));
                return;
            }
        }
        event.setCancelled(true);
        String format;
        if (player.hasPermission("papaz.admin")) format = "&8[&c&lADMİN&8] &c%s &8» &f%s";
        else if (player.hasPermission("papaz.mod")) format = "&8[&9MOD&8] &9%s &8» &f%s";
        else if (player.hasPermission("papaz.vip")) format = "&8[&6VIP&8] &e%s &8» &f%s";
        else format = "&8[&7Üye&8] &7%s &8» &f%s";
        Bukkit.broadcastMessage(PapazCore.colorize(String.format(format, player.getName(), message)));
    }
}

