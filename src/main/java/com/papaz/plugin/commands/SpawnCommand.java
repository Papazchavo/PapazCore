package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpawnCommand implements CommandExecutor {
    private final PapazCore plugin;
    public SpawnCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        Location spawn = plugin.getDataManager().getSpawnLocation();
        if (spawn == null) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("spawn.ayarlanmamis")); return true; }
        int delay = plugin.getConfig().getInt("tpa.isinlanma-gecikmesi", 3);
        Location startLoc = player.getLocation().clone();
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("spawn.isinlanma-basliyor").replace("%sure%", String.valueOf(delay)));
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("spawn.hareket-etme"));
        new BukkitRunnable() {
            int countdown = delay;
            @Override public void run() {
                if (!player.isOnline()) { cancel(); return; }
                if (player.getLocation().distance(startLoc) > 0.5) { player.sendMessage(plugin.getPrefix() + plugin.getMessage("spawn.isinlanma-iptal")); cancel(); return; }
                if (countdown <= 0) { player.teleport(spawn); player.sendMessage(plugin.getPrefix() + plugin.getMessage("spawn.isinlandin")); player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 1.0f); cancel(); return; }
                player.sendMessage(plugin.getPrefix() + PapazCore.colorize("&e" + countdown + "...")); countdown--;
            }
        }.runTaskTimer(plugin, 0L, 20L);
        return true;
    }
}

