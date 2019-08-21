package brobotato.adventurepack.block;

import brobotato.adventurepack.AdventurePack;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public class BlockBase extends Block {

    public BlockBase(String name, Properties properties) {
        super(properties);
        this.setRegistryName(new ResourceLocation(AdventurePack.modId, name));
    }
}