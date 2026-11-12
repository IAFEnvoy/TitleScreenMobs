package com.iafenvoy.tsm._loader.neoforge;

import com.iafenvoy.jupiter.ConfigManager;
import com.iafenvoy.jupiter.render.screen.JupiterScreen;
import com.iafenvoy.tsm.TSMConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@EventBusSubscriber(Dist.CLIENT)
public class TitleScreenMobsNeoForgeClient {
    @SubscribeEvent
    public static void processClient(FMLClientSetupEvent event) {
        ConfigManager.getInstance().registerConfigHandler(TSMConfig.INSTANCE);
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (_, parent) -> JupiterScreen.getConfigScreen(parent, TSMConfig.INSTANCE, true));
    }
}
