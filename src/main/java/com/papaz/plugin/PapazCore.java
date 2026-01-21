package com.papaz.plugin;

import com.papaz.plugin.commands.*;
import com.papaz.plugin.listeners.*;
import com.papaz.plugin.managers.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.io.File;
import java.util.List;
import java.util.Random;

public class PapazCore extends JavaPlugin {

    private static PapazCore instance;
    private DataManager dataManager;
    private EconomyManager economyManager;
    private LevelManager levelManager;
    private HomeManager homeManager;
    private KitManager kitManager;
    private TpaManager tpaManager;
    private String serverVersion;

    @Override
    public void onEnable() {
        instance = this;
        
        // Sunucu versiyonunu al
        serverVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
        
        saveDefaultConfig();
        saveResource("messages.yml", false);
        
        dataManager = new DataManager(this);
        economyManager = new EconomyManager(this);
        levelManager = new LevelManager(this);
        homeManager = new HomeManager(this);
        kitManager = new KitManager(this);
        tpaManager = new TpaManager(this);
        
        registerCommands();
        registerListeners();
        startAutoMessages();
        startScoreboard();
        
        getLogger().info("========================================");
        getLogger().info("  PapazCore v1.0.0 Aktif!");
        getLogger().info("  Gelistirici: Papaz");
        getLogger().info("  Minecraft: " + serverVersion);
        getLogger().info("========================================");
    }

    @Override
    public void onDisable() {
        if (dataManager != null) dataManager.saveAllData();
        getLogger().info("PapazCore kapatildi!");
    }

    private void registerCommands() {
        getCommand("spawn").setExecutor(new SpawnCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getCommand("sethome").setExecutor(new SetHomeCommand(this));
        getCommand("home").setExecutor(new HomeCommand(this));
        getCommand("delhome").setExecutor(new DelHomeCommand(this));
        getCommand("homes").setExecutor(new HomesCommand(this));
        getCommand("tpa").setExecutor(new TpaCommand(this));
        getCommand("tpaccept").setExecutor(new TpAcceptCommand(this));
        getCommand("tpdeny").setExecutor(new TpDenyCommand(this));
        getCommand("para").setExecutor(new ParaCommand(this));
        getCommand("paragonder").setExecutor(new ParaGonderCommand(this));
        getCommand("kit").setExecutor(new KitCommand(this));
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("heal").setExecutor(new HealCommand(this));
        getCommand("feed").setExecutor(new FeedCommand(this));
        getCommand("gm").setExecutor(new GamemodeCommand(this));
        getCommand("invsee").setExecutor(new InvseeCommand(this));
        getCommand("broadcast").setExecutor(new BroadcastCommand(this));
        getCommand("market").setExecutor(new MarketCommand(this));
        getCommand("seviye").setExecutor(new SeviyeCommand(this));
        getCommand("stats").setExecutor(new StatsCommand(this));
        getCommand("pvp").setExecutor(new PvpCommand(this));
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerQuitListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(this), this);
        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerDamageListener(this), this);
        Bukkit.getPluginManager().registerEvents(new MarketListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerConsumeListener(this), this);
    }

    private void startAutoMessages() {
        if (!getConfig().getBoolean("oto-mesajlar.aktif", true)) return;
        int interval = getConfig().getInt("oto-mesajlar.aralik", 300) * 20;
        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                List<String> messages = getConfig().getStringList("oto-mesajlar.mesajlar");
                if (messages.isEmpty()) return;
                String message = messages.get(new Random().nextInt(messages.size()));
                message = message.replace("%discord%", getConfig().getString("sunucu.discord", ""))
                               .replace("%website%", getConfig().getString("sunucu.website", ""));
                String prefix = getConfig().getString("sunucu.prefix", "&8[&6&l*&8] &e");
                Bukkit.broadcastMessage(colorize(prefix + message));
            }
        }, interval, interval);
    }

    private void startScoreboard() {
        if (!getConfig().getBoolean("scoreboard.aktif", true)) return;
        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    updateScoreboard(player);
                }
            }
        }, 20L, 20L);
    }

    public void updateScoreboard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        if (manager == null) return;
        
        Scoreboard board = manager.getNewScoreboard();
        String title = getConfig().getString("scoreboard.baslik", "&6&l* %sunucu% *")
                .replace("%sunucu%", getConfig().getString("sunucu.isim", "Server"));
        
        Objective obj = board.registerNewObjective("papaz", "dummy");
        obj.setDisplayName(colorize(title));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        int money = economyManager.getMoney(player.getUniqueId());
        int level = levelManager.getLevel(player.getUniqueId());
        int kills = dataManager.getKills(player.getUniqueId());
        
        Score s9 = obj.getScore(colorize("&8&m--------------"));
        s9.setScore(9);
        Score s8 = obj.getScore(colorize("&7Oyuncu: &f" + player.getName()));
        s8.setScore(8);
        Score s7 = obj.getScore(colorize("&7Seviye: &e" + level));
        s7.setScore(7);
        Score s6 = obj.getScore(colorize("&7Para: &6" + money));
        s6.setScore(6);
        Score s5 = obj.getScore(colorize("&7Oldurmeler: &c" + kills));
        s5.setScore(5);
        Score s4 = obj.getScore(colorize("&8&m-------------- "));
        s4.setScore(4);
        Score s3 = obj.getScore(colorize("&7Online: &a" + Bukkit.getOnlinePlayers().size()));
        s3.setScore(3);
        Score s2 = obj.getScore(colorize("&8&m--------------  "));
        s2.setScore(2);
        Score s1 = obj.getScore(colorize("&e" + getConfig().getString("sunucu.discord", "")));
        s1.setScore(1);
        
        player.setScoreboard(board);
    }

    public static PapazCore getInstance() { return instance; }
    public DataManager getDataManager() { return dataManager; }
    public EconomyManager getEconomyManager() { return economyManager; }
    public LevelManager getLevelManager() { return levelManager; }
    public HomeManager getHomeManager() { return homeManager; }
    public KitManager getKitManager() { return kitManager; }
    public TpaManager getTpaManager() { return tpaManager; }
    public String getServerVersion() { return serverVersion; }
    public String getPrefix() { return colorize(getConfig().getString("sunucu.prefix", "&8[&6&l*&8] &e")); }
    public static String colorize(String text) { return ChatColor.translateAlternateColorCodes('&', text); }
    
    public String getMessage(String path) {
        org.bukkit.configuration.file.YamlConfiguration messages = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(
            new File(getDataFolder(), "messages.yml"));
        return colorize(messages.getString(path, "&cMesaj bulunamadi: " + path));
    }
    
    // Version check helper
    public boolean isLegacyVersion() {
        return serverVersion.startsWith("v1_8") || serverVersion.startsWith("v1_9") || 
               serverVersion.startsWith("v1_10") || serverVersion.startsWith("v1_11") || 
               serverVersion.startsWith("v1_12");
    }
}
