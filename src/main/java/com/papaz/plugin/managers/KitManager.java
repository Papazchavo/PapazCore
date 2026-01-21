package com.papaz.plugin.managers;

import com.papaz.plugin.PapazCore;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.*;

public class KitManager {
    private final PapazCore plugin;
    private final Map<UUID, Map<String, Long>> kitCooldowns = new HashMap<>();

    public KitManager(PapazCore plugin) { this.plugin = plugin; }

    public boolean canUseKit(UUID uuid, String kitName) {
        Map<String, Long> playerCooldowns = kitCooldowns.get(uuid);
        if (playerCooldowns == null) return true;
        Long lastUsed = playerCooldowns.get(kitName);
        if (lastUsed == null) return true;
        int cooldownSeconds = plugin.getConfig().getInt("kit." + kitName + ".bekleme", 3600);
        return System.currentTimeMillis() - lastUsed >= cooldownSeconds * 1000L;
    }

    public long getRemainingCooldown(UUID uuid, String kitName) {
        Map<String, Long> playerCooldowns = kitCooldowns.get(uuid);
        if (playerCooldowns == null) return 0;
        Long lastUsed = playerCooldowns.get(kitName);
        if (lastUsed == null) return 0;
        int cooldownSeconds = plugin.getConfig().getInt("kit." + kitName + ".bekleme", 3600);
        long remaining = (cooldownSeconds * 1000L) - (System.currentTimeMillis() - lastUsed);
        return Math.max(0, remaining / 1000);
    }

    public String formatCooldown(long seconds) {
        if (seconds < 60) return seconds + " saniye";
        else if (seconds < 3600) return (seconds / 60) + " dakika " + (seconds % 60) + " saniye";
        else return (seconds / 3600) + " saat " + ((seconds % 3600) / 60) + " dakika";
    }

    public void setKitUsed(UUID uuid, String kitName) {
        kitCooldowns.computeIfAbsent(uuid, k -> new HashMap<>()).put(kitName, System.currentTimeMillis());
    }

    public void giveStarterKit(Player player) {
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD));
        player.getInventory().addItem(new ItemStack(Material.IRON_PICKAXE));
        player.getInventory().addItem(new ItemStack(Material.IRON_AXE));
        player.getInventory().addItem(new ItemStack(Material.COOKED_BEEF, 32));
        player.getInventory().addItem(new ItemStack(Material.TORCH, 64));
    }

    public void giveWarriorKit(Player player) {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.SHARPNESS, 2);
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION, 2);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION, 2);
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        leggings.addEnchantment(Enchantment.PROTECTION, 2);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION, 2);
        player.getInventory().addItem(sword);
        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 64));
    }

    public void giveDiamondKit(Player player) {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        sword.addEnchantment(Enchantment.SHARPNESS, 5);
        sword.addEnchantment(Enchantment.FIRE_ASPECT, 2);
        sword.addEnchantment(Enchantment.UNBREAKING, 3);
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        helmet.addEnchantment(Enchantment.PROTECTION, 4);
        helmet.addEnchantment(Enchantment.UNBREAKING, 3);
        ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        chestplate.addEnchantment(Enchantment.PROTECTION, 4);
        chestplate.addEnchantment(Enchantment.UNBREAKING, 3);
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        leggings.addEnchantment(Enchantment.PROTECTION, 4);
        leggings.addEnchantment(Enchantment.UNBREAKING, 3);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        boots.addEnchantment(Enchantment.PROTECTION, 4);
        boots.addEnchantment(Enchantment.UNBREAKING, 3);
        player.getInventory().addItem(sword);
        player.getInventory().setHelmet(helmet);
        player.getInventory().setChestplate(chestplate);
        player.getInventory().setLeggings(leggings);
        player.getInventory().setBoots(boots);
        player.getInventory().addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 64));
        player.getInventory().addItem(new ItemStack(Material.ELYTRA));
        player.getInventory().addItem(new ItemStack(Material.FIREWORK_ROCKET, 64));
    }
}

