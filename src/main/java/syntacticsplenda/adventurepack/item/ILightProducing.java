package syntacticsplenda.adventurepack.item;

import com.sun.javafx.geom.Vec3d;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3i;
import syntacticsplenda.adventurepack.block.ModBlocks;
import syntacticsplenda.adventurepack.config.Config;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

public interface ILightProducing {

    default ActionResult<ItemStack> toggleLight(PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!itemStack.hasTag()) {
            itemStack.getOrCreateTag();
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("on",1);
            itemStack.setTag(tag);
        }
        if (itemStack.getTag().getInt("on") == 1) {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("on", 0);
            itemStack.setTag(tag);
        } else if (itemStack.getTag().getInt("on") == 0) {
            CompoundNBT tag = new CompoundNBT();
            tag.putInt("on", 1);
            itemStack.setTag(tag);
        }
        return new ActionResult<ItemStack>(ActionResultType.PASS, playerIn.getHeldItem(handIn));
    }

    default BlockRayTraceResult rayTrace(double blockReachDistance, float partialTicks, PlayerEntity player) {
        Vector3d vec3d = new Vector3d(player.getPositionVec().getX(), player.getPositionVec().getY() + player.getEyeHeight(), player.getPositionVec().getZ());
        Vector3d vec3d1 = player.getLook(partialTicks);
        Vector3d vec3d2 = vec3d.add(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
        return player.world.rayTraceBlocks(new RayTraceContext(vec3d, vec3d2, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, player));
    }

    default void createLight(ItemStack itemStack, World world, PlayerEntity player) {
        if (!world.isRemote) {
            if (itemStack.hasTag() && itemStack.getTag().getInt("on") == 1) return;
            BlockRayTraceResult lookPos = rayTrace(Config.COMMON.helmetRange.get(), 1.0f, player);
            BlockPos pos;
            if (lookPos.getType() == RayTraceResult.Type.MISS) return;
            if (lookPos.getType() == RayTraceResult.Type.BLOCK) pos = lookPos.getPos().offset(lookPos.getFace());
            else pos = lookPos.getPos();
            double vecDistance = Math.pow(lookPos.getPos().distanceSq(player.getPositionVec().getX(), player.getPositionVec().getY(), player.getPositionVec().getZ(), true), 0.5);
            if (vecDistance <= Config.COMMON.helmetRange.get()) {
                setBlockToLight(pos, world, player);
            }
        }
    }

    default void setBlockToLight(BlockPos pos, World world, PlayerEntity player) {
        if (world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos), world, pos)) {
            player.world.setBlockState(pos, ModBlocks.blockLight.getDefaultState(), 2);
        } else if (world.getBlockState(pos.add(0, 1, 0)).getBlock().isAir(world.getBlockState(pos.add(0, 1, 0)), world, pos.add(0, 1, 0))) {
            player.world.setBlockState(pos.add(0, 1, 0), ModBlocks.blockLight.getDefaultState(), 2);
        }
    }
}
