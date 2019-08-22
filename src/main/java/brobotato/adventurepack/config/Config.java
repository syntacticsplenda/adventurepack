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
            builder.push("Client-side Configuration");
            lanternRange = builder.comment("The search radius of the Ender Lantern.").defineInRange("lanternRange", 4, 0, Integer.MAX_VALUE);
            builder.pop();
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
        public final ForgeConfigSpec.BooleanValue instantRope;
        public final ForgeConfigSpec.BooleanValue ropeSpawn;

        public final ForgeConfigSpec.IntValue lanternMax;
        public final ForgeConfigSpec.IntValue helmetRange;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("General Configuration");
            instantRope = builder.comment("If the rope should be instant").define("instantRope", false);
            ropeSpawn = builder.comment("If the rope should teleport the user to their spawn if it has no stored location").define("ropeSpawn", true);
            lanternMax = builder.comment("The maximum radius of the Ender Lantern.").defineInRange("lanternMax", 4, 0, Integer.MAX_VALUE);
            helmetRange = builder.comment("The range of the Mining Helmet.").defineInRange("helmetRange", 7, 2, Integer.MAX_VALUE);
            builder.pop();
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