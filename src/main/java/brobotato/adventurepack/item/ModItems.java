package brobotato.adventurepack.item;


import brobotato.adventurepack.config.ModConfig;
import brobotato.adventurepack.item.armor.ItemCowboyHat;
import brobotato.adventurepack.item.armor.ItemExplorerHat;
import brobotato.adventurepack.item.armor.ItemMiningHelm;
import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
    public static ItemRope escapeRope = new ItemRope();
    public static ItemFlashlight flashlight = new ItemFlashlight();
    public static ItemLantern lantern = new ItemLantern();
    public static ItemMiningLantern miningLantern = new ItemMiningLantern();
    public static ItemMiningHelm miningHelm = new ItemMiningHelm();
    public static ItemExplorerHat explorerHat = new ItemExplorerHat();
    public static ItemCowboyHat cowboyHat = new ItemCowboyHat();

    public static void register(IForgeRegistry<Item> registry) {
        if (ModConfig.ropeEnabled)
            registry.registerAll(
                    escapeRope
            );
        if (ModConfig.flashlightEnabled)
            registry.registerAll(
                    flashlight
            );
        if (ModConfig.lanternEnabled)
            registry.registerAll(
                    lantern
            );
        if (ModConfig.miningEnabled)
            registry.registerAll(
                    miningLantern
            );
        if (ModConfig.helmetEnabled)
            registry.registerAll(
                    miningHelm
            );
        if (ModConfig.hatEnabled)
            registry.registerAll(
                    explorerHat
            );
        if (ModConfig.cowboyEnabled)
            registry.registerAll(
                    cowboyHat
            );
    }

    public static void registerModels() {
        escapeRope.registerItemModel();
        flashlight.registerItemModel();
        lantern.registerItemModel();
        miningHelm.registerItemModel();
        explorerHat.registerItemModel();
        miningLantern.registerItemModel();
        cowboyHat.registerItemModel();
    }
}
