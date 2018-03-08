package brobotato.adventurepack;

import brobotato.adventurepack.item.ItemLantern;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class RenderHighlightedHandler {

    @SubscribeEvent
    public void renderWorldLastEvent(RenderWorldLastEvent evt) {
        ItemLantern.highlightHandler();
    }
}
