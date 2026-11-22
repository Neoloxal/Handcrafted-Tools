package com.neoloxal.handcrafted_tools.compat;

import com.neoloxal.handcrafted_tools.recipe.DataComponentSmithingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IIngredientAcceptor;
import mezz.jei.api.recipe.category.extensions.vanilla.smithing.ISmithingCategoryExtension;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.level.Level;

public class DataComponentSmithingExtension implements ISmithingCategoryExtension<DataComponentSmithingRecipe> {
    @Override
    public <T extends IIngredientAcceptor<T>> void setTemplate(DataComponentSmithingRecipe recipe, T ingredientAcceptor) {
        ingredientAcceptor.addIngredients(recipe.getTemplate());
    }

    @Override
    public <T extends IIngredientAcceptor<T>> void setBase(DataComponentSmithingRecipe recipe, T ingredientAcceptor) {
        ingredientAcceptor.addIngredients(recipe.getBase());
    }

    @Override
    public <T extends IIngredientAcceptor<T>> void setAddition(DataComponentSmithingRecipe recipe, T ingredientAcceptor) {
        ingredientAcceptor.addIngredients(recipe.getAddition());
    }

    @Override
    public <T extends IIngredientAcceptor<T>> void setOutput(DataComponentSmithingRecipe recipe, T ingredientAcceptor) {
        Level level = Minecraft.getInstance().level;
        ItemStack[] templates = recipe.getTemplate().getItems();
        ItemStack[] additions = recipe.getAddition().getItems();

        if (level != null && templates.length > 0 && additions.length > 0) {
            for (ItemStack base : recipe.getBase().getItems()) {
                ingredientAcceptor.addIngredient(VanillaTypes.ITEM_STACK, recipe.assemble(new SmithingRecipeInput(
                        templates[0],
                        base,
                        additions[0]
                ), level.registryAccess()));
            }
        }
    }
}
