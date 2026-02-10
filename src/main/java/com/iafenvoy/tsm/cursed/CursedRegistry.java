package com.iafenvoy.tsm.cursed;

import com.mojang.serialization.Lifecycle;
import net.minecraft.core.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

@SuppressWarnings("deprecation")
public class CursedRegistry<T> extends DefaultedMappedRegistry<T> implements HolderOwner<T> {
    private final T defaultValue;

    public CursedRegistry(ResourceKey<? extends Registry<T>> key, ResourceLocation defaultId, T defaultValue) {
        super(defaultId.toString(), key, Lifecycle.stable(), false);
        this.defaultValue = defaultValue;
        if (defaultValue != null) Registry.register(this, defaultId, defaultValue);
        this.freeze();
    }

    @Override
    public Holder.@NotNull Reference<T> createIntrusiveHolder(T value) {
        return Holder.Reference.createIntrusive(this, value);
    }

    @Override
    public @NotNull Optional<Holder.Reference<T>> /*? >=1.21.2 {*//*get*//*?} else {*/getHolder/*?}*/(ResourceKey<T> key) {
        return Optional.of(this.createIntrusiveHolder(this.defaultValue));
    }
}