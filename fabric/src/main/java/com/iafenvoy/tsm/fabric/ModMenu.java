package com.iafenvoy.tsm.fabric;

import com.iafenvoy.jupiter.render.screen.ClientConfigScreen;
import com.iafenvoy.tsm.config.TsmConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class ModMenu implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> new ClientConfigScreen(parent, TsmConfig.INSTANCE);
    }
}
