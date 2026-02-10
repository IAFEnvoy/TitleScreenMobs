package com.iafenvoy.tsm;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.network.chat.Component;

public class ToastHelper {
    public static void sendWarningWithCheck() {
        if (TSMConfig.INSTANCE.general.sendWarning.getValue())
            try {
                TitleScreenMobs.LOGGER.error("You can disable in-game warning in config");
                Minecraft.getInstance()./*? >=1.21.2 {*//*getToastManager*//*?} else {*/getToasts/*?}*/().addToast(new SystemToast(SystemToast./*? >=1.20.2 {*//*SystemToastId*//*?} else {*/SystemToastIds/*?}*/.PACK_LOAD_FAILURE,  Component.translatable("toast.title_screen_mobs.warning.title"), Component.translatable("toast.title_screen_mobs.warning.description")));
            } catch (Exception ignored) {
            }
    }
}
