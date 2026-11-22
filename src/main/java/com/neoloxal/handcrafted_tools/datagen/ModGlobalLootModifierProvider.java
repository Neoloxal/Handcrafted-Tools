package com.neoloxal.handcrafted_tools.datagen;

import com.neoloxal.handcrafted_tools.HandcraftedTools;
import com.neoloxal.handcrafted_tools.loot.AddItemModifier;
import com.neoloxal.handcrafted_tools.loot.LootComponentTableLootModifier;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.packs.VanillaChestLoot;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, HandcraftedTools.MODID);
    }

    @Override
    protected void start() {
        add("add_loot_component_to_all",
                new LootComponentTableLootModifier(new LootItemCondition[]{}));

        add("add_unbreaking_blueprints_to_structures",
                new AddItemModifier(new LootItemCondition[] {
                        getIds(),
                        LootItemRandomChanceCondition.randomChance(0.25f).build()
                }, HandcraftedTools.MOD_ITEMS.getItem("unbreaking_blueprint")));
    }

    private LootItemCondition.Builder tableIdConditionFromKey(ResourceKey<LootTable> key) {
        return new LootTableIdCondition.Builder(key.location());
    }

    private LootItemCondition getIds() {
        ArrayList<ResourceKey<LootTable>> keys = new ArrayList<>();
        keys.add(BuiltInLootTables.ANCIENT_CITY);
        keys.add(BuiltInLootTables.END_CITY_TREASURE);
        keys.add(BuiltInLootTables.BASTION_TREASURE);
        keys.add(BuiltInLootTables.DESERT_PYRAMID);
        keys.add(BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY);
        keys.add(BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_RARE);

        LootItemCondition.Builder builder = tableIdConditionFromKey(keys.getFirst());
        keys.removeFirst();

        for (ResourceKey key : keys) {
            builder = builder.or(tableIdConditionFromKey(key));
        }
        return builder.build();
    }
}
