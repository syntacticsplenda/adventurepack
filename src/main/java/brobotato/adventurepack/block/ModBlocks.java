package brobotato.adventurepack.block;

import brobotato.adventurepack.block.light.BlockLight;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks {
    public static BlockLight blockLight = new BlockLight();

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(blockLight);
        GameRegistry.registerTileEntity(blockLight.getTileEntityClass(), blockLight.getRegistryName().toString());
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(blockLight.createItemBlock());
    }

    public static void registerModels() {
        blockLight.registerItemModel(Item.getItemFromBlock(blockLight));
    }

}
