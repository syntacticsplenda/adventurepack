package syntacticsplenda.adventurepack.item.armor;

import syntacticsplenda.adventurepack.AdventurePack;
import syntacticsplenda.adventurepack.client.model.ModelCowboyHat;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;

public class ItemCowboyHat extends ArmorItem implements IDyeableArmorItem {

    public static final IArmorMaterial cowboyArmorMaterial = new IArmorMaterial() {
        private final int[] damageReduction = {2, 2, 2, 2};

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return 133;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return damageReduction[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return 9;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return SoundEvents.ITEM_ARMOR_EQUIP_LEATHER;
        }

        @Override
        public Ingredient getRepairMaterial() {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", "leather"));
            return Ingredient.fromItems(item);
        }

        @Override
        public String getName() {
            return "cowboy";
        }

        @Override
        public float getToughness() {
            return 0;
        }
    };

    public final EquipmentSlotType type;

    public ItemCowboyHat(EquipmentSlotType type, IArmorMaterial mat, Properties props) {
        super(mat, type, props);
        this.type = type;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel original) {
        return new ModelCowboyHat(1.0f);
    }

    @Nonnull
    @Override
    public final String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return type == null ? AdventurePack.modId + ":" + "/textures/models/armor/cowboy_layer_1.png" : AdventurePack.modId + ":" + "/textures/models/armor/cowboy_layer_1_overlay.png";
    }
}
