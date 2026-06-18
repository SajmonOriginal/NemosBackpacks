package com.devnemo.nemos.backpacks.client;

import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

import static com.devnemo.nemos.backpacks.NemosBackpacks.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BackpackKeyMappings {

    public static final String NEMOS_BACKPACKS_KEYBINDING = "key.categories." + MOD_ID;

    public static final KeyMapping SWAP_BACKPACK = new KeyMapping(
            MOD_ID + ".key.swapBackpack",
            GLFW.GLFW_KEY_B,
            NEMOS_BACKPACKS_KEYBINDING
    );

    public static final KeyMapping INTERACT_BACKPACK = new KeyMapping(
            MOD_ID + ".key.interactBackpack",
            GLFW.GLFW_KEY_I,
            NEMOS_BACKPACKS_KEYBINDING
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(SWAP_BACKPACK);
        event.register(INTERACT_BACKPACK);
    }
}
