package brobotato.adventurepack.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class ItemRope extends ItemBase {

    public ItemRope(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
//        if (Config.COMMON.instantRope.get()) {
//            teleportUser(stack, world, player);
//        }
        return new TypedActionResult(ActionResult.SUCCESS, stack);
    }

    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    public int getMaxUseTime(ItemStack stack) {
        /*if (!Config.COMMON.instantRope.get())*/
        return 72000;
//        else return 0;
    }

    public void onStoppedUsing(ItemStack stack, World world, LivingEntity entity, int timeLeft) {

        if (!(entity instanceof PlayerEntity)) {
            return;
        }

        int i = this.getMaxUseTime(stack) - timeLeft;
        float f = i / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1f) {
            f = 1f;
        }
        if ((double) f >= 0.1D) {
            teleportUser(stack, world, (PlayerEntity) entity);
        }
    }

    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isClient) {
            BlockPos pos = new BlockPos(entity.x, entity.y, entity.z);
            if (world.isSkyVisible(pos) && entity.onGround) {
                if (!itemStack.hasTag()) {
                    itemStack.getOrCreateTag();
                }
                CompoundTag tag = new CompoundTag();
                tag.put("x", new IntTag(pos.getX()));
                tag.put("y", new IntTag(pos.getY()));
                tag.put("z", new IntTag(pos.getZ()));
                tag.put("dim", new IntTag(world.getDimension().getType().getRawId()));
                itemStack.setTag(tag);
            }
        }
    }

    public void teleportUser(ItemStack stack, World world, PlayerEntity player) {
        BlockPos currentPos = player.getBlockPos();
        if (player instanceof ServerPlayerEntity && !stack.hasTag()) {
//            if (Config.COMMON.ropeSpawn.get()) {
            if (!world.isClient && !world.isSkyVisible(currentPos) && (player.getSpawnPosition() != null)) {
                player.requestTeleport(player.getSpawnPosition().getX(),
                        player.getSpawnPosition().getY(),
                        player.getSpawnPosition().getZ()
                );
                if (!player.abilities.creativeMode)
                    stack.decrement(1);
            }

//        }
        } else {
            if (!world.isClient && !world.isSkyVisible(currentPos) && (world.getDimension().getType().getRawId() == stack.getTag().getInt("dim"))) {
                player.requestTeleport(stack.getTag().getInt("x"),
                        stack.getTag().getInt("y"),
                        stack.getTag().getInt("z"));
                if (!player.abilities.creativeMode)
                    stack.decrement(1);
            } else if (world.isClient && !world.isSkyVisible(currentPos) && (world.getDimension().getType().getRawId() == stack.getTag().getInt("dim"))) {
                player.updateTrackedPosition(stack.getTag().getInt("x"),
                        stack.getTag().getInt("y"),
                        stack.getTag().getInt("z"));
            }
        }
    }
}