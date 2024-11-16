package dev.imb11.flow.render;

import dev.imb11.flow.Flow;
import dev.imb11.flow.api.rendering.FlowBlurHelper;
import dev.imb11.flow.config.FlowConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.math.MathHelper;

import java.awt.*;

public class FlowBackgroundHelper {
    public static boolean shouldSkipRender() {
        return Flow.areBackgroundModsPresent() || FlowConfig.get().disableAllBackgroundModifications;
    }
    public static void renderStaticBg(Screen screen, DrawContext context) {
        if(shouldSkipRender()) return;

        Color color = FlowConfig.get().bgColorTint;
        int AARRGGBB = (color.getAlpha() << 24) | (color.getRGB() & 0x00FFFFFF);
        renderBgEffects(screen.width, screen.height, context, FlowConfig.get().bgBlurIntensity * 16, AARRGGBB);
    }

    public static void renderBgEffects(float width, float height, DrawContext context, float blurIntensity, int color) {
        if(shouldSkipRender()) return;

        if(!FlowConfig.get().disableBgTint) {
            context.fill(0, 0, (int) width, (int) height, color);
        }

        if(!FlowConfig.get().disableBgBlur) {
            FlowBlurHelper.apply(width, height, context, blurIntensity, 4);
        }
    }
}
