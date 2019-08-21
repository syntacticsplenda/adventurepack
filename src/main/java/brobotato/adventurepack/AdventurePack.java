package brobotato.adventurepack;

import brobotato.adventurepack.block.ModBlocks;
import brobotato.adventurepack.config.Config;
import brobotato.adventurepack.item.ModItemGroup;
import brobotato.adventurepack.item.ModItems;
import brobotato.adventurepack.proxy.ClientProxy;
import brobotato.adventurepack.proxy.CommonProxy;
import brobotato.adventurepack.proxy.IProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;


@Mod(AdventurePack.modId)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AdventurePack {


    public static AdventurePack instance;
    public static IProxy proxy;

    public static final String modId = "adventurepack";

    public static final ItemGroup ADVENTURE_PACK = new ModItemGroup("adventure_pack");

    public AdventurePack() {
        instance = this;
        proxy = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
        proxy.registerHandlers();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
    }

}