package com.papaz.plugin.listeners;

import com.papaz.plugin.PapazCore;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.Particle;
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
            if (type == Material.DIAMOND_ORE || type == Material.DEEPSLATE_DIAMOND_ORE) {
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(PapazCore.colorize("&b&l✦ ELMAS BULDUN! ✦")));
                player.getWorld().spawnParticle(Particle.HAPPY_VILLAGER, event.getBlock().getLocation().add(0.5, 0.5, 0.5), 10);
            }
        }
    }

    private boolean isOre(Material type) {
        return type == Material.COAL_ORE || type == Material.DEEPSLATE_COAL_ORE ||
               type == Material.IRON_ORE || type == Material.DEEPSLATE_IRON_ORE ||
               type == Material.GOLD_ORE || type == Material.DEEPSLATE_GOLD_ORE ||
               type == Material.DIAMOND_ORE || type == Material.DEEPSLATE_DIAMOND_ORE ||
               type == Material.EMERALD_ORE || type == Material.DEEPSLATE_EMERALD_ORE ||
               type == Material.LAPIS_ORE || type == Material.DEEPSLATE_LAPIS_ORE ||
               type == Material.REDSTONE_ORE || type == Material.DEEPSLATE_REDSTONE_ORE ||
               type == Material.COPPER_ORE || type == Material.DEEPSLATE_COPPER_ORE ||
               type == Material.NETHER_GOLD_ORE || type == Material.NETHER_QUARTZ_ORE || type == Material.ANCIENT_DEBRIS;
    }
}

