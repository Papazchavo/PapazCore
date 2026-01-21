package com.papaz.plugin.managers;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class HomeManager {
    private final PapazCore plugin;
    private final File homesFile;
    private YamlConfiguration homesData;
    private final Map<UUID, Map<String, Location>> homes = new HashMap<UUID, Map<String, Location>>();

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
                Map<String, Location> playerHomes = new HashMap<String, Location>();
                for (String homeName : homesData.getConfigurationSection("homes." + uuidStr).getKeys(false)) {
                    String path = "homes." + uuidStr + "." + homeName + ".";
                    World world = Bukkit.getWorld(homesData.getString(path + "world"));
                    if (world != null) {
                        double x = homesData.getDouble(path + "x");
                        double y = homesData.getDouble(path + "y");
                        double z = homesData.getDouble(path + "z");
                        float yaw = (float) homesData.getDouble(path + "yaw");
                        float pitch = (float) homesData.getDouble(path + "pitch");
                        playerHomes.put(homeName, new Location(world, x, y, z, yaw, pitch));
                    }
                }
                homes.put(uuid, playerHomes);
            }
        }
    }

    public void saveData() {
        for (Map.Entry<UUID, Map<String, Location>> entry : homes.entrySet()) {
            for (Map.Entry<String, Location> homeEntry : entry.getValue().entrySet()) {
                String path = "homes." + entry.getKey().toString() + "." + homeEntry.getKey() + ".";
                Location loc = homeEntry.getValue();
                homesData.set(path + "world", loc.getWorld().getName());
                homesData.set(path + "x", loc.getX());
                homesData.set(path + "y", loc.getY());
                homesData.set(path + "z", loc.getZ());
                homesData.set(path + "yaw", loc.getYaw());
                homesData.set(path + "pitch", loc.getPitch());
            }
        }
        try { homesData.save(homesFile); } catch (IOException e) { e.printStackTrace(); }
    }

    public void setHome(UUID uuid, String name, Location location) {
        Map<String, Location> playerHomes = homes.get(uuid);
        if (playerHomes == null) {
            playerHomes = new HashMap<String, Location>();
            homes.put(uuid, playerHomes);
        }
        playerHomes.put(name.toLowerCase(), location);
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
        return playerHomes == null ? Collections.<String>emptySet() : playerHomes.keySet();
    }

    public boolean hasHome(UUID uuid, String name) { return getHome(uuid, name) != null; }
}
