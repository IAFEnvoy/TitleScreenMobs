package com.iafenvoy.tsm.cursed;

import com.iafenvoy.tsm.TitleScreenMobs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Difficulty;

public class DummyClientWorld extends ClientLevel {
    private static DummyClientWorld instance;

    public static DummyClientWorld getInstance() {
        if (instance == null) instance = new DummyClientWorld();
        return instance;
    }

    private DummyClientWorld() {
        super(
                DummyClientPlayNetworkHandler.getInstance(),
                new ClientLevelData(Difficulty.EASY, false, true),
                ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "dummy")),
                DummyClientPlayNetworkHandler.CURSED_DIMENSION_TYPE_REGISTRY.getOrThrow(ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "dummy"))),
                0,
                0,
                Minecraft.getInstance().levelRenderer,
                false,
                0L,
                60
        );
    }
}