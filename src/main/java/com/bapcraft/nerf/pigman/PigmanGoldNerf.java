package com.bapcraft.nerf.pigman;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class PigmanGoldNerf extends JavaPlugin {

	public static PigmanGoldNerf INSTANCE;
	
	@Override
	public void onEnable() {
		
		INSTANCE = this;
		
		// Config crap.
		this.saveDefaultConfig();
		
		// Register the listener.
		this.getPluginLoader().createRegisteredListeners(new PigmanDropSuppressor(), this);
		
	}
	
	@Override
	public void onDisable() {
		
		INSTANCE = null;
		
	}
	
	public boolean hasBlockedDrops(World world) {
		
		// TODO Use configs.
		return world.getName().equals(Bukkit.getWorlds().get(0).getName());
		
	}
	
}
