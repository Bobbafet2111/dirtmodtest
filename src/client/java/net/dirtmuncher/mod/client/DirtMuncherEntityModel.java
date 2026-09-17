package net.dirtmuncher.mod.client;

import net.dirtmuncher.mod.DirtMuncherMod;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.util.Identifier;

/**
 * A plain 16x16x16 cube — the mob is meant to look like a walking dirt
 * block, so there is no biped skeleton here, just one rigid box.
 *
 * NOTE: 1.21.2+ renders off an EntityRenderState snapshot rather than the
 * live entity. If this signature has drifted further by the time you build,
 * compare against net.minecraft.client.render.entity.model.ZombieEntityModel
 * in your decompiled sources and adjust setAngles()/the type parameter.
 */
public class DirtMuncherEntityModel extends SinglePartEntityModel<LivingEntityRenderState> {
	public static final EntityModelLayer LAYER =
			new EntityModelLayer(Identifier.of(DirtMuncherMod.MOD_ID, "dirt_muncher"), "main");

	private final ModelPart body;

	public DirtMuncherEntityModel(ModelPart root) {
		this.body = root.getChild("body");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData root = modelData.getRoot();
		root.addChild("body",
				ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-8.0F, -16.0F, -8.0F, 16.0F, 16.0F, 16.0F),
				ModelTransform.pivot(0.0F, 24.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 32);
	}

	@Override
	public ModelPart getPart() {
		return this.body;
	}

	@Override
	public void setAngles(LivingEntityRenderState state) {
		// It's a block — no limbs to swing. Left intentionally empty.
	}
}
