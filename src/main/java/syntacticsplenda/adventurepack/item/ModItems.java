package syntacticsplenda.adventurepack.item;


import syntacticsplenda.adventurepack.AdventurePack;
import syntacticsplenda.adventurepack.item.armor.ItemCowboyHat;
import syntacticsplenda.adventurepack.item.armor.ItemExplorerHat;
import syntacticsplenda.adventurepack.item.armor.ItemMiningHelm;
import syntacticsplenda.adventurepack.item.armor.ModArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModItems {
    public static final Item escapeRope = new ItemRope(defaultBuilder());
    public static final Item flashlight = new ItemFlashlight(unstackable());
    public static final Item miningLantern = new ItemMiningLantern(unstackable());
    public static final Item enderLantern = new ItemEnderLantern(unstackable());
    public static final Item miningHelm = new ItemMiningHelm(EquipmentSlot.HEAD, ModArmorMaterial.MINING, unstackable());
    public static final Item explorerHat = new ItemExplorerHat(EquipmentSlot.HEAD, ModArmorMaterial.EXPLORER, unstackable());
    public static final Item cowboyHat = new ItemCowboyHat(EquipmentSlot.HEAD, ModArmorMaterial.COWBOY, unstackable());

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(AdventurePack.modId, "escape_rope"), escapeRope);
        Registry.register(Registry.ITEM, new Identifier(AdventurePack.modId, "flashlight"), flashlight);
        Registry.register(Registry.ITEM, new Identifier(AdventurePack.modId, "mining_lantern"), miningLantern);
        Registry.register(Registry.ITEM, new Identifier(AdventurePack.modId, "ender_lantern"), enderLantern);
        Registry.register(Registry.ITEM, new Identifier(AdventurePack.modId, "mining_helmet"), miningHelm);
        Registry.register(Registry.ITEM, new Identifier(AdventurePack.modId, "explorer_hat"), explorerHat);
        Registry.register(Registry.ITEM, new Identifier(AdventurePack.modId, "cowboy_hat"), cowboyHat);
    }

    // thanks botania for these
    public static Item.Settings defaultBuilder() {
        return new Item.Settings().group(AdventurePack.ADVENTURE_PACK);
    }

    private static Item.Settings unstackable() {
        return defaultBuilder().maxCount(1);
    }
}
