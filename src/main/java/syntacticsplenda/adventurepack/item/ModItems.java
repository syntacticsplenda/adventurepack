package syntacticsplenda.adventurepack.item;


import syntacticsplenda.adventurepack.AdventurePack;
import syntacticsplenda.adventurepack.item.armor.ItemCowboyHat;
import syntacticsplenda.adventurepack.item.armor.ItemExplorerHat;
import syntacticsplenda.adventurepack.item.armor.ItemMiningHelm;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems {
    public static final Item escapeRope = new ItemRope(defaultBuilder()).setRegistryName(AdventurePack.modId, "escape_rope");
    public static final Item flashlight = new ItemFlashlight(unstackable()).setRegistryName(AdventurePack.modId, "flashlight");
    public static final Item miningLantern = new ItemMiningLantern(unstackable()).setRegistryName(AdventurePack.modId, "mining_lantern");
    public static final Item enderLantern = new ItemEnderLantern(unstackable()).setRegistryName(AdventurePack.modId, "ender_lantern");
    public static final Item miningHelm = new ItemMiningHelm(EquipmentSlotType.HEAD, ItemMiningHelm.miningArmorMaterial, unstackable()).setRegistryName(AdventurePack.modId, "mining_helmet");
    public static final Item explorerHat = new ItemExplorerHat(EquipmentSlotType.HEAD, ItemExplorerHat.explorerArmorMaterial, unstackable()).setRegistryName(AdventurePack.modId, "explorer_hat");
    public static final Item cowboyHat = new ItemCowboyHat(EquipmentSlotType.HEAD, ItemCowboyHat.cowboyArmorMaterial, unstackable()).setRegistryName(AdventurePack.modId, "cowboy_hat");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> evt) {
        IForgeRegistry<Item> registry = evt.getRegistry();
        registry.register(escapeRope);
        registry.register(flashlight);
        registry.register(enderLantern);
        registry.register(miningLantern);
        registry.register(miningHelm);
        registry.register(explorerHat);
        registry.register(cowboyHat);
    }

    // thanks botania for these
    public static Item.Properties defaultBuilder() {
        return new Item.Properties().group(AdventurePack.ADVENTURE_PACK);
    }

    private static Item.Properties unstackable() {
        return defaultBuilder().maxStackSize(1);
    }
}
