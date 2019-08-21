package brobotato.adventurepack.block;

import brobotato.adventurepack.AdventurePack;
import brobotato.adventurepack.block.tileentity.TileEntityLight;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(AdventurePack.modId)
public class ModBlocks {

    public static final Block blockLight = null;
    public static final TileEntityType<TileEntityLight> tileEntityLight = null;

    public static void registerBlocks(RegistryEvent.Register<Block> registry) {
        registry.getRegistry().register(new BlockLight("adventurepack_light", Block.Properties.from(Blocks.AIR)));
    }

    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> registry) {
        registry.getRegistry().register(TileEntityType.Builder.create(TileEntityLight::new).build(null).setRegistryName(AdventurePack.modId, "tile_entity_light"));
    }

}
