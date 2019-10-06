package brobotato.adventurepack.item;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class ItemEnderLantern extends ItemBase {

    public ItemEnderLantern(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    public static void highlightHandler() {
        MinecraftClient mc = MinecraftClient.getInstance();

        PlayerEntity player = mc.player;
        if (player.inventory.getSlotWithStack(new ItemStack(ModItems.enderLantern)) != -1 && !mc.gameRenderer.getCamera().isThirdPerson()) {
            ArrayList<BlockPos> nearbyOres = nearbyOre();
            for (BlockPos orePos : nearbyOres) {
                highlightBlock(orePos, 1.0f);
            }
        }
    }

    // wouldn't have been possible without mcjtylib's highlight functions, thank you
    @Environment(EnvType.CLIENT)
    public static void highlightBlock(BlockPos hiPos, float ticks) {
        MinecraftClient mc = MinecraftClient.getInstance();

        PlayerEntity player = mc.player;

        double doubleX = player.field_7524 + (player.x - player.field_7524) * ticks;
        double doubleY = player.field_7502 + (double) player.getEyeHeight(player.getPose()) + (player.y - player.field_7502) * ticks;
        double doubleZ = player.field_7522 + (player.z - player.field_7522) * ticks;
        GlStateManager.pushMatrix();

        float r = 0.8f;
        float g = 0.0f;
        float b = 0.98f;
        float a = 0.5f;

        GlStateManager.color3f(r, g, b);
        GlStateManager.lineWidth(3);
        GlStateManager.translated(-doubleX, -doubleY, -doubleZ);

        GlStateManager.disableDepthTest();
        GlStateManager.disableTexture();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBufferBuilder();

        float mx = (float) hiPos.getX();
        float my = (float) hiPos.getY();
        float mz = (float) hiPos.getZ();

        buffer.begin(GL11.GL_LINES, VertexFormats.POSITION_COLOR);

        buffer.vertex(mx, my, mz).color(r, g, b, a).end();
        buffer.vertex(mx, my, mz + 1).color(r, g, b, a).end();

        buffer.vertex(mx + 1, my, mz).color(r, g, b, a).end();
        buffer.vertex(mx + 1, my, mz + 1).color(r, g, b, a).end();

        buffer.vertex(mx, my, mz).color(r, g, b, a).end();
        buffer.vertex(mx + 1, my, mz).color(r, g, b, a).end();

        buffer.vertex(mx, my, mz + 1).color(r, g, b, a).end();
        buffer.vertex(mx + 1, my, mz + 1).color(r, g, b, a).end();

        buffer.vertex(mx, my + 1, mz).color(r, g, b, a).end();
        buffer.vertex(mx, my + 1, mz + 1).color(r, g, b, a).end();

        buffer.vertex(mx + 1, my + 1, mz).color(r, g, b, a).end();
        buffer.vertex(mx + 1, my + 1, mz + 1).color(r, g, b, a).end();

        buffer.vertex(mx, my + 1, mz).color(r, g, b, a).end();
        buffer.vertex(mx + 1, my + 1, mz).color(r, g, b, a).end();

        buffer.vertex(mx, my + 1, mz + 1).color(r, g, b, a).end();
        buffer.vertex(mx + 1, my + 1, mz + 1).color(r, g, b, a).end();

        buffer.vertex(mx, my, mz).color(r, g, b, a).end();
        buffer.vertex(mx, my + 1, mz).color(r, g, b, a).end();

        buffer.vertex(mx + 1, my, mz).color(r, g, b, a).end();
        buffer.vertex(mx + 1, my + 1, mz).color(r, g, b, a).end();

        buffer.vertex(mx, my, mz + 1).color(r, g, b, a).end();
        buffer.vertex(mx, my + 1, mz + 1).color(r, g, b, a).end();

        buffer.vertex(mx + 1, my, mz + 1).color(r, g, b, a).end();
        buffer.vertex(mx + 1, my + 1, mz + 1).color(r, g, b, a).end();

        tessellator.draw();

        GlStateManager.enableTexture();
        GlStateManager.popMatrix();
    }

    @Environment(EnvType.CLIENT)
    private static ArrayList<BlockPos> nearbyOre() {
        ArrayList<BlockPos> oreList = new ArrayList<>();
        MinecraftClient mc = MinecraftClient.getInstance();
        PlayerEntity player = mc.player;
        BlockPos currentPos = player.getBlockPos();
        int radius = 4; // Math.min(Config.COMMON.lanternMax.get(), Config.CLIENT.lanternRange.get());
        // if (radius == 0) radius = 4;
        for (int x = currentPos.getX() - radius; x < currentPos.getX() + radius; x++) {
            for (int y = currentPos.getY() - radius; y < currentPos.getY() + radius; y++) {
                for (int z = currentPos.getZ() - radius; z < currentPos.getZ() + radius; z++) {
                    Block testBlock = mc.world.getBlockState(new BlockPos(x, y, z)).getBlock();
                    String name = testBlock.getName().getString();
                    if (name.toLowerCase().contains("ore") || name.toLowerCase().equals("tile.netherquartz"))
                        oreList.add(new BlockPos(x, y, z));
                }
            }
        }
        return oreList;
    }
}
