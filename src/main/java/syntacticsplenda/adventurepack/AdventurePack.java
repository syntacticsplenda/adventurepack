package syntacticsplenda.adventurepack;

import syntacticsplenda.adventurepack.block.ModBlocks;
import syntacticsplenda.adventurepack.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;


public class AdventurePack implements ModInitializer {

    public static final String modId = "adventurepack";

    public static final ItemGroup ADVENTURE_PACK = FabricItemGroupBuilder.create(
            new Identifier(modId, "general"))
            .icon(() -> new ItemStack(ModItems.escapeRope))
            .appendItems(stacks ->
            {
                stacks.add(new ItemStack(ModItems.cowboyHat));
                stacks.add(new ItemStack(ModItems.enderLantern));
                stacks.add(new ItemStack(ModItems.escapeRope));
                stacks.add(new ItemStack(ModItems.explorerHat));
                stacks.add(new ItemStack(ModItems.flashlight));
                stacks.add(new ItemStack(ModItems.miningHelm));
                stacks.add(new ItemStack(ModItems.miningLantern));
            })
            .build();


    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
        ModBlocks.registerTileEntities();
    }
}