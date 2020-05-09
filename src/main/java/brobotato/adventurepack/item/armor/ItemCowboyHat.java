package brobotato.adventurepack.item.armor;

import brobotato.adventurepack.AdventurePack;
import brobotato.adventurepack.client.model.ModelCowboyHat;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.ItemStack;

public class ItemCowboyHat extends DyeableArmorItem {

    public final EquipmentSlot type;

    public ItemCowboyHat(EquipmentSlot type, ArmorMaterial mat, Settings settings) {
        super(mat, type, settings);
        this.type = type;
    }

    //need mixins into ArmorFeatureRenderer
    @Environment(EnvType.CLIENT)
    public BipedEntityModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, BipedEntityModel original) {
        return new ModelCowboyHat(1.0f);
    }

    public final String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return type == null ? AdventurePack.modId + ":" + "/textures/models/armor/cowboy_layer_1.png" : AdventurePack.modId + ":" + "/textures/models/armor/cowboy_layer_1_overlay.png";
    }
}
