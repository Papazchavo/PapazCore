package com.papaz.plugin.managers;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataManager {
    private final PapazCore plugin;
    private final File dataFile;
    private YamlConfiguration data;
    private Location spawnLocation;
    private final Map<UUID, Boolean> pvpEnabled = new HashMap<UUID, Boolean>();
    private final Map<UUID, Integer> kills = new HashMap<UUID, Integer>();
    private final Map<UUID, Integer> deaths = new HashMap<UUID, Integer>();
    private final Map<UUID, Boolean> firstJoin = new HashMap<UUID, Boolean>();

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
        
        // Load spawn
        if (data.contains("spawn.world")) {
            World world = Bukkit.getWorld(data.getString("spawn.world"));
            if (world != null) {
                double x = data.getDouble("spawn.x");
                double y = data.getDouble("spawn.y");
                double z = data.getDouble("spawn.z");
                float yaw = (float) data.getDouble("spawn.yaw");
                float pitch = (float) data.getDouble("spawn.pitch");
                spawnLocation = new Location(world, x, y, z, yaw, pitch);
            }
        }
        
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
        if (spawnLocation != null) {
            data.set("spawn.world", spawnLocation.getWorld().getName());
            data.set("spawn.x", spawnLocation.getX());
            data.set("spawn.y", spawnLocation.getY());
            data.set("spawn.z", spawnLocation.getZ());
            data.set("spawn.yaw", spawnLocation.getYaw());
            data.set("spawn.pitch", spawnLocation.getPitch());
        }
        for (UUID uuid : pvpEnabled.keySet()) {
            String path = "players." + uuid.toString() + ".";
            Boolean pvp = pvpEnabled.get(uuid);
            Integer k = kills.get(uuid);
            Integer d = deaths.get(uuid);
            Boolean fj = firstJoin.get(uuid);
            data.set(path + "pvp", pvp != null ? pvp : false);
            data.set(path + "kills", k != null ? k : 0);
            data.set(path + "deaths", d != null ? d : 0);
            data.set(path + "first-join", fj != null ? fj : true);
        }
        try { data.save(dataFile); } catch (IOException e) { e.printStackTrace(); }
    }

    public Location getSpawnLocation() { return spawnLocation; }
    public void setSpawnLocation(Location location) { this.spawnLocation = location; saveAllData(); }
    
    public boolean isPvpEnabled(UUID uuid) { 
        Boolean val = pvpEnabled.get(uuid);
        return val != null ? val : plugin.getConfig().getBoolean("pvp.varsayilan", false); 
    }
    public void setPvpEnabled(UUID uuid, boolean enabled) { pvpEnabled.put(uuid, enabled); }
    public void togglePvp(UUID uuid) { pvpEnabled.put(uuid, !isPvpEnabled(uuid)); }
    
    public int getKills(UUID uuid) { 
        Integer val = kills.get(uuid);
        return val != null ? val : 0; 
    }
    public void addKill(UUID uuid) { kills.put(uuid, getKills(uuid) + 1); }
    
    public int getDeaths(UUID uuid) { 
        Integer val = deaths.get(uuid);
        return val != null ? val : 0; 
    }
    public void addDeath(UUID uuid) { deaths.put(uuid, getDeaths(uuid) + 1); }
    
    public boolean isFirstJoin(UUID uuid) { 
        Boolean val = firstJoin.get(uuid);
        return val != null ? val : true; 
    }
    public void setFirstJoin(UUID uuid, boolean first) { firstJoin.put(uuid, first); }
}
