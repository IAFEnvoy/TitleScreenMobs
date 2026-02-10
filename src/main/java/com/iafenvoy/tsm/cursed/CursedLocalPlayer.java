package com.iafenvoy.tsm.cursed;

import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.PlayerModelPart;
//? >=1.21.6 {
//import net.minecraft.world.entity.player.Input;
//?}
//? >=1.21.9 {
//import net.minecraft.world.entity.player.PlayerSkin;
//?} else >=1.20.2 {
//import net.minecraft.client.resources.PlayerSkin;
//?} else {
import net.minecraft.resources.ResourceLocation;
//?}
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CursedLocalPlayer extends LocalPlayer {
    public static final CursedLocalPlayer INSTANCE = new CursedLocalPlayer();
    //? >=1.20.2 {
//    private PlayerSkin skinTextures = null;
    //?} else {
    private ResourceLocation skinTexture = null, capeTexture = null;
    private String modelName = null;
    //?}

    private CursedLocalPlayer() {
        super(Minecraft.getInstance(), CursedClientLevel.INSTANCE, CursedClientPacketListener.INSTANCE, null, null,/*? >=1.21.6 {*//* Input.EMPTY*//*?} else {*/false/*?}*/, false);
        this.setUUID(UUID.randomUUID());
        //? >=1.20.2 {
//        Minecraft.getInstance().getSkinManager()./*? >=1.21.9 {*//*get*//*?} else {*/getOrLoad/*?}*/(this.getGameProfile()).thenAccept(textures -> this.skinTextures = textures/*? >=1.21.4 {*//*.orElse(DefaultPlayerSkin.get(this.getGameProfile()))*//*?}*/);
        //?} else {
        Minecraft.getInstance().getSkinManager().registerSkins(this.getGameProfile(), (type, rl, texture) -> {
            if (type == MinecraftProfileTexture.Type.SKIN) {
                this.skinTexture = rl;
                this.modelName = texture.getMetadata("model");
                if (this.modelName == null) this.modelName = "default";
            }
            if (type == MinecraftProfileTexture.Type.CAPE) this.capeTexture = rl;
        }, true);
        //?}
    }

    @Override
    public boolean isModelPartShown(@NotNull PlayerModelPart modelPart) {
        return true;
    }

    //? >=1.20.2 {
//    @Override
//    public @NotNull PlayerSkin getSkin() {
//        return this.skinTextures == null ? DefaultPlayerSkin.get(this.getUUID()) : this.skinTextures;
//    }
    //?} else {
    @Override
    public boolean isSkinLoaded() {
        return true;
    }

    @Override
    public @NotNull ResourceLocation getSkinTextureLocation() {
        return this.skinTexture == null ? DefaultPlayerSkin.getDefaultSkin(this.getUUID()) : this.skinTexture;
    }

    @Override
    public boolean isCapeLoaded() {
        return true;
    }

    @Override
    public @Nullable ResourceLocation getCloakTextureLocation() {
        return this.capeTexture;
    }

    @Override
    public @NotNull String getModelName() {
        return this.modelName == null ? DefaultPlayerSkin.getSkinModelName(this.getUUID()) : this.modelName;
    }
    //?}

    @Nullable
    @Override
    protected PlayerInfo getPlayerInfo() {
        return null;
    }

    @Override
    public boolean isSpectator() {
        return false;
    }

    @Override
    public boolean isCreative() {
        return true;
    }

    @Override
    public @NotNull ItemStack getItemBySlot(@NotNull EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }
}