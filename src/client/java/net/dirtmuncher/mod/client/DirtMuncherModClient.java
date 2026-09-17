package net.dirtmuncher.mod.client;

import net.dirtmuncher.mod.registry.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class DirtMuncherModClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(DirtMuncherEntityModel.LAYER, DirtMuncherEntityModel::getTexturedModelData);
		EntityRendererRegistry.register(ModEntities.DIRT_MUNCHER, DirtMuncherEntityRenderer::new);
	}
}
