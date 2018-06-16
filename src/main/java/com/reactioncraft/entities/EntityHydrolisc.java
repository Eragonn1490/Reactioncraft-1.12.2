package com.reactioncraft.entities;

import com.reactioncraft.registration.instances.ItemIndex;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.UUID;

public class EntityHydrolisc extends EntityTameable
{
    private int force_sync = 50;
    private EntityAITempt aiTempt;
    public int foodNum;
    public int tamedNum;

    public EntityHydrolisc(World par1World)
    {
        super(par1World);
        this.setSize(0.8F, 0.8F);
//        this.fireResistance = 100;
        this.setSitting(false);

    }

    protected void entityInit() {
        super.entityInit();
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, this.aiSit=new EntityAISit(this));
        this.tasks.addTask(2, this.aiTempt = new EntityAITempt(this, 0.20000000298023224D, Items.FISH, true));
        tasks.addTask(3,new EntityAIAttackMelee(this,1,false));
        this.tasks.addTask(4, new EntityAIFollowOwner(this, 1, 10.0F, 5.0F));
        tasks.addTask(5,new EntityAIOwnerHurtTarget(this));
//        this.tasks.addTask(6, new EntityAIPanic(this, 1));
        this.tasks.addTask(8, new EntityAIWander(this, 1));
        this.tasks.addTask(9, new EntityAILookIdle(this));
        this.tasks.addTask(10, new EntityAIMoveIndoors(this));
        targetTasks.addTask(1,new EntityAIOwnerHurtByTarget(this));
        //TODO
        ;
        targetTasks.addTask(2,new EntityAIHurtByTarget(this,true));
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem()==Items.FISH || stack.getItem()== ItemIndex.salmonRaw || stack.getItem()==ItemIndex.yellowTailRaw;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        ItemStack itemStack=player.getHeldItem(hand);
        if(itemStack.getItem()==Items.FISH || itemStack.getItem()==ItemIndex.salmonRaw || itemStack.getItem()==ItemIndex.yellowTailRaw)
        {
            if(!player.capabilities.isCreativeMode)
                itemStack.shrink(1);
            if(!world.isRemote)
            {
                if(!this.isTamed()) {
                    if (rand.nextInt(3) == 1) {
                        setTamedBy(player);
                        navigator.clearPath();
                        setAttackTarget(null);
                        playTameEffect(true);
                        world.setEntityState(this, (byte) 7);
                    } else {
                        playTameEffect(false);
                        world.setEntityState(this, (byte) 6);
                        return true;
                    }
                }
                else{

                }
            }
        }
        return super.processInteract(player, hand);
    }

    /**
     * Determines if an entity can be despawned, used on idle far away entities
     */
    protected boolean canDespawn()
    {
        return !this.isTamed();
    }




    public boolean canBreatheUnderwater()
    {
        return true;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4);
        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
//        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate() {
        super.onLivingUpdate();
    }


    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }


    /**
     * Returns the volume for the sounds this mob makes.
     */
    protected float getSoundVolume()
    {
        return 0.4F;
    }

    /**
     * Returns the item ID for the item the mob drops on death.
     */
    protected Item getDropItem()
    {
        return Items.FISH;
    }

    /**
     * Returns true if the mob is currently able to mate with the specified mob.
     */
    public boolean canMateWith(EntityAnimal par1EntityAnimal)
    {
        if (par1EntityAnimal == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(par1EntityAnimal instanceof EntityHydrolisc))
        {
            return false;
        }
        else
        {
            EntityHydrolisc entityHydrolisc = (EntityHydrolisc)par1EntityAnimal;
            return entityHydrolisc.isTamed() && (!entityHydrolisc.isSitting() && (this.isInLove() && entityHydrolisc.isInLove()));
        }
    }

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
        EntityHydrolisc child=new EntityHydrolisc(world);
        UUID uuid=getOwnerId();
        if(uuid!=null) {
            child.setOwnerId(uuid);
            child.setTamed(true);
        }
		return child;
	}

    @Override
    public boolean getCanSpawnHere() {
        return true;
    }
}
