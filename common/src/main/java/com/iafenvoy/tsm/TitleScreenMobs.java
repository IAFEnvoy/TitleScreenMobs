package com.iafenvoy.tsm;

import com.iafenvoy.tsm.config.TsmConfig;
import com.mojang.logging.LogUtils;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import org.slf4j.Logger;

public final class TitleScreenMobs {
    public static final String MOD_ID = "title_screen_mobs";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void onInitializeClient() {
        AutoConfig.register(TsmConfig.class, GsonConfigSerializer::new);
    }
}
