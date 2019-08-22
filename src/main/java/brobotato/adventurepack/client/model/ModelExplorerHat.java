package brobotato.adventurepack.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;

public class ModelExplorerHat extends BipedModel {
    //fields
    public RendererModel ExplorerHat;

    private final RendererModel Piece1;
    private final RendererModel Piece2;
    private final RendererModel Piece3;
    private final RendererModel Piece4;
    private final RendererModel Piece5;
    private final RendererModel Piece6;
    private final RendererModel Piece7;
    private final RendererModel Piece8;
    private final RendererModel Piece9;
    private final RendererModel Piece10;

    public ModelExplorerHat(float par1) {
        super(par1, 0, 64, 64);

        Piece1 = new RendererModel(this, 0, 32);
        Piece2 = new RendererModel(this, 0, 45);
        Piece3 = new RendererModel(this, 0, 45);
        Piece4 = new RendererModel(this, 0, 47);
        Piece5 = new RendererModel(this, 0, 50);
        Piece6 = new RendererModel(this, 32, 45);
        Piece7 = new RendererModel(this, 0, 47);
        Piece8 = new RendererModel(this, 32, 54);
        Piece9 = new RendererModel(this, 32, 54);
        Piece10 = new RendererModel(this, 48, 54);
        Piece1.addBox(-7F, 0F, -6F, 14, 1, 12);
        Piece2.addBox(-6F, 0F, 6F, 12, 1, 1);
        Piece3.addBox(-6F, 0F, -7F, 12, 1, 1);
        Piece4.addBox(-4.5F, -2F, 3.5F, 9, 2, 1);
        Piece5.addBox(-4F, -5F, -4F, 8, 5, 8);
        Piece6.addBox(-3F, -6F, -4F, 6, 1, 8);
        Piece7.addBox(-4.5F, -2F, -4.5F, 9, 2, 1);
        Piece8.addBox(-4.5F, -2F, -3.5F, 1, 2, 7);
        Piece9.addBox(3.5F, -2F, -3.5F, 1, 2, 7);
        Piece10.addBox(-1F, -6F, -4.2F, 2, 4, 1);

        ExplorerHat = new RendererModel(this, "Hat");

        textureWidth = 64;
        textureHeight = 64;

        ExplorerHat.setRotationPoint(0F, -6F, 0F);
        setRotation(ExplorerHat, 0F, 0F, 0F);
        ExplorerHat.mirror = true;
        ExplorerHat.addChild(Piece1);
        ExplorerHat.addChild(Piece2);
        ExplorerHat.addChild(Piece3);
        ExplorerHat.addChild(Piece4);
        ExplorerHat.addChild(Piece5);
        ExplorerHat.addChild(Piece6);
        ExplorerHat.addChild(Piece7);
        ExplorerHat.addChild(Piece8);
        ExplorerHat.addChild(Piece9);
        ExplorerHat.addChild(Piece10);
        ExplorerHat.setTextureSize(textureWidth, textureHeight);

        this.bipedHead.addChild(ExplorerHat);
    }

    public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
    }

    private void setRotation(RendererModel model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
