package com.iafenvoy.tsm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;

public class ToastHelper {
    public static void sendWarningWithCheck() {
        if (TSMConfig.INSTANCE.general.sendWarning.getValue())
            try {
                TitleScreenMobs.LOGGER.error("You can disable in-game warning in config");
                Minecraft.getInstance()./*? >=26.2 {*//*gui.toastManager*//*?} else {*/getToastManager/*?}*/().addToast(new SystemToast(SystemToast.SystemToastId.PACK_LOAD_FAILURE, Component.translatable("toast.title_screen_mobs.warning.title"), Component.translatable("toast.title_screen_mobs.warning.description")));
            } catch (Exception ignored) {
            }
    }
}
