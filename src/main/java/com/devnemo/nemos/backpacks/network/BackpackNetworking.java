package com.devnemo.nemos.backpacks.network;

import com.devnemo.nemos.backpacks.core.component.BackpacksDataComponents;
import com.devnemo.nemos.backpacks.helper.Backpacker;
import com.devnemo.nemos.backpacks.world.item.BackpackItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import static com.devnemo.nemos.backpacks.NemosBackpacks.BACKPACK_SLOT;
import static com.devnemo.nemos.backpacks.NemosBackpacks.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class BackpackNetworking {

    @SubscribeEvent
    public static void onRegisterPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(MOD_ID).versioned("1.0");

        registrar.playToServer(
                BackpackInteractPayload.TYPE,
                BackpackInteractPayload.STREAM_CODEC,
                (payload, context) -> {
                    context.enqueueWork(() -> {
                        var player = context.player();
                        var playerInventory = player.getInventory();
                        var itemStack = playerInventory.getItem(BACKPACK_SLOT);

                        if (itemStack.getItem() instanceof BackpackItem backpackItem) {
                            itemStack.set(BackpacksDataComponents.IS_BACKPACK_OPEN.get(), true);
                            if (player instanceof ServerPlayer serverPlayer) {
                                PacketDistributor.sendToPlayer(serverPlayer, new BackpackOpenedPayload(itemStack));
                            }
                            var menuProvider = backpackItem.createScreenHandlerFactory(itemStack);
                            if (menuProvider != null) {
                                player.openMenu(menuProvider);
                            }
                        }
                    });
                }
        );

        registrar.playToServer(
                BackpackSwapPayload.TYPE,
                BackpackSwapPayload.STREAM_CODEC,
                (payload, context) -> {
                    context.enqueueWork(() -> {
                        var player = context.player();
                        if (player.isSpectator()) return;

                        var playerInventory = player.getInventory();
                        var backpackItemStack = playerInventory.getItem(BACKPACK_SLOT);
                        var itemStackInHand = player.getItemInHand(InteractionHand.MAIN_HAND);

                        if (!itemStackInHand.isEmpty() && !(itemStackInHand.getItem() instanceof BackpackItem)) {
                            return;
                        }

                        playerInventory.setItem(BACKPACK_SLOT, itemStackInHand);
                        player.setItemInHand(InteractionHand.MAIN_HAND, backpackItemStack);
                        player.stopUsingItem();
                    });
                }
        );

        registrar.playToClient(
                BackpackOpenedPayload.TYPE,
                BackpackOpenedPayload.STREAM_CODEC,
                (payload, context) -> {
                    context.enqueueWork(() -> {
                        var player = context.player();
                        if (player != null) {
                            ((Backpacker) player).nemosBackpacks$setLastOpenedBackpack(payload.itemStack());
                        }
                    });
                }
        );
    }
}
