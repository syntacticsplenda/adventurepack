package brobotato.adventurepack.item.armor;

import brobotato.adventurepack.block.ModBlocks;
import brobotato.adventurepack.client.model.ModelMiningHelm;
import brobotato.adventurepack.config.Config;
import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceFluidMode;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemMiningHelm extends ItemArmor {

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

    private RayTraceResult rayTrace(double blockReachDistance, float partialTicks, EntityPlayer player) {
        Vec3d vec3d = new Vec3d(player.getPosition().getX(), player.getPosition().getY() + player.getEyeHeight(), player.getPosition().getZ());
        Vec3d vec3d1 = player.getLook(partialTicks);
        Vec3d vec3d2 = vec3d.add(vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance);
        return player.world.rayTraceBlocks(vec3d, vec3d2, RayTraceFluidMode.NEVER, true, true);
    }

    @Override
    public void onArmorTick(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (itemStack.hasTag() && itemStack.getTag().getInt("on") == 1) return;
            RayTraceResult lookPos = rayTrace(Config.COMMON.helmetRange.get(), 1.0f, player);
            BlockPos pos;
            if (lookPos == null) return;
            if (lookPos.sideHit != null) pos = lookPos.getBlockPos().offset(lookPos.sideHit);
            else pos = lookPos.getBlockPos();
            double vecDistance = Math.pow(lookPos.hitVec.squareDistanceTo(player.posX, player.posY, player.posZ), 0.5);
            if (vecDistance <= Config.COMMON.helmetRange.get()) {
                if (world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos), world, pos)) {
                    player.world.setBlockState(pos, ModBlocks.blockLight.getDefaultState(), 2);
                } else if (world.getBlockState(pos.add(0, 1, 0)).getBlock().isAir(world.getBlockState(pos.add(0, 1, 0)), world, pos.add(0, 1, 0))) {
                    player.world.setBlockState(pos.add(0, 1, 0), ModBlocks.blockLight.getDefaultState(), 2);
                }
            }
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (!playerIn.isSneaking()) super.onItemRightClick(worldIn, playerIn, handIn);
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        if (!itemStack.hasTag()) {
            itemStack.getOrCreateTag();
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("on", new NBTTagInt(1));
            itemStack.setTag(tag);
        }
        if (itemStack.getTag().getInt("on") == 1) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("on", new NBTTagInt(0));
            itemStack.setTag(tag);
        } else if (itemStack.getTag().getInt("on") == 0) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setTag("on", new NBTTagInt(1));
            itemStack.setTag(tag);
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped original) {
        return model;
    }

}
