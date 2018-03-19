package brobotato.adventurepack.item.armor;

import brobotato.adventurepack.AdventurePack;
import brobotato.adventurepack.block.ModBlocks;
import brobotato.adventurepack.config.ModConfig;
import brobotato.adventurepack.proxy.ClientProxy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExplorerHat extends ItemArmor {

    public static final ItemArmor.ArmorMaterial explorerArmorMaterial = EnumHelper.addArmorMaterial("EXPLORER",
            AdventurePack.modId + ":explorer", 15, new int[]{2, 2, 2, 2}, 9,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public ItemExplorerHat() {
        super(explorerArmorMaterial, EntityEquipmentSlot.HEAD, "explorer_hat");
        this.setMaxDamage(133);
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
                        armorModel = AdventurePack.proxy.getArmorModel(ClientProxy.E_HAT);
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
