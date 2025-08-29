package com.iafenvoy.tsm;

import com.iafenvoy.jupiter.ConfigManager;
import com.iafenvoy.tsm.config.TsmConfig;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

public final class TitleScreenMobs {
    public static final String MOD_ID = "title_screen_mobs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void onInitializeClient() {
        ConfigManager.getInstance().registerConfigHandler(TsmConfig.INSTANCE);
    }
}
