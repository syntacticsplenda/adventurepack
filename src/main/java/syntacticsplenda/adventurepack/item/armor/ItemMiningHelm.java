package syntacticsplenda.adventurepack.item.armor;

import syntacticsplenda.adventurepack.AdventurePack;
import syntacticsplenda.adventurepack.client.model.ModelMiningHelm;
import syntacticsplenda.adventurepack.item.ILightProducing;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;


public class ItemMiningHelm extends ItemArmor implements ILightProducing {

    public static final IArmorMaterial miningArmorMaterial = new IArmorMaterial() {
        private final int[] damageReduction = {1, 1, 1, 1};

        @Override
        public int getDurability(EntityEquipmentSlot slotIn) {
            return 190;
        }

        @Override
        public int getDamageReductionAmount(EntityEquipmentSlot slotIn) {
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
    };

    public final EntityEquipmentSlot type;

    protected ModelBiped model = new ModelMiningHelm(1.0f);

    public ItemMiningHelm(EntityEquipmentSlot type, IArmorMaterial mat, Properties props) {
        super(mat, type, props);
        this.type = type;
    }

    @Override
    public void onArmorTick(ItemStack itemStack, World world, EntityPlayer player) {
        createLight(itemStack, world, player);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!playerIn.isSneaking()) super.onItemRightClick(worldIn, playerIn, handIn);
        return toggleLight(playerIn, handIn);
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped original) {
        return model;
    }

    @Nonnull
    @Override
    public final String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return AdventurePack.modId + ":" + "/textures/models/armor/mining_layer_1.png";
    }

}
