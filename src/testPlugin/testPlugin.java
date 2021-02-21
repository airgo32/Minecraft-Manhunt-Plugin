package testPlugin;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import commands.ClearRunner;
import commands.SetRunner;

public class testPlugin extends JavaPlugin {


	protected static Runner runner = new Runner();
	protected static boolean gametime = false;
	
	// Fired when plugin is first enabled
	@Override
	public void onEnable() {
		
		getServer().getPluginManager().registerEvents(new MyListener(), this);
		
		// enable the /setrunner command
		this.getCommand("setrunner").setExecutor(new SetRunner());
		this.getCommand("clearrunner").setExecutor(new ClearRunner());

		
	}
	
	public static void setRunner(Player player) {
		
		runner.setPlayer(player);
		
	}
	
	public static Runner getRunner() {
		
		return runner;
		
	}
	
	// Fired when plugin is disabled
	@Override
	public void onDisable() {
		
	}
}
