package syntacticsplenda.adventurepack.item.armor;

import syntacticsplenda.adventurepack.AdventurePack;
import syntacticsplenda.adventurepack.client.model.ModelExplorerHat;
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

public class ItemExplorerHat extends ArmorItem implements IDyeableArmorItem {

    public static final IArmorMaterial explorerArmorMaterial = new IArmorMaterial() {
        private final int[] damageReduction = {2, 2, 2, 2};

        @Override
        public int getDurabilityForSlot(EquipmentSlotType slotIn) {
            return 133;
        }

        @Override
        public int getDefenseForSlot(EquipmentSlotType slotIn) {
            return damageReduction[slotIn.getIndex()];
        }

        @Override
        public int getEnchantmentValue() {
            return 9;
        }

        @Override
        public SoundEvent getEquipSound() {
            return SoundEvents.ARMOR_EQUIP_IRON;
        }

        @Override
        public Ingredient getRepairIngredient() {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", "leather"));
            return Ingredient.of(item);
        }

        @Override
        public String getName() {
            return "explorer";
        }

        @Override
        public float getToughness() {
            return 0;
        }

        @Override
        public float getKnockbackResistance() {
            return 0;
        }
    };

    public final EquipmentSlotType type;

    public ItemExplorerHat(EquipmentSlotType type, IArmorMaterial mat, Properties props) {
        super(mat, type, props);
        this.type = type;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel original) {
        return new ModelExplorerHat(1.0f);
    }

    @Nonnull
    @Override
    public final String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return type == null ? AdventurePack.modId + ":" + "/textures/models/armor/explorer_layer_1.png" : AdventurePack.modId + ":" + "/textures/models/armor/explorer_layer_1_overlay.png";
    }
}
