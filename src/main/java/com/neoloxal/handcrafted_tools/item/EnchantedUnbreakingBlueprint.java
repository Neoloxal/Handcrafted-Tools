package com.neoloxal.handcrafted_tools.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class EnchantedUnbreakingBlueprint extends Item {
    public EnchantedUnbreakingBlueprint(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        tooltipComponents.add(Component.translatable("item.minecraft.smithing_template.applies_to").withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(CommonComponents.space().append(Component.translatable("item.handcrafted_tools.enchanted_unbreaking_blueprint.applies_to").withStyle(ChatFormatting.BLUE)));
        tooltipComponents.add(Component.translatable("item.minecraft.smithing_template.ingredients").withStyle(ChatFormatting.GRAY));
        tooltipComponents.add(CommonComponents.space().append(Component.translatable("item.handcrafted_tools.enchanted_unbreaking_blueprint.ingredients").withStyle(ChatFormatting.BLUE)));
    }
}
