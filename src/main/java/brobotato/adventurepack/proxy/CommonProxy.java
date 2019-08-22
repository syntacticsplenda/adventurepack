package brobotato.adventurepack.proxy;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)
public class CommonProxy implements IProxy {

    public BipedModel getArmorModel(String type) {
        return null;
    }
}
