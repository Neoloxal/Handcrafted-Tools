package com.neoloxal.handcrafted_tools.data_components;

import com.mojang.serialization.Codec;
import com.neoloxal.handcrafted_tools.HandcraftedTools;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModDataComponents {
    private static final DeferredRegister.DataComponents DATA_COMPONENTS =
            DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, HandcraftedTools.MODID);

    public static DeferredHolder<DataComponentType<?>, DataComponentType<Boolean>> LOOT = DATA_COMPONENTS.registerComponentType(
            "loot",
            builder -> builder.persistent(Codec.BOOL).networkSynchronized(ByteBufCodecs.BOOL)
    );

    public static void register(IEventBus eventBus) {
        DATA_COMPONENTS.register(eventBus);
    }
}
