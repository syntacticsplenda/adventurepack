package brobotato.adventurepack.item;

import brobotato.adventurepack.block.ModBlocks;
import brobotato.adventurepack.config.Config;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public interface ILightProducing {

    default ActionResult<ItemStack> toggleLight(EntityPlayer playerIn, EnumHand handIn){
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!itemStack.hasTag()) {
            itemStack.getOrCreateTag();
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("on", new NBTTagInt(1));
            itemStack.setTag(tag);
        }
        if (itemStack.getTag().getInt("on") == 1) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("on", new NBTTagInt(0));
            itemStack.setTag(tag);
        } else if (itemStack.getTag().getInt("on") == 0) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("on", new NBTTagInt(1));
            itemStack.setTag(tag);
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    default RayTraceResult rayTrace(double blockReachDistance, float partialTicks, EntityPlayer player) {
        Vec3d vec3d = new Vec3d(player.getPosition().getX(), player.getPosition().getY() + player.getEyeHeight(), player.getPosition().getZ());
        Vec3d vec3d1 = player.getLook(partialTicks);
        Vec3d vec3d2 = vec3d.add(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
        return player.world.rayTraceBlocks(vec3d, vec3d2, RayTraceFluidMode.NEVER, true, true);
    }

    default void createLight(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (itemStack.hasTag() && itemStack.getTag().getInt("on") == 1) return;
            RayTraceResult lookPos = rayTrace(Config.COMMON.helmetRange.get(), 1.0f, player);
            BlockPos pos;
            if (lookPos == null) return;
            if (lookPos.sideHit != null) pos = lookPos.getBlockPos().offset(lookPos.sideHit);
            else pos = lookPos.getBlockPos();
            double vecDistance = Math.pow(lookPos.hitVec.squareDistanceTo(player.posX, player.posY, player.posZ), 0.5);
            if (vecDistance <= Config.COMMON.helmetRange.get()) {
                if (world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos), world, pos)) {
                    player.world.setBlockState(pos, ModBlocks.blockLight.getDefaultState(), 2);
                } else if (world.getBlockState(pos.add(0, 1, 0)).getBlock().isAir(world.getBlockState(pos.add(0, 1, 0)), world, pos.add(0, 1, 0))) {
                    player.world.setBlockState(pos.add(0, 1, 0), ModBlocks.blockLight.getDefaultState(), 2);
                }
            }
        }
    }
}
