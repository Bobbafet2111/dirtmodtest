package net.dirtmuncher.mod.client;

import net.dirtmuncher.mod.DirtMuncherMod;
import net.dirtmuncher.mod.entity.DirtMuncherEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

public class DirtMuncherEntityRenderer extends MobEntityRenderer<DirtMuncherEntity, LivingEntityRenderState, DirtMuncherEntityModel> {
	private static final Identifier TEXTURE = Identifier.of(DirtMuncherMod.MOD_ID, "textures/entity/dirt_muncher.png");

	public DirtMuncherEntityRenderer(EntityRendererFactory.Context context) {
		super(context, new DirtMuncherEntityModel(context.getPart(DirtMuncherEntityModel.LAYER)), 0.6F);
	}

	@Override
	public Identifier getTexture(LivingEntityRenderState state) {
		return TEXTURE;
	}

	@Override
	public LivingEntityRenderState createRenderState() {
		return new LivingEntityRenderState();
	}
}
