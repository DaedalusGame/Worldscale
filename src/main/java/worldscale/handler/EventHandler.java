package worldscale.handler;

import net.minecraft.block.BlockBanner;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import worldscale.block.BlockLattice;
import worldscale.util.BannerUtil;

public class EventHandler {
    private final float HardnessThreshold = 5.0f;

    @SubscribeEvent
    public void worldTick(TickEvent.WorldTickEvent tickEvent) {
        World world = tickEvent.world;
        WorldScaleData.getInstance(world).cleanup();
    }

    @SubscribeEvent
    public void breakBlock(PlayerEvent.BreakSpeed breakEvent) {
        World world = breakEvent.getEntity().getEntityWorld();
        Entity entity = breakEvent.getEntity();
        WorldScaleData scaledata = WorldScaleData.getInstance(world);
        BlockPos worldscale = scaledata.getNearbyScale(breakEvent.getPos());
        ItemStack banner;
        BlockPos breakpos = breakEvent.getPos();
        IBlockState blockstate = world.getBlockState(breakpos);

        float hardnessmod = 1.0f;

        if (!(blockstate.getBlock() instanceof BlockLattice) && isNextToHardener(world, breakpos)) {
            hardnessmod = 2.0f;
        }

        float hardness = blockstate.getBlockHardness(world, breakEvent.getPos()) * hardnessmod;
        float newspeed = breakEvent.getNewSpeed() * (1.0f / hardnessmod);

        if (worldscale != null && !(banner = BannerUtil.getBannerItemFromBlock(world, worldscale.up())).isEmpty()) {
            if (!BannerUtil.isSameBanner(banner, entity)) {
                newspeed = breakEvent.getOriginalSpeed() * (Math.max(0f, HardnessThreshold - hardness) / HardnessThreshold) * 0.3333f * (1.0f / hardnessmod);
            }
        }

        breakEvent.setNewSpeed(newspeed);
    }

    private boolean isNextToHardener(World world, BlockPos pos) {
        for (EnumFacing facing : EnumFacing.VALUES) {
            BlockPos nextpos = pos.offset(facing);
            IBlockState state = world.getBlockState(nextpos);
            if (state.getBlock() instanceof BlockLattice)
                return true;
        }

        return false;
    }

    @SubscribeEvent
    public void onBlockInteract(PlayerInteractEvent event) {
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        IBlockState blockstate = world.getBlockState(pos);
        EntityPlayer player = event.getEntityPlayer();

        if (blockstate.getBlock() instanceof BlockBanner) {
            EnumHand hand = EnumHand.MAIN_HAND;
            ItemStack stack = player.getHeldItemMainhand();
            BlockBanner bannerblock = (BlockBanner) blockstate.getBlock();

            if (stack.isEmpty() && player.isSneaking() && player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty()) {
                ItemStack bannerstack = bannerblock.getItem(world, pos, blockstate);
                player.swingArm(hand);

                world.removeTileEntity(pos);
                world.setBlockToAir(pos);

                player.setItemStackToSlot(EntityEquipmentSlot.HEAD, bannerstack);

                if (!event.getWorld().isRemote) {
                    event.setCanceled(true);
                }
            }
        }
    }
}
