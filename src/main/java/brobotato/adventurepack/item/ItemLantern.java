package brobotato.adventurepack.item;

import brobotato.adventurepack.config.Config;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class ItemLantern extends ItemBase {

    public ItemLantern(Properties properties) {
        super(properties);
    }

    public static void highlightHandler(RenderWorldLastEvent evt) {
        Minecraft mc = Minecraft.getInstance();

        PlayerEntity player = mc.player;
        if (player.inventory.hasItemStack(new ItemStack(ModItems.lantern))) {
            ArrayList<BlockPos> nearbyOres = nearbyOre();
            for (BlockPos orePos : nearbyOres) {
                highlightBlock(orePos, evt.getPartialTicks());
            }
        }
    }

    // wouldn't have been possible without mcjtylib's highlight functions, thank you
    public static void highlightBlock(BlockPos hiPos, float ticks) {
        Minecraft mc = Minecraft.getInstance();

        PlayerEntity player = mc.player;

        double doubleX = player.lastTickPosX + (player.posX - player.lastTickPosX) * ticks;
        double doubleY = player.lastTickPosY + (double) player.getEyeHeight() + (player.posY - player.lastTickPosY) * ticks;
        double doubleZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * ticks;
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
        BufferBuilder buffer = tessellator.getBuffer();

        float mx = (float) hiPos.getX();
        float my = (float) hiPos.getY();
        float mz = (float) hiPos.getZ();

        buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);

        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my, mz + 1).color(r, g, b, a).endVertex();

        buffer.pos(mx + 1, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz + 1).color(r, g, b, a).endVertex();

        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz).color(r, g, b, a).endVertex();

        buffer.pos(mx, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my, mz + 1).color(r, g, b, a).endVertex();

        buffer.pos(mx, my + 1, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz + 1).color(r, g, b, a).endVertex();

        buffer.pos(mx + 1, my + 1, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz + 1).color(r, g, b, a).endVertex();

        buffer.pos(mx, my + 1, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz).color(r, g, b, a).endVertex();

        buffer.pos(mx, my + 1, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz + 1).color(r, g, b, a).endVertex();

        buffer.pos(mx, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz).color(r, g, b, a).endVertex();

        buffer.pos(mx + 1, my, mz).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz).color(r, g, b, a).endVertex();

        buffer.pos(mx, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx, my + 1, mz + 1).color(r, g, b, a).endVertex();

        buffer.pos(mx + 1, my, mz + 1).color(r, g, b, a).endVertex();
        buffer.pos(mx + 1, my + 1, mz + 1).color(r, g, b, a).endVertex();

        tessellator.draw();

        GlStateManager.enableTexture();
        GlStateManager.popMatrix();
    }

    private static ArrayList<BlockPos> nearbyOre() {
        ArrayList<BlockPos> oreList = new ArrayList<>();
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        BlockPos currentPos = player.getPosition();
        int radius = Math.min(Config.COMMON.lanternMax.get(), Config.CLIENT.lanternRange.get());
        if (radius == 0) radius = 4;
        for (int x = currentPos.getX() - radius; x < currentPos.getX() + radius; x++) {
            for (int y = currentPos.getY() - radius; y < currentPos.getY() + radius; y++) {
                for (int z = currentPos.getZ() - radius; z < currentPos.getZ() + radius; z++) {
                    Block testBlock = mc.world.getBlockState(new BlockPos(x, y, z)).getBlock();
                    String name = testBlock.getNameTextComponent().getString();
                    if (name.toLowerCase().contains("ore") || name.toLowerCase().equals("tile.netherquartz"))
                        oreList.add(new BlockPos(x, y, z));
                }
            }
        }
        return oreList;
    }
}
