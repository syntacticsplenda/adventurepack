package syntacticsplenda.adventurepack;

import syntacticsplenda.adventurepack.block.ModBlocks;
import syntacticsplenda.adventurepack.crafting.ModCrafting;
import syntacticsplenda.adventurepack.item.ModItems;
import syntacticsplenda.adventurepack.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(modid = AdventurePack.modId, name = AdventurePack.name, version = AdventurePack.version)

public class AdventurePack {
    public static final String modId = "adventurepack";
    public static final String name = "Adventure Pack";
    public static final String version = "1.7.1";

    @SidedProxy(serverSide = "syntacticsplenda.adventurepack.proxy.CommonProxy", clientSide = "syntacticsplenda.adventurepack.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.Instance(modId)
    public static AdventurePack instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {
        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
            ModBlocks.registerItemBlocks(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event) {
            ModItems.registerModels();
            ModBlocks.registerModels();
        }

        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event) {
            ModBlocks.register(event.getRegistry());
        }

        @SubscribeEvent
        public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
            ModCrafting.register(event.getRegistry());
        }
    }

}