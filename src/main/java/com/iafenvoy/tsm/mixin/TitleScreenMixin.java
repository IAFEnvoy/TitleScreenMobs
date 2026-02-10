package com.iafenvoy.tsm.mixin;

import com.iafenvoy.tsm.RenderHelper;
import com.iafenvoy.tsm.TitleScreenMobs;
import com.iafenvoy.tsm.ToastHelper;
import com.iafenvoy.tsm.TSMConfig;
import com.iafenvoy.tsm.cursed.CursedLocalPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
//? >=1.21.9 {
import net.minecraft.client.input.MouseButtonEvent;
//?}
//? >=1.20 {
import net.minecraft.client.gui.GuiGraphics;
//?} else {
/*import com.mojang.blaze3d.vertex.PoseStack;
*///?}
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "<init>()V", at = @At("RETURN"))
    private void resetEntity(CallbackInfo ci) {
        RenderHelper.livingEntity = null;
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void onRender(/*? >=1.20 {*/GuiGraphics/*?} else {*//*PoseStack*//*?}*/ context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        TitleScreen sc = (TitleScreen) (Object) this;
        TSMConfig config = TSMConfig.INSTANCE;
        if (Minecraft.getInstance() != null) {
            if (config.general.leftVisible.getValue() && RenderHelper.enableLeft)
                try {
                    int x = sc.width / 2 - 160 + config.general.leftXOffset.getValue(), y = sc.height / 4 + 100 + config.general.leftYOffset.getValue(), a = config.general.leftScale.getValue() * 3;
                    InventoryScreen.renderEntityInInventoryFollowsMouse(context,/*? >=1.20.2 {*/ x - a, y - a, x + a, y + a,/*?} else {*/ /*x, y,*//*?}*/ config.general.leftScale.getValue(),/*? >=1.20.2 {*/ 0, mouseX, mouseY,/*?} else {*//* -mouseX + x, -mouseY + y - 30,*//*?}*/ CursedLocalPlayer.INSTANCE);
                } catch (Throwable e) {
                    RenderHelper.enableLeft = false;
                    TitleScreenMobs.LOGGER.error("Failed to render player on title screen, disabling", e);
                    ToastHelper.sendWarningWithCheck();
                }
            if (RenderHelper.livingEntity != null && config.general.rightVisible.getValue() && RenderHelper.enableRight) {
                try {
                    int x = sc.width / 2 + 160 + config.general.rightXOffset.getValue(), y = sc.height / 4 + 100 + config.general.rightYOffset.getValue(), a = config.general.rightScale.getValue() * 3;
                    InventoryScreen.renderEntityInInventoryFollowsMouse(context,/*? >=1.20.2 {*/ x - a, y - a, x + a, y + a,/*?} else {*/ /*x, y,*//*?}*/ config.general.rightScale.getValue(),/*? >=1.20.2 {*/ 0, mouseX, mouseY,/*?} else {*//* -mouseX + x, -mouseY + y - 30,*//*?}*/ RenderHelper.livingEntity);
                } catch (Throwable e) {
                    RenderHelper.livingEntity = null;
                    RenderHelper.enableRight = false;
                    TitleScreenMobs.LOGGER.error("Failed to render entity on title screen, disabling", e);
                    ToastHelper.sendWarningWithCheck();
                }
            }
        }
    }

    @Inject(method = "mouseClicked", at = @At("RETURN"))
            //? >=1.21.9 {
    private void handleFoxClick(MouseButtonEvent event, boolean isDoubleClick, CallbackInfoReturnable<Boolean> cir) {
        double mouseX = event.x(), mouseY = event.y();
        //?} else {
    /*private void handleFoxClick(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        *///?}
        TitleScreen sc = (TitleScreen) (Object) this;
        if (Minecraft.getInstance() != null) {
            int height = sc.height / 4 + 132;
            int entityX = sc.width / 2 + 160;
            if (mouseX >= entityX - 10 && mouseY >= height - 20 && mouseX <= entityX + 10 && mouseY <= height)
                RenderHelper.foxRotate = true;
        }
    }
}
