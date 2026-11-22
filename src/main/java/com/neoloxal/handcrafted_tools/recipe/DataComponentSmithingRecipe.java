package com.neoloxal.handcrafted_tools.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;

public class DataComponentSmithingRecipe extends SmithingTransformRecipe {
    private final Ingredient template;
    private final Ingredient base;
    private final Ingredient addition;
    private final DataComponentPatch dataComponentPatch;

    public DataComponentSmithingRecipe(Ingredient template, Ingredient base, Ingredient addition, DataComponentPatch dataComponentPatch) {
        super(template, base, addition, ItemStack.EMPTY);
        this.template = template;
        this.base = base;
        this.addition = addition;
        this.dataComponentPatch = dataComponentPatch;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.DATA_COMPONENT_SMITHING_RECIPE_SERIALIZER.get();
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries) {
        ItemStack output = input.base().copy();
        output.applyComponents(dataComponentPatch);
        return output;
    }

    public Ingredient getTemplate() {
        return template;
    }

    public Ingredient getBase() {
        return base;
    }

    public Ingredient getAddition() {
        return addition;
    }

    public DataComponentPatch getDataComponentPatch() {
        return dataComponentPatch;
    }
}
