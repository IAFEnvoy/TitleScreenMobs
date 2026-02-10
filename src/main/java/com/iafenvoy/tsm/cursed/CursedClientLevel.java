package com.iafenvoy.tsm.cursed;

import com.iafenvoy.jupiter.util.RLUtil;
import com.iafenvoy.tsm.TitleScreenMobs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.registries.Registries;
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
                ResourceKey.create(Registries.DIMENSION, RLUtil.id(TitleScreenMobs.MOD_ID, "dummy")),
                CursedRegistries.CURSED_DIMENSION_TYPE_REGISTRY./*? >=1.21.2 {*//*getOrThrow*//*?} else {*/getHolderOrThrow/*?}*/(ResourceKey.create(Registries.DIMENSION_TYPE, RLUtil.id(TitleScreenMobs.MOD_ID, "dummy"))),
                0,
                0,
                /*? <=1.21.1 {*/() -> Minecraft.getInstance().getProfiler(),/*?}*/
                Minecraft.getInstance().levelRenderer,
                false,
                0L/*? >=1.21.2 {*//*, 60*//*?}*/
        );
    }
}