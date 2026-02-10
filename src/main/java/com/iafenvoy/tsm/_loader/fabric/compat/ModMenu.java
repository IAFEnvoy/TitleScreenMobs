package com.iafenvoy.tsm._loader.fabric.compat;

//? fabric {
import com.iafenvoy.jupiter.render.screen.JupiterScreen;
import com.iafenvoy.tsm.TSMConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> JupiterScreen.getConfigScreen(parent, TSMConfig.INSTANCE, true);
    }
}
