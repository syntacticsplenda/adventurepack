package syntacticsplenda.adventurepack.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.LivingEntity;


public class ModelCowboyHat extends BipedModel {

    public final RendererModel CowboyHat;

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
    private final RendererModel Piece11;
    private final RendererModel Piece12;
    private final RendererModel Piece13;

    public ModelCowboyHat(float par1) {
        super(par1, 0, 64, 64);

        Piece1 = new RendererModel(this, 24, 53);
        Piece2 = new RendererModel(this, 42, 42);
        Piece3 = new RendererModel(this, 42, 42);
        Piece4 = new RendererModel(this, 44, 44);
        Piece5 = new RendererModel(this, 44, 44);
        Piece6 = new RendererModel(this, 0, 54);
        Piece7 = new RendererModel(this, 0, 51);
        Piece8 = new RendererModel(this, 0, 54);
        Piece9 = new RendererModel(this, 0, 51);
        Piece10 = new RendererModel(this, 10, 32);
        Piece11 = new RendererModel(this, 42, 32);
        Piece12 = new RendererModel(this, 42, 32);
        Piece13 = new RendererModel(this, 43, 32);
        Piece1.addBox(-5F, 0F, -5F, 10, 1, 10);
        Piece2.addBox(-6F, -1F, -5F, 1, 1, 10);
        Piece3.addBox(5F, -1F, -5F, 1, 1, 10);
        Piece4.addBox(-7F, -2F, -4F, 1, 1, 8);
        Piece5.addBox(6F, -2F, -4F, 1, 1, 8);
        Piece6.addBox(-4.1F, -2F, -4.1F, 1, 2, 8);
        Piece7.addBox(-4.1F, -2F, 3.1F, 8, 2, 1);
        Piece8.addBox(3.1F, -2F, -3.9F, 1, 2, 8);
        Piece9.addBox(-3.9F, -2F, -4.1F, 8, 2, 1);
        Piece10.addBox(-4F, -3F, -4F, 8, 3, 8);
        Piece11.addBox(-3.5F, -5F, -4F, 3, 2, 8);
        Piece12.addBox(0.5F, -5F, -4F, 3, 2, 8);
        Piece13.addBox(-0.5F, -4.5F, -4F, 1, 2, 8);

        CowboyHat = new RendererModel(this, "hat");

        textureWidth = 64;
        textureHeight = 64;

        CowboyHat.setRotationPoint(0F, -6F, 0F);
        setRotation(CowboyHat, 0F, 0F, 0F);
        CowboyHat.mirror = true;
        CowboyHat.addChild(Piece1);
        CowboyHat.addChild(Piece2);
        CowboyHat.addChild(Piece3);
        CowboyHat.addChild(Piece4);
        CowboyHat.addChild(Piece5);
        CowboyHat.addChild(Piece6);
        CowboyHat.addChild(Piece7);
        CowboyHat.addChild(Piece8);
        CowboyHat.addChild(Piece9);
        CowboyHat.addChild(Piece10);
        CowboyHat.addChild(Piece11);
        CowboyHat.addChild(Piece12);
        CowboyHat.addChild(Piece13);
        CowboyHat.setTextureSize(textureWidth, textureHeight);

        this.bipedHead.addChild(CowboyHat);
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
