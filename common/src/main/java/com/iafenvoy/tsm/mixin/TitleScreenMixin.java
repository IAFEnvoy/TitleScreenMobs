package com.iafenvoy.tsm.mixin;

import com.iafenvoy.tsm.RenderHelper;
import com.iafenvoy.tsm.TitleScreenMobs;
import com.iafenvoy.tsm.ToastHelper;
import com.iafenvoy.tsm.config.TsmConfig;
import com.iafenvoy.tsm.cursed.DummyClientPlayerEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "<init>()V", at = @At("RETURN"))
    private void resetEntity(CallbackInfo ci) {
        RenderHelper.livingEntity = null;
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void onRender(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        TitleScreen sc = (TitleScreen) (Object) this;
        TsmConfig config = TsmConfig.INSTANCE;
        if (MinecraftClient.getInstance() != null) {
            ClientPlayerEntity player = DummyClientPlayerEntity.getInstance();
            int height = sc.height / 4 + 132;
            int playerX = sc.width / 2 - 160;
            if (config.generals.leftVisible.getValue() && RenderHelper.enableLeft)
                try {
                    InventoryScreen.drawEntity(context, playerX + config.generals.leftXOffset.getValue(), height + config.generals.leftYOffset.getValue(), (int) (30 * config.generals.leftScale.getValue()), -mouseX + playerX, -mouseY + height - 30, player);
                } catch (Exception e) {
                    RenderHelper.enableLeft = false;
                    TitleScreenMobs.LOGGER.error("Failed to render player on title screen, disabling", e);
                    ToastHelper.sendWarningWithCheck();
                }
            int entityX = sc.width / 2 + 160;
            LivingEntity livingEntity = RenderHelper.livingEntity;
            if (livingEntity != null && config.generals.rightVisible.getValue() && RenderHelper.enableRight) {
                try {
                    RenderHelper.renderEntity(context.getMatrices(), entityX + config.generals.rightXOffset.getValue(), height + config.generals.rightYOffset.getValue(), 30, -mouseX + entityX, -mouseY + height - 30, livingEntity, config.generals.rightScale.getValue().floatValue());
                } catch (Exception e) {
                    RenderHelper.livingEntity = null;
                    RenderHelper.enableRight = false;
                    TitleScreenMobs.LOGGER.error("Failed to render entity on title screen, disabling", e);
                    ToastHelper.sendWarningWithCheck();
                }
            }
        }
    }
}
