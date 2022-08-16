package me.tim.testplugin;

import org.bukkit.ChatColor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{
	
	private testplugin main;
	
	public Commands(testplugin plugin) {
		main = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Je bent geen speler je moet een speler zijn voor dit command");
			return false;
	    }
		Player p = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("welcome")) {
			if(args.length >= 1) {
				if(args[0].equalsIgnoreCase("get")) {
					String  loginMessage = main.getConfig().getString("inlogmessage " + p.getName());
					
					if(loginMessage !=null) {
						p.sendMessage(loginMessage);
					} else {
						p.sendMessage("§cEr is nog geen login message opgegeven");
					}
				} else if(args[0].equalsIgnoreCase("set")) {
					String loginMessage = "";
					
					for(int i = 1; i < args.length; i++) {
						loginMessage = loginMessage + args[i] + " ";
					}
					loginMessage = loginMessage.replaceAll("&","§");
					main.getConfig().set("inlogmessage " + p.getName(), loginMessage);
					main.saveConfig();
					p.sendMessage("§aInlogbericht veranderd naar: §f" + loginMessage);
					
					
				} else {
					p.sendMessage(ChatColor.RED + "Gebruik /welcome set of /welcome get");
					
				
				}
			} else {
				p.sendMessage(ChatColor.RED + "Gebruik /welcome set of /welcome get");
			}
			
		}
		return false;
	}
}
