package org.nulldev.LobbyFly;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
//CI test
    public Main plugin;
    public List<String> viewers = new ArrayList<String>();
    public Logger logger = Logger.getLogger("Minecraft");
    public static Main instance;
    public File f;
    public YamlConfiguration config;
    public Integer item;
    
    public static Main getInstance() {
        return instance;
    }
    
    public void onEnable() {
    	this.checkConfig();
        instance = this;
    	Bukkit.getServer().getPluginManager().registerEvents(this, this);
        PluginDescriptionFile pdfFile = this.getDescription();
        this.logger.info(String.valueOf(String.valueOf(pdfFile.getName())) + " Version: " + pdfFile.getVersion() + " by NullDev for EpticMC has been enabled!");
    }
    
    private void checkConfig() {
        File config = new File(this.getDataFolder(), "config.yml");
        if (!config.exists()) {
            this.saveDefaultConfig();
            this.getLogger().info("config.yml created.");
        }
    }
    
    @EventHandler
    //getTypeId() is depricated but still works fine. New method would be getType() which returns a Material
    @SuppressWarnings("deprecation")
    public void onItem(PlayerMoveEvent e){
        Player p = e.getPlayer();
    	GameMode gm = p.getGameMode();
        //We don't want to disable fly for OP's or people in GM1
    	if(!p.isOp() && !gm.toString().toLowerCase().equals("creative") && !p.hasPermission("lobyfly.bypass")){
            item = Main.getInstance().getConfig().getInt("fly-item-id");
    	    //Check if it's a valid item
    	    if (Material.getMaterial(item) != null && item != null){
                if(p.getItemInHand().getTypeId() == item){
                    if(p.getAllowFlight() == false){
                        p.setAllowFlight(true);
                        p.setFlying(true);
    		    }
    		}
    		else{
    		    if(p.getAllowFlight() == true){
    		        p.setAllowFlight(false);
    			p.setFlying(false);
    		    }
    		}
    	    }
    	    //Use fallback item 399 (NetherStar)
    	    else{
    	        if(p.getItemInHand().getTypeId() == 399){
    		    if(p.getAllowFlight() == false){
    		        p.setAllowFlight(true);
    			p.setFlying(true);
    		    }
    		}
    		else{
    		    if(p.getAllowFlight() == true){
    		        p.setAllowFlight(false);
    			p.setFlying(false);
    		    }
    		}
    		//Telling all OP's (and players with permission lobbyfly.admin that the ID is invalid
    		for(Player op : Bukkit.getOnlinePlayers()){
    	            if(op.hasPermission("lobbyfly.admin") || op.isOp()){
			//This is just one message. No need for a color wrapper (at the moment)
    	                op.sendMessage(ChatColor.GRAY + "[" + ChatColor.AQUA + "Lobby" + ChatColor.DARK_AQUA + "Fly" +
    	                	ChatColor.GRAY + "] " + ChatColor.RED + ChatColor.BOLD + "WARNING" + ChatColor.RESET + 
    	                	": Item ID in config is invalid! Using 399 (NetherStar) instead!");
    	            }
    	        }
            }
    	}
    }
    
    public void onDisable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        this.logger.info(String.valueOf(String.valueOf(pdfFile.getName())) + " Version: " + pdfFile.getVersion() + " by NullDev for EpticMC has been disabled!");
    }
}
