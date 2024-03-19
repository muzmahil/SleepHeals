package org.muzmahil.sleepheals;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;


public final class SleepHeals extends JavaPlugin implements Listener {

    ConfigSystem config = new ConfigSystem();
    LangSystem lang = new LangSystem();

    @Override
    public void onEnable() {
        if (!config.checkSum()){
            saveResource("config.yml",false);
        }
        if (!lang.checkSum()){
            saveResource("lang.yml",true);
        }
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new EventListener(), this);

    }

    public class EventListener implements Listener {

        @EventHandler
        public void playerSleep(PlayerBedEnterEvent player){


            if (getConfig().getString("enable").toString().equals("true")) {

                if (player.getBedEnterResult().toString().equals("OK")){

                    if (((int)player.getPlayer().getHealth()) != ((int)player.getPlayer().getHealthScale())){
                        // Set player health to max. It depends to player's health scale
                        player.getPlayer().setHealth(player.getPlayer().getHealthScale());
                        // When player sleeps play spesific sound at player's location
                        player.getPlayer().playSound(player.getPlayer().getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                        // Sends message to player who sleep
                        Bukkit.getPlayer(player.getPlayer().getUniqueId()).sendMessage(ChatColor.DARK_GREEN + "[SleepHeals] "+lang.get("message"));
                    }


                }

            }



        }
    }

    class LangSystem{
        ConfigSystem config = new ConfigSystem();
        public File langFile = new File(getDataFolder(),"lang.yml");
        public FileConfiguration langData = YamlConfiguration.loadConfiguration(langFile);

        public FileConfiguration getlangData(){
            return langData;
        }
       public String get(String value){
            return getlangData().getString(config.getConfig().getString("language")+"."+value);
       }

        public boolean checkSum(){
            if (!langFile.exists()){
                return false;
            }
            else{
                return true;
            }
        }
    }
    class ConfigSystem {

        private File configFile = new File(getDataFolder(),"config.yml");
        public FileConfiguration configData = YamlConfiguration.loadConfiguration(configFile);

        public FileConfiguration getConfig() {
            return configData;
        }
        public File getConfigFile() {
            return configFile;
        }
        public boolean checkSum(){
            if (configFile.exists()){
                return true;
            }
            else{
                return false;
            }
        }
    }

}
