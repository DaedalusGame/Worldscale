package worldscale.handler;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import worldscale.WorldScale;
import worldscale.block.BlockBannerDetector;
import worldscale.block.BlockLattice;
import worldscale.block.BlockWorldScale;
import worldscale.block.BlockWorldScaleActive;

import javax.annotation.Nonnull;

public class RegistryHandler {
    @GameRegistry.ObjectHolder("worldscale:lattice")
    public static BlockLattice LATTICE;
    @GameRegistry.ObjectHolder("worldscale:banner_detector")
    public static BlockBannerDetector BANNER_DETECTOR;
    @GameRegistry.ObjectHolder("worldscale:worldscale")
    public static BlockWorldScale WORLDSCALE;
    @GameRegistry.ObjectHolder("worldscale:worldscale_active")
    public static BlockWorldScaleActive WORLDSCALE_ACTIVE;

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event)
    {
        LATTICE = (BlockLattice) new BlockLattice().setRegistryName(WorldScale.MODID,"lattice").setUnlocalizedName("lattice").setCreativeTab(WorldScale.creativeTab);
        BANNER_DETECTOR = (BlockBannerDetector) new BlockBannerDetector().setRegistryName(WorldScale.MODID,"banner_detector").setUnlocalizedName("banner_detector").setCreativeTab(WorldScale.creativeTab);
        WORLDSCALE = (BlockWorldScale) new BlockWorldScale().setRegistryName(WorldScale.MODID,"worldscale").setUnlocalizedName("worldscale").setCreativeTab(WorldScale.creativeTab);
        WORLDSCALE_ACTIVE = (BlockWorldScaleActive) new BlockWorldScaleActive().setRegistryName(WorldScale.MODID,"worldscale_active").setUnlocalizedName("worldscale_active").setCreativeTab(WorldScale.creativeTab);

        event.getRegistry().register(LATTICE);
        event.getRegistry().register(BANNER_DETECTOR);
        event.getRegistry().register(WORLDSCALE);
        event.getRegistry().register(WORLDSCALE_ACTIVE);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(new ItemBlock(LATTICE).setRegistryName(LATTICE.getRegistryName()));
        event.getRegistry().register(new ItemBlock(BANNER_DETECTOR).setRegistryName(BANNER_DETECTOR.getRegistryName()));
        event.getRegistry().register(new ItemBlock(WORLDSCALE).setRegistryName(WORLDSCALE.getRegistryName()));
        event.getRegistry().register(new ItemBlock(WORLDSCALE_ACTIVE).setRegistryName(WORLDSCALE_ACTIVE.getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        registerItemModel(Item.getItemFromBlock(LATTICE),0,"inventory");
        registerItemModel(Item.getItemFromBlock(BANNER_DETECTOR),0,"inventory");
        registerItemModel(Item.getItemFromBlock(WORLDSCALE),0,"inventory");
        registerItemModel(Item.getItemFromBlock(WORLDSCALE_ACTIVE),0,"inventory");
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(@Nonnull Item item, int meta, String variant)
    {
        ModelLoader.setCustomModelResourceLocation(item,meta,new ModelResourceLocation(item.getRegistryName(),variant));

    }
}
