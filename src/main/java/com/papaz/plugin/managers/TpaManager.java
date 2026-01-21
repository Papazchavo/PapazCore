package com.papaz.plugin.managers;

import com.papaz.plugin.PapazCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.*;

public class TpaManager {
    private final PapazCore plugin;
    private final Map<UUID, UUID> tpaRequests = new HashMap<>();
    private final Map<UUID, Long> requestTimes = new HashMap<>();

    public TpaManager(PapazCore plugin) {
        this.plugin = plugin;
        Bukkit.getScheduler().runTaskTimer(plugin, this::checkTimeouts, 20L, 20L);
    }

    public void sendRequest(Player requester, Player target) {
        tpaRequests.put(target.getUniqueId(), requester.getUniqueId());
        requestTimes.put(target.getUniqueId(), System.currentTimeMillis());
    }

    public boolean hasRequest(UUID targetUuid) { return tpaRequests.containsKey(targetUuid); }

    public Player getRequester(UUID targetUuid) {
        UUID requesterUuid = tpaRequests.get(targetUuid);
        return requesterUuid == null ? null : Bukkit.getPlayer(requesterUuid);
    }

    public void removeRequest(UUID targetUuid) {
        tpaRequests.remove(targetUuid);
        requestTimes.remove(targetUuid);
    }

    private void checkTimeouts() {
        int timeout = plugin.getConfig().getInt("tpa.bekleme-suresi", 60) * 1000;
        long now = System.currentTimeMillis();
        tpaRequests.entrySet().removeIf(entry -> {
            Long requestTime = requestTimes.get(entry.getKey());
            if (requestTime == null) return true;
            if (now - requestTime > timeout) {
                Player requester = Bukkit.getPlayer(entry.getValue());
                if (requester != null) requester.sendMessage(plugin.getPrefix() + plugin.getMessage("tpa.zaman-asimi"));
                requestTimes.remove(entry.getKey());
                return true;
            }
            return false;
        });
    }
}

