package com.papaz.plugin.commands;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class MarketCommand implements CommandExecutor {
    private final PapazCore plugin;
    public MarketCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        Inventory inv = Bukkit.createInventory(null, 27, PapazCore.colorize("&6&lMarket"));
        ItemStack glass = createItem(Material.GRAY_STAINED_GLASS_PANE, " ");
        for (int i : new int[]{0, 1, 2, 6, 7, 8, 18, 19, 20, 21, 23, 24, 25, 26}) inv.setItem(i, glass);
        String currency = plugin.getEconomyManager().getCurrency();
        inv.setItem(10, createItem(Material.DIAMOND, "&b&lElmas", "&7Fiyat: &e" + plugin.getConfig().getInt("market.elmas.fiyat", 100) + " " + currency, "", "&aSatın almak için tıkla!"));
        inv.setItem(11, createItem(Material.IRON_INGOT, "&7&lDemir", "&7Fiyat: &e" + plugin.getConfig().getInt("market.demir.fiyat", 25) + " " + currency, "", "&aSatın almak için tıkla!"));
        inv.setItem(12, createItem(Material.GOLD_INGOT, "&6&lAltın", "&7Fiyat: &e" + plugin.getConfig().getInt("market.altin.fiyat", 50) + " " + currency, "", "&aSatın almak için tıkla!"));
        inv.setItem(13, createItem(Material.EMERALD, "&a&lZümrüt", "&7Fiyat: &e" + plugin.getConfig().getInt("market.zumrut.fiyat", 75) + " " + currency, "", "&aSatın almak için tıkla!"));
        inv.setItem(14, createItem(Material.GOLDEN_APPLE, "&6&lAltın Elma", "&7Fiyat: &e" + plugin.getConfig().getInt("market.altin-elma.fiyat", 200) + " " + currency, "", "&aSatın almak için tıkla!"));
        inv.setItem(15, createItem(Material.ENCHANTED_GOLDEN_APPLE, "&d&lTanrı Elması", "&7Fiyat: &e" + plugin.getConfig().getInt("market.tanri-elmasi.fiyat", 1000) + " " + currency, "", "&aSatın almak için tıkla!"));
        inv.setItem(16, createItem(Material.EXPERIENCE_BOTTLE, "&a&lXP Şişesi", "&7Fiyat: &e30 " + currency, "", "&aSatın almak için tıkla!"));
        inv.setItem(22, createItem(Material.BARRIER, "&c&lKapat", "&7Marketi kapatmak için tıkla"));
        player.openInventory(inv);
        return true;
    }

    private ItemStack createItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(PapazCore.colorize(name));
        if (lore.length > 0) meta.setLore(Arrays.stream(lore).map(PapazCore::colorize).toList());
        item.setItemMeta(meta);
        return item;
    }
}

