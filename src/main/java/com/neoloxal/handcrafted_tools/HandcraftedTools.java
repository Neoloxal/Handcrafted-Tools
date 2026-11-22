package com.neoloxal.handcrafted_tools;

import com.mojang.logging.LogUtils;
import com.neoloxal.handcrafted_tools.data_components.ModDataComponents;
import com.neoloxal.handcrafted_tools.datagen.ModBlockTagsProvider;
import com.neoloxal.handcrafted_tools.datagen.ModDataGen;
import com.neoloxal.handcrafted_tools.datagen.ModItemTagsProvider;
import com.neoloxal.handcrafted_tools.datagen.ModRecipeProvider;
import com.neoloxal.handcrafted_tools.item.ModItems;
import com.neoloxal.handcrafted_tools.loot.ModLootModifiers;
import com.neoloxal.handcrafted_tools.recipe.ModRecipes;
import com.neoloxal.paint_palette_lib.Palette;
import com.neoloxal.paint_palette_lib.PaletteMod;
import com.neoloxal.paint_palette_lib.PaletteUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(HandcraftedTools.MODID)
public class HandcraftedTools {
    public static final String MODID = "handcrafted_tools";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final ModItems MOD_ITEMS = new ModItems();

    public HandcraftedTools(IEventBus modEventBus, ModContainer modContainer) {
        Palette.registerMod(new PaletteMod(modContainer));

        ModDataComponents.register(modEventBus);
        MOD_ITEMS.register(modEventBus);
        ModRecipes.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        modEventBus.register(ModDataGen.class);
        PaletteUtils.Canvas.createRecipeGenerator(MODID, ModRecipeProvider::new);
        PaletteUtils.Canvas.createTagsGenerator(MODID, ModBlockTagsProvider::new, ModItemTagsProvider::new);

        NeoForge.EVENT_BUS.register(ModGameEvents.class);
        modEventBus.register(ModEvents.class);
    }

    @Mod(value = MODID, dist = Dist.CLIENT)
    public static class Client {
        public Client(IEventBus modEventBus, ModContainer modContainer) {
            modEventBus.register(ClientModEvents.class);
            NeoForge.EVENT_BUS.register(ClientGameEvents.class);
        }
    }
}
