package com.neoloxal.handcrafted_tools.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.neoloxal.handcrafted_tools.data_components.ModDataComponents;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(GrindstoneMenu.class)
public class GrindstoneMenuMixin {
    @ModifyReturnValue(
            method = "computeResult",
            at = @At(value = "RETURN", ordinal = 1)
    )
    private ItemStack computeResultMixin(ItemStack original) {
        return original.getOrDefault(ModDataComponents.LOOT.get(), false)
                ? handcraftedTools$removeLootTag(original)
                : original;
    }

    @Unique
    private ItemStack handcraftedTools$removeLootTag(ItemStack stack) {
        stack.remove(ModDataComponents.LOOT.get());
        return stack;
    }
}
