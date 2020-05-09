package brobotato.adventurepack.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class ModelExplorerHat extends BipedEntityModel {
    //fields
    public Cuboid ExplorerHat;

    private final Cuboid Piece1;
    private final Cuboid Piece2;
    private final Cuboid Piece3;
    private final Cuboid Piece4;
    private final Cuboid Piece5;
    private final Cuboid Piece6;
    private final Cuboid Piece7;
    private final Cuboid Piece8;
    private final Cuboid Piece9;
    private final Cuboid Piece10;

    public ModelExplorerHat(float par1) {
        super(par1, 0, 64, 64);

        Piece1 = new Cuboid(this, 0, 32);
        Piece2 = new Cuboid(this, 0, 45);
        Piece3 = new Cuboid(this, 0, 45);
        Piece4 = new Cuboid(this, 0, 47);
        Piece5 = new Cuboid(this, 0, 50);
        Piece6 = new Cuboid(this, 32, 45);
        Piece7 = new Cuboid(this, 0, 47);
        Piece8 = new Cuboid(this, 32, 54);
        Piece9 = new Cuboid(this, 32, 54);
        Piece10 = new Cuboid(this, 48, 54);
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

        ExplorerHat = new Cuboid(this, "Hat");

        textureWidth = 64;
        textureHeight = 64;

        ExplorerHat.setRotationPoint(0F, -6F, 0F);
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

        this.head.addChild(ExplorerHat);
    }

    public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.method_17088(entity, f, f1, f2, f3, f4, f5);
        method_17087(entity, f, f1, f2, f3, f4, f5);
    }

    private void setRotationPoint(Cuboid cuboid, float x, float y, float z) {
        cuboid.setRotationPoint(x, y, z);
    }

}
