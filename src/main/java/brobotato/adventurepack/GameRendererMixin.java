package brobotato.adventurepack;

import brobotato.adventurepack.item.ItemEnderLantern;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Inject(at = @At(value = "INVOKE"), method = "render", cancellable = true)
    public void render(CallbackInfoReturnable<Boolean> info) {
        ItemEnderLantern.highlightHandler();
    }
}
