package com.reactioncraft.entities;

import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityJellyfish extends EntityWaterMob
{
    public float field_21089_a;
    public float field_21088_b;
    public float field_21087_c;
    public float field_21086_f;
    public float field_21085_g;
    public float field_21084_h;
    public float tentacleAngle;
    public float lastTentacleAngle;
    private float randomMotionSpeed;
    private float field_21080_l;
    private float field_21079_m;
    private float randomMotionVecX;
    private float randomMotionVecY;
    private float randomMotionVecZ;

    public EntityJellyfish(World par1World)
    {
        this(par1World, 0);
    }

    public EntityJellyfish(World par1World, int par2)
    {
        super(par1World);
        this.field_21089_a = 0.0F;
        this.field_21088_b = 0.0F;
        this.field_21087_c = 0.0F;
        this.field_21086_f = 0.0F;
        this.field_21085_g = 0.0F;
        this.field_21084_h = 0.0F;
        this.tentacleAngle = 0.0F;
        this.lastTentacleAngle = 0.0F;
        this.randomMotionSpeed = 0.0F;
        this.field_21080_l = 0.0F;
        this.field_21079_m = 0.0F;
        this.randomMotionVecX = 0.0F;
        this.randomMotionVecY = 0.0F;
        this.randomMotionVecZ = 0.0F;
        this.setSize(0.3F, 0.3F);
        this.field_21080_l = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(8.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
    }

    /**
     * Returns the volume for the sounds this mob makes.
     */
    public float getSoundVolume()
    {
        return 0.4F;
    }


    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
        this.field_21088_b = this.field_21089_a;
        this.field_21086_f = this.field_21087_c;
        this.field_21084_h = this.field_21085_g;
        this.lastTentacleAngle = this.tentacleAngle;
        this.field_21085_g += this.field_21080_l;

        if (this.field_21085_g > ((float)Math.PI * 2F))
        {
            this.field_21085_g -= ((float)Math.PI * 2F);

            if (this.rand.nextInt(10) == 0)
            {
                this.field_21080_l = 1.0F / (this.rand.nextFloat() + 1.0F) * 0.2F;
            }
        }

        if (this.isInWater())
        {
            float f1;

            if (this.field_21085_g < (float)Math.PI)
            {
                f1 = this.field_21085_g / (float)Math.PI;
                this.tentacleAngle = MathHelper.sin(f1 * f1 * (float)Math.PI) * (float)Math.PI * 0.25F;

                if ((double)f1 > 0.75D)
                {
                    this.randomMotionSpeed = 1.0F;
                    this.field_21079_m = 1.0F;
                }
                else
                {
                    this.field_21079_m *= 0.8F;
                }
            }
            else
            {
                this.tentacleAngle = 0.0F;
                this.randomMotionSpeed *= 0.9F;
                this.field_21079_m *= 0.99F;
            }

            if (!this.world.isRemote)
            {
                this.motionX = (double)(this.randomMotionVecX * this.randomMotionSpeed);
                this.motionY = (double)(this.randomMotionVecY * this.randomMotionSpeed);
                this.motionZ = (double)(this.randomMotionVecZ * this.randomMotionSpeed);
            }

            f1 = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.renderYawOffset += (-((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI - this.renderYawOffset) * 0.1F;
            this.rotationYaw = this.renderYawOffset;
            this.field_21087_c += (float)Math.PI * this.field_21079_m * 1.5F;
            this.field_21089_a += (-((float)Math.atan2((double)f1, this.motionY)) * 180.0F / (float)Math.PI - this.field_21089_a) * 0.1F;
        }
        else
        {
            this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.field_21085_g)) * (float)Math.PI * 0.25F;

            if (!this.world.isRemote)
            {
                this.motionX = 0.0D;
                this.motionY -= 0.08D;
                this.motionY *= 0.9800000190734863D;
                this.motionZ = 0.0D;
            }

            this.field_21089_a = (float)((double)this.field_21089_a + (double)(-90.0F - this.field_21089_a) * 0.02D);
        }
    }


    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public void moveEntityWithHeading(float f, float f1)
    {

//        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        move(MoverType.SELF,motionX,motionY,motionZ);
    }

    /**
     * Checks if the entity's current position is a valid location to spawn this entity.
     */
    public boolean getCanSpawnHere()
    {
        return this.posY > 45.0D && this.posY < 63.0D && super.getCanSpawnHere();
    }



    public int getSkin()
    {
        return this.rand.nextInt(5) == 0 ? 1 : (this.rand.nextInt(5) == 1 ? 1 : 0);
    }
}
