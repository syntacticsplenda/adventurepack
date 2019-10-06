package brobotato.adventurepack.block.tileentity;

import brobotato.adventurepack.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Tickable;
import net.minecraft.world.World;

public class TileEntityLight extends BlockEntity implements Tickable {

    public TileEntityLight() {
        super(ModBlocks.tileLightEntity);
    }

    @Override
    public void tick() {
        if (!this.hasWorld()) return;
        World world = this.getWorld();
        if (world.isClient) return;
        world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
    }

}
