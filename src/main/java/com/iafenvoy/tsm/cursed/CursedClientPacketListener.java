package com.iafenvoy.tsm.cursed;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.CommonListenerCookie;
import net.minecraft.client.multiplayer.LevelLoadTracker;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class CursedClientPacketListener extends ClientPacketListener {
    public static final CursedClientPacketListener INSTANCE = new CursedClientPacketListener();

    private CursedClientPacketListener() {
        super(
                Minecraft.getInstance(),
                new Connection(PacketFlow.CLIENTBOUND),
                new CommonListenerCookie(
                        new LevelLoadTracker(),
                        Minecraft.getInstance().getGameProfile(),
                        Minecraft.getInstance().getTelemetryManager().createWorldSessionManager(true, Duration.ZERO, null),
                        CursedRegistries.CURSED_REGISTRY_MANAGER,
                        FeatureFlagSet.of(FeatureFlags.VANILLA),
                        "",
                        new ServerData("", "", ServerData.Type.OTHER),
                        null,
                        Map.of(),
                        new ChatComponent.State(List.of(), List.of(), List.of()),
                        Map.of(),
                        net.minecraft.server.ServerLinks.EMPTY,
                        Map.of(),
                        false
                )
        );
    }

    @Override
    public RegistryAccess.@NotNull Frozen registryAccess() {
        return CursedRegistries.CURSED_REGISTRY_MANAGER;
    }
}