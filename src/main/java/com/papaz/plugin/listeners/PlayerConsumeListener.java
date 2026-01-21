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
            player.sendTitle(PapazCore.colorize("&6&l✦ GÜÇLENDİN! ✦"), PapazCore.colorize("&eAltın elma yedin!"), 10, 40, 10);
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        } else if (type == Material.ENCHANTED_GOLDEN_APPLE) {
            player.sendTitle(PapazCore.colorize("&6&l⚡ TANRI ELMASI! ⚡"), PapazCore.colorize("&eİnanılmaz güçlendin!"), 10, 60, 10);
            player.playSound(player.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
            player.getWorld().strikeLightningEffect(player.getLocation());
        }
    }
}

