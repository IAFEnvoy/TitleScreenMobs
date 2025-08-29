package com.iafenvoy.tsm;

import com.iafenvoy.tsm.config.TsmConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

public class ToastHelper {
    public static void sendWarningWithCheck() {
        if (TsmConfig.INSTANCE.generals.sendWarning.getValue())
            try {
                TitleScreenMobs.LOGGER.error("You can disable in-game warning in config");
                MinecraftClient.getInstance().getToastManager().add(new SystemToast(SystemToast.Type.PACK_LOAD_FAILURE, Text.translatable("toast.title_screen_mobs.warning.title"), Text.translatable("toast.title_screen_mobs.warning.description")));
            } catch (Exception ignored) {
            }
    }
}
