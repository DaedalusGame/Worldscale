package worldscale.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockWorldScale extends Block {
    public static final PropertyBool CRACKED = PropertyBool.create("cracked");

    public BlockWorldScale() {
        super(Material.ROCK);

        this.setHardness(5.0F).setResistance(500.0F);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    public void setCracked(World worldIn, BlockPos pos, IBlockState state, boolean cracked)
    {
        if(state.getBlock() instanceof BlockWorldScale && state.getValue(CRACKED) != cracked) {
            worldIn.setBlockState(pos, state.withProperty(CRACKED, cracked), 2);
            worldIn.playEvent(2001, pos, Block.getStateId(state));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(CRACKED, meta != 0);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(CRACKED) ? 1 : 0;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] { CRACKED });
    }
}

