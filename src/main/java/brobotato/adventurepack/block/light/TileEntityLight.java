package brobotato.adventurepack.block.light;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityLight extends TileEntity implements ITickable {

    @Override
    public void update() {
        if (!this.hasWorld()) return;
        World world = this.getWorld();
        if (world.isRemote) return;
        world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
    }

}
