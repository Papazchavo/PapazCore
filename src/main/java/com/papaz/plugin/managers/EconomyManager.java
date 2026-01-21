package com.papaz.plugin.managers;

import com.papaz.plugin.PapazCore;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class EconomyManager {
    private final PapazCore plugin;
    private final File economyFile;
    private YamlConfiguration economyData;
    private final Map<UUID, Integer> balances = new HashMap<UUID, Integer>();

    public EconomyManager(PapazCore plugin) {
        this.plugin = plugin;
        this.economyFile = new File(plugin.getDataFolder(), "economy.yml");
        loadData();
    }

    private void loadData() {
        if (!economyFile.exists()) {
            try { economyFile.getParentFile().mkdirs(); economyFile.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }
        economyData = YamlConfiguration.loadConfiguration(economyFile);
        if (economyData.contains("balances")) {
            for (String uuidStr : economyData.getConfigurationSection("balances").getKeys(false)) {
                balances.put(UUID.fromString(uuidStr), economyData.getInt("balances." + uuidStr));
            }
        }
    }

    public void saveData() {
        for (Map.Entry<UUID, Integer> entry : balances.entrySet()) {
            economyData.set("balances." + entry.getKey().toString(), entry.getValue());
        }
        try { economyData.save(economyFile); } catch (IOException e) { e.printStackTrace(); }
    }

    public int getMoney(UUID uuid) { 
        Integer val = balances.get(uuid);
        return val != null ? val : 0; 
    }
    public void setMoney(UUID uuid, int amount) { balances.put(uuid, Math.max(0, amount)); saveData(); }
    public void addMoney(UUID uuid, int amount) { setMoney(uuid, getMoney(uuid) + amount); }
    public void removeMoney(UUID uuid, int amount) { setMoney(uuid, getMoney(uuid) - amount); }
    public boolean hasMoney(UUID uuid, int amount) { return getMoney(uuid) >= amount; }
    public void initPlayer(UUID uuid) {
        if (!balances.containsKey(uuid)) {
            balances.put(uuid, plugin.getConfig().getInt("ekonomi.baslangic-parasi", 1000));
            saveData();
        }
    }
    public String getCurrency() { return plugin.getConfig().getString("ekonomi.para-birimi", "Coin"); }
}
