package brobotato.adventurepack.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMiningLantern extends ItemBase implements ILightProducing {

    public ItemMiningLantern(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entity;
        if (!(player.getStackInHand(Hand.MAIN_HAND) == itemStack) && !(player.getStackInHand(Hand.OFF_HAND) == itemStack))
            return;

        createLight(itemStack, world, player);
    }

    public void createLight(ItemStack itemStack, World world, PlayerEntity player) {
        if (!world.isClient) {
            BlockPos pos = player.getBlockPos();
            setBlockToLight(pos, world, player);
        }
    }
}
