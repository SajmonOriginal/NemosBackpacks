package com.devnemo.nemos.backpacks.mixin.client;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.devnemo.nemos.backpacks.NemosBackpacks.BACKPACK_SLOT;

@Mixin(Gui.class)
public abstract class GuiMixin {

    @Shadow @Final private Minecraft minecraft;

    @Shadow
    protected abstract Player getCameraPlayer();

    @Inject(method = "renderHotbarAndDecorations", at = @At("HEAD"))
    private void renderCarriedBackpack(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        var player = this.getCameraPlayer();
        if (player == null) return;

        if (this.minecraft.gameMode != null && !this.minecraft.gameMode.canHurtPlayer()) return;

        var backpackItemStack = player.getInventory().getItem(BACKPACK_SLOT);
        if (backpackItemStack.isEmpty()) return;

        var backpackLocation = BuiltInRegistries.ITEM.getKey(backpackItemStack.getItem());
        var textureLocation = ResourceLocation.fromNamespaceAndPath(backpackLocation.getNamespace(), "hud/backpack/" + backpackLocation.getPath());

        int x = guiGraphics.guiWidth() / 2 - 5;
        int y = guiGraphics.guiHeight() - 49;

        guiGraphics.blitSprite(textureLocation, x, y, 9, 9);
    }
}
