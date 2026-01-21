package com.papaz.plugin.managers;

import com.papaz.plugin.PapazCore;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class HomeManager {
    private final PapazCore plugin;
    private final File homesFile;
    private YamlConfiguration homesData;
    private final Map<UUID, Map<String, Location>> homes = new HashMap<>();

    public HomeManager(PapazCore plugin) {
        this.plugin = plugin;
        this.homesFile = new File(plugin.getDataFolder(), "homes.yml");
        loadData();
    }

    private void loadData() {
        if (!homesFile.exists()) {
            try { homesFile.getParentFile().mkdirs(); homesFile.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }
        homesData = YamlConfiguration.loadConfiguration(homesFile);
        if (homesData.contains("homes")) {
            for (String uuidStr : homesData.getConfigurationSection("homes").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidStr);
                Map<String, Location> playerHomes = new HashMap<>();
                for (String homeName : homesData.getConfigurationSection("homes." + uuidStr).getKeys(false)) {
                    Location loc = homesData.getLocation("homes." + uuidStr + "." + homeName);
                    if (loc != null) playerHomes.put(homeName, loc);
                }
                homes.put(uuid, playerHomes);
            }
        }
    }

    public void saveData() {
        for (Map.Entry<UUID, Map<String, Location>> entry : homes.entrySet()) {
            for (Map.Entry<String, Location> homeEntry : entry.getValue().entrySet()) {
                homesData.set("homes." + entry.getKey().toString() + "." + homeEntry.getKey(), homeEntry.getValue());
            }
        }
        try { homesData.save(homesFile); } catch (IOException e) { e.printStackTrace(); }
    }

    public void setHome(UUID uuid, String name, Location location) {
        homes.computeIfAbsent(uuid, k -> new HashMap<>()).put(name.toLowerCase(), location);
        saveData();
    }

    public Location getHome(UUID uuid, String name) {
        Map<String, Location> playerHomes = homes.get(uuid);
        return playerHomes == null ? null : playerHomes.get(name.toLowerCase());
    }

    public boolean deleteHome(UUID uuid, String name) {
        Map<String, Location> playerHomes = homes.get(uuid);
        if (playerHomes == null) return false;
        Location removed = playerHomes.remove(name.toLowerCase());
        if (removed != null) {
            homesData.set("homes." + uuid.toString() + "." + name.toLowerCase(), null);
            saveData();
            return true;
        }
        return false;
    }

    public Set<String> getHomes(UUID uuid) {
        Map<String, Location> playerHomes = homes.get(uuid);
        return playerHomes == null ? Collections.emptySet() : playerHomes.keySet();
    }

    public boolean hasHome(UUID uuid, String name) { return getHome(uuid, name) != null; }
}

