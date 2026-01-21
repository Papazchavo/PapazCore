package com.papaz.plugin.managers;

import com.papaz.plugin.PapazCore;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class LevelManager {
    private final PapazCore plugin;
    private final File levelFile;
    private YamlConfiguration levelData;
    private final Map<UUID, Integer> levels = new HashMap<UUID, Integer>();
    private final Map<UUID, Integer> xp = new HashMap<UUID, Integer>();

    public LevelManager(PapazCore plugin) {
        this.plugin = plugin;
        this.levelFile = new File(plugin.getDataFolder(), "levels.yml");
        loadData();
    }

    private void loadData() {
        if (!levelFile.exists()) {
            try { levelFile.getParentFile().mkdirs(); levelFile.createNewFile(); } catch (IOException e) { e.printStackTrace(); }
        }
        levelData = YamlConfiguration.loadConfiguration(levelFile);
        if (levelData.contains("players")) {
            for (String uuidStr : levelData.getConfigurationSection("players").getKeys(false)) {
                UUID uuid = UUID.fromString(uuidStr);
                levels.put(uuid, levelData.getInt("players." + uuidStr + ".level", 1));
                xp.put(uuid, levelData.getInt("players." + uuidStr + ".xp", 0));
            }
        }
    }

    public void saveData() {
        for (UUID uuid : levels.keySet()) {
            levelData.set("players." + uuid.toString() + ".level", levels.get(uuid));
            Integer xpVal = xp.get(uuid);
            levelData.set("players." + uuid.toString() + ".xp", xpVal != null ? xpVal : 0);
        }
        try { levelData.save(levelFile); } catch (IOException e) { e.printStackTrace(); }
    }

    public int getLevel(UUID uuid) { 
        Integer val = levels.get(uuid);
        return val != null ? val : 1; 
    }
    public int getXp(UUID uuid) { 
        Integer val = xp.get(uuid);
        return val != null ? val : 0; 
    }
    public int getRequiredXp(UUID uuid) { return getLevel(uuid) * 100; }

    public void addXp(Player player, int amount) {
        UUID uuid = player.getUniqueId();
        int currentXp = getXp(uuid) + amount;
        int requiredXp = getRequiredXp(uuid);
        if (currentXp >= requiredXp) {
            currentXp -= requiredXp;
            int newLevel = getLevel(uuid) + 1;
            levels.put(uuid, newLevel);
            int reward = plugin.getConfig().getInt("seviye.seviye-atlama-odulu", 100) * newLevel;
            plugin.getEconomyManager().addMoney(uuid, reward);
            String message = plugin.getMessage("seviye.atladin")
                    .replace("%seviye%", String.valueOf(newLevel))
                    .replace("%odul%", String.valueOf(reward))
                    .replace("%birim%", plugin.getEconomyManager().getCurrency());
            player.sendMessage(message);
            try {
                player.playSound(player.getLocation(), Sound.valueOf("LEVEL_UP"), 1.0f, 1.0f);
            } catch (Exception e) {
                try {
                    player.playSound(player.getLocation(), Sound.valueOf("ENTITY_PLAYER_LEVELUP"), 1.0f, 1.0f);
                } catch (Exception ignored) {}
            }
        }
        xp.put(uuid, currentXp);
        saveData();
    }

    public void initPlayer(UUID uuid) {
        if (!levels.containsKey(uuid)) { levels.put(uuid, 1); xp.put(uuid, 0); saveData(); }
    }
}
