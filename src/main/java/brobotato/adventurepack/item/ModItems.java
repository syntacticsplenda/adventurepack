package brobotato.adventurepack.item;


import brobotato.adventurepack.config.ModConfig;
import brobotato.adventurepack.item.armor.ItemExplorerHat;
import brobotato.adventurepack.item.armor.ItemMiningHelm;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
    public static ItemRope escapeRope = new ItemRope();
    public static ItemLantern lantern = new ItemLantern();
    public static ItemMiningHelm miningHelm = new ItemMiningHelm();
    public static ItemExplorerHat explorerHat = new ItemExplorerHat();

    public static void register(IForgeRegistry<Item> registry) {
        if (ModConfig.lanternEnabled)
            registry.registerAll(
                    lantern
            );
        if (ModConfig.helmetEnabled)
            registry.registerAll(
                    miningHelm
            );
        if (ModConfig.hatEnabled)
            registry.registerAll(
                    explorerHat
            );
        if (ModConfig.ropeEnabled)
            registry.registerAll(
                    escapeRope
            );
    }

    public static void registerModels() {
        escapeRope.registerItemModel();
        lantern.registerItemModel();
        miningHelm.registerItemModel();
        explorerHat.registerItemModel();
    }
}
