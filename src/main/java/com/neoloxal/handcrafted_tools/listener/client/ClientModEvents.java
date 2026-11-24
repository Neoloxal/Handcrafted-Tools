package com.neoloxal.handcrafted_tools.listener.client;

import com.neoloxal.handcrafted_tools.component.ClientOutlineComponent;
import com.neoloxal.handcrafted_tools.component.OutlineComponent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;

@OnlyIn(Dist.CLIENT)
public class ClientModEvents {
    @SubscribeEvent
    public static void registerComponents(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(OutlineComponent.class, ClientOutlineComponent::new);
    }
}
