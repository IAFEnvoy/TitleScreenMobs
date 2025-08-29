package com.iafenvoy.tsm.config;

import com.iafenvoy.jupiter.config.container.AutoInitConfigContainer;
import com.iafenvoy.jupiter.config.entry.*;
import com.iafenvoy.jupiter.interfaces.IConfigEntry;
import com.iafenvoy.tsm.TitleScreenMobs;
import net.minecraft.util.Identifier;

import java.util.List;

public class TsmConfig extends AutoInitConfigContainer {
    public static final TsmConfig INSTANCE = new TsmConfig();
    public final Generals generals = new Generals();

    public TsmConfig() {
        super(Identifier.of(TitleScreenMobs.MOD_ID, "client"), "config.%s.title".formatted(TitleScreenMobs.MOD_ID), "./config/title-screen-mobs.json");
    }

    private static String formatKey(String id) {
        return "config.%s.generals.%s".formatted(TitleScreenMobs.MOD_ID, id);
    }

    public static class Generals extends AutoInitConfigCategoryBase {
        public final IConfigEntry<List<String>> whitelist = new ListStringEntry(formatKey("whitelist"), List.of()).json("whitelist");
        public final IConfigEntry<List<String>> blacklist = new ListStringEntry(formatKey("blacklist"), List.of("minecraft:wither", "minecraft:ender_dragon")).json("blacklist");
        public final SeparatorEntry s1 = new SeparatorEntry();
        public final IConfigEntry<Boolean> leftVisible = new BooleanEntry(formatKey("left.visible"), true).json("left.visible");
        public final IConfigEntry<Integer> leftXOffset = new IntegerEntry(formatKey("left.xOffset"), 0).json("left.xOffset");
        public final IConfigEntry<Integer> leftYOffset = new IntegerEntry(formatKey("left.yOffset"), 0).json("left.yOffset");
        public final IConfigEntry<Double> leftScale = new DoubleEntry(formatKey("left.scale"), 1).json("left.scale");
        public final SeparatorEntry s2 = new SeparatorEntry();
        public final IConfigEntry<Boolean> rightVisible = new BooleanEntry(formatKey("right.visible"), true).json("right.visible");
        public final IConfigEntry<Integer> rightXOffset = new IntegerEntry(formatKey("right.xOffset"), 0).json("right.xOffset");
        public final IConfigEntry<Integer> rightYOffset = new IntegerEntry(formatKey("right.yOffset"), 0).json("right.yOffset");
        public final IConfigEntry<Double> rightScale = new DoubleEntry(formatKey("right.scale"), 1).json("right.scale");
        public final SeparatorEntry s3 = new SeparatorEntry();
        public final IConfigEntry<Boolean> sendWarning = new BooleanEntry(formatKey("sendWarning"), true).json("sendWarning");

        public Generals() {
            super("generals", "config.%s.generals.title".formatted(TitleScreenMobs.MOD_ID));
        }
    }
}
