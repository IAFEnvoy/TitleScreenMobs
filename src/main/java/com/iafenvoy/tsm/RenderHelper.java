package com.iafenvoy.tsm;

import com.iafenvoy.tsm.cursed.CursedClientLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;

import java.util.List;
import java.util.Random;

public class RenderHelper {
    public static final int MAX_RIGHT_ATTEMPTS = 10;
    public static boolean enableLeft = true, enableRight = true;
    private static final Random RANDOM = new Random();
    public static LivingEntity livingEntity = null;
    private static int rightAttempts = 0;
    private static final List<? extends EntityType<?>> ALLOW_ENTITIES;

    static {
        List<? extends EntityType<?>> collect = TSMConfig.INSTANCE.general.whitelist.getValue().stream().map(Identifier::tryParse).map(BuiltInRegistries.ENTITY_TYPE::getValue).toList();
        if (collect.isEmpty())
            collect = BuiltInRegistries.ENTITY_TYPE.stream().filter((e) -> !TSMConfig.INSTANCE.general.blacklist.getValue().contains(BuiltInRegistries.ENTITY_TYPE.getKey(e).toString())).toList();
        ALLOW_ENTITIES = collect;
    }

    public static void resetRightAttempts() {
        rightAttempts = 0;
    }

    /**
     * Records a failed attempt to create or render the right-side mob and drops the current one.
     *
     * @return whether another attempt is allowed; disables the right side when exhausted
     */
    public static boolean onRightAttemptFailed() {
        livingEntity = null;
        if (++rightAttempts < MAX_RIGHT_ATTEMPTS) return true;
        enableRight = false;
        return false;
    }

    public static int getRightAttempts() {
        return rightAttempts;
    }

    public static void endClientTick() {
        if (Minecraft.getInstance()./*? >=26.2 {*//*gui.screen()*//*?} else {*/screen/*?}*/ instanceof TitleScreen) {
            try {
                CursedClientLevel.touch();
            } catch (Exception e) {
                enableLeft = enableRight = false;
                TitleScreenMobs.LOGGER.error("Failed to create fake world, disable title screen mobs rendering.", e);
                ToastHelper.sendWarningWithCheck();
            }
            if (enableRight && livingEntity == null)
                try {
                    Entity entity = ALLOW_ENTITIES.get(RANDOM.nextInt(ALLOW_ENTITIES.size())).create(CursedClientLevel.INSTANCE, EntitySpawnReason.MOB_SUMMONED);
                    if (entity instanceof LivingEntity living) {
                        // Not added to the level; see CursedLocalPlayer
                        living.setId(2);
                        livingEntity = living;
                    }
                } catch (Exception e) {
                    if (RenderHelper.onRightAttemptFailed())
                        TitleScreenMobs.LOGGER.warn("Failed to create fake entity, re-rolling ({}/{}).", RenderHelper.getRightAttempts(), MAX_RIGHT_ATTEMPTS, e);
                    else {
                        TitleScreenMobs.LOGGER.error("Failed to create fake entity after {} attempts, disabling right-side mob rendering.", MAX_RIGHT_ATTEMPTS, e);
                        ToastHelper.sendWarningWithCheck();
                    }
                }
        }
    }
}
