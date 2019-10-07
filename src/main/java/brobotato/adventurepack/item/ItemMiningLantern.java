package brobotato.adventurepack.item;

import brobotato.adventurepack.block.ModBlocks;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMiningLantern extends ItemBase {

    public ItemMiningLantern() {
        super("mining_lantern");
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setMaxStackSize(1);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) entity;
        if (!(player.getHeldItemMainhand() == itemStack) && !(player.getHeldItemOffhand() == itemStack)) return;
        BlockPos pos = player.getPosition().add(0, 2, 0);
        if (world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos), world, pos)) {
            player.world.setBlockState(pos, ModBlocks.blockLight.getDefaultState(), 2);
        } else if (world.getBlockState(pos.add(0, 1, 0)).getBlock().isAir(world.getBlockState(pos.add(0, 1, 0)), world, pos.add(0, 1, 0))) {
            player.world.setBlockState(pos.add(0, 1, 0), ModBlocks.blockLight.getDefaultState(), 2);
        }
    }
}
