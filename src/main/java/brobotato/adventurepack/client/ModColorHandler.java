package brobotato.adventurepack.client;

import brobotato.adventurepack.AdventurePack;
import brobotato.adventurepack.item.ModItems;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = AdventurePack.modId)
public class ModColorHandler {

    @SubscribeEvent
    public static void registerItemColourHandlers(final ColorHandlerEvent.Item event) {
        final ItemColors itemColors = event.getItemColors();

        itemColors.registerItemColorHandler(new IItemColor() {
            public int colorMultiplier(ItemStack stack, int tintIndex) {
                return tintIndex > 0 ? -1 : ((ItemArmor) stack.getItem()).getColor(stack);
            }
        }, ModItems.explorerHat);
    }
}
