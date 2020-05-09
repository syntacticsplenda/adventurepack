package brobotato.adventurepack.item;

import com.mojang.blaze3d.platform.GlStateManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;

import java.util.ArrayList;

public class ItemEnderLantern extends ItemBase {

    public ItemEnderLantern(Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    public static void highlightHandler() {
        MinecraftClient mc = MinecraftClient.getInstance();
        PlayerEntity player = mc.player;
        if (player == null) return;
        if (player.inventory.getSlotWithStack(new ItemStack(ModItems.enderLantern)) != -1) { // && !mc.gameRenderer.getCamera().isThirdPerson()) {
            ArrayList<BlockPos> nearbyOres = nearbyOre(mc, player);
            for (BlockPos orePos : nearbyOres) {
                highlightBlock(mc, mc.gameRenderer.getCamera(), orePos, 1);
            }
        }
    }

    // wouldn't have been possible without mcjtylib's highlight functions, thank you
    @Environment(EnvType.CLIENT)
    public static void highlightBlock(MinecraftClient mc, Camera camera, BlockPos blockPos, int ticks) {
        BlockState blockState_1 = mc.world.getBlockState(blockPos);
        if (!blockState_1.isAir() && mc.world.getWorldBorder().contains(blockPos)) {
            GlStateManager.enableBlend();
            GlStateManager.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.lineWidth(Math.max(2.5F, (float) mc.window.getFramebufferWidth() / 1920.0F * 2.5F));
            GlStateManager.disableTexture();
            GlStateManager.depthMask(false);
            GlStateManager.matrixMode(5889);
            GlStateManager.pushMatrix();
            GlStateManager.scalef(1.0F, 1.0F, 0.999F);
            GlStateManager.rotatef(camera.getPitch(), 1.0F, 0.0F, 0.0F);
            GlStateManager.rotatef(camera.getYaw() + 180.0F, 0.0F, 1.0F, 0.0F);
            double double_1 = camera.getPos().x;
            double double_2 = camera.getPos().y;
            double double_3 = camera.getPos().z;
            try {
                drawShapeOutline(blockState_1.getOutlineShape(mc.world, blockPos, EntityContext.of(camera.getFocusedEntity())), (double) blockPos.getX() - double_1, (double) blockPos.getY() - double_2, (double) blockPos.getZ() - double_3, 0.8F, 0.0F, 0.98F, 0.5F);
            }
            // this is literally worst practice but who's gonna stop me
            // are you the code police? didn't think so
            catch (Exception e){
                return;
            }
            GlStateManager.popMatrix();
            GlStateManager.matrixMode(5888);
            GlStateManager.depthMask(true);
            GlStateManager.enableTexture();
            GlStateManager.disableBlend();
        }

    }

    @Environment(EnvType.CLIENT)
    public static void drawShapeOutline(VoxelShape voxelShape_1, double double_1, double double_2, double double_3, float float_1, float float_2, float float_3, float float_4) {
        Tessellator tessellator_1 = Tessellator.getInstance();
        BufferBuilder bufferBuilder_1 = tessellator_1.getBufferBuilder();
        bufferBuilder_1.begin(1, VertexFormats.POSITION_COLOR);
        voxelShape_1.forEachEdge((double_4, double_5, double_6, double_7, double_8, double_9) -> {
            bufferBuilder_1.vertex(double_4 + double_1, double_5 + double_2, double_6 + double_3).color(float_1, float_2, float_3, float_4).next();
            bufferBuilder_1.vertex(double_7 + double_1, double_8 + double_2, double_9 + double_3).color(float_1, float_2, float_3, float_4).next();
        });
        tessellator_1.draw();
    }

    @Environment(EnvType.CLIENT)
    private static ArrayList<BlockPos> nearbyOre(MinecraftClient mc, PlayerEntity player) {
        ArrayList<BlockPos> oreList = new ArrayList<>();
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
