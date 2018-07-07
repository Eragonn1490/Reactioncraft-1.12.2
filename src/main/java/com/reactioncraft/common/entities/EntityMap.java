package com.reactioncraft.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMap extends EntityThrowable
{
    public EntityMap(World worldIn)
    {
        super(worldIn);
    }

    public EntityMap(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityMap(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesSnowball(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "Mapinabottle");
    }

    /**
     * Handler for {@link World#setEntityState}
     */
    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        if (id == 3)
        {
            for (int i = 0; i < 8; ++i)
            {
                //this.world.spawnParticle(EnumParticleTypes., this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);//Replace with glass particle
            }
        }
    }

    /**
     * Called when this EntityThrowable hits a block or entity.
     */
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null)
        {
            int i = 1;

            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)i);
        }

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            world.spawnEntity(new EntityItem(world, this.posX, this.posY, this.posZ, new ItemStack(Items.MAP)));
            this.setDead();
        }
    }
}