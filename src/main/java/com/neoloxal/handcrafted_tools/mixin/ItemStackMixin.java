package com.neoloxal.handcrafted_tools.mixin;

import com.neoloxal.handcrafted_tools.data_components.ModDataComponents;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow @Final private PatchedDataComponentMap components;

    @Shadow public abstract Item getItem();

    @Inject(
            method = "hurtAndBreak(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/entity/EquipmentSlot;)V",
            at = @At("HEAD"),
            cancellable = true
    )
    private void hurtAndBreakMixin(int amount, LivingEntity entity, EquipmentSlot slot, CallbackInfo ci) {
        if (this.components.has(ModDataComponents.LOOT.get())) {
            ci.cancel();
        }
    }
}
