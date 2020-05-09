package syntacticsplenda.adventurepack.client.model;


import net.minecraft.client.renderer.entity.model.ModelBiped;
import net.minecraft.client.renderer.entity.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMiningHelm extends ModelBiped {
    //fields
    public ModelRenderer MiningHelmet;

    public ModelMiningHelm(float par1) {
        super(par1, 0, 64, 64);

        this.setTextureOffset("Helmet.Piece1", 0, 32);
        this.setTextureOffset("Helmet.Piece2", 0, 43);
        this.setTextureOffset("Helmet.Piece3", 0, 55);
        this.setTextureOffset("Helmet.Piece4", 40, 32);
        this.setTextureOffset("Helmet.Piece5", 40, 35);
        this.setTextureOffset("Helmet.Piece6", 40, 35);
        this.setTextureOffset("Helmet.Piece7", 40, 45);
        this.setTextureOffset("Helmet.Piece8", 40, 45);

        MiningHelmet = new ModelRenderer(this, "Helmet");

        textureWidth = 64;
        textureHeight = 64;

        MiningHelmet.setRotationPoint(0F, -6F, 0F);
        setRotation(MiningHelmet, 0F, 0F, 0F);
        MiningHelmet.mirror = true;
        MiningHelmet.addBox("Piece1", -5F, 0F, -5F, 10, 1, 10);
        MiningHelmet.addBox("Piece2", -4F, -4F, -4F, 8, 4, 8);
        MiningHelmet.addBox("Piece3", -3F, -5F, -4F, 6, 1, 8);
        MiningHelmet.addBox("Piece4", -1F, -2F, -5F, 2, 2, 1);
        MiningHelmet.addBox("Piece5", 3.5F, -2F, -4F, 1, 2, 8);
        MiningHelmet.addBox("Piece6", -4.5F, -2F, -4F, 1, 2, 8);
        MiningHelmet.addBox("Piece7", -4.5F, -2F, 3.5F, 9, 2, 1);
        MiningHelmet.addBox("Piece8", -4.5F, -2F, -4.5F, 9, 2, 1);
        MiningHelmet.setTextureSize(textureWidth, textureHeight);

        this.bipedHead.addChild(MiningHelmet);
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
