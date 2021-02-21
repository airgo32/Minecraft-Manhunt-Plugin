package commands;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

import testPlugin.testPlugin;

public class SetRunner implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
			Player player = (Player) sender;
			
			// get an array of all players in the world
			
			@SuppressWarnings("unchecked")
			List<Player> players = (List<Player>) player.getServer().getOnlinePlayers();
			
			Player target = player.getServer().getPlayer(args[0]);
			
			if (players.contains(target)) {
				
				testPlugin.setRunner(target);
				
				Bukkit.broadcastMessage(testPlugin.getRunner().getPlayer().getName() + " is now runner");
				
			} else {
				
				Bukkit.broadcastMessage("Could not find player '" + args[0] + "'");
				
				return true;
				
			}
			
			for (int i = 0; i < players.size(); i++) {
				
				Player hunter = players.get(i);
				
				if (!hunter.equals(player)) {
					
					ItemStack compass = new ItemStack(Material.COMPASS, 1);
					CompassMeta meta = (CompassMeta) compass.getItemMeta();
					
					meta.setLodestone(testPlugin.getRunner().getLocation(hunter.getLocation()));
					meta.setLodestoneTracked(false);
					meta.setDisplayName(testPlugin.getRunner().getPlayer().getName() + "'s Location");
					
					compass.setItemMeta(meta);
					
					hunter.getInventory().setItem(0, compass);
					
				}

			}

			
			

		}
		
		
		
		return true;
	}
}
