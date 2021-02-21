package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import testPlugin.testPlugin;

public class ClearRunner implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (sender instanceof Player) {
						
			if (testPlugin.getRunner().getPlayer() == null) {
				
				Bukkit.broadcastMessage("No one is currently the runner");
				
				return true;
				
			}
			String runner = testPlugin.getRunner().getPlayer().getName();
			
			testPlugin.getRunner().setPlayer(null);
			
			Bukkit.broadcastMessage(runner + " is no longer the runner");
		}
		
		return true;
	}
	
}
