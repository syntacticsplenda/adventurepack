package brobotato.adventurepack.item;

import brobotato.adventurepack.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class ItemLantern extends ItemBase {

    public ItemLantern() {
        super("lantern");
        this.setCreativeTab(CreativeTabs.TOOLS);
        this.setMaxStackSize(1);
    }

    public static void highlightHandler(RenderWorldLastEvent evt) {
        Minecraft mc = Minecraft.getMinecraft();

        EntityPlayerSP player = mc.player;
        if (player.inventory.hasItemStack(new ItemStack(ModItems.lantern))) {
            ArrayList<BlockPos> nearbyOres = nearbyOre();
            for (BlockPos orePos : nearbyOres) {
                highlightBlock(orePos, evt.getPartialTicks());
            }
        }
    }

    // wouldn't have been possible without mcjtylib's highlight functions, thank you
    public static void highlightBlock(BlockPos hiPos, float ticks) {
        Minecraft mc = Minecraft.getMinecraft();

        EntityPlayerSP player = mc.player;

        double doubleX = player.lastTickPosX + (player.posX - player.lastTickPosX) * ticks;
        double doubleY = player.lastTickPosY + (player.posY - player.lastTickPosY) * ticks;
        double doubleZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * ticks;

        GlStateManager.pushMatrix();

        float r = 0.8f;
        float g = 0.0f;
        float b = 0.98f;
        float a = 0.5f;

        GlStateManager.color(r, g, b);
        GlStateManager.glLineWidth(3);
        GlStateManager.translate(-doubleX, -doubleY, -doubleZ);

        GlStateManager.disableDepth();
        GlStateManager.disableTexture2D();

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

        GlStateManager.enableTexture2D();
        GlStateManager.popMatrix();
    }

    public static ArrayList<BlockPos> nearbyOre() {
        ArrayList<BlockPos> oreList = new ArrayList<>();
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.player;
        BlockPos currentPos = player.getPosition();
        int radius = Math.min(ModConfig.lanternMax,ModConfig.client.lanternRadius);
        if (radius == 0) radius = 4;
        for (int x = currentPos.getX() - radius; x < currentPos.getX() + radius; x++) {
            for (int y = currentPos.getY() - radius; y < currentPos.getY() + radius; y++) {
                for (int z = currentPos.getZ() - radius; z < currentPos.getZ() + radius; z++) {
                    Block testBlock = mc.world.getBlockState(new BlockPos(x, y, z)).getBlock();
                    String name = testBlock.getUnlocalizedName();
                    if (name.toLowerCase().contains("ore") || name.toLowerCase().equals("tile.netherquartz"))
                        oreList.add(new BlockPos(x, y, z));
                }
            }
        }
        return oreList;
    }
}
