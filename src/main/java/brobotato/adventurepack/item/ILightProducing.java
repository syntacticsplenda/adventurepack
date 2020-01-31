package brobotato.adventurepack.item;

import brobotato.adventurepack.block.ModBlocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RayTraceContext;
import net.minecraft.world.World;

public interface ILightProducing {

    default TypedActionResult<ItemStack> toggleLight(PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getStackInHand(handIn);
        if (!itemStack.hasTag()) {
            itemStack.getOrCreateTag();
            CompoundTag tag = new CompoundTag();
            tag.put("on", new IntTag(1));
            itemStack.setTag(tag);
        }
        if (itemStack.getTag().getInt("on") == 1) {
            CompoundTag tag = new CompoundTag();
            tag.put("on", new IntTag(0));
            itemStack.setTag(tag);
        } else if (itemStack.getTag().getInt("on") == 0) {
            CompoundTag tag = new CompoundTag();
            tag.put("on", new IntTag(1));
            itemStack.setTag(tag);
        }
        return new TypedActionResult<ItemStack>(ActionResult.PASS, playerIn.getStackInHand(handIn));
    }

    default BlockHitResult rayTrace(double blockReachDistance, float partialTicks, PlayerEntity player) {
        float float_1 = player.pitch;
        float float_2 = player.yaw;
        Vec3d vec3d_1 = player.getCameraPosVec(partialTicks);
        float float_3 = MathHelper.cos(-float_2 * 0.017453292F - 3.1415927F);
        float float_4 = MathHelper.sin(-float_2 * 0.017453292F - 3.1415927F);
        float float_5 = -MathHelper.cos(-float_1 * 0.017453292F);
        float float_6 = MathHelper.sin(-float_1 * 0.017453292F);
        float float_7 = float_4 * float_5;
        float float_9 = float_3 * float_5;
        Vec3d vec3d_2 = vec3d_1.add((double) float_7 * blockReachDistance, (double) float_6 * blockReachDistance, (double) float_9 * blockReachDistance);
        return player.world.rayTrace(new RayTraceContext(vec3d_1, vec3d_2, RayTraceContext.ShapeType.OUTLINE, RayTraceContext.FluidHandling.NONE, player));
    }

    default void createLight(ItemStack itemStack, World world, PlayerEntity player) {
        if (!world.isClient) {
            if (itemStack.hasTag() && itemStack.getTag().getInt("on") == 1) return;
            BlockHitResult lookPos = rayTrace(15/*Config.COMMON.helmetRange.get()*/, 1.0f, player);
            BlockPos pos;
            if (lookPos.getType() == BlockHitResult.Type.MISS) return;
            if (lookPos.getType() == BlockHitResult.Type.BLOCK)
                pos = lookPos.getBlockPos().add(lookPos.getSide().getVector());
            else pos = lookPos.getBlockPos();
            double vecDistance = Math.pow(lookPos.getPos().squaredDistanceTo(player.getPos()), 0.5);
            if (vecDistance <= 15 /*Config.COMMON.helmetRange.get()*/) {
                setBlockToLight(pos, world, player);
            }
        }
    }

    default void setBlockToLight(BlockPos pos, World world, PlayerEntity player) {
        if (world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos))) {
            player.world.setBlockState(pos, ModBlocks.blockLight.getDefaultState(), 2);
        } else if (world.getBlockState(pos.add(0, 1, 0)).getBlock().isAir(world.getBlockState(pos.add(0, 1, 0)))) {
            player.world.setBlockState(pos.add(0, 1, 0), ModBlocks.blockLight.getDefaultState(), 2);
        }
    }
}
