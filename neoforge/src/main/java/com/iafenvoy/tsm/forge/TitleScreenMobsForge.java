package com.iafenvoy.tsm.forge;

import com.iafenvoy.jupiter.render.screen.ClientConfigScreen;
import com.iafenvoy.tsm.RenderHelper;
import com.iafenvoy.tsm.TitleScreenMobs;
import com.iafenvoy.tsm.config.TsmConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(TitleScreenMobs.MOD_ID)
@EventBusSubscriber(Dist.CLIENT)
public final class TitleScreenMobsForge {
    public TitleScreenMobsForge() {
        TitleScreenMobs.onInitializeClient();
        RenderHelper.isNeoForge = true;
    }

    @SubscribeEvent
    public static void initClient(FMLClientSetupEvent event) {
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (client, parent) -> new ClientConfigScreen(parent, TsmConfig.INSTANCE));
    }
}
