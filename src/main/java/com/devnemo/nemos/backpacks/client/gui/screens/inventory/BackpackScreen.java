package com.devnemo.nemos.backpacks.client.gui.screens.inventory;

import com.devnemo.nemos.backpacks.world.inventory.BackpackMenu;
import com.devnemo.nemos.backpacks.world.item.BackpackMaterial;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.DyeColor;
import org.jetbrains.annotations.NotNull;

import static com.devnemo.nemos.backpacks.NemosBackpacks.MOD_ID;
import static com.devnemo.nemos.backpacks.client.BackpackKeyMappings.INTERACT_BACKPACK;

public class BackpackScreen extends AbstractContainerScreen<BackpackMenu> {

    private final ResourceLocation backpackBackground;
    private final ResourceLocation backpackOverlay;
    private final int containerRows;

    public BackpackScreen(BackpackMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.containerRows = menu.getRowCount();
        this.imageHeight = 114 + this.containerRows * 18;
        this.inventoryLabelY = this.imageHeight - 94;
        backpackOverlay = getOverlayTextureLocation(menu.getBackpackMaterial());
        backpackBackground = getBackgroundTextureLocation(menu.getDyeColor());
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        int leftPos = (this.width - this.imageWidth) / 2;
        int topPos = (this.height - this.imageHeight) / 2;

        guiGraphics.blit(backpackBackground, leftPos, topPos, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
        guiGraphics.blit(backpackBackground, leftPos, topPos + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
        guiGraphics.blit(backpackOverlay, leftPos, topPos, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
        guiGraphics.blit(backpackOverlay, leftPos, topPos + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (INTERACT_BACKPACK.matches(keyCode, scanCode)) {
            this.onClose();
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (INTERACT_BACKPACK.matchesMouse(button)) {
            this.onClose();
            return true;
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private static ResourceLocation getBackgroundTextureLocation(DyeColor dyeColor) {
        var dyeColorString = "default";
        if (dyeColor != null) {
            dyeColorString = dyeColor.getName();
        }
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, String.format("textures/gui/container/backpack/background/%s_backpack_background.png", dyeColorString));
    }

    private static ResourceLocation getOverlayTextureLocation(BackpackMaterial backpackMaterial) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, String.format("textures/gui/container/backpack/overlay/%s_backpack_overlay.png", backpackMaterial.getName()));
    }
}
