package com.neoloxal.handcrafted_tools.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.neoloxal.handcrafted_tools.data_components.ModDataComponents;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class LootComponentTableLootModifier extends LootModifier {
    public static final MapCodec<LootComponentTableLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance ->
            codecStart(instance).apply(instance, LootComponentTableLootModifier::new));

    public LootComponentTableLootModifier(LootItemCondition[] lootItemConditions) {
        super(lootItemConditions);
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.forEach(stack -> {
            if (stack.is(Tags.Items.TOOLS) || stack.is(Tags.Items.ARMORS)) {
                stack.set(ModDataComponents.LOOT.get(), true);
                stack.setDamageValue(0);
                EnchantmentHelper.updateEnchantments(stack, mutable -> {
                    removeEnchantment(mutable, Enchantments.UNBREAKING, context.getLevel());
                    removeEnchantment(mutable, Enchantments.MENDING, context.getLevel());
                });
            }
        });
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return ModLootModifiers.LOOT_COMPONENT_TABLE_LOOT.get();
    }

    private static void removeEnchantment(ItemEnchantments.Mutable mutable, ResourceKey<Enchantment> enchantment, Level level) {
        mutable.set(level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(enchantment), 0);
    }
}
