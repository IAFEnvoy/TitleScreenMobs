package com.iafenvoy.tsm.mixin;

import com.iafenvoy.tsm.cursed.DummyClientPlayerEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(EntityRenderer.class)
public class EntityRendererMixin<T extends Entity, S extends EntityRenderState> {
    @Inject(method = "updateRenderState", at = @At("HEAD"), cancellable = true)
    private void cancelDistance(T entity, S state, float tickDelta, CallbackInfo ci) {
        if (entity instanceof DummyClientPlayerEntity || MinecraftClient.getInstance().currentScreen instanceof TitleScreen)
            ci.cancel();
    }
}
