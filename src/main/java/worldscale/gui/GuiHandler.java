package worldscale.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class GuiHandler implements IGuiHandler
{
    public static final int BANNER_DETECTOR = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID)
        {
            case(BANNER_DETECTOR):
                return new ContainerBannerDetector(player,world,x,y,z);
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch(ID)
        {
            case(BANNER_DETECTOR):
                return new GuiBannerDetector(player,world,x,y,z);
            default:
                return null;
        }
    }

}
