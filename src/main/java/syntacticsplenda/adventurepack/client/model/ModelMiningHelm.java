package syntacticsplenda.adventurepack.client.model;


import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelMiningHelm extends BipedModel {
    //fields
    public ModelRenderer MiningHelmet;

    private final ModelRenderer Piece1;
    private final ModelRenderer Piece2;
    private final ModelRenderer Piece3;
    private final ModelRenderer Piece4;
    private final ModelRenderer Piece5;
    private final ModelRenderer Piece6;
    private final ModelRenderer Piece7;
    private final ModelRenderer Piece8;

    public ModelMiningHelm(float modelSize) {
        super(modelSize, 0, 64, 64);
        // manual adjust LOL
        modelSize /= 16;

        Piece1 = new ModelRenderer(this, 0, 32);
        Piece2 = new ModelRenderer(this, 0, 43);
        Piece3 = new ModelRenderer(this, 0, 55);
        Piece4 = new ModelRenderer(this, 40, 32);
        Piece5 = new ModelRenderer(this, 40, 35);
        Piece6 = new ModelRenderer(this, 40, 35);
        Piece7 = new ModelRenderer(this, 40, 45);
        Piece8 = new ModelRenderer(this, 40, 45);
        Piece1.func_228301_a_(-5F, 0F, -5F, 10, 1, 10, modelSize);
        Piece2.func_228301_a_(-4F, -4F, -4F, 8, 4, 8, modelSize);
        Piece3.func_228301_a_(-3F, -5F, -4F, 6, 1, 8, modelSize);
        Piece4.func_228301_a_(-1F, -2F, -5F, 2, 2, 1, modelSize);
        Piece5.func_228301_a_(3.5F, -2F, -4F, 1, 2, 8, modelSize);
        Piece6.func_228301_a_(-4.5F, -2F, -4F, 1, 2, 8, modelSize);
        Piece7.func_228301_a_(-4.5F, -2F, 3.5F, 9, 2, 1, modelSize);
        Piece8.func_228301_a_(-4.5F, -2F, -4.5F, 9, 2, 1, modelSize);

        MiningHelmet = new ModelRenderer(this);

        textureWidth = 64;
        textureHeight = 64;

        MiningHelmet.setRotationPoint(0F, -6F, 0F);
        setRotation(MiningHelmet, 0F, 0F, 0F);
        MiningHelmet.mirror = true;
        MiningHelmet.addChild(Piece1);
        MiningHelmet.addChild(Piece2);
        MiningHelmet.addChild(Piece3);
        MiningHelmet.addChild(Piece4);
        MiningHelmet.addChild(Piece5);
        MiningHelmet.addChild(Piece6);
        MiningHelmet.addChild(Piece7);
        MiningHelmet.addChild(Piece8);
        MiningHelmet.setTextureSize(textureWidth, textureHeight);

        this.bipedHead.addChild(MiningHelmet);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
