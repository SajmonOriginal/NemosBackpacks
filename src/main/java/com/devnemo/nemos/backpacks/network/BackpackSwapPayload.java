package com.devnemo.nemos.backpacks.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import static com.devnemo.nemos.backpacks.NemosBackpacks.MOD_ID;

public record BackpackSwapPayload() implements CustomPacketPayload {
    public static final Type<BackpackSwapPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(MOD_ID, "backpack_swap"));
    public static final StreamCodec<FriendlyByteBuf, BackpackSwapPayload> STREAM_CODEC = StreamCodec.unit(new BackpackSwapPayload());

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
