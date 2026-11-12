package com.iafenvoy.tsm.cursed;

import com.iafenvoy.tsm.TitleScreenMobs;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.HolderSet;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.dimension.DimensionType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.stream.Stream;

public final class CursedRegistries {
    public static final Registry<DimensionType> CURSED_DIMENSION_TYPE_REGISTRY = new MappedRegistry<>(Registries.DIMENSION_TYPE, Lifecycle.stable());
    private static final Registry<Biome> CURSED_BIOME_REGISTRY = new CursedRegistry<>(Registries.BIOME, Identifier.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake_biomes"), null);
    private static final Registry<BannerPattern> CURSED_BANNER_REGISTRY = new DefaultedMappedRegistry<>("dummy", Registries.BANNER_PATTERN, Lifecycle.stable(), true);
    public static final RegistryAccess.Frozen CURSED_REGISTRY_MANAGER = new RegistryAccess.Frozen() {
        private final CursedRegistry<DamageType> damageTypes = new CursedRegistry<>(Registries.DAMAGE_TYPE, Identifier.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake_damage"), new DamageType("", DamageScaling.NEVER, 0));
        private final CursedRegistry<Item> items = new CursedRegistry<>(Registries.ITEM, Identifier.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake_items"), Items.AIR);

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public <E> @NotNull Optional<Registry<E>> lookup(ResourceKey<? extends Registry<? extends E>> key) {
            Registry<?> x = BuiltInRegistries.REGISTRY.getValue(key.identifier());
            if (x != null)
                return cast(x);
            else if (Registries.DAMAGE_TYPE.equals(key))
                return cast(this.damageTypes);
            else if (Registries.BIOME.equals(key))
                return cast(CURSED_BIOME_REGISTRY);
            else if (Registries.DIMENSION_TYPE.equals(key))
                return cast(CURSED_DIMENSION_TYPE_REGISTRY);
            else if (Registries.BANNER_PATTERN.equals(key))
                return cast(CURSED_BANNER_REGISTRY);
            else if (Registries.ITEM.equals(key))
                return cast(this.items);
            return Optional.empty();
        }

        @SuppressWarnings("unchecked")
        private static <E> Optional<Registry<E>> cast(Registry<?> registry) {
            return Optional.of((Registry<E>) registry);
        }

        @SuppressWarnings({"rawtypes", "unchecked", "UnnecessaryLocalVariable"})
        @Override
        public <E> @NotNull Registry<E> lookupOrThrow(ResourceKey<? extends Registry<? extends E>> key) {
            return this.lookup(key).orElseGet(() -> {
                ResourceKey sillyKey = key;
                return new CursedRegistry<>(sillyKey, Identifier.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake"), null);
            });
        }

        @Override
        public @NotNull Stream<RegistryEntry<?>> registries() {
            return Stream.empty();
        }
    };

    static {
        Registry.register(CURSED_DIMENSION_TYPE_REGISTRY, Identifier.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "dummy"), new DimensionType(
                true,
                true,
                false,
                false,
                1.0,
                -64,
                384,
                384,
                /*? >=26.2 {*//*HolderSet.empty()*//*?} else {*/BlockTags.INFINIBURN_OVERWORLD/*?}*/,
                0.0f,
                new DimensionType.MonsterSettings(
                        UniformInt.of(0, 7),
                        0
                ),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.EMPTY,
                HolderSet.empty(),
                Optional.empty()
        ));
    }
}
