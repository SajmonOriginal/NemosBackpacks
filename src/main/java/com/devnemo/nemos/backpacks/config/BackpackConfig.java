package com.devnemo.nemos.backpacks.config;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import static com.devnemo.nemos.backpacks.NemosBackpacks.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BackpackConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue RENDER_BACKPACK_ON_BACK = BUILDER
            .comment("Whether to render the backpack on the player's back when equipped")
            .define("renderBackpackOnBack", true);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean renderBackpackOnBack = true;

    public static boolean shouldRenderBackpackOnBack() {
        return renderBackpackOnBack;
    }

    @SubscribeEvent
    public static void onConfigLoad(ModConfigEvent event) {
        if (event.getConfig().getSpec() == SPEC) {
            renderBackpackOnBack = RENDER_BACKPACK_ON_BACK.get();
        }
    }
}
