package com.neoloxal.handcrafted_tools.component;

import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.inventory.tooltip.TooltipComponent;

public record OutlineComponent(FormattedCharSequence text, int outlineColor) implements TooltipComponent {
}
