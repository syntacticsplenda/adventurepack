package brobotato.adventurepack.item.armor;

import brobotato.adventurepack.AdventurePack;
import brobotato.adventurepack.block.light.BlockLight;
import brobotato.adventurepack.config.ModConfig;
import brobotato.adventurepack.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMiningHelm extends ItemArmor {

    RayTraceResult lookPos = null, lastLookPos;

    public static final ItemArmor.ArmorMaterial miningArmorMaterial = EnumHelper.addArmorMaterial("MINING",
            AdventurePack.modId + ":mining", 15, new int[]{2, 0, 0, 0}, 9,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public ItemMiningHelm() {
        super(miningArmorMaterial, EntityEquipmentSlot.HEAD, "mining_helmet");
    }

    public BlockPos getRayTraceBefore(RayTraceResult rayTrace) {
        int x = rayTrace.getBlockPos().getX();
        int y = rayTrace.getBlockPos().getY();
        int z = rayTrace.getBlockPos().getZ();
        switch (rayTrace.sideHit) {
            case DOWN:
                y -= 1;
                break;
            case UP:
                y += 1;
                break;
            case NORTH:
                z -= 1;
                break;
            case SOUTH:
                z += 1;
                break;
            case WEST:
                x -= 1;
                break;
            case EAST:
                x += 1;
                break;
        }
        return new BlockPos(x, y, z);
    }

    public int getRayTraceDistance(RayTraceResult rayTrace, EntityPlayer playerIn) {
        return (int) Math.sqrt(Math.pow(playerIn.posX - rayTrace.getBlockPos().getX(), 2)
                + Math.pow(playerIn.posY - rayTrace.getBlockPos().getY(), 2)
                + Math.pow(playerIn.posZ - rayTrace.getBlockPos().getZ(), 2));
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
        System.currentTimeMillis();
        lookPos = player.rayTrace(15, Minecraft.getMinecraft().getRenderPartialTicks());
        if (getRayTraceDistance(lookPos, player) <= ModConfig.client.helmetRange)
            world.setBlockState(getRayTraceBefore(lookPos), new BlockLight().getDefaultState());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot,
                                    ModelBiped defaultModel) {

        if (itemStack != null) {
            if (itemStack.getItem() instanceof ItemArmor) {

                EntityEquipmentSlot type = ((ItemArmor) itemStack.getItem()).armorType;
                ModelBiped armorModel = null;
                switch (type) {
                    case HEAD:
                        armorModel = AdventurePack.proxy.getArmorModel(ClientProxy.M_HELM);
                        break;
                    case LEGS:
                    case FEET:
                    case CHEST:
                    default:
                        break;
                }

                armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                armorModel.bipedHeadwear.showModel = armorSlot == EntityEquipmentSlot.HEAD;
                armorModel.bipedBody.showModel = (armorSlot == EntityEquipmentSlot.CHEST);
                armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
                armorModel.bipedRightLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
                        || (armorSlot == EntityEquipmentSlot.FEET);
                armorModel.bipedLeftLeg.showModel = (armorSlot == EntityEquipmentSlot.LEGS)
                        || (armorSlot == EntityEquipmentSlot.FEET);

                armorModel.isSneak = defaultModel.isSneak;
                armorModel.isRiding = defaultModel.isRiding;
                armorModel.isChild = defaultModel.isChild;
                armorModel.rightArmPose = defaultModel.rightArmPose;
                armorModel.leftArmPose = defaultModel.leftArmPose;

                return armorModel;
            }
        }
        return null;
    }
}
