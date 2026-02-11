package com.iafenvoy.tsm.mixin;

import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import org.spongepowered.asm.mixin.Mixin;
//? <=1.21.8 {
//import net.minecraft.client.Camera;
//import net.minecraft.world.entity.Entity;
//import org.jetbrains.annotations.Nullable;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//?}

@Mixin(EntityRenderDispatcher.class)
public class EntityRenderDispatcherMixin {
    //Mojang has solved this issue in `EntityRenderer.extractRenderState` since 1.21.9 so we only need to mixin before 1.21.8
    //? <=1.21.8 {
    /*@Shadow
    @Nullable
    public Camera camera;

    @Inject(method = "distanceToSqr(Lnet/minecraft/world/entity/Entity;)D", at = @At("HEAD"), cancellable = true)
    private void wrapCameraDistance(Entity entity, CallbackInfoReturnable<Double> cir) {
        if (this.camera == null) cir.setReturnValue(4096.0);
    }
    *///?}
}
