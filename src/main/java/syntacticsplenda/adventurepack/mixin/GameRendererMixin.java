package syntacticsplenda.adventurepack.mixin;

import syntacticsplenda.adventurepack.item.ItemEnderLantern;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Inject(at = @At(value = "INVOKE"), method = "renderWorld", cancellable = true)
    public void render(CallbackInfo info) {
        ItemEnderLantern.highlightHandler();
    }
}
