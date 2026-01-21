package com.papaz.plugin.listeners;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDamageListener implements Listener {
    private final PapazCore plugin;
    public PlayerDamageListener(PapazCore plugin) { this.plugin = plugin; }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player victim)) return;
        if (!(event.getDamager() instanceof Player attacker)) return;
        Location spawn = plugin.getDataManager().getSpawnLocation();
        if (spawn != null && plugin.getConfig().getBoolean("spawn-korumasi.aktif", true)) {
            int radius = plugin.getConfig().getInt("spawn-korumasi.yaricap", 10);
            if (victim.getLocation().distance(spawn) <= radius) {
                event.setCancelled(true);
                attacker.sendMessage(plugin.getPrefix() + PapazCore.colorize("&cSpawn'da hasar veremezsin!"));
                return;
            }
        }
        if (!plugin.getDataManager().isPvpEnabled(victim.getUniqueId())) {
            event.setCancelled(true);
            attacker.sendMessage(plugin.getPrefix() + plugin.getMessage("pvp.hedef-kapali").replace("%oyuncu%", victim.getName()));
            return;
        }
        if (!plugin.getDataManager().isPvpEnabled(attacker.getUniqueId())) {
            event.setCancelled(true);
            attacker.sendMessage(plugin.getPrefix() + plugin.getMessage("pvp.senin-kapali"));
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        if (killer == null) return;
        plugin.getDataManager().addKill(killer.getUniqueId());
        plugin.getDataManager().addDeath(victim.getUniqueId());
        int reward = plugin.getConfig().getInt("pvp.oldurme-odulu", 50);
        plugin.getEconomyManager().addMoney(killer.getUniqueId(), reward);
        Bukkit.broadcastMessage(plugin.getPrefix() + plugin.getMessage("pvp.oldurme").replace("%kurban%", victim.getName()).replace("%katil%", killer.getName()));
        killer.sendMessage(plugin.getPrefix() + plugin.getMessage("pvp.odul").replace("%odul%", String.valueOf(reward)).replace("%birim%", plugin.getEconomyManager().getCurrency()));
    }
}

