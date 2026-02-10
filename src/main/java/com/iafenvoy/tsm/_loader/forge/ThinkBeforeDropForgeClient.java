package com.iafenvoy.tsm._loader.forge;

//? forge {
/*import com.iafenvoy.jupiter.ConfigManager;
import com.iafenvoy.jupiter.render.screen.ConfigContainerScreen;
import com.iafenvoy.thinkbeforedrop.TBDConfig;
import com.iafenvoy.thinkbeforedrop.ThinkBeforeDrop;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod(TitleScreenMobs.MOD_ID)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ThinkBeforeDropForgeClient {
    @SuppressWarnings("removal")
    @SubscribeEvent
    public static void processClient(FMLClientSetupEvent event) {
        ConfigManager.getInstance().registerConfigHandler(TBDConfig.INSTANCE);
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((minecraft, parent) -> new ConfigContainerScreen(parent, TBDConfig.INSTANCE, true)));
    }
}
*/