package syntacticsplenda.adventurepack.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

public class CommonProxy {

    public void registerItemRenderer(Item item, int meta, String id) {
    }

    public void init(FMLInitializationEvent e) {
    }

    public ModelBiped getArmorModel(String type) {
        return null;
    }
}
