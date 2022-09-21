package syntacticsplenda.adventurepack.block;

import syntacticsplenda.adventurepack.AdventurePack;
import syntacticsplenda.adventurepack.block.tileentity.TileEntityLight;
import syntacticsplenda.adventurepack.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(AdventurePack.modId)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public final class ModBlocks {

    @ObjectHolder("block_light")
    public static Block blockLight;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> evt) {
        IForgeRegistry<Block> registry = evt.getRegistry();
        registry.register(new BlockLight(Block.Properties.of(Material.AIR).lightLevel((x) -> 15)).setRegistryName(AdventurePack.modId, "block_light"));
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> evt) {
        IForgeRegistry<Item> r = evt.getRegistry();
        Item.Properties props = ModItems.defaultBuilder();
        r.register(new BlockItem(blockLight, props).setRegistryName(blockLight.getRegistryName()));
    }

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> evt) {
        IForgeRegistry<TileEntityType<?>> registry = evt.getRegistry();
        registry.register(TileEntityType.Builder.of(TileEntityLight::new, blockLight).build(null).setRegistryName(AdventurePack.modId, "tile_entity_light"));
    }

}
