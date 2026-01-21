package com.papaz.plugin.listeners;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinListener implements Listener {
    private final PapazCore plugin;
    public PlayerJoinListener(PapazCore plugin) { this.plugin = plugin; }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        plugin.getEconomyManager().initPlayer(player.getUniqueId());
        plugin.getLevelManager().initPlayer(player.getUniqueId());
        
        String header = PapazCore.colorize("\n&6&l✦ " + plugin.getConfig().getString("sunucu.isim", "Server") + " &6&l✦\n&7Hoş geldin, &f" + player.getName() + "\n");
        String footer = PapazCore.colorize("\n&7Online: &a" + Bukkit.getOnlinePlayers().size() + "\n");
        player.setPlayerListHeaderFooter(header, footer);
        
        if (plugin.getDataManager().isFirstJoin(player.getUniqueId())) {
            plugin.getDataManager().setFirstJoin(player.getUniqueId(), false);
            int startMoney = plugin.getConfig().getInt("hosgeldin.yeni-oyuncu-para", 1000);
            plugin.getEconomyManager().setMoney(player.getUniqueId(), startMoney);
            event.setJoinMessage(null);
            String broadcast = plugin.getMessage("hosgeldin.yeni-oyuncu").replace("%oyuncu%", player.getName());
            Bukkit.broadcastMessage(broadcast);
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                String personal = plugin.getMessage("hosgeldin.yeni-oyuncu-kisisel")
                        .replace("%oyuncu%", player.getName())
                        .replace("%prefix%", plugin.getPrefix())
                        .replace("%discord%", plugin.getConfig().getString("sunucu.discord", ""));
                player.sendMessage(personal);
            }, 20L);
            if (plugin.getConfig().getBoolean("hosgeldin.baslangic-esyalari", true)) {
                player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 16));
                player.getInventory().addItem(new ItemStack(Material.STONE_SWORD));
                player.getInventory().addItem(new ItemStack(Material.STONE_PICKAXE));
                player.getInventory().addItem(new ItemStack(Material.STONE_AXE));
                player.getInventory().addItem(new ItemStack(Material.TORCH, 32));
            }
        } else {
            String joinMsg = plugin.getMessage("hosgeldin.giris").replace("%oyuncu%", player.getName());
            event.setJoinMessage(plugin.getPrefix() + joinMsg);
        }
        plugin.updateScoreboard(player);
    }
}

