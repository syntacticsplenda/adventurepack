package brobotato.adventurepack.item.armor;

import brobotato.adventurepack.AdventurePack;
import brobotato.adventurepack.proxy.ClientProxy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMiningHelm extends ItemArmor {
    public static final ItemArmor.ArmorMaterial miningArmorMaterial = EnumHelper.addArmorMaterial("MINING",
            AdventurePack.modId + ":mining", 15, new int[]{2, 0, 0, 0}, 9,
            SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);

    public ItemMiningHelm() {
        super(miningArmorMaterial, EntityEquipmentSlot.HEAD, "mining_helmet");
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
