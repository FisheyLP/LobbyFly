package org.nulldev.LobbyFly;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.nulldev.LobbyFly.Main;

public class CmdHandle implements CommandExecutor {

	public ArrayList<String> denylist = new ArrayList<String>();
	public String prefix;
    
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] a) {
    	prefix = Main.getInstance().getConfig().getString("prefix");
		Player p; //p = (Player)sender;
		JSONParser jsonParse = new JSONParser();
        try
        {
        	File jsonData = new File(Main.getDataFolder(), "denylist.json");
            Object obj = jsonParse.parse(new FileReader(jsonData));
            JSONObject jsonObj = (JSONObject)obj;
            JSONArray denied = (JSONArray)jsonObj.get("denied"); //No need for ToLowerCase here 
            for(Object deny : denied)
            {
                denylist.add(deny.toString());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        if (!sender.hasPermission("lobbyfly.admin") || !sender.isOp()) {
            sender.sendMessage(colWrap(prefix + " &cNo permission!"));
            return true;
        }
        if (!(sender instanceof Player)) {
            sender.sendMessage(colWrap(prefix + " &cThis can only be executed ingame!"));
            return true;
        }
        if (a.length < 1 || !a[0].equals("deny") && a[0].equals("denylist") && !a[0].equals("undeny") || a[0].equalsIgnoreCase("help")) {
            sender.sendMessage(colWrap(prefix + " &fHelp Menu:"));
            sender.sendMessage(colWrap("&b----------&9============== &6&lHelp &9==============&b----------"));
            sender.sendMessage(colWrap("&b/lfly help               &f&l| &9Display this help menu"));
            sender.sendMessage(colWrap("&b/lfly deny (username)    &f&l| &9Deny a user from flying"));
            sender.sendMessage(colWrap("&b/lfly undeny (username)  &f&l| &9Allow a user to fly again"));
            sender.sendMessage(colWrap("&b/lfly denylist           &f&l| &9List all denied users"));
            sender.sendMessage(colWrap("&b/lfly status (username)  &f&l| &9See if a user is denied"));
            sender.sendMessage(colWrap("&b------------------------------------------------------"));
            sender.sendMessage(colWrap("&6       &l~ &b&lNOTE: &bYou can use &9/lobbyfly &bor &9/lfly ~"));
            sender.sendMessage(colWrap("This plugin was coded by &a&lNullDev &ffor &c&lEptic&4&lMC&f.com"));
            sender.sendMessage(colWrap("&b======================================================"));
            return true;
        }
        if (a[0].equalsIgnoreCase("deny")) {
            p = (Player)sender;

        }
        if (a[0].equalsIgnoreCase("denylist")) {
            p = (Player)sender;

        }
        if (a[0].equalsIgnoreCase("undeny")) {
            p = (Player)sender;

        }
        return true;
    }
}
