package brobotato.adventurepack;

import brobotato.adventurepack.client.handler.ColorHandler;
import brobotato.adventurepack.config.Config;
import brobotato.adventurepack.item.ModItemGroup;
import brobotato.adventurepack.proxy.ClientProxy;
import brobotato.adventurepack.proxy.CommonProxy;
import brobotato.adventurepack.proxy.IProxy;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(AdventurePack.modId)
public class AdventurePack {

    public static IProxy proxy;

    public static final String modId = "adventurepack";

    public static final ItemGroup ADVENTURE_PACK = new ModItemGroup("adventure_pack");

    public AdventurePack() {
        proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        proxy.registerHandlers();

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            bus.register(new ColorHandler());
        });

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
    }

}