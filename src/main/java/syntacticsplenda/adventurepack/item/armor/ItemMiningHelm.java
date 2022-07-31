package syntacticsplenda.adventurepack.item.armor;

import syntacticsplenda.adventurepack.AdventurePack;
import syntacticsplenda.adventurepack.client.model.ModelMiningHelm;
import syntacticsplenda.adventurepack.item.ILightProducing;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;


public class ItemMiningHelm extends ArmorItem implements ILightProducing {

    public static final IArmorMaterial miningArmorMaterial = new IArmorMaterial() {
        private final int[] damageReduction = {1, 1, 1, 1};

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return 190;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return damageReduction[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return SoundEvents.ITEM_ARMOR_EQUIP_IRON;
        }

        @Override
        public Ingredient getRepairMaterial() {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation("minecraft", "iron_ingot"));
            return Ingredient.fromItems(item);
        }

        @Override
        public String getName() {
            return "mining";
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

    public ItemMiningHelm(EquipmentSlotType type, IArmorMaterial mat, Properties props) {
        super(mat, type, props);
        this.type = type;
    }

    @Override
    public void onArmorTick(ItemStack itemStack, World world, PlayerEntity player) {
        createLight(itemStack, world, player);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (!playerIn.isSneaking()) super.onItemRightClick(worldIn, playerIn, handIn);
        return toggleLight(playerIn, handIn);
    }


    @OnlyIn(Dist.CLIENT)
    @Override
    public BipedModel getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, BipedModel original) {
        return new ModelMiningHelm(1.0f);
    }

    @Nonnull
    @Override
    public final String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
        return AdventurePack.modId + ":" + "/textures/models/armor/mining_layer_1.png";
    }

}
