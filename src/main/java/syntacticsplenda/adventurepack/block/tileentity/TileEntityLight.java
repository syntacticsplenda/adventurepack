package syntacticsplenda.adventurepack.block.tileentity;

import syntacticsplenda.adventurepack.AdventurePack;
import net.minecraft.block.Blocks;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.world.World;
import net.minecraftforge.registries.ObjectHolder;

public class TileEntityLight extends TileEntity implements ITickableTileEntity {

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
