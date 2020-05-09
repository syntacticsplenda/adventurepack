package syntacticsplenda.adventurepack;

import syntacticsplenda.adventurepack.client.handler.ColorHandler;
import syntacticsplenda.adventurepack.config.Config;
import syntacticsplenda.adventurepack.item.ModItemGroup;
import syntacticsplenda.adventurepack.proxy.ClientProxy;
import syntacticsplenda.adventurepack.proxy.CommonProxy;
import syntacticsplenda.adventurepack.proxy.IProxy;
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