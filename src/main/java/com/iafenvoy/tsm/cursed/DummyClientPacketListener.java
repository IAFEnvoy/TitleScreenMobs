package com.iafenvoy.tsm.cursed;

import com.iafenvoy.tsm.TitleScreenMobs;
import com.mojang.serialization.Lifecycle;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.CommonListenerCookie;
import net.minecraft.client.multiplayer.LevelLoadTracker;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Stream;

public class DummyClientPacketListener extends ClientPacketListener {
    public static final Registry<DimensionType> CURSED_DIMENSION_TYPE_REGISTRY = new MappedRegistry<>(Registries.DIMENSION_TYPE, Lifecycle.stable());

    static {
        Registry.register(CURSED_DIMENSION_TYPE_REGISTRY, ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "dummy"), new DimensionType(
                OptionalLong.of(6000L),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64,
                384,
                384,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0f,
                Optional.empty(),
                new DimensionType.MonsterSettings(
                        false,
                        true,
                        UniformInt.of(0, 7),
                        0
                )
        ));
    }

    private static DummyClientPacketListener instance;

    public static DummyClientPacketListener getInstance() {
        if (instance == null) instance = new DummyClientPacketListener();
        return instance;
    }

    private static final Registry<Biome> cursedBiomeRegistry = new CursedRegistry<>(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake_biomes"), null);

    private static final Registry<BannerPattern> cursedBannerRegistry = new DefaultedMappedRegistry<>("dummy", Registries.BANNER_PATTERN, Lifecycle.stable(), true);

    private static final RegistryAccess.Frozen cursedRegistryManager = new RegistryAccess.Frozen() {
        private final CursedRegistry<DamageType> damageTypes = new CursedRegistry<>(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake_damage"),
                new DamageType("", DamageScaling.NEVER, 0));
        private final CursedRegistry<Item> items = new CursedRegistry<>(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake_items"),
                Items.AIR);

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public @NotNull Optional<Registry> lookup(ResourceKey key) {
            var x = BuiltInRegistries.REGISTRY.getValue(key);
            if (x != null) {
                return Optional.of(x);
            } else if (Registries.DAMAGE_TYPE.equals(key)) {
                return Optional.of(this.damageTypes);
            } else if (Registries.BIOME.equals(key)) {
                return Optional.of(cursedBiomeRegistry);
            } else if (Registries.DIMENSION_TYPE.equals(key)) {
                return Optional.of(CURSED_DIMENSION_TYPE_REGISTRY);
            } else if (Registries.BANNER_PATTERN.equals(key)) {
                // This fixes lithium compat post-1.20.5
                return Optional.of(cursedBannerRegistry);
            } else if (Registries.ITEM.equals(key)) {
                return Optional.of(this.items);
            }

            return Optional.empty();
        }

        @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
        @Override
        public <E> @NotNull Registry<E> lookupOrThrow(ResourceKey<? extends Registry<? extends E>> key) {
            return this.lookup(key).orElseGet(() -> {
                ResourceKey sillyKey = key;
                return new CursedRegistry<>(sillyKey, ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake"), null);
            });
        }

        @Override
        public @NotNull Stream<RegistryEntry<?>> registries() {
            return Stream.empty();
        }
    };

    private DummyClientPacketListener() {
        super(
                Minecraft.getInstance(),
                new Connection(PacketFlow.CLIENTBOUND),
                new CommonListenerCookie(
                        new LevelLoadTracker(),
                        Minecraft.getInstance().getGameProfile(),
                        Minecraft.getInstance().getTelemetryManager().createWorldSessionManager(true, Duration.ZERO, null),
                        cursedRegistryManager,
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
        return cursedRegistryManager;
    }
}