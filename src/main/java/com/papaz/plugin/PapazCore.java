package com.papaz.plugin;

import com.papaz.plugin.commands.*;
import com.papaz.plugin.listeners.*;
import com.papaz.plugin.managers.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class PapazCore extends JavaPlugin {

    private static PapazCore instance;
    private DataManager dataManager;
    private EconomyManager economyManager;
    private LevelManager levelManager;
    private HomeManager homeManager;
    private KitManager kitManager;
    private TpaManager tpaManager;

    @Override
    public void onEnable() {
        instance = this;
        
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
        
        getLogger().info("╔═══════════════════════════════════════════╗");
        getLogger().info("║     PapazCore v1.0.0 Aktif!               ║");
        getLogger().info("║     Geliştirici: Papaz                    ║");
        getLogger().info("╚═══════════════════════════════════════════╝");
    }

    @Override
    public void onDisable() {
        if (dataManager != null) dataManager.saveAllData();
        getLogger().info("PapazCore kapatıldı!");
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
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            java.util.List<String> messages = getConfig().getStringList("oto-mesajlar.mesajlar");
            if (messages.isEmpty()) return;
            String message = messages.get(new java.util.Random().nextInt(messages.size()));
            message = message.replace("%discord%", getConfig().getString("sunucu.discord", ""))
                           .replace("%website%", getConfig().getString("sunucu.website", ""));
            String prefix = getConfig().getString("sunucu.prefix", "&8[&6&l★&8] &e");
            Bukkit.broadcastMessage(colorize(prefix + message));
        }, interval, interval);
    }

    private void startScoreboard() {
        if (!getConfig().getBoolean("scoreboard.aktif", true)) return;
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
                updateScoreboard(player);
            }
        }, 20L, 20L);
    }

    public void updateScoreboard(org.bukkit.entity.Player player) {
        org.bukkit.scoreboard.Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        String title = getConfig().getString("scoreboard.baslik", "&6&l✦ %sunucu% ✦")
                .replace("%sunucu%", getConfig().getString("sunucu.isim", "Server"));
        org.bukkit.scoreboard.Objective obj = board.registerNewObjective("papaz", "dummy", colorize(title));
        obj.setDisplaySlot(org.bukkit.scoreboard.DisplaySlot.SIDEBAR);
        
        int money = economyManager.getMoney(player.getUniqueId());
        int level = levelManager.getLevel(player.getUniqueId());
        int kills = dataManager.getKills(player.getUniqueId());
        
        obj.getScore(colorize("&8&m━━━━━━━━━━━━━━━━━")).setScore(9);
        obj.getScore(colorize("&7Oyuncu: &f" + player.getName())).setScore(8);
        obj.getScore(colorize("&7Seviye: &e" + level)).setScore(7);
        obj.getScore(colorize("&7Para: &6" + money + " " + getConfig().getString("ekonomi.para-birimi", "Coin"))).setScore(6);
        obj.getScore(colorize("&7Öldürmeler: &c" + kills)).setScore(5);
        obj.getScore(colorize("&8&m━━━━━━━━━━━━━━━━━ ")).setScore(4);
        obj.getScore(colorize("&7Online: &a" + Bukkit.getOnlinePlayers().size())).setScore(3);
        obj.getScore(colorize("&8&m━━━━━━━━━━━━━━━━━  ")).setScore(2);
        obj.getScore(colorize("&e" + getConfig().getString("sunucu.discord", ""))).setScore(1);
        player.setScoreboard(board);
    }

    public static PapazCore getInstance() { return instance; }
    public DataManager getDataManager() { return dataManager; }
    public EconomyManager getEconomyManager() { return economyManager; }
    public LevelManager getLevelManager() { return levelManager; }
    public HomeManager getHomeManager() { return homeManager; }
    public KitManager getKitManager() { return kitManager; }
    public TpaManager getTpaManager() { return tpaManager; }
    public String getPrefix() { return colorize(getConfig().getString("sunucu.prefix", "&8[&6&l★&8] &e")); }
    public static String colorize(String text) { return ChatColor.translateAlternateColorCodes('&', text); }
    
    public String getMessage(String path) {
        org.bukkit.configuration.file.YamlConfiguration messages = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(
            new java.io.File(getDataFolder(), "messages.yml"));
        return colorize(messages.getString(path, "&cMesaj bulunamadı: " + path));
    }
}

