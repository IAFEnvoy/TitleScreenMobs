package com.iafenvoy.tsm.cursed;

import com.iafenvoy.tsm.TitleScreenMobs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.Difficulty;

public class CursedClientLevel extends ClientLevel {
    public static final CursedClientLevel INSTANCE = new CursedClientLevel();

    public static void touch() {
    }

    private CursedClientLevel() {
        super(
                CursedClientPacketListener.INSTANCE,
                new ClientLevelData(Difficulty.EASY, false, true),
                ResourceKey.create(Registries.DIMENSION, Identifier.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "dummy")),
                CursedRegistries.CURSED_DIMENSION_TYPE_REGISTRY.getOrThrow(ResourceKey.create(Registries.DIMENSION_TYPE, Identifier.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "dummy"))),
                0,
                0,
                Minecraft.getInstance()./*? >=26.2 {*//*levelExtractor*//*?} else {*/levelRenderer/*?}*/,
                false,
                0L, 60
        );
    }
}