package com.reactioncraft.mobs.common.ai;

import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.mojang.authlib.GameProfile;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIEatGrass;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.FakePlayer;

public class EntityAIEatGrassMate extends EntityAIEatGrass
{
    private static final Predicate<IBlockState> IS_TALL_GRASS = BlockStateMatcher.forBlock(Blocks.TALLGRASS).where(BlockTallGrass.TYPE, Predicates.equalTo(BlockTallGrass.EnumType.GRASS));
    /** The entity owner of this AITask */
    private final EntityLiving grassEaterEntity;
    /** The world the grass eater entity is eating from */
    private final World entityWorld;
    /** Number of ticks since the entity started to eat grass */
    int eatingGrassTimer;
	
    /** Fake player to simulate a player triggering isInLove **/
    WorldServer worldServer = DimensionManager.getWorld(0); // default world
    GameProfile gameProfile = new GameProfile(UUID.randomUUID(), "FakePlayer");
    FakePlayer fakePlayer = new FakePlayer(worldServer, gameProfile);

    
    @Nullable
	EntityPlayer player = fakePlayer;
    

    
    public EntityAIEatGrassMate(EntityLiving grassEaterEntityIn)
    {
    	super(grassEaterEntityIn);
        this.grassEaterEntity = grassEaterEntityIn;
        this.entityWorld = grassEaterEntityIn.world;
        this.setMutexBits(7);
    }
    
    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0)
        {
            return false;
        }
        else
        {
            BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);

            if (IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos)))
            {
				if(!this.grassEaterEntity.isChild())
				{
					((EntityAnimal)grassEaterEntity).setInLove(player);
					((EntityAnimal)grassEaterEntity).setInLove(player);
				}
				if (this.grassEaterEntity.isChild())
		        {
		            ((EntityAgeable) this.grassEaterEntity).addGrowth(30);
		        }
                return true;
            }
            else
            {
            	if(!this.grassEaterEntity.isChild())
				{
            		((EntityAnimal)grassEaterEntity).getLoveCause();
				}
            	if (this.grassEaterEntity.isChild())
		        {
		            ((EntityAgeable) this.grassEaterEntity).addGrowth(60);
		        }
                return this.entityWorld.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS;
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.eatingGrassTimer = 40;
        this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
        this.grassEaterEntity.getNavigator().clearPath();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void resetTask()
    {
        this.eatingGrassTimer = 0;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean shouldContinueExecuting()
    {
        return this.eatingGrassTimer > 0;
    }

    /**
     * Number of ticks since the entity started to eat grass
     */
    public int getEatingGrassTimer()
    {
        return this.eatingGrassTimer;
    }
}