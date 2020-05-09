package syntacticsplenda.adventurepack.mixin;

import syntacticsplenda.adventurepack.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.color.world.GrassColors;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.potion.PotionUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ExtendedBlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Iterator;

@Mixin(ItemColors.class)
public abstract class ItemColorsMixin {

    @Overwrite
    public static ItemColors create(BlockColors blockColors_1) {
        ItemColors itemColors_1 = new ItemColors();
        itemColors_1.register((itemStack_1, int_1) -> {
            return int_1 > 0 ? -1 : ((DyeableItem) itemStack_1.getItem()).getColor(itemStack_1);
        }, Items.LEATHER_HELMET, Items.LEATHER_CHESTPLATE, Items.LEATHER_LEGGINGS, Items.LEATHER_BOOTS, Items.LEATHER_HORSE_ARMOR, ModItems.cowboyHat, ModItems.explorerHat);
        itemColors_1.register((itemStack_1, int_1) -> {
            return GrassColors.getColor(0.5D, 1.0D);
        }, Blocks.TALL_GRASS, Blocks.LARGE_FERN);
        itemColors_1.register((itemStack_1, int_1) -> {
            if (int_1 != 1) {
                return -1;
            } else {
                CompoundTag compoundTag_1 = itemStack_1.getSubTag("Explosion");
                int[] ints_1 = compoundTag_1 != null && compoundTag_1.containsKey("Colors", 11) ? compoundTag_1.getIntArray("Colors") : null;
                if (ints_1 == null) {
                    return 9079434;
                } else if (ints_1.length == 1) {
                    return ints_1[0];
                } else {
                    int int_2 = 0;
                    int int_3 = 0;
                    int int_4 = 0;
                    int[] var7 = ints_1;
                    int var8 = ints_1.length;

                    for (int var9 = 0; var9 < var8; ++var9) {
                        int int_5 = var7[var9];
                        int_2 += (int_5 & 16711680) >> 16;
                        int_3 += (int_5 & '\uff00') >> 8;
                        int_4 += (int_5 & 255) >> 0;
                    }

                    int_2 /= ints_1.length;
                    int_3 /= ints_1.length;
                    int_4 /= ints_1.length;
                    return int_2 << 16 | int_3 << 8 | int_4;
                }
            }
        }, Items.FIREWORK_STAR);
        itemColors_1.register((itemStack_1, int_1) -> {
            return int_1 > 0 ? -1 : PotionUtil.getColor(itemStack_1);
        }, Items.POTION, Items.SPLASH_POTION, Items.LINGERING_POTION);
        Iterator var2 = SpawnEggItem.getAll().iterator();

        while (var2.hasNext()) {
            SpawnEggItem spawnEggItem_1 = (SpawnEggItem) var2.next();
            itemColors_1.register((itemStack_1, int_1) -> {
                return spawnEggItem_1.getColor(int_1);
            }, spawnEggItem_1);
        }

        itemColors_1.register((itemStack_1, int_1) -> {
            BlockState blockState_1 = ((BlockItem) itemStack_1.getItem()).getBlock().getDefaultState();
            return blockColors_1.getColorMultiplier(blockState_1, (ExtendedBlockView) null, (BlockPos) null, int_1);
        }, Blocks.GRASS_BLOCK, Blocks.GRASS, Blocks.FERN, Blocks.VINE, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.BIRCH_LEAVES, Blocks.JUNGLE_LEAVES, Blocks.ACACIA_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.LILY_PAD);
        itemColors_1.register((itemStack_1, int_1) -> {
            return int_1 == 0 ? PotionUtil.getColor(itemStack_1) : -1;
        }, Items.TIPPED_ARROW);
        itemColors_1.register((itemStack_1, int_1) -> {
            return int_1 == 0 ? -1 : FilledMapItem.getMapColor(itemStack_1);
        }, Items.FILLED_MAP);
        return itemColors_1;
    }
}
