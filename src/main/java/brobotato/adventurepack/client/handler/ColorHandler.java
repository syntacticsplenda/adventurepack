//package brobotato.adventurepack.client.handler;
//
//import brobotato.adventurepack.AdventurePack;
//import brobotato.adventurepack.item.ModItems;
//import net.minecraft.client.renderer.color.IItemColor;
//import net.minecraft.client.renderer.color.ItemColors;
//import net.minecraft.item.IDyeableArmorItem;
//import net.minecraft.item.Item;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.client.event.ColorHandlerEvent;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT, modid = AdventurePack.modId)
//public class ColorHandler {
//    private static final List<Item> COLORED_ITEMS = new ArrayList<>();
//
//    private static IItemColor ModItemColor = (stack, tintIndex) -> tintIndex > 0 ? -1 : ((IDyeableArmorItem) stack.getItem()).getColor(stack);
//
//    @SubscribeEvent
//    public static void registerItemColourHandlers(ColorHandlerEvent.Item event) {
//        ItemColors itemColors = event.getItemColors();
//
//        COLORED_ITEMS.add(ModItems.explorerHat);
//        COLORED_ITEMS.add(ModItems.cowboyHat);
//
//        itemColors.register(ModItemColor, COLORED_ITEMS.toArray(new Item[0]));
//    }
//
//}

// TODO: This almost definitely needs to be a mixin