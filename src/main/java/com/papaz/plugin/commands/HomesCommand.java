package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.Set;

public class HomesCommand implements CommandExecutor {
    private final PapazCore plugin;
    public HomesCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        Set<String> homes = plugin.getHomeManager().getHomes(player.getUniqueId());
        player.sendMessage(PapazCore.colorize("&8&m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("ev.liste-baslik"));
        if (homes.isEmpty()) player.sendMessage(PapazCore.colorize("  &7Henüz evin yok!"));
        else for (String home : homes) player.sendMessage(PapazCore.colorize("  &8► &e" + home));
        player.sendMessage(PapazCore.colorize("&8&m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"));
        return true;
    }
}

