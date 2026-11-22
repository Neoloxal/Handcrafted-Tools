package com.neoloxal.handcrafted_tools;

import com.mojang.datafixers.util.Either;
import com.neoloxal.handcrafted_tools.component.OutlineComponent;
import com.neoloxal.handcrafted_tools.data_components.ModDataComponents;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderTooltipEvent;

import java.util.List;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class ClientGameEvents {
    @SubscribeEvent
    public static void modifyTooltip(RenderTooltipEvent.GatherComponents event) {
        ItemStack stack = event.getItemStack();
        if (stack.has(ModDataComponents.LOOT.get())) {
            List<Either<FormattedText, TooltipComponent>> list = event.getTooltipElements();
            if (list.isEmpty()) return;

            Either<FormattedText, TooltipComponent> original = list.getFirst();
            Optional<FormattedText> left = original.left();

            if (left.isPresent()) {
                list.set(0, Either.right(new OutlineComponent(
                        Language.getInstance().getVisualOrder(left.get()),
                        Boolean.TRUE.equals(
                                stack.get(ModDataComponents.LOOT.get()))
                                ? 0x6800fa
                                : 0x5a5c51
                )));
            }
        }
    }
}
