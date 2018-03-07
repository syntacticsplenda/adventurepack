package brobotato.adventurepack.item;


import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import brobotato.adventurepack.AdventurePack;

public class ModItems {
    public static ItemRope escapeRope = new ItemRope();

    public static void register(IForgeRegistry<Item> registry) {

        registry.registerAll(
                escapeRope
        );
    }

    public static void registerModels() {
        escapeRope.registerItemModel();

    }
}
