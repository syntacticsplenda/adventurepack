package brobotato.adventurepack.proxy;

import brobotato.adventurepack.AdventurePack;
import brobotato.adventurepack.RenderHighlightedHandler;
import brobotato.adventurepack.item.ItemLantern;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(AdventurePack.modId + ":" + id, "inventory"));
    }


    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        MinecraftForge.EVENT_BUS.register(new RenderHighlightedHandler());
    }

}
