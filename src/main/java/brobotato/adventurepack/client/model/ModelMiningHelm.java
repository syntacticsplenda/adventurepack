package brobotato.adventurepack.client.model;


import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

public class ModelMiningHelm extends BipedEntityModel {
    //fields
    public Cuboid MiningHelmet;

    private final Cuboid Piece1;
    private final Cuboid Piece2;
    private final Cuboid Piece3;
    private final Cuboid Piece4;
    private final Cuboid Piece5;
    private final Cuboid Piece6;
    private final Cuboid Piece7;
    private final Cuboid Piece8;

    public ModelMiningHelm(float par1) {
        super(par1, 0, 64, 64);

        Piece1 = new Cuboid(this, 0, 32);
        Piece2 = new Cuboid(this, 0, 43);
        Piece3 = new Cuboid(this, 0, 55);
        Piece4 = new Cuboid(this, 40, 32);
        Piece5 = new Cuboid(this, 40, 35);
        Piece6 = new Cuboid(this, 40, 35);
        Piece7 = new Cuboid(this, 40, 45);
        Piece8 = new Cuboid(this, 40, 45);
        Piece1.addBox(-5F, 0F, -5F, 10, 1, 10);
        Piece2.addBox(-4F, -4F, -4F, 8, 4, 8);
        Piece3.addBox(-3F, -5F, -4F, 6, 1, 8);
        Piece4.addBox(-1F, -2F, -5F, 2, 2, 1);
        Piece5.addBox(3.5F, -2F, -4F, 1, 2, 8);
        Piece6.addBox(-4.5F, -2F, -4F, 1, 2, 8);
        Piece7.addBox(-4.5F, -2F, 3.5F, 9, 2, 1);
        Piece8.addBox(-4.5F, -2F, -4.5F, 9, 2, 1);

        MiningHelmet = new Cuboid(this, "Helmet");

        textureWidth = 64;
        textureHeight = 64;

        MiningHelmet.setRotationPoint(0F, -6F, 0F);
        setRotationPoint(MiningHelmet, 0F, 0F, 0F);
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

        this.head.addChild(MiningHelmet);
    }

    public void method_17088(LivingEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.method_17088(entity, f, f1, f2, f3, f4, f5);
        super.method_17087(entity, f, f1, f2, f3, f4, f5);
    }

    private void setRotationPoint(Cuboid cuboid, float x, float y, float z) {
        cuboid.setRotationPoint(x, y, z);
    }

}
