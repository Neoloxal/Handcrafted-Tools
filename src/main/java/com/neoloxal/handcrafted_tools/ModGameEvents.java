package com.neoloxal.handcrafted_tools;

import com.neoloxal.handcrafted_tools.data_components.ModDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

import java.util.List;

public class ModGameEvents {
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        List<Component> tooltip = event.getToolTip();
        ItemStack stack = event.getItemStack();

        if (stack.has(ModDataComponents.LOOT.get())) {
            ChatFormatting star = Boolean.TRUE.equals(stack.get(ModDataComponents.LOOT.get()))
                    ? ChatFormatting.GOLD
                    : ChatFormatting.GRAY;
            tooltip.set(0, tooltip.getFirst().copy().append(" ").append(Component.translatable("item.handcrafted_tools.loot").withStyle(star)));
        }
    }
}
