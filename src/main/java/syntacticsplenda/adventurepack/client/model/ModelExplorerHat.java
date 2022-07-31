package syntacticsplenda.adventurepack.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class ModelExplorerHat extends BipedModel {
    //fields
    public ModelRenderer ExplorerHat;

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

    public ModelExplorerHat(float modelSize) {
        super(modelSize, 0, 64, 64);
        // manual adjust LOL
        modelSize /= 16;

        Piece1 = new ModelRenderer(this, 0, 32);
        Piece2 = new ModelRenderer(this, 0, 45);
        Piece3 = new ModelRenderer(this, 0, 45);
        Piece4 = new ModelRenderer(this, 0, 47);
        Piece5 = new ModelRenderer(this, 0, 50);
        Piece6 = new ModelRenderer(this, 32, 45);
        Piece7 = new ModelRenderer(this, 0, 47);
        Piece8 = new ModelRenderer(this, 32, 54);
        Piece9 = new ModelRenderer(this, 32, 54);
        Piece10 = new ModelRenderer(this, 48, 54);
        Piece1.addBox(-7F, 0F, -6F, 14, 1, 12, modelSize);
        Piece2.addBox(-6F, 0F, 6F, 12, 1, 1, modelSize);
        Piece3.addBox(-6F, 0F, -7F, 12, 1, 1, modelSize);
        Piece4.addBox(-4.5F, -2F, 3.5F, 9, 2, 1, modelSize);
        Piece5.addBox(-4F, -5F, -4F, 8, 5, 8, modelSize);
        Piece6.addBox(-3F, -6F, -4F, 6, 1, 8, modelSize);
        Piece7.addBox(-4.5F, -2F, -4.5F, 9, 2, 1, modelSize);
        Piece8.addBox(-4.5F, -2F, -3.5F, 1, 2, 7, modelSize);
        Piece9.addBox(3.5F, -2F, -3.5F, 1, 2, 7, modelSize);
        Piece10.addBox(-1F, -6F, -4.2F, 2, 4, 1, modelSize);

        ExplorerHat = new ModelRenderer(this);

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

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

}
