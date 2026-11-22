package com.neoloxal.handcrafted_tools;

import com.mojang.logging.LogUtils;
import com.neoloxal.handcrafted_tools.data_components.ModDataComponents;
import com.neoloxal.handcrafted_tools.datagen.ModDataGen;
import com.neoloxal.handcrafted_tools.loot_table.ModGlobalLootModifierSerializers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(HandcraftedTools.MODID)
public class HandcraftedTools {
    public static final String MODID = "handcrafted_tools";
    private static final Logger LOGGER = LogUtils.getLogger();

    public HandcraftedTools(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(ModGameEvents.class);

        ModDataComponents.register(modEventBus);
        ModGlobalLootModifierSerializers.register(modEventBus);

        modEventBus.register(ModDataGen.class);
    }
}
