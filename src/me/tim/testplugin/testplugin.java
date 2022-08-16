package me.tim.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

import org.bukkit.plugin.java.JavaPlugin;


public class testplugin extends JavaPlugin implements CommandExecutor{

		public void onEnable() {
		getCommand("hallo").setExecutor(this);
		getCommand("nick").setExecutor(this);
		getCommand("ping").setExecutor(this);
		getCommand("c").setExecutor(this);
		getCommand("spawn").setExecutor(this);
		getCommand("welcome").setExecutor(new Commands(this));
		getCommand("listheader").setExecutor(this);
		 
		Bukkit.getPluginManager().registerEvents(new Events(this), this);
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Je bent geen speler je moet een speler zijn voor dit command!");
			return false;
	    }
	
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("c")) {
			if(p.getGameMode() == GameMode.SURVIVAL) {
				Location location = p.getLocation();
				getConfig().set("GState" + p.getName(), location);
				saveConfig();
				p.setGameMode(GameMode.SPECTATOR);
				return true;
			}
			if(p.getGameMode() == GameMode.SPECTATOR ) {
				Location GState = (Location) getConfig().get("GState" + p.getName());
				p.teleport(GState);
				p.setGameMode(GameMode.SURVIVAL);
			}
		}
		if(cmd.getName().equalsIgnoreCase("hallo")) {
			p.sendMessage(ChatColor.GREEN + "Hallo " + p.getName() + ", welkom op de server!");
		}
		if(cmd.getName().equalsIgnoreCase("ping")) {
			p.sendMessage(ChatColor.WHITE + "pong!");
		}
		if(cmd.getName().equalsIgnoreCase("spawn")) {
			p.chat("/trigger spawn");
		}
		if(cmd.getName().equalsIgnoreCase("nick")) {
			
			p.setDisplayName(args[0]);
			p.setPlayerListName(args[0]);
			p.setCustomName(args[0]);
			p.setCustomNameVisible(true);
			
		}
		if(cmd.getName().equalsIgnoreCase("listheader")) {
			p.setPlayerListHeader(args[0]);
		}
	
	
	    
		
	
		
		
        return false;
		
	}

	
}