package com.iafenvoy.tsm.cursed;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.multiplayer.chat.ChatAbilities;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Input;
import net.minecraft.world.entity.player.PlayerModelPart;
import net.minecraft.world.entity.player.PlayerSkin;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CursedLocalPlayer extends LocalPlayer {
    public static final CursedLocalPlayer INSTANCE = new CursedLocalPlayer();
    private PlayerSkin skinTextures = null;

    private CursedLocalPlayer() {
        super(Minecraft.getInstance(), CursedClientLevel.INSTANCE, CursedClientPacketListener.INSTANCE, null, null, Input.EMPTY, false, ChatAbilities.NO_RESTRICTIONS);
        this.setUUID(UUID.randomUUID());
        // Not added to the level, so no id is assigned; item model rendering requires a non-zero id (26.2+)
        this.setId(1);
        Minecraft.getInstance().getSkinManager().get(this.getGameProfile()).thenAccept(textures -> this.skinTextures = textures.orElse(DefaultPlayerSkin.get(this.getGameProfile())));
    }

    @Override
    public boolean isModelPartShown(@NotNull PlayerModelPart modelPart) {
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
    public @NotNull ItemStack getItemBySlot(@NotNull EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }
}
