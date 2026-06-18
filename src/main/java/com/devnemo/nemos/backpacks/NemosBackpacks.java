package com.devnemo.nemos.backpacks;

import com.devnemo.nemos.backpacks.config.BackpackConfig;
import com.devnemo.nemos.backpacks.core.component.BackpacksDataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(NemosBackpacks.MOD_ID)
public class NemosBackpacks {

    public static final String MOD_ID = "nemos_backpacks";
    public static final int BACKPACK_SLOT = 46;
    public static final Logger LOG = LoggerFactory.getLogger(MOD_ID);

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Registries.ITEM, MOD_ID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, MOD_ID);

    public NemosBackpacks(IEventBus eventBus, ModContainer modContainer) {
        LOG.info("Thank you for using Nemo's Backpacks");

        modContainer.registerConfig(ModConfig.Type.CLIENT, BackpackConfig.SPEC);

        BackpacksDataComponents.init();
        NemosBackpackItems.init();
        NemosBackpackCreativeModeTabs.init();
        NemosBackpackMenuTypes.init();

        BackpacksDataComponents.DATA_COMPONENTS.register(eventBus);
        ITEMS.register(eventBus);
        CREATIVE_TABS.register(eventBus);
        MENUS.register(eventBus);
    }
}
