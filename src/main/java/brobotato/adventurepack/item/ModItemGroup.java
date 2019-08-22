package brobotato.adventurepack.item;

import brobotato.adventurepack.AdventurePack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class ModItemGroup extends ItemGroup {

    private NonNullList<ItemStack> list;

    public ModItemGroup(String label) {
        super(-1, label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.escapeRope);
    }

    @Override
    public void fill(@Nonnull NonNullList<ItemStack> list) {
        this.list = list;
        addItem(ModItems.escapeRope);
        addItem(ModItems.explorerHat);
        addItem(ModItems.flashlight);
        addItem(ModItems.miningHelm);
        addItem(ModItems.lantern);
    }
    
    private void addItem(IItemProvider item) {
        item.asItem().fillItemGroup(this, list);
    }

}