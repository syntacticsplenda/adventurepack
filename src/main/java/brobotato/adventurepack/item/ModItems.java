package brobotato.adventurepack.item;


import brobotato.adventurepack.AdventurePack;
import brobotato.adventurepack.config.Config;
import brobotato.adventurepack.item.armor.ItemExplorerHat;
import brobotato.adventurepack.item.armor.ItemMiningHelm;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

public class ModItems {
    public static final Item escapeRope = new ItemRope(defaultBuilder()).setRegistryName(AdventurePack.modId, "escape_rope");
    public static final Item flashlight = new ItemFlashlight(unstackable()).setRegistryName(AdventurePack.modId, "flashlight");
    public static final Item lantern = new ItemLantern(unstackable()).setRegistryName(AdventurePack.modId, "lantern");
    public static final Item miningHelm = new ItemMiningHelm(EntityEquipmentSlot.HEAD, ItemMiningHelm.miningArmorMaterial, unstackable()).setRegistryName(AdventurePack.modId, "mining_helmet");
    public static final Item explorerHat = new ItemExplorerHat(EntityEquipmentSlot.HEAD, ItemExplorerHat.explorerArmorMaterial, unstackable()).setRegistryName(AdventurePack.modId, "explorer_hat");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> evt) {
        IForgeRegistry<Item> registry = evt.getRegistry();
        if (Config.COMMON.ropeEnabled.get())
            registry.register(escapeRope);
        if (Config.COMMON.flashlightEnabled.get())
            registry.register(flashlight);
        if (Config.COMMON.lanternEnabled.get())
            registry.register(lantern);
        if (Config.COMMON.helmetEnabled.get())
            registry.register(miningHelm);
        if (Config.COMMON.hatEnabled.get())
            registry.register(explorerHat);
    }

    // thanks botania for these
    public static Item.Properties defaultBuilder() {
        return new Item.Properties().group(AdventurePack.ADVENTURE_PACK);
    }

    private static Item.Properties unstackable() {
        return defaultBuilder().maxStackSize(1);
    }
}
