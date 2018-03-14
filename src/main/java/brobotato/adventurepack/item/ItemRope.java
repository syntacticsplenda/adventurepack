package brobotato.adventurepack.item;

import brobotato.adventurepack.config.ModConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;


public class ItemRope extends ItemBase {

    BlockPos lastOpenPos = null;
    int dimension;

    public ItemRope() {
        super("escape_rope");
        this.setCreativeTab(CreativeTabs.TOOLS);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        if (ModConfig.client.instantRope) {
            BlockPos currentPos = player.getPosition();
            if (!world.isRemote && !world.canBlockSeeSky(currentPos) && (world.provider.getDimension() == dimension)) {
                player.setPositionAndUpdate(lastOpenPos.getX(), lastOpenPos.getY(), lastOpenPos.getZ());
                if (!player.capabilities.isCreativeMode)
                    stack.shrink(1);
            }
        }
        return ActionResult.newResult(EnumActionResult.SUCCESS, stack);
    }

    @Nonnull
    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack stack) {
        if(!ModConfig.client.instantRope) return 72000;
        else return 0;
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entity, int timeLeft) {

        if (!(entity instanceof EntityPlayer)) {
            return;
        }

        // stolen fron TiCo slime sling lmao
        int i = this.getMaxItemUseDuration(stack) - timeLeft;
        float f = i / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        f *= 4f;

        if (f > 6f) {
            f = 6f;
        }

        if (f == 6f && !ModConfig.client.instantRope) {
            BlockPos currentPos = entity.getPosition();
            if (!world.isRemote && !world.canBlockSeeSky(currentPos) && (world.provider.getDimension() == dimension)) {
                entity.setPositionAndUpdate(lastOpenPos.getX(), lastOpenPos.getY(), lastOpenPos.getZ());
                EntityPlayer playerIn = (EntityPlayer) entity;
                if (!playerIn.capabilities.isCreativeMode)
                    stack.shrink(1);
            }
        }
    }

    @Override
    public void onUpdate(ItemStack ItemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote) {
            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
            if (world.canBlockSeeSky(pos) && entity.onGround) {
                lastOpenPos = pos;
                dimension = world.provider.getDimension();
            }
        }
    }
}
