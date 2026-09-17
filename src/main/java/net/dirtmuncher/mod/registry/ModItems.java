package net.dirtmuncher.mod.registry;

import net.dirtmuncher.mod.DirtMuncherMod;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

	// Dirt-brown primary colour, a darker brown for the speckles.
	public static final Item DIRT_MUNCHER_SPAWN_EGG = Registry.register(
			Registries.ITEM,
			Identifier.of(DirtMuncherMod.MOD_ID, "dirt_muncher_spawn_egg"),
			new SpawnEggItem(ModEntities.DIRT_MUNCHER, 0x8B6544, 0x5A3E29, new Item.Settings())
	);

	public static void register() {
		// Registration above happens on class load; this just forces that to run.
	}
}
