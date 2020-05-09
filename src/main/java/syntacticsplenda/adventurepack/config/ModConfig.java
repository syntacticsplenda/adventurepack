package syntacticsplenda.adventurepack.config;

import syntacticsplenda.adventurepack.AdventurePack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = AdventurePack.modId)
@Config.LangKey("adventurepack.config")

public class ModConfig {

    public static Client client = new Client();

    @Config.Comment("If the Escape Rope is enabled.")
    public static boolean ropeEnabled = true;
    @Config.Comment("If the rope should be instant")
    public static boolean instantRope = false;
    @Config.Comment("If the rope should teleport the user to their spawn if it has no stored location")
    public static boolean ropeSpawn = true;
    @Config.Comment("If the Flashlight is enabled.")
    public static boolean flashlightEnabled = true;
    @Config.Comment("If the Ender Lantern is enabled.")
    public static boolean lanternEnabled = true;
    @Config.Comment("The max radius of the Ender Lantern. 0 = default (4)")
    public static int lanternMax = 0;
    @Config.Comment("If the Mining Helmet is enabled.")
    public static boolean helmetEnabled = true;
    @Config.Comment("The strength of the Mining Helmet. 0 = light is disabled. 15 max")
    public static int helmetPower = 7;
    @Config.Comment("The range of the Mining Helmet. 0 = light is disabled.")
    public static int helmetRange = 7;
    @Config.Comment("If the Explorer Hat is enabled.")
    public static boolean hatEnabled = true;
    @Config.Comment("If the Cowboy Hat is enabled.")
    public static boolean cowboyEnabled = true;
    @Config.Comment("If the Mining Lantern is enabled.")
    public static boolean miningEnabled = true;

    public static class Client {
        @Config.Comment("The radius of the Ender Lantern. 0 = default (4)")
        public int lanternRadius = 0;
    }

    @Mod.EventBusSubscriber
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(AdventurePack.modId)) {
                ConfigManager.sync(AdventurePack.modId, Config.Type.INSTANCE);
            }
        }
    }
}