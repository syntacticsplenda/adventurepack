package syntacticsplenda.adventurepack.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;


public class ModelCowboyHat extends BipedModel {

    public final ModelRenderer CowboyHat;

    private final ModelRenderer Piece1;
    private final ModelRenderer Piece2;
    private final ModelRenderer Piece3;
    private final ModelRenderer Piece4;
    private final ModelRenderer Piece5;
    private final ModelRenderer Piece6;
    private final ModelRenderer Piece7;
    private final ModelRenderer Piece8;
    private final ModelRenderer Piece9;
    private final ModelRenderer Piece10;
    private final ModelRenderer Piece11;
    private final ModelRenderer Piece12;
    private final ModelRenderer Piece13;

    public ModelCowboyHat(float modelSize) {
        super(modelSize, 0, 64, 64);
        // manual adjust LOL
        modelSize /= 16;

        Piece1 = new ModelRenderer(this, 24, 53);
        Piece2 = new ModelRenderer(this, 42, 42);
        Piece3 = new ModelRenderer(this, 42, 42);
        Piece4 = new ModelRenderer(this, 44, 44);
        Piece5 = new ModelRenderer(this, 44, 44);
        Piece6 = new ModelRenderer(this, 0, 54);
        Piece7 = new ModelRenderer(this, 0, 51);
        Piece8 = new ModelRenderer(this, 0, 54);
        Piece9 = new ModelRenderer(this, 0, 51);
        Piece10 = new ModelRenderer(this, 10, 32);
        Piece11 = new ModelRenderer(this, 42, 32);
        Piece12 = new ModelRenderer(this, 42, 32);
        Piece13 = new ModelRenderer(this, 43, 32);
        Piece1.addBox(-5F, 0F, -5F, 10, 1, 10, modelSize);
        Piece2.addBox(-6F, -1F, -5F, 1, 1, 10, modelSize);
        Piece3.addBox(5F, -1F, -5F, 1, 1, 10, modelSize);
        Piece4.addBox(-7F, -2F, -4F, 1, 1, 8, modelSize);
        Piece5.addBox(6F, -2F, -4F, 1, 1, 8, modelSize);
        Piece6.addBox(-4.1F, -2F, -4.1F, 1, 2, 8, modelSize);
        Piece7.addBox(-4.1F, -2F, 3.1F, 8, 2, 1, modelSize);
        Piece8.addBox(3.1F, -2F, -3.9F, 1, 2, 8, modelSize);
        Piece9.addBox(-3.9F, -2F, -4.1F, 8, 2, 1, modelSize);
        Piece10.addBox(-4F, -3F, -4F, 8, 3, 8, modelSize);
        Piece11.addBox(-3.5F, -5F, -4F, 3, 2, 8, modelSize);
        Piece12.addBox(0.5F, -5F, -4F, 3, 2, 8, modelSize);
        Piece13.addBox(-0.5F, -4.5F, -4F, 1, 2, 8, modelSize);

        CowboyHat = new ModelRenderer(this);

        texWidth = 64;
        texHeight = 64;

        CowboyHat.setPos(0F, -6F, 0F);
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
        CowboyHat.setTexSize(texWidth, texHeight);

        this.head.addChild(CowboyHat);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

}
