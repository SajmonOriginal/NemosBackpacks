package com.devnemo.nemos.backpacks.mixin;

import com.devnemo.nemos.backpacks.helper.BackpackGetter;
import com.devnemo.nemos.backpacks.world.item.BackpackItem;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.devnemo.nemos.backpacks.NemosBackpacks.BACKPACK_SLOT;

@Mixin(Inventory.class)
public abstract class InventoryMixin implements BackpackGetter {

    @Shadow @Final public Player player;

    @Unique
    private ItemStack nemosBackpacks$backpackItemStack = ItemStack.EMPTY;

    // Use HEAD instead of TAIL because vanilla setItem mutates the index parameter in a loop
    @Inject(method = "setItem", at = @At("HEAD"), cancellable = true)
    private void setBackpackItem(int index, ItemStack stack, CallbackInfo ci) {
        if (index == BACKPACK_SLOT) {
            if (stack.getItem() instanceof BackpackItem || stack.isEmpty()) {
                nemosBackpacks$backpackItemStack = stack;
            }
            ci.cancel(); // Don't let vanilla try to process slot 46
        }
    }

    @Inject(method = "save", at = @At("TAIL"))
    private void saveBackpack(ListTag listTag, CallbackInfoReturnable<ListTag> cir) {
        if (!nemosBackpacks$backpackItemStack.isEmpty()) {
            CompoundTag compoundTag = new CompoundTag();
            compoundTag.putByte("Slot", (byte) BACKPACK_SLOT);
            listTag.add(nemosBackpacks$backpackItemStack.save(player.registryAccess(), compoundTag));
        }
    }

    @Inject(method = "load", at = @At("TAIL"))
    private void loadBackpack(ListTag listTag, CallbackInfo ci) {
        nemosBackpacks$backpackItemStack = ItemStack.EMPTY;
        for (int i = 0; i < listTag.size(); i++) {
            CompoundTag compoundTag = listTag.getCompound(i);
            int slot = compoundTag.getByte("Slot") & 255;
            if (slot == BACKPACK_SLOT) {
                nemosBackpacks$backpackItemStack = ItemStack.parse(player.registryAccess(), compoundTag).orElse(ItemStack.EMPTY);
            }
        }
    }

    // Use HEAD to intercept before vanilla mutates the index
    @Inject(method = "getItem", at = @At("HEAD"), cancellable = true)
    private void getBackpackItem(int index, CallbackInfoReturnable<ItemStack> cir) {
        if (index == BACKPACK_SLOT) {
            cir.setReturnValue(nemosBackpacks$backpackItemStack);
        }
    }

    // Use HEAD to intercept before vanilla mutates the index
    @Inject(method = "removeItem(II)Lnet/minecraft/world/item/ItemStack;", at = @At("HEAD"), cancellable = true)
    private void removeBackpackItem(int index, int count, CallbackInfoReturnable<ItemStack> cir) {
        if (index == BACKPACK_SLOT) {
            if (!nemosBackpacks$backpackItemStack.isEmpty()) {
                cir.setReturnValue(nemosBackpacks$backpackItemStack.split(count));
            } else {
                cir.setReturnValue(ItemStack.EMPTY);
            }
        }
    }

    @Inject(method = "removeItem(Lnet/minecraft/world/item/ItemStack;)V", at = @At("RETURN"))
    private void removeBackpackItem(ItemStack stack, CallbackInfo ci) {
        if (stack == nemosBackpacks$backpackItemStack) {
            nemosBackpacks$backpackItemStack = ItemStack.EMPTY;
        }
    }

    // Use HEAD to intercept before vanilla mutates the index
    @Inject(method = "removeItemNoUpdate", at = @At("HEAD"), cancellable = true)
    private void removeBackpackNoUpdate(int index, CallbackInfoReturnable<ItemStack> cir) {
        if (index == BACKPACK_SLOT) {
            ItemStack result = nemosBackpacks$backpackItemStack;
            nemosBackpacks$backpackItemStack = ItemStack.EMPTY;
            cir.setReturnValue(result);
        }
    }

    @ModifyReturnValue(method = "getContainerSize", at = @At("RETURN"))
    private int getContainerSize(int original) {
        return original + 1;
    }

    @ModifyReturnValue(method = "isEmpty", at = @At("RETURN"))
    private boolean isEmpty(boolean original) {
        if (original) {
            return nemosBackpacks$backpackItemStack.isEmpty();
        }
        return false;
    }

    @Inject(method = "dropAll", at = @At("TAIL"))
    private void dropBackpackItem(CallbackInfo ci) {
        if (!nemosBackpacks$backpackItemStack.isEmpty()) {
            player.drop(nemosBackpacks$backpackItemStack, true, false);
            nemosBackpacks$backpackItemStack = ItemStack.EMPTY;
        }
    }

    @Inject(method = "replaceWith", at = @At("HEAD"))
    private void keepBackpack(Inventory playerInventory, CallbackInfo ci) {
        nemosBackpacks$backpackItemStack = ((BackpackGetter) playerInventory).nemosBackpacks$getBackpack();
    }

    @Override
    public ItemStack nemosBackpacks$getBackpack() {
        return nemosBackpacks$backpackItemStack;
    }
}
