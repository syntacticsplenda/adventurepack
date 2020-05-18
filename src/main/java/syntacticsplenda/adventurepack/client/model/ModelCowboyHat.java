package syntacticsplenda.adventurepack.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCowboyHat extends ModelBiped {
    //fields
    public ModelRenderer CowboyHat;

    public ModelCowboyHat(float par1) {
        super(par1, 0, 64, 64);

        this.setTextureOffset("Hat.Piece1", 24, 53);
        this.setTextureOffset("Hat.Piece2", 42, 42);
        this.setTextureOffset("Hat.Piece3", 42, 42);
        this.setTextureOffset("Hat.Piece4", 44, 44);
        this.setTextureOffset("Hat.Piece5", 44, 44);
        this.setTextureOffset("Hat.Piece6", 0, 54);
        this.setTextureOffset("Hat.Piece7", 0, 51);
        this.setTextureOffset("Hat.Piece8", 0, 54);
        this.setTextureOffset("Hat.Piece9", 0, 51);
        this.setTextureOffset("Hat.Piece10", 10, 32);
        this.setTextureOffset("Hat.Piece11", 42, 32);
        this.setTextureOffset("Hat.Piece12", 42, 32);
        this.setTextureOffset("Hat.Piece13", 43, 32);

        CowboyHat = new ModelRenderer(this, "Hat");

        textureWidth = 64;
        textureHeight = 64;

        CowboyHat.setRotationPoint(0F, -6F, 0F);
        setRotation(CowboyHat, 0F, 0F, 0F);
        CowboyHat.mirror = true;
        CowboyHat.addBox("Piece1", -5F, 0F, -5F, 10, 1, 10);
        CowboyHat.addBox("Piece2", -6F, -1F, -5F, 1, 1, 10);
        CowboyHat.addBox("Piece3", 5F, -1F, -5F, 1, 1, 10);
        CowboyHat.addBox("Piece4", -7F, -2F, -4F, 1, 1, 8);
        CowboyHat.addBox("Piece6", -4.1F, -2F, -4.1F, 1, 2, 8);
        CowboyHat.addBox("Piece7", -4.1F, -2F, 3.1F, 8, 2, 1);
        CowboyHat.addBox("Piece8", 3.1F, -2F, -3.9F, 1, 2, 8);
        CowboyHat.addBox("Piece9", -3.9F, -2F, -4.1F, 8, 2, 1);
        CowboyHat.addBox("Piece10", -4F, -3F, -4F, 8, 3, 8);
        CowboyHat.addBox("Piece11", -3.5F, -5F, -4F, 3, 2, 8);
        CowboyHat.addBox("Piece12", 0.5F, -5F, -4F, 3, 2, 8);
        CowboyHat.addBox("Piece13", -1F, -4.5F, -4F, 2, 2, 8);
        CowboyHat.setTextureSize(textureHeight, textureWidth);

        this.bipedHead.addChild(CowboyHat);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }
}
