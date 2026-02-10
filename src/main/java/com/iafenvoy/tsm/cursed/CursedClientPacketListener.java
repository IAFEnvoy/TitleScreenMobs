package com.iafenvoy.tsm.cursed;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.PacketFlow;
import org.jetbrains.annotations.NotNull;
import java.time.Duration;
//? >=1.21.9 {
//import net.minecraft.client.multiplayer.LevelLoadTracker;
//?}
//? >=1.21 {
//import net.minecraft.server.ServerLinks;
//?}
//? >=1.20.5 {
//import net.minecraft.client.gui.components.ChatComponent;
//import java.util.List;
//import java.util.Map;
//?}
//? >=1.20.2 {
//import net.minecraft.client.multiplayer.CommonListenerCookie;
//import net.minecraft.client.multiplayer.ServerData;
//import net.minecraft.world.flag.FeatureFlagSet;
//import net.minecraft.world.flag.FeatureFlags;
//import java.time.temporal.ChronoUnit;
//?}

public class CursedClientPacketListener extends ClientPacketListener {
    public static final CursedClientPacketListener INSTANCE = new CursedClientPacketListener();

    private CursedClientPacketListener() {
        super(
                Minecraft.getInstance(),
                //? >=1.20.2 {
//                new Connection(PacketFlow.CLIENTBOUND),
//                new CommonListenerCookie(
//                        /*? >=1.21.9 {*//*new LevelLoadTracker(),*//*?}*/
//                        Minecraft.getInstance().getGameProfile(),
//                        Minecraft.getInstance().getTelemetryManager().createWorldSessionManager(true, Duration.ZERO, null),
//                        CursedRegistries.CURSED_REGISTRY_MANAGER,
//                        FeatureFlagSet.of(FeatureFlags.VANILLA),
//                        "",
//                        new ServerData("", "", ServerData.Type.OTHER),
//                        null
//                        /*? >=1.20.5 {*//*, Map.of(), new ChatComponent.State(List.of(), List.of(), List.of())*//*?}*/
//                        /*? <=1.21.1 >=1.20.5 {*//*, false*//*?}*/
//                        /*? >=1.21.9 {*//*, Map.of(), false*//*?}*/
//                        /*? >=1.21 {*//*, Map.of(), ServerLinks.EMPTY*//*?}*/
//                        /*? >=1.21.9 {*//*, Map.of(), false*//*?}*/
//                )
                //?} else {
                null,
                new Connection(PacketFlow.CLIENTBOUND),
                Minecraft.getInstance().getCurrentServer(),
                Minecraft.getInstance().getUser().getGameProfile(),
                Minecraft.getInstance().getTelemetryManager().createWorldSessionManager(true, Duration.ZERO, null)
                //?}
        );
    }

    @Override
    public RegistryAccess.@NotNull Frozen registryAccess() {
        return CursedRegistries.CURSED_REGISTRY_MANAGER;
    }
}