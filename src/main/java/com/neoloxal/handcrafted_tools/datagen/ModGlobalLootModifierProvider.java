package com.neoloxal.handcrafted_tools.datagen;

import com.neoloxal.handcrafted_tools.HandcraftedTools;
import com.neoloxal.handcrafted_tools.loot_table.LootComponentTableLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, HandcraftedTools.MODID);
    }

    @Override
    protected void start() {
        add("add_loot_component",
                new LootComponentTableLootModifier(new LootItemCondition[]{}));
    }
}
