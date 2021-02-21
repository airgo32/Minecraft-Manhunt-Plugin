package testPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

public class MyListener implements Listener {

	
//	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		
		Player player = event.getPlayer();
		
		Bukkit.broadcastMessage(event.getPlayer().getName() + " just chatted.");
		Bukkit.broadcastMessage(event.getPlayer().getInventory().getItemInMainHand().getType().toString());
		Bukkit.broadcastMessage(event.getPlayer().getLocation().getWorld().toString());
		
		if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.COMPASS)) {
			
			double x = (Math.random() * 1000) - 500;
			double y = Math.random() * 100;
			double z = (Math.random() * 1000) - 500;
			
			Location dest = new Location(event.getPlayer().getLocation().getWorld(), x, y, z);
			event.getPlayer().setCompassTarget(dest);
			
			Bukkit.broadcastMessage(player.getName() + "'s compass is now pointing to x=" + x + ", y=" +
			y + ", z=" + z + " in their current world");
						
		}
		
		
	}
	
//	@EventHandler
	public void changeCompass(PlayerInteractEvent event) {
		
		Bukkit.broadcastMessage(event.getAction().toString());
		
	}
	
	@EventHandler
	public void updateCompass(PlayerMoveEvent event) {
		
		if (testPlugin.getRunner().getPlayer() == null) {
			
			return;
		}
		
		
		
		if (event.getPlayer().equals(testPlugin.getRunner().getPlayer())) {
			
			testPlugin.getRunner().setLocation();
		
		}
		
		// set the player's compass towards the runner
		if (event.getPlayer().getInventory().contains(Material.COMPASS)) {
			
			int compass_slot = event.getPlayer().getInventory().first(Material.COMPASS);
//			Bukkit.broadcastMessage("compass_slot=" + compass_slot);
			
			ItemStack compass = event.getPlayer().getInventory().getItem(compass_slot);
//			Bukkit.broadcastMessage("compass="+compass.toString());
			
			CompassMeta meta = (CompassMeta) compass.getItemMeta();
//			Bukkit.broadcastMessage(meta.toString());
			
			meta.setLodestone(testPlugin.getRunner().getLocation(event.getPlayer().getLocation()));
			meta.setLodestoneTracked(false);
			meta.setDisplayName(testPlugin.getRunner().getPlayer().getName() + "'s Location");
			compass.setItemMeta(meta);
			
		}
		
//		event.getPlayer().setCompassTarget(testPlugin.getRunner().getLocation(event.getPlayer().getLocation()));
	}
	
	// gives the hunters a compass when they respawn
	@EventHandler
	public void giveHunterCompass(PlayerRespawnEvent event) {
		
		if (testPlugin.getRunner().getPlayer() == null) {
			
			return;
			
		}
		
		if (!event.getPlayer().equals(testPlugin.getRunner().getPlayer())) {
			
			ItemStack compass = new ItemStack(Material.COMPASS, 1);
			CompassMeta meta = (CompassMeta) compass.getItemMeta();
			
			meta.setLodestone(testPlugin.getRunner().getLocation(event.getPlayer().getLocation()));
			meta.setLodestoneTracked(false);
			meta.setDisplayName(testPlugin.getRunner().getPlayer().getName() + "'s Location");
			
			compass.setItemMeta(meta);
			
			event.getPlayer().getInventory().setItem(0, compass);
			
		}
		
	}
}