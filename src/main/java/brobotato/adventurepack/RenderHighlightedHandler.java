package brobotato.adventurepack;

import brobotato.adventurepack.item.ItemEnderLantern;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RenderHighlightedHandler {

    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent evt) {
        ItemEnderLantern.highlightHandler(evt);
    }
}
