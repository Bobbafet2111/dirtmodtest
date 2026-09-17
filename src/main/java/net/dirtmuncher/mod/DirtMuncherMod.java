package net.dirtmuncher.mod;

import net.dirtmuncher.mod.registry.ModEntities;
import net.dirtmuncher.mod.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirtMuncherMod implements ModInitializer {
	public static final String MOD_ID = "dirtmuncher";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntities.register();
		ModItems.register();

		// Put the spawn egg in the creative "Spawn Eggs" tab.
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS)
				.register(entries -> entries.add(ModItems.DIRT_MUNCHER_SPAWN_EGG));

		LOGGER.info("Dirt Muncher is hungry.");
	}
}
