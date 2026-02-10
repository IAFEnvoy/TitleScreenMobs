package com.iafenvoy.tsm._loader.neoforge;

//? neoforge {
/*import com.iafenvoy.jupiter.ConfigManager;
import com.iafenvoy.jupiter.render.screen.ConfigContainerScreen;
import com.iafenvoy.tsm.TSMConfig;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
//? >=1.20.5 {
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
//?} else {
/^import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler;
^///?}

//? >=1.21 {
@EventBusSubscriber(Dist.CLIENT)
//?} elif >=1.20.5 {
/^@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
 ^///?} else {
/^@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
 ^///?}
public class TitleScreenMobsNeoForgeClient {
    @SubscribeEvent
    public static void processClient(FMLClientSetupEvent event) {
        ConfigManager.getInstance().registerConfigHandler(TSMConfig.INSTANCE);
        ModLoadingContext.get().registerExtensionPoint(/^? >=1.20.5 {^/IConfigScreenFactory/^?} else {^//^ConfigScreenHandler.ConfigScreenFactory^//^?}^/.class, () -> /^? <=1.20.4 {^//^new ConfigScreenHandler.ConfigScreenFactory^//^?}^/((minecraft, parent) -> new ConfigContainerScreen(parent, TSMConfig.INSTANCE, true)));
    }
}

*/