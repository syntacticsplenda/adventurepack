package brobotato.adventurepack.item.armor;

import brobotato.adventurepack.AdventurePack;
import brobotato.adventurepack.client.model.ModelMiningHelm;
import brobotato.adventurepack.item.ILightProducing;
import brobotato.adventurepack.item.ModItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class ItemMiningHelm extends ArmorItem implements ILightProducing {

    public final EquipmentSlot type;

    public ItemMiningHelm(EquipmentSlot type, ArmorMaterial mat, Settings settings) {
        super(mat, type, settings);
        this.type = type;
    }

    public void inventoryTick(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entity;
        DefaultedList<ItemStack> armor = (DefaultedList) player.getArmorItems();
        if (armor.get(3).isItemEqualIgnoreDamage(new ItemStack(ModItems.miningHelm)))
            createLight(itemStack, world, player);
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!playerIn.isSneaking()) super.use(worldIn, playerIn, handIn);
        return toggleLight(playerIn, handIn);
    }


    //need mixins into ArmorFeatureRenderer
    @Environment(EnvType.CLIENT)
    public BipedEntityModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, BipedEntityModel original) {
        return new ModelMiningHelm(1.0f);
    }

    public final String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        return AdventurePack.modId + ":" + "/textures/models/armor/mining_layer_1.png";
    }

}
