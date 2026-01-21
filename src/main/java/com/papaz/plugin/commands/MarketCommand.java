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
import java.util.ArrayList;
import java.util.List;

public class MarketCommand implements CommandExecutor {
    private final PapazCore plugin;
    public MarketCommand(PapazCore plugin) { this.plugin = plugin; }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) { sender.sendMessage("Bu komutu sadece oyuncular kullanabilir!"); return true; }
        Player player = (Player) sender;
        
        Inventory inv = Bukkit.createInventory(null, 27, PapazCore.colorize("&6&lMarket"));
        ItemStack glass = createItem(getGlassPane(), " ");
        int[] glassSlots = {0, 1, 2, 6, 7, 8, 18, 19, 20, 21, 23, 24, 25, 26};
        for (int i : glassSlots) inv.setItem(i, glass);
        
        String currency = plugin.getEconomyManager().getCurrency();
        inv.setItem(10, createItem(Material.DIAMOND, "&b&lElmas", "&7Fiyat: &e" + plugin.getConfig().getInt("market.elmas.fiyat", 100) + " " + currency, "", "&aSatin almak icin tikla!"));
        inv.setItem(11, createItem(Material.IRON_INGOT, "&7&lDemir", "&7Fiyat: &e" + plugin.getConfig().getInt("market.demir.fiyat", 25) + " " + currency, "", "&aSatin almak icin tikla!"));
        inv.setItem(12, createItem(Material.GOLD_INGOT, "&6&lAltin", "&7Fiyat: &e" + plugin.getConfig().getInt("market.altin.fiyat", 50) + " " + currency, "", "&aSatin almak icin tikla!"));
        inv.setItem(13, createItem(Material.EMERALD, "&a&lZumrut", "&7Fiyat: &e" + plugin.getConfig().getInt("market.zumrut.fiyat", 75) + " " + currency, "", "&aSatin almak icin tikla!"));
        inv.setItem(14, createItem(Material.GOLDEN_APPLE, "&6&lAltin Elma", "&7Fiyat: &e" + plugin.getConfig().getInt("market.altin-elma.fiyat", 200) + " " + currency, "", "&aSatin almak icin tikla!"));
        inv.setItem(15, createEnchantedApple("&d&lTanri Elmasi", "&7Fiyat: &e" + plugin.getConfig().getInt("market.tanri-elmasi.fiyat", 1000) + " " + currency, "", "&aSatin almak icin tikla!"));
        inv.setItem(16, createItem(Material.EXP_BOTTLE, "&a&lXP Sisesi", "&7Fiyat: &e30 " + currency, "", "&aSatin almak icin tikla!"));
        inv.setItem(22, createItem(Material.BARRIER, "&c&lKapat", "&7Marketi kapatmak icin tikla"));
        
        player.openInventory(inv);
        return true;
    }
    
    private Material getGlassPane() {
        try {
            return Material.valueOf("GRAY_STAINED_GLASS_PANE");
        } catch (Exception e) {
            return Material.valueOf("STAINED_GLASS_PANE");
        }
    }

    private ItemStack createItem(Material material, String name, String... lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(PapazCore.colorize(name));
        if (lore.length > 0) {
            List<String> loreList = new ArrayList<String>();
            for (String line : lore) {
                loreList.add(PapazCore.colorize(line));
            }
            meta.setLore(loreList);
        }
        item.setItemMeta(meta);
        return item;
    }
    
    @SuppressWarnings("deprecation")
    private ItemStack createEnchantedApple(String name, String... lore) {
        ItemStack item;
        try {
            Material egapple = Material.valueOf("ENCHANTED_GOLDEN_APPLE");
            item = new ItemStack(egapple);
        } catch (Exception e) {
            item = new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1);
        }
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(PapazCore.colorize(name));
        if (lore.length > 0) {
            List<String> loreList = new ArrayList<String>();
            for (String line : lore) {
                loreList.add(PapazCore.colorize(line));
            }
            meta.setLore(loreList);
        }
        item.setItemMeta(meta);
        return item;
    }
}
