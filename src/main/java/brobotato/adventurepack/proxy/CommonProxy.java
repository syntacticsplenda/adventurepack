package brobotato.adventurepack.proxy;

import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.DEDICATED_SERVER)
public class CommonProxy implements IProxy{

    public ModelBiped getArmorModel(String type) {
        return null;
    }
}
