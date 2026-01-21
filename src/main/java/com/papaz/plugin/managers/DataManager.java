package com.papaz.plugin.managers;

import com.papaz.plugin.PapazCore;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataManager {
    private final PapazCore plugin;
    private final File dataFile;
    private YamlConfiguration data;
    private Location spawnLocation;
    private final Map<UUID, Boolean> pvpEnabled = new HashMap<>();
    private final Map<UUID, Integer> kills = new HashMap<>();
    private final Map<UUID, Integer> deaths = new HashMap<>();
    private final Map<UUID, Boolean> firstJoin = new HashMap<>();

    public DataManager(PapazCore plugin) {
        this.plugin = plugin;
        this.dataFile = new File(plugin.getDataFolder(), "data.yml");
        loadData();
    }

    private void loadData() {
        if (!dataFile.exists()) {
            try { dataFile.getParentFile().mkdirs(); dataFile.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }
        data = YamlConfiguration.loadConfiguration(dataFile);
        if (data.contains("spawn")) spawnLocation = data.getLocation("spawn");
        if (data.contains("players")) {
            for (String uuidStr : data.getConfigurationSection("players").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidStr);
                String path = "players." + uuidStr + ".";
                pvpEnabled.put(uuid, data.getBoolean(path + "pvp", false));
                kills.put(uuid, data.getInt(path + "kills", 0));
                deaths.put(uuid, data.getInt(path + "deaths", 0));
                firstJoin.put(uuid, data.getBoolean(path + "first-join", true));
            }
        }
    }

    public void saveAllData() {
        if (spawnLocation != null) data.set("spawn", spawnLocation);
        for (UUID uuid : pvpEnabled.keySet()) {
            String path = "players." + uuid.toString() + ".";
            data.set(path + "pvp", pvpEnabled.getOrDefault(uuid, false));
            data.set(path + "kills", kills.getOrDefault(uuid, 0));
            data.set(path + "deaths", deaths.getOrDefault(uuid, 0));
            data.set(path + "first-join", firstJoin.getOrDefault(uuid, true));
        }
        try { data.save(dataFile); } catch (IOException e) { e.printStackTrace(); }
    }

    public Location getSpawnLocation() { return spawnLocation; }
    public void setSpawnLocation(Location location) { this.spawnLocation = location; saveAllData(); }
    public boolean isPvpEnabled(UUID uuid) { return pvpEnabled.getOrDefault(uuid, plugin.getConfig().getBoolean("pvp.varsayilan", false)); }
    public void setPvpEnabled(UUID uuid, boolean enabled) { pvpEnabled.put(uuid, enabled); }
    public void togglePvp(UUID uuid) { pvpEnabled.put(uuid, !isPvpEnabled(uuid)); }
    public int getKills(UUID uuid) { return kills.getOrDefault(uuid, 0); }
    public void addKill(UUID uuid) { kills.put(uuid, getKills(uuid) + 1); }
    public int getDeaths(UUID uuid) { return deaths.getOrDefault(uuid, 0); }
    public void addDeath(UUID uuid) { deaths.put(uuid, getDeaths(uuid) + 1); }
    public boolean isFirstJoin(UUID uuid) { return firstJoin.getOrDefault(uuid, true); }
    public void setFirstJoin(UUID uuid, boolean first) { firstJoin.put(uuid, first); }
}

