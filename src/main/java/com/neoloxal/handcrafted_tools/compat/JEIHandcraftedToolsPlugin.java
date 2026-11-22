package com.neoloxal.handcrafted_tools.compat;

import com.mojang.logging.LogUtils;
import com.neoloxal.handcrafted_tools.HandcraftedTools;
import com.neoloxal.handcrafted_tools.recipe.DataComponentSmithingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

@JeiPlugin
public class JEIHandcraftedToolsPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(HandcraftedTools.MODID, "jei_plugin");
    }

    @Override
    public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration) {
        IModPlugin.super.registerVanillaCategoryExtensions(registration);
        registration.getSmithingCategory().addExtension(DataComponentSmithingRecipe.class, new DataComponentSmithingExtension());
    }
}
