package worldscale.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import worldscale.tileentity.TileEntityWorldScaleActive;

public class BlockWorldScaleActive extends Block {
    public BlockWorldScaleActive() {
        super(Material.ROCK);

        this.setHardness(5.0F).setResistance(500.0F);
    }

    @Override
    public void breakBlock(World world, BlockPos blockpos, IBlockState blockstate) {
        ((TileEntityWorldScaleActive) world.getTileEntity(blockpos)).unclaimAllChunks();

        super.breakBlock(world, blockpos, blockstate);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileEntityWorldScaleActive();
    }
}