package com.neoloxal.handcrafted_tools.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class DataComponentSmithingRecipeSerializer implements RecipeSerializer<DataComponentSmithingRecipe> {
    public static final MapCodec<DataComponentSmithingRecipe> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
            Ingredient.CODEC.fieldOf("template").forGetter(DataComponentSmithingRecipe::getTemplate),
            Ingredient.CODEC.fieldOf("base").forGetter(DataComponentSmithingRecipe::getBase),
            Ingredient.CODEC.fieldOf("addition").forGetter(DataComponentSmithingRecipe::getAddition),
            DataComponentPatch.CODEC.fieldOf("data_component_patch").forGetter(DataComponentSmithingRecipe::getDataComponentPatch)
        ).apply(instance, DataComponentSmithingRecipe::new)
    );

    public static final StreamCodec<RegistryFriendlyByteBuf, DataComponentSmithingRecipe> STREAM_CODEC = StreamCodec.of(
            (buffer, recipe) -> {
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getTemplate());
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getBase());
                Ingredient.CONTENTS_STREAM_CODEC.encode(buffer, recipe.getAddition());
                DataComponentPatch.STREAM_CODEC.encode(buffer, recipe.getDataComponentPatch());
            },
            buffer -> {
                Ingredient template = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
                Ingredient base = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
                Ingredient addition = Ingredient.CONTENTS_STREAM_CODEC.decode(buffer);
                DataComponentPatch dataComponentPatch = DataComponentPatch.STREAM_CODEC.decode(buffer);
                return new DataComponentSmithingRecipe(template, base, addition, dataComponentPatch);
            }
    );

    @Override
    public MapCodec codec() {
        return CODEC;
    }

    @Override
    public StreamCodec streamCodec() {
        return STREAM_CODEC;
    }
}
