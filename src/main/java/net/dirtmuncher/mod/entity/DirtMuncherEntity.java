package net.dirtmuncher.mod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

/**
 * A hostile mob shaped like a dirt block. It ignores the player and instead
 * hunts down pigs; when it lands the killing blow on one, it "eats" it —
 * healing itself and puffing out a few particles.
 */
public class DirtMuncherEntity extends HostileEntity {

	public DirtMuncherEntity(EntityType<? extends HostileEntity> entityType, World world) {
		super(entityType, world);
	}

	public static DefaultAttributeContainer.Builder createDirtMuncherAttributes() {
		return HostileEntity.createHostileAttributes()
				.add(EntityAttributes.MAX_HEALTH, 20.0)
				.add(EntityAttributes.MOVEMENT_SPEED, 0.28)
				.add(EntityAttributes.ATTACK_DAMAGE, 3.0)
				.add(EntityAttributes.FOLLOW_RANGE, 24.0)
				.add(EntityAttributes.KNOCKBACK_RESISTANCE, 0.4);
	}

	@Override
	protected void initGoals() {
		this.goalSelector.add(1, new SwimGoal(this));
		this.goalSelector.add(2, new MeleeAttackGoal(this, 1.15D, false));
		this.goalSelector.add(3, new WanderAroundFarGoal(this, 0.9D));
		this.goalSelector.add(4, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(5, new LookAroundGoal(this));

		// Only ever targets pigs — it has no interest in the player.
		this.targetSelector.add(1, new ActiveTargetGoal<>(this, PigEntity.class, true));
	}

	@Override
	public boolean tryAttack(ServerWorld world, net.minecraft.entity.Entity target) {
		boolean success = super.tryAttack(world, target);
		if (success && target instanceof PigEntity pig && !pig.isAlive()) {
			this.eat(world);
		}
		return success;
	}

	private void eat(ServerWorld world) {
		this.heal(6.0F);
		world.spawnParticles(ParticleTypes.HAPPY_VILLAGER,
				this.getX(), this.getBodyY(0.6D), this.getZ(),
				10, 0.3D, 0.3D, 0.3D, 0.0D);
		this.playSound(SoundEvents.ENTITY_GENERIC_EAT, 1.0F, 0.8F);
	}

	@Override
	public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
		return this.getY() < world.getSeaLevel() + 12 || super.canSpawn(world, spawnReason);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.BLOCK_GRAVEL_STEP;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.BLOCK_GRAVEL_HIT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_GRAVEL_BREAK;
	}

	@Override
	protected void playStepSound(net.minecraft.util.math.BlockPos pos, net.minecraft.block.BlockState state) {
		this.playSound(SoundEvents.BLOCK_GRAVEL_STEP, 0.5F, 1.0F);
	}
}
