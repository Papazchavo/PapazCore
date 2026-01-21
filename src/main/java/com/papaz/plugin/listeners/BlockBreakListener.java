package com.papaz.plugin.listeners;

import com.papaz.plugin.PapazCore;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {
    private final PapazCore plugin;
    public BlockBreakListener(PapazCore plugin) { this.plugin = plugin; }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Material type = event.getBlock().getType();
        if (isOre(type)) {
            int xp = plugin.getConfig().getInt("seviye.maden-xp", 5);
            plugin.getLevelManager().addXp(player, xp);
            if (isDiamondOre(type)) {
                player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                player.sendMessage(PapazCore.colorize("&b&l* ELMAS BULDUN! *"));
                // Particle effect - works in all versions
                try {
                    player.getWorld().playEffect(event.getBlock().getLocation(), Effect.MOBSPAWNER_FLAMES, 0);
                } catch (Exception ignored) {}
            }
        }
    }

    private boolean isDiamondOre(Material type) {
        String name = type.name();
        return name.equals("DIAMOND_ORE") || name.equals("DEEPSLATE_DIAMOND_ORE");
    }

    private boolean isOre(Material type) {
        String name = type.name();
        return name.contains("_ORE") || name.equals("ANCIENT_DEBRIS");
    }
}
