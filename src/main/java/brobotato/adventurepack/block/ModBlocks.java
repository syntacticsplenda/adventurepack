package brobotato.adventurepack.block;

import brobotato.adventurepack.AdventurePack;
import brobotato.adventurepack.block.tileentity.TileEntityLight;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ModBlocks {

    public static final Block blockLight = new BlockLight(FabricBlockSettings.of(Material.AIR).build());
    public static BlockEntityType<TileEntityLight> tileLightEntity;

    public static void registerBlocks() {
        Registry.register(Registry.BLOCK, new Identifier(AdventurePack.modId, "block_light"), blockLight);
    }

    public static void registerTileEntities() {
        tileLightEntity = Registry.register(Registry.BLOCK_ENTITY, new Identifier(AdventurePack.modId, "tile_entity_light"), BlockEntityType.Builder.create(TileEntityLight::new, blockLight).build(null));
    }

}
