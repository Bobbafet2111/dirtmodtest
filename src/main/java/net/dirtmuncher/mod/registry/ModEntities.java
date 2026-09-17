package net.dirtmuncher.mod.registry;

import net.dirtmuncher.mod.DirtMuncherMod;
import net.dirtmuncher.mod.entity.DirtMuncherEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

	public static final RegistryKey<EntityType<?>> DIRT_MUNCHER_KEY =
			RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(DirtMuncherMod.MOD_ID, "dirt_muncher"));

	public static final EntityType<DirtMuncherEntity> DIRT_MUNCHER = Registry.register(
			Registries.ENTITY_TYPE,
			DIRT_MUNCHER_KEY,
			EntityType.Builder.create(DirtMuncherEntity::new, SpawnGroup.MONSTER)
					.dimensions(0.9F, 0.95F)
					.maxTrackingRange(8)
					.build(DIRT_MUNCHER_KEY)
	);

	public static void register() {
		FabricDefaultAttributeRegistry.register(DIRT_MUNCHER, DirtMuncherEntity.createDirtMuncherAttributes());
	}
}
