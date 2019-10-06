package brobotato.adventurepack.client.model;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;


public class ModelCowboyHat extends BipedEntityModel {

    public final Cuboid CowboyHat;

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
    private final Cuboid Piece11;
    private final Cuboid Piece12;
    private final Cuboid Piece13;

    public ModelCowboyHat(float par1) {
        super(par1, 0, 64, 64);

        Piece1 = new Cuboid(this, 24, 53);
        Piece2 = new Cuboid(this, 42, 42);
        Piece3 = new Cuboid(this, 42, 42);
        Piece4 = new Cuboid(this, 44, 44);
        Piece5 = new Cuboid(this, 44, 44);
        Piece6 = new Cuboid(this, 0, 54);
        Piece7 = new Cuboid(this, 0, 51);
        Piece8 = new Cuboid(this, 0, 54);
        Piece9 = new Cuboid(this, 0, 51);
        Piece10 = new Cuboid(this, 10, 32);
        Piece11 = new Cuboid(this, 42, 32);
        Piece12 = new Cuboid(this, 42, 32);
        Piece13 = new Cuboid(this, 43, 32);
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

        CowboyHat = new Cuboid(this, "hat");

        textureWidth = 64;
        textureHeight = 64;

        CowboyHat.setRotationPoint(0F, -6F, 0F);
        setRotationPoint(CowboyHat, 0F, 0F, 0F);
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

        this.head.addChild(CowboyHat);
    }

    public void render(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.method_17088(entity, f, f1, f2, f3, f4, f5);
        method_17087(entity, f, f1, f2, f3, f4, f5);
    }

    private void setRotationPoint(Cuboid cuboid, float x, float y, float z) {
        cuboid.setRotationPoint(x, y, z);
    }
}
