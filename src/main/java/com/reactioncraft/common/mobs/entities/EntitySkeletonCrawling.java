package com.reactioncraft.common.mobs.entities;

import javax.annotation.Nullable;

import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBreakDoor;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntitySkeletonCrawling extends EntityMob
{
    static final DataParameter<Byte> skeletonVariation= EntityDataManager.createKey(EntitySkeletonCrawling.class,DataSerializers.BYTE);
    static final String TYPE="Variation";
    public EntitySkeletonCrawling(World world)
    {
        super(world);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIBreakDoor(this));
        tasks.addTask(2,new EntityAIAttackMelee(this,1,false));
        this.tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(7, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
        targetTasks.addTask(2,new EntityAINearestAttackableTarget<EntityPlayer>(this,EntityPlayer.class,true));
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(skeletonVariation,(byte) 0);
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        int type=rand.nextInt(3);
        dataManager.set(skeletonVariation,(byte) type);
        return super.onInitialSpawn(difficulty, livingdata);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setByte(TYPE,getSkeletonVariation());
        super.writeEntityToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        dataManager.set(skeletonVariation,compound.getByte(TYPE));
    }

    public byte getSkeletonVariation()
    {
        return dataManager.get(skeletonVariation);
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(18.0D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    }

    /**
     * Returns the current armor value as determined by a call to InventoryPlayer.getTotalArmorValue
     */
    public int getTotalArmorValue()
    {
        int var1 = super.getTotalArmorValue() + 2;

        if (var1 > 20)
        {
            var1 = 20;
        }

        return var1;
    }

    /**
     * Get this Entity's EnumCreatureAttribute
     */
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    protected void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
        super.dropFewItems(wasRecentlyHit, lootingModifier);
    }
    
    /**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		if (this.world.isDaytime() && !this.world.isRemote && !this.isChild())
		{
			float var1 = this.getBrightness();

			if (var1 > 0.5F && this.rand.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F && this.world.canSeeSky(getPosition()))
			{
				boolean var2 = true;
				ItemStack itemStack = this.getHeldItemMainhand();

				if (!itemStack .isEmpty())
				{
					if (itemStack.isItemStackDamageable())
					{
						itemStack.setItemDamage(itemStack.getItemDamage() + this.rand.nextInt(2));

						if (itemStack.getItemDamage()>= itemStack.getMaxDamage())
						{
							this.renderBrokenItemStack(itemStack);
							itemStack.shrink(1);
						}
					}

					var2 = false;
				}

				if (var2)
				{
					this.setFire(8);
				}
			}
		}

		super.onLivingUpdate();
	}

    //    public void dropRareDrop(int par1)
//    {
//        switch (this.rand.nextInt(3))
//        {
//            case 0:
//                this.entityDropItem(new ItemStack(Items.SKULL, 1, 0), 0.0F);
//                break;
//
//            case 1:
//                this.entityDropItem(new ItemStack(Items.SKULL, 1, 0), 0.0F);
//                break;
//
//            case 2:
//                this.entityDropItem(new ItemStack(Items.SKULL, 1, 0), 0.0F);
//        }
//    }
}
