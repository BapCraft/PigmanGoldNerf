package com.bapcraft.nerf.pigman;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class PigmanDropSuppressor implements Listener {

	public List<Monster> blockedDrops = new ArrayList<>();
	
	@EventHandler
	public void onEntitySpawn(EntitySpawnEvent event) {

		Entity e = event.getEntity();
		EntityType type = e.getType();

		if (type == EntityType.PIG_ZOMBIE) {
			
			Bukkit.getLogger().info("Checking entity " + e);
			
			if (PigmanGoldNerf.INSTANCE.hasBlockedDrops(e.getWorld())) {
				
				Bukkit.getLogger().info("Blocking drops for " + e);
				this.blockedDrops.add((Monster) e);
				
			}
			
		}

	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {

		Entity e = event.getEntity();

		if (this.blockedDrops.contains(e)) {

			event.getDrops().removeIf(is -> is.getType().name().toLowerCase()
					.contains(PigmanGoldNerf.INSTANCE.removalCheck.toLowerCase()));
			this.blockedDrops.remove(e);

		}

	}

}
