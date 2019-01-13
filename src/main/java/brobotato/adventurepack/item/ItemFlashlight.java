package brobotato.adventurepack.item;

import brobotato.adventurepack.block.ModBlocks;
import brobotato.adventurepack.config.ModConfig;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;


public class ItemFlashlight extends ItemBase {

    public ItemFlashlight() {
        super("flashlight");
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setMaxStackSize(1);
        this.addPropertyOverride(new ResourceLocation("light"), new IItemPropertyGetter()
        {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
            {
                if (entityIn == null)
                {
                    return 0.0F;
                }
                else
                {
                    if (!stack.hasTagCompound()) return 0.0F;
                    return stack.getTagCompound().getInteger("on") == 1 ? 0.0F : 1.0F;
                }
            }
        });
    }

    private RayTraceResult rayTrace(double blockReachDistance, float partialTicks, EntityPlayer player) {
        Vec3d vec3d = player.getPositionEyes(partialTicks);
        Vec3d vec3d1 = player.getLook(partialTicks);
        Vec3d vec3d2 = vec3d.addVector(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
        return player.world.rayTraceBlocks(vec3d, vec3d2, false, true, true);
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean flag) {
        if (!(entity instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer) entity;
        if (!(player.getHeldItemMainhand() == itemStack) && !(player.getHeldItemOffhand() == itemStack)) return;
        if (!world.isRemote) {
            if (itemStack.hasTagCompound() && itemStack.getTagCompound().getInteger("on") == 1) return;
            RayTraceResult lookPos = rayTrace(ModConfig.helmetRange, 1.0f, player);
            BlockPos pos;
            if (lookPos == null) return;
            if (lookPos.sideHit != null) pos = lookPos.getBlockPos().offset(lookPos.sideHit);
            else pos = lookPos.getBlockPos();
            double vecDistance = Math.pow(lookPos.hitVec.squareDistanceTo(player.posX, player.posY, player.posZ), 0.5);
            if (vecDistance <= ModConfig.helmetRange) {
                if (world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos), world, pos)) {
                    player.world.setBlockState(pos, ModBlocks.blockLight.getDefaultState(), 2);
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!itemStack.hasTagCompound()) {
            itemStack.getOrCreateSubCompound("on");
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("on", new NBTTagInt(1));
            itemStack.setTagCompound(tag);
        }
        if (itemStack.getTagCompound().getInteger("on") == 1) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("on", new NBTTagInt(0));
            itemStack.setTagCompound(tag);
        } else if (itemStack.getTagCompound().getInteger("on") == 0) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("on", new NBTTagInt(1));
            itemStack.setTagCompound(tag);
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }
}
