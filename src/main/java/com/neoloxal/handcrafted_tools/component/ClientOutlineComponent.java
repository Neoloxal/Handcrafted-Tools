package com.neoloxal.handcrafted_tools.component;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix4f;

public class ClientOutlineComponent implements ClientTooltipComponent {
    private final FormattedCharSequence text;
    private final int outlineColor;

    public ClientOutlineComponent(OutlineComponent component) {
        this.text = component.text();
        this.outlineColor = component.outlineColor();
    }

    @Override
    public int getHeight() {
        return 10;
    }

    @Override
    public int getWidth(Font font) {
        return font.width(this.text);
    }

    @Override
    public void renderText(Font font, int mouseX, int mouseY, Matrix4f matrix, MultiBufferSource.BufferSource bufferSource) {
        font.drawInBatch8xOutline(
                this.text,
                (float) mouseX, (float) mouseY,
                -1, this.outlineColor,
                matrix, bufferSource,
                15728880 // Fullbright
        );
    }
}
