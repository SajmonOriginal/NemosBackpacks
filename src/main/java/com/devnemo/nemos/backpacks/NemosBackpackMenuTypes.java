package com.devnemo.nemos.backpacks;

import com.devnemo.nemos.backpacks.world.inventory.BackpackMenu;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Supplier;

public class NemosBackpackMenuTypes {

    public static Supplier<MenuType<BackpackMenu>> DEFAULT_BACKPACK = register("default_backpack", BackpackMenu::defaultBackpack);
    public static Supplier<MenuType<BackpackMenu>> COPPER_BACKPACK = register("copper_backpack", BackpackMenu::copperBackpack);
    public static Supplier<MenuType<BackpackMenu>> IRON_BACKPACK = register("iron_backpack", BackpackMenu::ironBackpack);
    public static Supplier<MenuType<BackpackMenu>> GOLDEN_BACKPACK = register("golden_backpack", BackpackMenu::goldenBackpack);
    public static Supplier<MenuType<BackpackMenu>> DIAMOND_BACKPACK = register("diamond_backpack", BackpackMenu::diamondBackpack);
    public static Supplier<MenuType<BackpackMenu>> NETHERITE_BACKPACK = register("netherite_backpack", BackpackMenu::netheriteBackpack);

    public static void init() {}

    private static Supplier<MenuType<BackpackMenu>> register(String id, MenuType.MenuSupplier<BackpackMenu> menuSupplier) {
        return NemosBackpacks.MENUS.register(id, () -> new MenuType<>(menuSupplier, FeatureFlags.DEFAULT_FLAGS));
    }
}
