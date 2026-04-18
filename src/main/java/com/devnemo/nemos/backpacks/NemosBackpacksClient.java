package com.devnemo.nemos.backpacks;

import com.devnemo.nemos.backpacks.client.gui.screens.inventory.BackpackScreen;
import com.devnemo.nemos.backpacks.client.render.BackpackFeature;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

import static com.devnemo.nemos.backpacks.NemosBackpackMenuTypes.*;

@EventBusSubscriber(modid = NemosBackpacks.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NemosBackpacksClient {

    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(DEFAULT_BACKPACK.get(), BackpackScreen::new);
        event.register(COPPER_BACKPACK.get(), BackpackScreen::new);
        event.register(IRON_BACKPACK.get(), BackpackScreen::new);
        event.register(GOLDEN_BACKPACK.get(), BackpackScreen::new);
        event.register(DIAMOND_BACKPACK.get(), BackpackScreen::new);
        event.register(NETHERITE_BACKPACK.get(), BackpackScreen::new);
    }

    @SubscribeEvent
    public static void addLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model skin : event.getSkins()) {
            if (event.getSkin(skin) instanceof PlayerRenderer renderer) {
                renderer.addLayer(new BackpackFeature(renderer));
            }
        }
    }
}
