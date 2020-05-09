package syntacticsplenda.adventurepack.item.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelExplorerHat extends ModelBiped {
    //fields
    public ModelRenderer ExplorerHat;

    public ModelExplorerHat(float par1) {
        super(par1, 0, 64, 64);

        this.setTextureOffset("Hat.Piece1", 0, 32);
        this.setTextureOffset("Hat.Piece2", 0, 45);
        this.setTextureOffset("Hat.Piece3", 0, 45);
        this.setTextureOffset("Hat.Piece4", 0, 47);
        this.setTextureOffset("Hat.Piece5", 0, 50);
        this.setTextureOffset("Hat.Piece6", 32, 45);
        this.setTextureOffset("Hat.Piece7", 0, 47);
        this.setTextureOffset("Hat.Piece8", 32, 54);
        this.setTextureOffset("Hat.Piece9", 32, 54);
        this.setTextureOffset("Hat.Piece10", 48, 54);

        ExplorerHat = new ModelRenderer(this, "Hat");

        textureWidth = 64;
        textureHeight = 64;

        ExplorerHat.setRotationPoint(0F, -6F, 0F);
        setRotation(ExplorerHat, 0F, 0F, 0F);
        ExplorerHat.mirror = true;
        ExplorerHat.addBox("Piece1", -7F, 0F, -6F, 14, 1, 12);
        ExplorerHat.addBox("Piece2", -6F, 0F, 6F, 12, 1, 1);
        ExplorerHat.addBox("Piece3", -6F, 0F, -7F, 12, 1, 1);
        ExplorerHat.addBox("Piece4", -4.5F, -2F, 3.5F, 9, 2, 1);
        ExplorerHat.addBox("Piece5", -4F, -5F, -4F, 8, 5, 8);
        ExplorerHat.addBox("Piece6", -3F, -6F, -4F, 6, 1, 8);
        ExplorerHat.addBox("Piece7", -4.5F, -2F, -4.5F, 9, 2, 1);
        ExplorerHat.addBox("Piece8", -4.5F, -2F, -3.5F, 1, 2, 7);
        ExplorerHat.addBox("Piece9", 3.5F, -2F, -3.5F, 1, 2, 7);
        ExplorerHat.addBox("Piece10", -1F, -6F, -4.2F, 2, 4, 1);
        ExplorerHat.setTextureSize(textureWidth, textureHeight);

        this.bipedHead.addChild(ExplorerHat);
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
