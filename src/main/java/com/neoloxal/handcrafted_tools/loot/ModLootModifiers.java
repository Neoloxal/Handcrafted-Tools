package com.neoloxal.handcrafted_tools.loot;

import com.mojang.serialization.MapCodec;
import com.neoloxal.handcrafted_tools.HandcraftedTools;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModLootModifiers {
    private static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS
            = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, HandcraftedTools.MODID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> LOOT_COMPONENT_TABLE_LOOT =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("loot_component_table_loot", () -> LootComponentTableLootModifier.CODEC);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
            GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);

    public static void register(IEventBus eventBus) {
        GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
