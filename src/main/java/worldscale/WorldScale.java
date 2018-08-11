package worldscale;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import worldscale.gui.GuiHandler;
import worldscale.handler.EventHandler;
import worldscale.handler.RegistryHandler;
import worldscale.tileentity.TileEntityBannerDetector;
import worldscale.tileentity.TileEntityWorldScaleActive;

@Mod(modid = WorldScale.MODID, acceptedMinecraftVersions = "[1.12, 1.13)")
@Mod.EventBusSubscriber
public class WorldScale
{
    public static final String MODID = "worldscale";
    public static final String NAME = "World Scale";

    @Mod.Instance(WorldScale.MODID)
    public static WorldScale instance;

    public static WorldScaleCreativeTab creativeTab;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        creativeTab = new WorldScaleCreativeTab();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new EventHandler());
        MinecraftForge.EVENT_BUS.register(new RegistryHandler());

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @Mod.EventHandler
    public void preInit(FMLInitializationEvent event)
    {
        GameRegistry.registerTileEntity(TileEntityBannerDetector.class,"worldscale:banner_detector");
        GameRegistry.registerTileEntity(TileEntityWorldScaleActive.class,"worldscale:worldscale_active");
    }
}
