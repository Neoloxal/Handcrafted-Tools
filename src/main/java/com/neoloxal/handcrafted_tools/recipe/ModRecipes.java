package com.neoloxal.handcrafted_tools.recipe;

import com.neoloxal.handcrafted_tools.HandcraftedTools;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, HandcraftedTools.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<DataComponentSmithingRecipe>> DATA_COMPONENT_SMITHING_RECIPE_SERIALIZER =
            SERIALIZERS.register("data_component_smithing_recipe", DataComponentSmithingRecipeSerializer::new);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
