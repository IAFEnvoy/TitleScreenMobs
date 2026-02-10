package com.iafenvoy.tsm.cursed;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.TagLoader;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

@SuppressWarnings("deprecation")
public record CursedRegistry<T>(ResourceKey<? extends Registry<T>> registryKey, ResourceLocation defaultId,
                                T defaultValue) implements Registry<T>, HolderOwner<T> {
    @Override
    public @NotNull ResourceKey<? extends Registry<T>> key() {
        return this.registryKey;
    }

    @Nullable
    @Override
    public ResourceLocation getKey(T value) {
        return this.defaultId;
    }

    @Override
    public @NotNull Optional<ResourceKey<T>> getResourceKey(T entry) {
        return Optional.empty();
    }

    @Override
    public int getId(@Nullable T value) {
        return 0;
    }

    @Nullable
    @Override
    public T byId(int index) {
        return this.defaultValue;
    }

    @Override
    public int size() {
        return 1;
    }

    @Nullable
    @Override
    public T getValue(@Nullable ResourceKey<T> key) {
        return this.defaultValue;
    }

    @Nullable
    @Override
    public T getValue(@Nullable ResourceLocation id) {
        return this.defaultValue;
    }

    @Override
    public @NotNull Optional<net.minecraft.core.RegistrationInfo> registrationInfo(ResourceKey<T> key) {
        return Optional.empty();
    }

    @Override
    public @NotNull Optional<Holder.Reference<T>> get(ResourceLocation id) {
        return Optional.empty();
    }


    @Override
    public @NotNull Lifecycle registryLifecycle() {
        return Lifecycle.experimental();
    }

    @Override
    public @NotNull Optional<Holder.Reference<T>> getAny() {
        return Optional.empty();
    }

    @Override
    public @NotNull Set<ResourceLocation> keySet() {
        return Set.of(this.defaultId);
    }

    @Override
    public @NotNull Set<Map.Entry<ResourceKey<T>, T>> entrySet() {
        return Set.of();
    }

    @Override
    public @NotNull Set<ResourceKey<T>> registryKeySet() {
        return Set.of();
    }

    @Override
    public @NotNull Optional<Holder.Reference<T>> getRandom(RandomSource random) {
        return Optional.empty();
    }

    @Override
    public boolean containsKey(ResourceLocation id) {
        return true;
    }

    @Override
    public boolean containsKey(ResourceKey<T> key) {
        return true;
    }

    @Override
    public @NotNull Registry<T> freeze() {
        return this;
    }

    @Override
    public Holder.@NotNull Reference<T> createIntrusiveHolder(T value) {
        return Holder.Reference.createIntrusive(this, value);
    }

    @Override
    public @NotNull Optional<Holder.Reference<T>> get(int rawId) {
        return Optional.empty();
    }

    @Override
    public @NotNull Holder<T> wrapAsHolder(T value) {
        return Holder.direct(value);
    }

    @Override
    public Stream<Holder.Reference<T>> listElements() {
        return null;
    }

    @Override
    public @NotNull Stream<HolderSet.Named<T>> listTags() {
        return Stream.empty();
    }

    @Override
    public @NotNull Stream<HolderSet.Named<T>> getTags() {
        return Stream.empty();
    }

    @Override
    public PendingTags<T> prepareTagReload(TagLoader.LoadResult<T> tags) {
        return null;
    }

    @Override
    public @NotNull Optional<Holder.Reference<T>> get(ResourceKey<T> key) {
        return Optional.of(this.createIntrusiveHolder(this.defaultValue));
    }

    @Override
    public @NotNull Optional<HolderSet.Named<T>> get(TagKey<T> tag) {
        return Optional.empty();
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}