package syntacticsplenda.adventurepack.mixin;

import syntacticsplenda.adventurepack.client.model.ModelCowboyHat;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ArmorFeatureRenderer.class)
public abstract class ArmorFeatureRendererMixin<T extends LivingEntity, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> {

    @ModifyVariable(method = "renderArmor",
            at = @At("FIELD"),
            ordinal = 0,
            index = 12
    )
    public A getModArmorModel(A bipedEntityModel_1) {
        A model = (A) new ModelCowboyHat(1.0f);
        return model;
    }

}