package syntacticsplenda.adventurepack.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMiningLantern extends ItemBase implements ILightProducing {

    public ItemMiningLantern(Properties properties) {
        super(properties);
    }


    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entity;
        if (!(player.getMainHandItem() == itemStack) && !(player.getOffhandItem() == itemStack)) return;
        createLight(itemStack, world, player);
    }

    @Override
    public void createLight(ItemStack itemStack, World world, PlayerEntity player) {
        if (!world.isClientSide) {
            BlockPos pos = new BlockPos(player.position());
            setBlockToLight(pos, world, player);
        }
    }
}
