package syntacticsplenda.adventurepack;

import syntacticsplenda.adventurepack.item.ItemLantern;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderHighlightedHandler {

    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent evt) {
        ItemLantern.highlightHandler(evt);
    }
}