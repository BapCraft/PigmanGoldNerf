package com.bapcraft.nerf.pigman;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Monster;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;

public class PigmanDropSuppressor implements Listener {
	
	public List<Monster> blockedDrops = new ArrayList<>();
	
	public void onEntitySpawn(EntitySpawnEvent event) {
		
		Entity e = event.getEntity();
		EntityType type = e.getType();
		
		if (type == EntityType.PIG_ZOMBIE && PigmanGoldNerf.INSTANCE.hasBlockedDrops(e.getWorld())) {
			this.blockedDrops.add((Monster) e);
		}
		
	}
	
	public void onEntityDeath(EntityDeathEvent event) {
		
		Entity e = event.getEntity();
		
		if (this.blockedDrops.contains(e)) {
			event.getDrops().removeIf(is -> is.getType().name().toLowerCase().matches(PigmanGoldNerf.INSTANCE.itemRegex));
		}
		
	}
	
}
