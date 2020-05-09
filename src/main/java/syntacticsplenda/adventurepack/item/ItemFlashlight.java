package syntacticsplenda.adventurepack.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;


public class ItemFlashlight extends ItemBase implements ILightProducing {

    public ItemFlashlight(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entity;
        if (!(player.getHeldItemMainhand() == itemStack) && !(player.getHeldItemOffhand() == itemStack)) return;
        createLight(itemStack, world, player);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        return toggleLight(playerIn, handIn);
    }

}
