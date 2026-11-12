package com.iafenvoy.tsm.mixin;

import com.iafenvoy.tsm.RenderHelper;
import com.iafenvoy.tsm.TitleScreenMobs;
import com.iafenvoy.tsm.ToastHelper;
import com.iafenvoy.tsm.TSMConfig;
import com.iafenvoy.tsm.cursed.CursedLocalPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "<init>*", at = @At("RETURN"))
    private void resetEntity(CallbackInfo ci) {
        RenderHelper.livingEntity = null;
    }

    @Inject(method = "extractRenderState", at = @At("RETURN"))
    private void onRender(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        TitleScreen sc = (TitleScreen) (Object) this;
        TSMConfig config = TSMConfig.INSTANCE;
        if (Minecraft.getInstance() != null) {
            if (config.general.leftVisible.getValue() && RenderHelper.enableLeft)
                try {
                    int x = sc.width / 2 - 160 + config.general.leftXOffset.getValue(), y = sc.height / 4 + 100 + config.general.leftYOffset.getValue(), a = config.general.leftScale.getValue() * 3;
                    InventoryScreen.extractEntityInInventoryFollowsMouse(context, x - a, y - a, x + a, y + a, config.general.leftScale.getValue(), 0, mouseX, mouseY, CursedLocalPlayer.INSTANCE);
                } catch (Throwable e) {
                    RenderHelper.enableLeft = false;
                    TitleScreenMobs.LOGGER.error("Failed to render player on title screen, disabling", e);
                    ToastHelper.sendWarningWithCheck();
                }
            if (RenderHelper.livingEntity != null && config.general.rightVisible.getValue() && RenderHelper.enableRight) {
                try {
                    int x = sc.width / 2 + 160 + config.general.rightXOffset.getValue(), y = sc.height / 4 + 100 + config.general.rightYOffset.getValue(), a = config.general.rightScale.getValue() * 3;
                    InventoryScreen.extractEntityInInventoryFollowsMouse(context, x - a, y - a, x + a, y + a, config.general.rightScale.getValue(), 0, mouseX, mouseY, RenderHelper.livingEntity);
                } catch (Throwable e) {
                    RenderHelper.livingEntity = null;
                    RenderHelper.enableRight = false;
                    TitleScreenMobs.LOGGER.error("Failed to render entity on title screen, disabling", e);
                    ToastHelper.sendWarningWithCheck();
                }
            }
        }
    }
}
