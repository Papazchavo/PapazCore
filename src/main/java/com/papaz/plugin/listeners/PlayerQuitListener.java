package com.papaz.plugin.listeners;

import com.papaz.plugin.PapazCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {
    private final PapazCore plugin;
    public PlayerQuitListener(PapazCore plugin) { this.plugin = plugin; }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String quitMsg = plugin.getMessage("hosgeldin.cikis").replace("%oyuncu%", player.getName());
        event.setQuitMessage(plugin.getPrefix() + quitMsg);
        plugin.getTpaManager().removeRequest(player.getUniqueId());
    }
}
