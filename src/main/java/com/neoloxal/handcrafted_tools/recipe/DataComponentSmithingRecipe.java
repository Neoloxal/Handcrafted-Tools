package com.neoloxal.handcrafted_tools.recipe;

import com.neoloxal.handcrafted_tools.HandcraftedTools;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import java.util.stream.Stream;

public class DataComponentSmithingRecipe implements SmithingRecipe {
    private final Ingredient template;
    private final Ingredient base;
    private final Ingredient addition;
    private final DataComponentPatch dataComponentPatch;

    public DataComponentSmithingRecipe(Ingredient template, Ingredient base, Ingredient addition, DataComponentPatch dataComponentPatch) {
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
    public boolean matches(SmithingRecipeInput input, Level level) {
        return this.template.test(input.template()) && this.base.test(input.base()) && this.addition.test(input.addition());
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput input, HolderLookup.Provider registries) {
        ItemStack output = input.base().copy();
        output.applyComponents(dataComponentPatch);
        return output;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        // Placeholder item for JEI
        return new ItemStack(HandcraftedTools.MOD_ITEMS.getItem("unbreaking_blueprint"));
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

    @Override
    public boolean isTemplateIngredient(ItemStack stack) {
        return this.template.test(stack);
    }

    @Override
    public boolean isBaseIngredient(ItemStack stack) {
        return this.base.test(stack);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack stack) {
        return this.addition.test(stack);
    }

    @Override
    public boolean isIncomplete() {
        return Stream.of(this.template, this.base, this.addition).anyMatch(Ingredient::hasNoItems);
    }
}
