package com.devnemo.nemos.backpacks.network;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static com.devnemo.nemos.backpacks.NemosBackpacks.MOD_ID;

public record BackpackOpenedPayload(ItemStack itemStack) implements CustomPacketPayload {
    public static final Type<BackpackOpenedPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MOD_ID, "backpack_opened"));
    public static final StreamCodec<RegistryFriendlyByteBuf, BackpackOpenedPayload> STREAM_CODEC = StreamCodec.composite(
            ItemStack.OPTIONAL_STREAM_CODEC, BackpackOpenedPayload::itemStack, BackpackOpenedPayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
