package brobotato.adventurepack.item;

import brobotato.adventurepack.config.ModConfig;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;


public class ItemRope extends ItemBase {

    public ItemRope() {
        super("escape_rope");
        this.setCreativeTab(CreativeTabs.TOOLS);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        player.setActiveHand(hand);
        if (ModConfig.instantRope) {
            BlockPos currentPos = player.getPosition();
            if (!stack.hasTagCompound()) {
                if (ModConfig.ropeSpawn) {
                    if (!world.isRemote && !world.canBlockSeeSky(currentPos) && (player.getBedLocation() != null)) {
                        player.setPositionAndUpdate(player.getBedLocation().getX(),
                                player.getBedLocation().getY(),
                                player.getBedLocation().getZ()
                        );
                        if (!player.capabilities.isCreativeMode)
                            stack.shrink(1);
                    }

                }
            } else if (!world.isRemote && !world.canBlockSeeSky(currentPos) && (world.provider.getDimension() == stack.getTagCompound().getInteger("dim"))) {
                player.setPositionAndUpdate(stack.getTagCompound().getInteger("x"),
                        stack.getTagCompound().getInteger("y"),
                        stack.getTagCompound().getInteger("z"));
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
        if (!ModConfig.instantRope) return 72000;
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

        if (f == 6f && !ModConfig.instantRope) {
            BlockPos currentPos = entity.getPosition();
            EntityPlayer playerIn = (EntityPlayer) entity;
            if (!stack.hasTagCompound()) {
                if (ModConfig.ropeSpawn) {
                    if (!world.isRemote && !world.canBlockSeeSky(currentPos) && (playerIn.getBedLocation() != null)) {
                        entity.setPositionAndUpdate(playerIn.getBedLocation().getX(),
                                playerIn.getBedLocation().getY(),
                                playerIn.getBedLocation().getZ()
                        );
                        if (!playerIn.capabilities.isCreativeMode)
                            stack.shrink(1);
                    }

                }
            } else if (!world.isRemote && !world.canBlockSeeSky(currentPos) && (world.provider.getDimension() == stack.getTagCompound().getInteger("dim"))) {
                entity.setPositionAndUpdate(stack.getTagCompound().getInteger("x"),
                        stack.getTagCompound().getInteger("y"),
                        stack.getTagCompound().getInteger("z"));
                if (!playerIn.capabilities.isCreativeMode)
                    stack.shrink(1);
            }
        }
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote) {
            BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
            if (world.canBlockSeeSky(pos) && entity.onGround) {
                if (!itemStack.hasTagCompound()) {
                    itemStack.getOrCreateSubCompound("x");
                    itemStack.getOrCreateSubCompound("y");
                    itemStack.getOrCreateSubCompound("z");
                    itemStack.getOrCreateSubCompound("dim");
                }
                NBTTagCompound tag = new NBTTagCompound();
                tag.setTag("x", new NBTTagInt(pos.getX()));
                tag.setTag("y", new NBTTagInt(pos.getY()));
                tag.setTag("z", new NBTTagInt(pos.getZ()));
                tag.setTag("dim", new NBTTagInt(world.provider.getDimension()));
                itemStack.setTagCompound(tag);
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        TextComponentString info = new TextComponentString("Teleports you out of caves");
        info.setStyle(new Style().setItalic(true));
        tooltip.add(info.getFormattedText());
    }
}
