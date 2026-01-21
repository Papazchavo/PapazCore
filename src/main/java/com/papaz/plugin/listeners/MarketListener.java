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
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (!PapazCore.colorize("&6&lMarket").equals(event.getView().getTitle())) return;
        event.setCancelled(true);
        int slot = event.getRawSlot();
        String currency = plugin.getEconomyManager().getCurrency();
        switch (slot) {
            case 10 -> buyItem(player, Material.DIAMOND, "elmas", 100, currency);
            case 11 -> buyItem(player, Material.IRON_INGOT, "demir", 25, currency);
            case 12 -> buyItem(player, Material.GOLD_INGOT, "altin", 50, currency);
            case 13 -> buyItem(player, Material.EMERALD, "zumrut", 75, currency);
            case 14 -> buyItem(player, Material.GOLDEN_APPLE, "altin-elma", 200, currency);
            case 15 -> buyItem(player, Material.ENCHANTED_GOLDEN_APPLE, "tanri-elmasi", 1000, currency);
            case 16 -> buyItem(player, Material.EXPERIENCE_BOTTLE, "xp-sisesi", 30, currency);
            case 22 -> player.closeInventory();
        }
    }

    private void buyItem(Player player, Material material, String itemName, int defaultPrice, String currency) {
        int price = plugin.getConfig().getInt("market." + itemName.replace("-", "") + ".fiyat", defaultPrice);
        if (!plugin.getEconomyManager().hasMoney(player.getUniqueId(), price)) {
            player.sendMessage(plugin.getPrefix() + plugin.getMessage("market.yetersiz-bakiye").replace("%fiyat%", String.valueOf(price)).replace("%birim%", currency));
            player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
            return;
        }
        plugin.getEconomyManager().removeMoney(player.getUniqueId(), price);
        player.getInventory().addItem(new ItemStack(material));
        String displayName = switch (itemName) {
            case "elmas" -> "Elmas"; case "demir" -> "Demir"; case "altin" -> "Altın"; case "zumrut" -> "Zümrüt";
            case "altin-elma" -> "Altın Elma"; case "tanri-elmasi" -> "Tanrı Elması"; case "xp-sisesi" -> "XP Şişesi"; default -> itemName;
        };
        player.sendMessage(plugin.getPrefix() + plugin.getMessage("market.satin-aldin").replace("%esya%", displayName).replace("%fiyat%", String.valueOf(price)).replace("%birim%", currency));
        player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_YES, 1.0f, 1.0f);
    }
}

