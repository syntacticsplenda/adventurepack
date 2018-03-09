package brobotato.adventurepack.config;

import brobotato.adventurepack.AdventurePack;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

@Config(modid = AdventurePack.modId)
@Config.LangKey("adventurepack.config")

public class ModConfig {

    public static Client client = new Client();

    public static class Client {

        @Config.Comment("If the rope should be instant")
        public boolean instantRope = false;
        @Config.Comment("If the Ender Lantern is enabled.")
        public boolean lanternEnabled = true;
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