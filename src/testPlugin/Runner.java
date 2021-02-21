package testPlugin;

import org.bukkit.Location;
import org.bukkit.World.Environment;
import org.bukkit.entity.Player;

public class Runner {

	private Player player;
	private Location overworld_location;
	private Location nether_location;
	private Location end_location;
	
	public Runner() {
		player = null;
		overworld_location = null;
		nether_location = null;
		end_location = null;
	}
	
	// returns the last known position of the runner in the specified dimension
	public Location getLocation(Location location) {
		
		if (location.getWorld().getEnvironment().equals(Environment.NORMAL)) {
			
			return getOverworld_location();
			
		} else if (location.getWorld().getEnvironment().equals(Environment.NETHER)) {
			
			return getNether_location();
			
		} else {
			
			return getEnd_location();
		}
	}
	
	// updates the latest position of the runner
	public void setLocation() {
		
		Environment dimension = player.getWorld().getEnvironment();
		
		if (dimension.equals(Environment.NORMAL)) {
			
			setOverworld_location(player.getLocation());
			
		} else if (dimension.equals(Environment.NETHER)) {
			
			setNether_location(player.getLocation());
			
		} else {
			
			setEnd_location(player.getLocation());
		}
		
	}
	
	
	
	
	
	
	// generated getters and setters
	public Player getPlayer() {
		
		return player;
	}
	
	public void setPlayer(Player player) {
		
		this.player = player;
	}
	
	public Location getOverworld_location() {
		
		return overworld_location;
	}
	
	public void setOverworld_location(Location overworld_location) {
		
		this.overworld_location = overworld_location;
	}
	
	public Location getNether_location() {
		
		return nether_location;
	}
	
	public void setNether_location(Location nether_location) {
		
		this.nether_location = nether_location;
	}
	
	public Location getEnd_location() {
		
		return end_location;
	}
	
	public void setEnd_location(Location end_location) {
		
		this.end_location = end_location;
	}
}
