package me.tim.testplugin;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;








public class Events implements Listener {
	
	private testplugin main;
	
	public Events(testplugin plugin) {
		this.main = plugin;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		String inlogMessage = main.getConfig().getString("inlogmessage " + player.getName());
		if(inlogMessage != null) {
			player.sendMessage(inlogMessage);
		} else {
			player.sendMessage("§aWelkom op deze §l§6SUPER COOLE §aserver!");
		}
		
	
	
	}
	

}	

	

