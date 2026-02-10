package com.iafenvoy.tsm.cursed;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerInfo;
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
import java.util.function.Function;

public class DummyClientPlayerEntity extends LocalPlayer {
    private static DummyClientPlayerEntity instance;
    private PlayerSkin skinTextures = null;
    public Function<EquipmentSlot, ItemStack> equippedStackSupplier = slot -> ItemStack.EMPTY;

    public static DummyClientPlayerEntity getInstance() {
        if (instance == null) instance = new DummyClientPlayerEntity();
        return instance;
    }

    private DummyClientPlayerEntity() {
        super(Minecraft.getInstance(), DummyClientWorld.getInstance(), DummyClientPlayNetworkHandler.getInstance(), null, null, Input.EMPTY, false);
        this.setUUID(UUID.randomUUID());
        Minecraft.getInstance().getSkinManager().get(this.getGameProfile()).thenAccept(textures -> this.skinTextures = textures.orElse(DefaultPlayerSkin.get(this.getGameProfile())));
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
        return this.equippedStackSupplier.apply(slot);
    }
}