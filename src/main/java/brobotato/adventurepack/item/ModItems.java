package brobotato.adventurepack.item;


import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import brobotato.adventurepack.AdventurePack;

public class ModItems {
    public static ItemRope escapeRope = new ItemRope();
    public static ItemLantern lantern = new ItemLantern();

    public static void register(IForgeRegistry<Item> registry) {

        registry.registerAll(
                escapeRope,
                lantern
        );
    }

    public static void registerModels() {
        escapeRope.registerItemModel();
        lantern.registerItemModel();

    }
}
