package com.papaz.plugin.listeners;

import com.papaz.plugin.PapazCore;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class PlayerConsumeListener implements Listener {
    private final PapazCore plugin;
    public PlayerConsumeListener(PapazCore plugin) { this.plugin = plugin; }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        Material type = event.getItem().getType();
        
        if (type == Material.GOLDEN_APPLE) {
            // Check if enchanted (1.8 uses data value, newer versions use different material)
            boolean isEnchanted = false;
            try {
                if (type.name().equals("ENCHANTED_GOLDEN_APPLE")) {
                    isEnchanted = true;
                } else if (event.getItem().getDurability() == 1) {
                    // 1.8 style enchanted golden apple
                    isEnchanted = true;
                }
            } catch (Exception ignored) {}
            
            if (isEnchanted) {
                player.sendMessage(PapazCore.colorize("&6&l* TANRI ELMASI! *"));
                player.sendMessage(PapazCore.colorize("&eInanilmaz guclendin!"));
                try {
                    player.playSound(player.getLocation(), Sound.valueOf("LEVEL_UP"), 1.0f, 1.0f);
                } catch (Exception e) {
                    try {
                        player.playSound(player.getLocation(), Sound.valueOf("ENTITY_PLAYER_LEVELUP"), 1.0f, 1.0f);
                    } catch (Exception ignored) {}
                }
            } else {
                player.sendMessage(PapazCore.colorize("&6&l* GUCLENDIN! *"));
                player.sendMessage(PapazCore.colorize("&eAltin elma yedin!"));
                try {
                    player.playSound(player.getLocation(), Sound.valueOf("LEVEL_UP"), 1.0f, 1.0f);
                } catch (Exception e) {
                    try {
                        player.playSound(player.getLocation(), Sound.valueOf("ENTITY_PLAYER_LEVELUP"), 1.0f, 1.0f);
                    } catch (Exception ignored) {}
                }
            }
        }
        
        // Check for ENCHANTED_GOLDEN_APPLE material (1.9+)
        if (type.name().equals("ENCHANTED_GOLDEN_APPLE")) {
            player.sendMessage(PapazCore.colorize("&6&l* TANRI ELMASI! *"));
            player.sendMessage(PapazCore.colorize("&eInanilmaz guclendin!"));
        }
    }
}
