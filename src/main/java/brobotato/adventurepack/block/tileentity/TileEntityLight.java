package brobotato.adventurepack.block.tileentity;

import brobotato.adventurepack.block.ModBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityLight extends TileEntity implements ITickable {

    public TileEntityLight() {
        super(ModBlocks.tileEntityLight);
    }

    @Override
    public void tick() {
        if (!this.hasWorld()) return;
        World world = this.getWorld();
        if (world.isRemote) return;
        world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
    }

}
