package com.reactioncraft.world;

import com.reactioncraft.registration.instances.BlockIndex;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

/**
 * Created on 12/27/17.
 */
public class CactusGenerator extends WorldGenerator {
    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        for (int i = 0; i < 10; ++i)
        {
            BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), 0, rand.nextInt(8) - rand.nextInt(8));

//            System.out.println(blockpos);
            if (worldIn.isAirBlock(blockpos))
            {
                if (BlockIndex.redCactus.canBlockStay(worldIn, blockpos,worldIn.getBlockState(position)))
                {
                    boolean red=rand.nextBoolean();
                    if(red)
                        worldIn.setBlockState(blockpos, BlockIndex.redCactus.getDefaultState(), 2);
                    else worldIn.setBlockState(blockpos,BlockIndex.greenCactus.getDefaultState(),2);
                    return true;
                }
            }
        }
        return false;
    }
}
