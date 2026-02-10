package com.iafenvoy.tsm.cursed;

import com.iafenvoy.tsm.TitleScreenMobs;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.DefaultedMappedRegistry;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageScaling;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.OptionalLong;
import java.util.stream.Stream;

public final class CursedRegistries {
    public static final Registry<DimensionType> CURSED_DIMENSION_TYPE_REGISTRY = new MappedRegistry<>(Registries.DIMENSION_TYPE, Lifecycle.stable());
    private static final Registry<Biome> CURSED_BIOME_REGISTRY = new CursedRegistry<>(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake_biomes"), null);
    private static final Registry<BannerPattern> CURSED_BANNER_REGISTRY = new DefaultedMappedRegistry<>("dummy", Registries.BANNER_PATTERN, Lifecycle.stable(), true);
    public static final RegistryAccess.Frozen CURSED_REGISTRY_MANAGER = new RegistryAccess.Frozen() {
        private final CursedRegistry<DamageType> damageTypes = new CursedRegistry<>(Registries.DAMAGE_TYPE, ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake_damage"), new DamageType("", DamageScaling.NEVER, 0));
        private final CursedRegistry<Item> items = new CursedRegistry<>(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "fake_items"), Items.AIR);

        @SuppressWarnings({"unchecked", "rawtypes"})
        @Override
        public @NotNull Optional<Registry> lookup(ResourceKey key) {
            var x = BuiltInRegistries.REGISTRY.getValue(key);
            if (x != null)
                return Optional.of(x);
            else if (Registries.DAMAGE_TYPE.equals(key))
                return Optional.of(this.damageTypes);
            else if (Registries.BIOME.equals(key))
                return Optional.of(CURSED_BIOME_REGISTRY);
            else if (Registries.DIMENSION_TYPE.equals(key))
                return Optional.of(CURSED_DIMENSION_TYPE_REGISTRY);
            else if (Registries.BANNER_PATTERN.equals(key))
                return Optional.of(CURSED_BANNER_REGISTRY);
            else if (Registries.ITEM.equals(key))
                return Optional.of(this.items);
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
}
