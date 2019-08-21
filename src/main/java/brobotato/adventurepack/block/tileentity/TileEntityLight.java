package brobotato.adventurepack.block.tileentity;

import brobotato.adventurepack.AdventurePack;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import net.minecraftforge.registries.ObjectHolder;

public class TileEntityLight extends TileEntity implements ITickable {

    @ObjectHolder(AdventurePack.modId + ":" + "tile_entity_light")
    public static TileEntityType<TileEntityLight> TYPE;

    public TileEntityLight() {
        super(TYPE);
    }

    @Override
    public void tick() {
        if (!this.hasWorld()) return;
        World world = this.getWorld();
        if (world.isRemote) return;
        world.setBlockState(this.pos, Blocks.AIR.getDefaultState());
    }

}
