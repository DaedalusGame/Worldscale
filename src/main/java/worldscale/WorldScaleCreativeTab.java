package worldscale;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import worldscale.handler.RegistryHandler;

public class WorldScaleCreativeTab extends CreativeTabs {
    public WorldScaleCreativeTab() {
        super("worldscale.name");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getTabIconItem() {
        return new ItemStack(RegistryHandler.WORLDSCALE);
    }
}
