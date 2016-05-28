package com.bapcraft.nerf.pigman;

import java.util.List;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PigmanGoldNerf extends JavaPlugin {

	public static PigmanGoldNerf INSTANCE;
	
	public List<String> suppressionWorlds;
	public String itemRegex;
	
	@Override
	public void onEnable() {
		
		INSTANCE = this;
		
		// Config crap.
		this.saveDefaultConfig();
		FileConfiguration fc = this.getConfig();
		this.suppressionWorlds = fc.getStringList("worlds");
		this.itemRegex = fc.getString("removalregex");
		
		// Register the listener.
		this.getPluginLoader().createRegisteredListeners(new PigmanDropSuppressor(), this);
		
	}
	
	@Override
	public void onDisable() {
		
		INSTANCE = null;
		
	}
	
	public boolean hasBlockedDrops(World world) {
		return this.suppressionWorlds.contains(world.getName());
	}
	
}
