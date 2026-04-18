package com.devnemo.nemos.backpacks.helper;

import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

import static com.devnemo.nemos.backpacks.NemosBackpacks.MOD_ID;

public class SmithingTemplateItemHelper {

    private static final ResourceLocation EMPTY_SLOT_BACKPACK = ResourceLocation.fromNamespaceAndPath(MOD_ID, "container/slot/backpack");
    private static final ResourceLocation EMPTY_SLOT_INGOT = ResourceLocation.fromNamespaceAndPath("minecraft", "container/slot/ingot");
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;

    private static final Component COPPER_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", resourceLocation("copper_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component COPPER_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.copper_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component COPPER_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.copper_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component COPPER_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.copper_upgrade.base_slot_description")));
    private static final Component COPPER_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.copper_upgrade.additions_slot_description")));

    private static final Component IRON_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", resourceLocation("iron_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component IRON_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.iron_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component IRON_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.iron_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component IRON_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.iron_upgrade.base_slot_description")));
    private static final Component IRON_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.iron_upgrade.additions_slot_description")));

    private static final Component GOLD_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", resourceLocation("gold_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component GOLD_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.gold_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component GOLD_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.gold_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component GOLD_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.gold_upgrade.base_slot_description")));
    private static final Component GOLD_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.gold_upgrade.additions_slot_description")));

    private static final Component DIAMOND_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", resourceLocation("diamond_upgrade"))).withStyle(TITLE_FORMAT);
    private static final Component DIAMOND_UPGRADE_APPLIES_TO = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.diamond_upgrade.applies_to"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component DIAMOND_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.diamond_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component DIAMOND_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.diamond_upgrade.base_slot_description")));
    private static final Component DIAMOND_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", resourceLocation("smithing_template.diamond_upgrade.additions_slot_description")));

    public static SmithingTemplateItem createCopperUpgradeTemplate() {
        return new SmithingTemplateItem(COPPER_UPGRADE_APPLIES_TO, COPPER_UPGRADE_INGREDIENTS, COPPER_UPGRADE, COPPER_UPGRADE_BASE_SLOT_DESCRIPTION, COPPER_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createUpgradeIconList(), createUpgradeMaterialList());
    }

    public static SmithingTemplateItem createIronUpgradeTemplate() {
        return new SmithingTemplateItem(IRON_UPGRADE_APPLIES_TO, IRON_UPGRADE_INGREDIENTS, IRON_UPGRADE, IRON_UPGRADE_BASE_SLOT_DESCRIPTION, IRON_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createUpgradeIconList(), createUpgradeMaterialList());
    }

    public static SmithingTemplateItem createGoldUpgradeTemplate() {
        return new SmithingTemplateItem(GOLD_UPGRADE_APPLIES_TO, GOLD_UPGRADE_INGREDIENTS, GOLD_UPGRADE, GOLD_UPGRADE_BASE_SLOT_DESCRIPTION, GOLD_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createUpgradeIconList(), createUpgradeMaterialList());
    }

    public static SmithingTemplateItem createDiamondUpgradeTemplate() {
        return new SmithingTemplateItem(DIAMOND_UPGRADE_APPLIES_TO, DIAMOND_UPGRADE_INGREDIENTS, DIAMOND_UPGRADE, DIAMOND_UPGRADE_BASE_SLOT_DESCRIPTION, DIAMOND_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createUpgradeIconList(), createUpgradeMaterialList());
    }

    private static ResourceLocation resourceLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    private static List<ResourceLocation> createUpgradeIconList() {
        return List.of(EMPTY_SLOT_BACKPACK);
    }

    private static List<ResourceLocation> createUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
}
