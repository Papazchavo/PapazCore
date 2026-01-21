package com.papaz.plugin.listeners;

import com.papaz.plugin.PapazCore;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MarketListener implements Listener {
    private final PapazCore plugin;
    public MarketListener(PapazCore plugin) { this.plugin = plugin; }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        Player player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();
        if (!PapazCore.colorize("&6&lMarket").equals(title)) return;
        
        event.setCancelled(true);
        int slot = event.getRawSlot();
        String currency = plugin.getEconomyManager().getCurrency();
        
        if (slot == 10) buyItem(player, Material.DIAMOND, "elmas", 100, currency);
        else if (slot == 11) buyItem(player, Material.IRON_INGOT, "demir", 25, currency);
        else if (slot == 12) buyItem(player, Material.GOLD_INGOT, "altin", 50, currency);
        else if (slot == 13) buyItem(player, Material.EMERALD, "zumrut", 75, currency);
        else if (slot == 14) buyItem(player, Material.GOLDEN_APPLE, "altin-elma", 200, currency);
        else if (slot == 15) buyEnchantedApple(player, 1000, currency);
        else if (slot == 16) buyItem(player, Material.EXP_BOTTLE, "xp-sisesi", 30, currency);
        else if (slot == 22) player.closeInventory();
    }

    private void buyItem(Player player, Material material, String itemName, int defaultPrice, String currency) {
        int price = plugin.getConfig().getInt("market." + itemName.replace("-", "") + ".fiyat", defaultPrice);
        if (!plugin.getEconomyManager().hasMoney(player.getUniqueId(), price)) {
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("market.yetersiz-bakiye").replace("%fiyat%", String.valueOf(price)).replace("%birim%", currency));
            playNoSound(player);
            return;
        }
        plugin.getEconomyManager().removeMoney(player.getUniqueId(), price);
        player.getInventory().addItem(new ItemStack(material));
        String displayName = getDisplayName(itemName);
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("market.satin-aldin").replace("%esya%", displayName).replace("%fiyat%", String.valueOf(price)).replace("%birim%", currency));
        playYesSound(player);
    }
    
    @SuppressWarnings("deprecation")
    private void buyEnchantedApple(Player player, int defaultPrice, String currency) {
        int price = plugin.getConfig().getInt("market.tanrielmasi.fiyat", defaultPrice);
        if (!plugin.getEconomyManager().hasMoney(player.getUniqueId(), price)) {
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("market.yetersiz-bakiye").replace("%fiyat%", String.valueOf(price)).replace("%birim%", currency));
            playNoSound(player);
            return;
        }
        plugin.getEconomyManager().removeMoney(player.getUniqueId(), price);
        
        // Try new material first, then fall back to 1.8 style
        try {
            Material egapple = Material.valueOf("ENCHANTED_GOLDEN_APPLE");
            player.getInventory().addItem(new ItemStack(egapple));
        } catch (Exception e) {
            player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1, (short) 1));
        }
        
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("market.satin-aldin").replace("%esya%", "Tanri Elmasi").replace("%fiyat%", String.valueOf(price)).replace("%birim%", currency));
        playYesSound(player);
    }
    
    private String getDisplayName(String itemName) {
        if (itemName.equals("elmas")) return "Elmas";
        if (itemName.equals("demir")) return "Demir";
        if (itemName.equals("altin")) return "Altin";
        if (itemName.equals("zumrut")) return "Zumrut";
        if (itemName.equals("altin-elma")) return "Altin Elma";
        if (itemName.equals("xp-sisesi")) return "XP Sisesi";
        return itemName;
    }
    
    private void playYesSound(Player player) {
        try {
            player.playSound(player.getLocation(), Sound.valueOf("VILLAGER_YES"), 1.0f, 1.0f);
        } catch (Exception e) {
            try {
                player.playSound(player.getLocation(), Sound.valueOf("ENTITY_VILLAGER_YES"), 1.0f, 1.0f);
            } catch (Exception ignored) {}
        }
    }
    
    private void playNoSound(Player player) {
        try {
            player.playSound(player.getLocation(), Sound.valueOf("VILLAGER_NO"), 1.0f, 1.0f);
        } catch (Exception e) {
            try {
                player.playSound(player.getLocation(), Sound.valueOf("ENTITY_VILLAGER_NO"), 1.0f, 1.0f);
            } catch (Exception ignored) {}
        }
    }
}
