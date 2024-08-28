package net.ihor.changecolor.menu.widgets;

import net.ihor.changecolor.config.ModConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.awt.*;
import java.util.function.Supplier;

public class CButtonWidget extends ButtonWidget {

    public static final NarrationSupplier DEFAULT_NARRATION_SUPPLIER = Supplier::get;

    public EntityTypeColor entityTypeColor;
    public CButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier, EntityTypeColor entityTypeColor) {
        super(x, y, width, height, message, onPress, narrationSupplier);
        this.entityTypeColor = entityTypeColor;
    }

    public enum EntityTypeColor {
        PLAYER,
        PASSIVE,
        MONSTER,
        DROP,
        PROJECTILE
    }
    @Override
    protected void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        int x1 = this.getX();
        int y1 = this.getY();
        int x2 = x1 + width;
        int y2 = y1 + height;
        int red = 0, green = 0, blue = 0, alpha = 0;
        Color color1 = new Color(38, 38, 38, 255);
        Color color2 = new Color(220, 220, 220, 255);
        for (int i = 0; i < width; i++) {
            float ratio = (float) i / width;
            int r = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
            int g = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
            int b = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
            int a = (int) (color1.getAlpha() * (1 - ratio) + color2.getAlpha() * ratio);
            int color = new Color(r, g, b, a).getRGB();
            context.fill(x1 + width - i, y2, x1 + width - i + 1, y2 - 1, color);
            context.fill(x1 + i, y1, x1 + i + 1, y1 + 1, color);
        }
        for (int i = 0; i < height; i++) {
            float ratio = (float) i / height;
            int r = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
            int g = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
            int b = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
            int a = (int) (color1.getAlpha() * (1 - ratio) + color2.getAlpha() * ratio);
            int color = new Color(r, g, b, a).getRGB();
            context.fill(x1, y1 + i, x1 + 1, y1 + i + 1, color);
            context.fill(x2, y1 + height - i, x2 + 1, y1 + height - i - 1, color);
        }
        if (entityTypeColor == EntityTypeColor.PLAYER) {
            red = (int) ModConfig.configData.getPlayerColor()[0];
            green = (int) ModConfig.configData.getPlayerColor()[1];
            blue = (int) ModConfig.configData.getPlayerColor()[2];
            alpha = (int) ModConfig.configData.getPlayerColor()[3];
        } else if (entityTypeColor == EntityTypeColor.PASSIVE) {
            red = (int) ModConfig.configData.getPassiveColor()[0];
            green = (int) ModConfig.configData.getPassiveColor()[1];
            blue = (int) ModConfig.configData.getPassiveColor()[2];
            alpha = (int) ModConfig.configData.getPassiveColor()[3];
        }  else if (entityTypeColor == EntityTypeColor.MONSTER) {
            red = (int) ModConfig.configData.getMonsterColor()[0];
            green = (int) ModConfig.configData.getMonsterColor()[1];
            blue = (int) ModConfig.configData.getMonsterColor()[2];
            alpha = (int) ModConfig.configData.getMonsterColor()[3];
        }  else if (entityTypeColor == EntityTypeColor.DROP) {
            red = (int) ModConfig.configData.getDropColor()[0];
            green = (int) ModConfig.configData.getDropColor()[1];
            blue = (int) ModConfig.configData.getDropColor()[2];
            alpha = (int) ModConfig.configData.getDropColor()[3];
        }  else if (entityTypeColor == EntityTypeColor.PROJECTILE) {
            red = (int) ModConfig.configData.getProjectileColor()[0];
            green = (int) ModConfig.configData.getProjectileColor()[1];
            blue = (int) ModConfig.configData.getProjectileColor()[2];
            alpha = (int) ModConfig.configData.getProjectileColor()[3];
        }
        Color background = new Color(red, green, blue, alpha);
        context.fill(x1 + 1, y1 + 1, x2, y2 - 1, background.getRGB());
    }
}
