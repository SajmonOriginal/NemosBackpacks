package com.devnemo.nemos.backpacks.mixin;

import com.devnemo.nemos.backpacks.world.item.BackpackItem;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.devnemo.nemos.backpacks.NemosBackpacks.BACKPACK_SLOT;
import static com.devnemo.nemos.backpacks.NemosBackpacks.MOD_ID;

@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin extends AbstractContainerMenu {

    @Unique
    private static final ResourceLocation EMPTY_SLOT_BACKPACK = ResourceLocation.fromNamespaceAndPath(MOD_ID, "container/slot/backpack");

    protected InventoryMenuMixin(@Nullable MenuType<?> menuType, int containerId) {
        super(menuType, containerId);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    private void addBackpackSlot(Inventory playerInventory, boolean active, Player owner, CallbackInfo ci) {
        this.addSlot(new Slot(playerInventory, BACKPACK_SLOT, 77, 26) {
            @Override
            public boolean mayPlace(@NotNull ItemStack stack) {
                return stack.getItem() instanceof BackpackItem;
            }

            @Override
            public @Nullable Pair<ResourceLocation, ResourceLocation> getNoItemIcon() {
                return Pair.of(InventoryMenu.BLOCK_ATLAS, EMPTY_SLOT_BACKPACK);
            }
        });
    }

    @Inject(method = "quickMoveStack",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/player/Player;getEquipmentSlotForItem(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/entity/EquipmentSlot;",
                    shift = At.Shift.BEFORE),
            cancellable = true)
    private void quickMoveBackpack(Player player, int index, CallbackInfoReturnable<ItemStack> cir,
                                   @Local(ordinal = 0) ItemStack itemstack,
                                   @Local(ordinal = 1) ItemStack itemstack1) {
        if (index != 0 && index != BACKPACK_SLOT && itemstack1.getItem() instanceof BackpackItem) {
            if (this.moveItemStackTo(itemstack1, BACKPACK_SLOT, BACKPACK_SLOT + 1, false)) {
                cir.setReturnValue(itemstack);
            }
        }
    }
}
