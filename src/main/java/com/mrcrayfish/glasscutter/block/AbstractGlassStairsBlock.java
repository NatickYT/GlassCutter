package com.mrcrayfish.glasscutter.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

/**
 * Author: MrCrayfish
 */
public class AbstractGlassStairsBlock extends StairsBlock
{
    private final Supplier<BlockState> state;

    public AbstractGlassStairsBlock(Supplier<BlockState> state, Properties properties)
    {
        super(state, properties);
        this.state = state;
    }

    @Override
    public boolean isTransparent(BlockState state)
    {
        return true;
    }

    // Help, what should I use instead???!
    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isSideInvisible(BlockState state, BlockState adjacentState, Direction side)
    {
        if(adjacentState.getBlock() == this && adjacentState.get(HALF) == state.get(HALF))
        {
            if(adjacentState.get(FACING) != state.get(FACING).getOpposite())
            {
                return true;
            }
        }
        if(adjacentState.getBlock() == this.state.get().getBlock())
        {
            return true;
        }
        return super.isSideInvisible(state, adjacentState, side);
    }

    // Help, what should I use instead???!
    @Override
    @OnlyIn(Dist.CLIENT)
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return 1.0F;
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos)
    {
        return true;
    }

    @Override
    public boolean canSpawnInBlock() {
        return false;
    }
}
