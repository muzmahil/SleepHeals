package org.muzmahil.sleepheals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class SleepHeals extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }
    public class EventListener implements Listener {

        @EventHandler
        public void playerSleep(PlayerBedEnterEvent player){

            if (player.getBedEnterResult().toString().equals("OK")){
                // Set player health to max. It depends to player's health scale
                Bukkit.getPlayer(player.getPlayer().getUniqueId()).setHealth(player.getPlayer().getHealthScale());
                // When player sleeps play spesific sound at player's location
                Bukkit.getPlayer(player.getPlayer().getUniqueId()).playSound(player.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                // Sends message to player who sleep
                Bukkit.getPlayer(player.getPlayer().getUniqueId()).sendMessage(ChatColor.DARK_GREEN + "[SleepHeals] Your wounds are healed!");

            }

        }
    }
}
