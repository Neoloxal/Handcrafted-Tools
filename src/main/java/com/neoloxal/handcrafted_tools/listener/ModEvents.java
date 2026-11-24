package com.neoloxal.handcrafted_tools.listener;

import com.neoloxal.handcrafted_tools.HandcraftedTools;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

public class ModEvents {
    @SubscribeEvent
    public static void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            ItemStack unbreakingBlueprint = HandcraftedTools.MOD_ITEMS.getItem("unbreaking_blueprint").getDefaultInstance();
            ItemStack enchantedUnbreakingBlueprint = HandcraftedTools.MOD_ITEMS.getItem("enchanted_unbreaking_blueprint").getDefaultInstance();

            event.insertAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE.getDefaultInstance(), unbreakingBlueprint, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
            event.insertAfter(unbreakingBlueprint, enchantedUnbreakingBlueprint, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
        }
    }
}
