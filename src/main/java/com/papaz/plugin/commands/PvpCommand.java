package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvpCommand implements CommandExecutor {
    private final PapazCore plugin;
    public PvpCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        plugin.getDataManager().togglePvp(player.getUniqueId());
        if (plugin.getDataManager().isPvpEnabled(player.getUniqueId())) player.sendMessage(plugin.getPrefix() + plugin.getMessage("pvp.acildi"));
        else player.sendMessage(plugin.getPrefix() + plugin.getMessage("pvp.kapandi"));
        return true;
    }
}

