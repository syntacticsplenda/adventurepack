package syntacticsplenda.adventurepack.item;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import org.lwjgl.opengl.GL11;
import syntacticsplenda.adventurepack.config.Config;

import java.util.ArrayList;

public class ItemEnderLantern extends ItemBase {

    public ItemEnderLantern(Properties properties) {
        super(properties);
    }

    @OnlyIn(Dist.CLIENT)
    public static void highlightHandler(RenderWorldLastEvent evt) {
        Minecraft mc = Minecraft.getInstance();

        PlayerEntity player = mc.player;
        if (player.inventory.hasItemStack(new ItemStack(ModItems.enderLantern))) {
            ArrayList<BlockPos> nearbyOres = nearbyOre();
            for (BlockPos orePos : nearbyOres) {
                highlightBlock(orePos);
            }
        }
    }

    // wouldn't have been possible without mcjtylib's highlight functions, thank you
    @OnlyIn(Dist.CLIENT)
    public static void highlightBlock(BlockPos hiPos) {
        Minecraft mc = Minecraft.getInstance();

        RenderSystem.pushMatrix();

        ActiveRenderInfo renderInfo = Minecraft.getInstance().gameRenderer.getActiveRenderInfo();
        Vector3d projectedView = renderInfo.getProjectedView();

        int r = 204;
        int g = 0;
        int b = 250;
        int a = 1;

        RenderSystem.color3f(r, g, b);
        RenderSystem.lineWidth(3);
        RenderSystem.rotatef(renderInfo.getPitch(), 1, 0, 0); // Fixes camera rotation.
        RenderSystem.rotatef(renderInfo.getYaw() + 180, 0, 1, 0); // Fixes camera rotation.
        RenderSystem.translated(-projectedView.x, -projectedView.y, -projectedView.z);

        RenderSystem.disableDepthTest();
        RenderSystem.disableTexture();

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder buffer = tessellator.getBuffer();

        float mx = (float) hiPos.getX();
        float my = (float) hiPos.getY();
        float mz = (float) hiPos.getZ();

        buffer.begin(GL11.GL_LINES, DefaultVertexFormats.POSITION_COLOR);

        buffer.addVertex(mx, my, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx, my, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx + 1, my, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx + 1, my, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx, my, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx + 1, my, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx, my, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx + 1, my, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx, my + 1, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx, my + 1, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx + 1, my + 1, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx + 1, my + 1, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx, my + 1, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx + 1, my + 1, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx, my + 1, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx + 1, my + 1, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx, my, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx, my + 1, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx + 1, my, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx + 1, my + 1, mz, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx, my, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx, my + 1, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        buffer.addVertex(mx + 1, my, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);
        buffer.addVertex(mx + 1, my + 1, mz + 1, r, g, b, a, 0.0F, 0.0F, 0, 0, 0.0F, 0.0F, 0.0F);

        tessellator.draw();

        RenderSystem.enableTexture();
        RenderSystem.popMatrix();
    }

    @OnlyIn(Dist.CLIENT)
    private static ArrayList<BlockPos> nearbyOre() {
        ArrayList<BlockPos> oreList = new ArrayList<>();
        Minecraft mc = Minecraft.getInstance();
        PlayerEntity player = mc.player;
        BlockPos currentPos = new BlockPos(player.getPositionVec());
        int radius = Math.min(Config.COMMON.lanternMax.get(), Config.CLIENT.lanternRange.get());
        if (radius == 0) radius = 4;
        for (int x = currentPos.getX() - radius; x < currentPos.getX() + radius; x++) {
            for (int y = currentPos.getY() - radius; y < currentPos.getY() + radius; y++) {
                for (int z = currentPos.getZ() - radius; z < currentPos.getZ() + radius; z++) {
                    Block testBlock = mc.world.getBlockState(new BlockPos(x, y, z)).getBlock();
                    String name = testBlock.getTranslationKey();
                    if (name.toLowerCase().contains("ore") || name.toLowerCase().equals("tile.netherquartz"))
                        oreList.add(new BlockPos(x, y, z));
                }
            }
        }
        return oreList;
    }
}
