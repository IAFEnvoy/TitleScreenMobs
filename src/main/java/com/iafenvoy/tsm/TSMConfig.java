package com.iafenvoy.tsm;

import com.iafenvoy.jupiter.config.container.AutoInitConfigContainer;
import com.iafenvoy.jupiter.config.entry.*;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class TSMConfig extends AutoInitConfigContainer {
    public static final TSMConfig INSTANCE = new TSMConfig();
    public final General general = new General();

    public TSMConfig() {
        super(ResourceLocation.fromNamespaceAndPath(TitleScreenMobs.MOD_ID, "client"), "config.%s.title".formatted(TitleScreenMobs.MOD_ID), "./config/title-screen-mobs.json");
    }

    private static String formatKey(String id) {
        return "config.%s.general.%s".formatted(TitleScreenMobs.MOD_ID, id);
    }

    @SuppressWarnings("unused")
    public static class General extends AutoInitConfigCategoryBase {
        public final ListStringEntry whitelist = ListStringEntry.builder(formatKey("whitelist"), List.of()).key("whitelist").build();
        public final ListStringEntry blacklist = ListStringEntry.builder(formatKey("blacklist"), List.of("minecraft:wither", "minecraft:ender_dragon")).key("blacklist").build();
        public final SeparatorEntry s1 = SeparatorEntry.builder().build();
        public final BooleanEntry leftVisible = BooleanEntry.builder(formatKey("left.visible"), true).key("left.visible").build();
        public final IntegerEntry leftXOffset = IntegerEntry.builder(formatKey("left.xOffset"), 0).key("left.xOffset").build();
        public final IntegerEntry leftYOffset = IntegerEntry.builder(formatKey("left.yOffset"), 0).key("left.yOffset").build();
        public final IntegerEntry leftScale = IntegerEntry.builder(formatKey("left.scale"), 30).key("left.scale").build();
        public final SeparatorEntry s2 = SeparatorEntry.builder().build();
        public final BooleanEntry rightVisible = BooleanEntry.builder(formatKey("right.visible"), true).key("right.visible").build();
        public final IntegerEntry rightXOffset = IntegerEntry.builder(formatKey("right.xOffset"), 0).key("right.xOffset").build();
        public final IntegerEntry rightYOffset = IntegerEntry.builder(formatKey("right.yOffset"), 0).key("right.yOffset").build();
        public final IntegerEntry rightScale = IntegerEntry.builder(formatKey("right.scale"), 30).key("right.scale").build();
        public final SeparatorEntry s3 = SeparatorEntry.builder().build();
        public final BooleanEntry sendWarning = BooleanEntry.builder(formatKey("sendWarning"), true).key("sendWarning").build();

        public General() {
            super("general", "config.%s.general.title".formatted(TitleScreenMobs.MOD_ID));
        }
    }
}
