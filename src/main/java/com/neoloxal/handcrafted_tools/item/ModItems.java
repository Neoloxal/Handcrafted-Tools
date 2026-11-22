package com.neoloxal.handcrafted_tools.item;

import com.neoloxal.handcrafted_tools.HandcraftedTools;
import com.neoloxal.paint_palette_lib.PaletteUtils;
import com.neoloxal.paint_palette_lib.registrar.ItemRegistrar;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;

public class ModItems extends ItemRegistrar {
    public ModItems() {
        super(HandcraftedTools.MODID);
    }

    @Override
    protected void registerItems() {
        basicItem("unbreaking_blueprint", () -> new Item(new Item.Properties()));
        PaletteUtils.Canvas.generateItemModel(getItemDeferred("unbreaking_blueprint"));

        basicItem("enchanted_unbreaking_blueprint", () -> new EnchantedUnbreakingBlueprint(new Item.Properties()
                .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));
        PaletteUtils.Canvas.generateItemModel(getItemDeferred("enchanted_unbreaking_blueprint"));
    }
}
