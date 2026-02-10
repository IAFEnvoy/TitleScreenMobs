package com.iafenvoy.tsm;

import com.iafenvoy.tsm.cursed.DummyClientWorld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.Random;

public class RenderHelper {
    public static boolean enableLeft = true, enableRight = true;
    private static final Random RANDOM = new Random();
    public static LivingEntity livingEntity = null;
    public static boolean foxRotate = false, isNeoForge = false;
    private static final List<? extends EntityType<?>> ALLOW_ENTITIES;

    static {
        List<? extends EntityType<?>> collect = TSMConfig.INSTANCE.general.whitelist.getValue().stream().map(ResourceLocation::tryParse).map(BuiltInRegistries.ENTITY_TYPE::getValue).toList();
        if (collect.isEmpty())
            collect = BuiltInRegistries.ENTITY_TYPE.stream().filter((e) -> !TSMConfig.INSTANCE.general.blacklist.getValue().contains(BuiltInRegistries.ENTITY_TYPE.getKey(e).toString())).toList();
        ALLOW_ENTITIES = collect;
    }

    public static void endClientTick() {
        try {
            DummyClientWorld.getInstance();
        } catch (Exception e) {
            enableLeft = enableRight = false;
            TitleScreenMobs.LOGGER.error("Failed to create fake world, disable title screen mobs rendering.", e);
            ToastHelper.sendWarningWithCheck();
        }
        if (enableRight && Minecraft.getInstance().screen instanceof TitleScreen && livingEntity == null) {
            Entity entity = ALLOW_ENTITIES.get(RANDOM.nextInt(ALLOW_ENTITIES.size())).create(DummyClientWorld.getInstance(), EntitySpawnReason.MOB_SUMMONED);
            if (entity instanceof LivingEntity) livingEntity = (LivingEntity) entity;
        }
        if (!(Minecraft.getInstance().screen instanceof TitleScreen)) foxRotate = false;
    }
}
