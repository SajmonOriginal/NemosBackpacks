package com.devnemo.nemos.backpacks.client;

import com.devnemo.nemos.backpacks.network.BackpackInteractPayload;
import com.devnemo.nemos.backpacks.network.BackpackSwapPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Optional;

import static com.devnemo.nemos.backpacks.NemosBackpacks.MOD_ID;
import static com.devnemo.nemos.backpacks.client.BackpackKeyMappings.INTERACT_BACKPACK;
import static com.devnemo.nemos.backpacks.client.BackpackKeyMappings.SWAP_BACKPACK;

@EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
public class ClientBackpackHandler {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        var mc = net.minecraft.client.Minecraft.getInstance();
        if (mc.player == null) return;

        while (INTERACT_BACKPACK.consumeClick()) {
            PacketDistributor.sendToServer(new BackpackInteractPayload());
        }

        while (SWAP_BACKPACK.consumeClick()) {
            if (!Optional.ofNullable(mc.player).map(Player::isSpectator).orElse(true)) {
                PacketDistributor.sendToServer(new BackpackSwapPayload());
            }
        }
    }
}
