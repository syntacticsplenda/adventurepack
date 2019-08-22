package brobotato.adventurepack.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;


public class ItemFlashlight extends ItemBase implements ILightProducing {

    public ItemFlashlight(Properties properties) {
        super(properties);
        addPropertyOverride(new ResourceLocation("light"), new IItemPropertyGetter() {
            @Override
            public float call(ItemStack stack, @Nullable World worldIn, @Nullable LivingEntity entityIn) {
                if (entityIn == null) {
                    return 0.0F;
                } else {
                    if (!stack.hasTag()) return 0.0F;
                    return stack.getTag().getInt("on") == 1 ? 0.0F : 1.0F;
                }
            }
        });
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
