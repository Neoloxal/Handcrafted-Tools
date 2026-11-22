package com.neoloxal.handcrafted_tools.datagen;

import com.neoloxal.handcrafted_tools.HandcraftedTools;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        super.buildRecipes(recipeOutput);

        Item unbreakableBlueprint = HandcraftedTools.MOD_ITEMS.getItem("unbreaking_blueprint");
        Item enchantedUnbreakableBlueprint = HandcraftedTools.MOD_ITEMS.getItem("enchanted_unbreaking_blueprint");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, new ItemStack(unbreakableBlueprint, 2))
                .pattern("DBD")
                .pattern("DLD")
                .pattern("DDD")
                .define('D', Items.DIAMOND).define('L', Items.LAPIS_BLOCK).define('B', unbreakableBlueprint)
                .unlockedBy("has_blueprint", has(unbreakableBlueprint)).save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, enchantedUnbreakableBlueprint)
                .pattern("lLl")
                .pattern("LBL")
                .pattern("lLl")
                .define('l', Items.LAPIS_LAZULI).define('L', Items.LAPIS_BLOCK).define('B', unbreakableBlueprint)
                .unlockedBy("has_blueprint", has(unbreakableBlueprint)).save(recipeOutput);
    }
}
