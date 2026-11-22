package com.neoloxal.handcrafted_tools;

import com.neoloxal.handcrafted_tools.data_components.ModDataComponents;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.GrindstoneEvent;

public class ModGameEvents {
    @SubscribeEvent
    public static void onGrind(GrindstoneEvent.OnPlaceItem event) {
        ItemStack topItem = event.getTopItem();
        ItemStack item = !topItem.isEmpty() ? topItem.copy() : event.getBottomItem().copy();
        if (item.getOrDefault(ModDataComponents.LOOT.get(), false)) {
            item.remove(ModDataComponents.LOOT.get());
            event.setOutput(item);
        }
    }
}
