package syntacticsplenda.adventurepack.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import syntacticsplenda.adventurepack.config.Config;

import javax.annotation.Nonnull;

public class ItemRope extends ItemBase {

    public ItemRope(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getItemInHand(hand);
        player.startUsingItem(hand);
        if (Config.COMMON.instantRope.get()) {
            teleportUser(stack, world, player);
        }
        return new ActionResult(ActionResultType.SUCCESS, stack);
    }

    @Nonnull
    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        if (!Config.COMMON.instantRope.get()) return 72000;
        else return 0;
    }

    @Override
    public void releaseUsing(ItemStack stack, World world, LivingEntity entity, int timeLeft) {

        if (!(entity instanceof PlayerEntity)) {
            return;
        }

        // stolen fron TiCo slime sling lmao
        int i = this.getUseDuration(stack) - timeLeft;
        float f = i / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        f *= 4f;

        if (f > 6f) {
            f = 6f;
        }

        if (f == 6f && !Config.COMMON.instantRope.get()) {
            teleportUser(stack, world, (PlayerEntity) entity);
        }
    }

    @Override
    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isClientSide) {
            BlockPos pos = new BlockPos(entity.position());
            if (world.canSeeSkyFromBelowWater(pos) && entity.isOnGround()) {
                if (!itemStack.hasTag()) {
                    itemStack.getOrCreateTag();
                }
                CompoundNBT tag = new CompoundNBT();
                tag.putInt("x", pos.getX());
                tag.putInt("y", pos.getY());
                tag.putInt("z", pos.getZ());
                tag.putString("dim", world.dimensionType().toString());
                itemStack.setTag(tag);
            }
        }
    }

    public void teleportUser(ItemStack stack, World world, PlayerEntity player) {
        BlockPos currentPos = new BlockPos(player.position());
        if (!stack.hasTag()) {
            if (Config.COMMON.ropeSpawn.get()) {
                if (!world.isClientSide && !world.canSeeSkyFromBelowWater(currentPos) && (player.getSleepingPos().isPresent())) {
                    player.teleportToWithTicket(player.getSleepingPos().get().getX(),
                            player.getSleepingPos().get().getY(),
                            player.getSleepingPos().get().getZ())
                    ;
                    if (!player.abilities.instabuild)
                        stack.shrink(1);
                }

            }
        } else if (!world.isClientSide && !world.canSeeSkyFromBelowWater(currentPos) &&
                (world.dimensionType().toString().equals(stack.getTag().getString("dim")))) {
            player.teleportTo(stack.getTag().getInt("x"),
                    stack.getTag().getInt("y"),
                    stack.getTag().getInt("z"));
            if (!player.abilities.instabuild)
                stack.shrink(1);
        }
    }
}