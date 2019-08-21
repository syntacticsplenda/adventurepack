package brobotato.adventurepack.config;

import brobotato.adventurepack.AdventurePack;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = AdventurePack.modId, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    public static class Client {
        public final ForgeConfigSpec.IntValue lanternRange;

        public Client(ForgeConfigSpec.Builder builder) {
            lanternRange = builder
                    .comment("The search radius of the Ender Lantern.")
                    .defineInRange("lanternRange", 4, 0, Integer.MAX_VALUE);
        }
    }

    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;

    static {
        final Pair<Client, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }

    public static class Common {
        public final ForgeConfigSpec.BooleanValue ropeEnabled;
        public final ForgeConfigSpec.BooleanValue instantRope;
        public final ForgeConfigSpec.BooleanValue ropeSpawn;
        public final ForgeConfigSpec.BooleanValue flashlightEnabled;
        public final ForgeConfigSpec.BooleanValue lanternEnabled;
        public final ForgeConfigSpec.BooleanValue helmetEnabled;
        public final ForgeConfigSpec.BooleanValue hatEnabled;

        public final ForgeConfigSpec.IntValue lanternMax;
        public final ForgeConfigSpec.IntValue helmetPower;
        public final ForgeConfigSpec.IntValue helmetRange;

        public Common(ForgeConfigSpec.Builder builder) {
            ropeEnabled = builder.comment("If the Escape Rope is enabled.").define("ropeEnabled", true);
            instantRope = builder.comment("If the rope should be instant").define("instantRope", false);
            ropeSpawn = builder.comment("If the rope should teleport the user to their spawn if it has no stored location").define("ropeSpawn", true);
            flashlightEnabled = builder.comment("If the Flashlight is enabled.").define("flashlightEnabled", true);
            lanternEnabled = builder.comment("If the Ender Lantern is enabled.").define("lanternEnabled", true);
            lanternMax = builder.comment("The maximum radius of the Ender Lantern.").defineInRange("lanternMax", 4, 0, Integer.MAX_VALUE);
            helmetEnabled = builder.comment("If the Mining Helmet is enabled.").define("helmetEnabled", true);
            helmetPower = builder.comment("The strength of the Mining Helmet.").defineInRange("helmetPower", 7, 1, 15);
            helmetRange = builder.comment("The range of the Mining Helmet.").defineInRange("helmetRange", 7, 2, Integer.MAX_VALUE);
            hatEnabled = builder.comment("If the Explorer Hat is enabled.").define("hatEnabled", true);
        }
    }


    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}