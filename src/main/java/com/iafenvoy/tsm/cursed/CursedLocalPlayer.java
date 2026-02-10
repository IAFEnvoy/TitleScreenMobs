package com.iafenvoy.tsm.cursed;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.world.entity.EquipmentSlot;
//? >=1.21.6 {
//import net.minecraft.world.entity.player.Input;
//?}
import net.minecraft.world.entity.player.PlayerModelPart;
//? >=1.21.9 {
//import net.minecraft.world.entity.player.PlayerSkin;
//?} else {
import net.minecraft.client.resources.PlayerSkin;
//?}
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CursedLocalPlayer extends LocalPlayer {
    public static final CursedLocalPlayer INSTANCE = new CursedLocalPlayer();
    private PlayerSkin skinTextures = null;

    /*? >=1.21.9 {*//*@SuppressWarnings("DataFlowIssue")*//*?}*/
    private CursedLocalPlayer() {
        super(Minecraft.getInstance(), CursedClientLevel.INSTANCE, CursedClientPacketListener.INSTANCE, null, null,/*? >=1.21.6 {*//* Input.EMPTY*//*?} else {*/false/*?}*/, false);
        this.setUUID(UUID.randomUUID());
        Minecraft.getInstance().getSkinManager()./*? >=1.21.9 {*//*get*//*?} else {*/getOrLoad/*?}*/(this.getGameProfile()).thenAccept(textures -> this.skinTextures = textures/*? >=1.21.4 {*//*.orElse(DefaultPlayerSkin.get(this.getGameProfile()))*//*?}*/);
    }

    @Override
    public boolean isModelPartShown(PlayerModelPart modelPart) {
        return true;
    }

    @Override
    public @NotNull PlayerSkin getSkin() {
        return this.skinTextures == null ? DefaultPlayerSkin.get(this.getUUID()) : this.skinTextures;
    }

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
    public @NotNull ItemStack getItemBySlot(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }
}