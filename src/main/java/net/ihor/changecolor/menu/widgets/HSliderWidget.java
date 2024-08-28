package net.ihor.changecolor.menu.widgets;

import net.ihor.changecolor.config.ModConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

public class HSliderWidget extends SliderWidget {

    private final Color color;
    private static final Identifier TEXTURE = new Identifier("textures/gui/slider.png");
    private final double min;
    private final double max;
    private final ColorType colorType;
    private final EntityType entityType;

    public HSliderWidget(int x, int y, int width, int height, Text text, float value, float min, float max, Color color, EntityType entityType, ColorType colorType) {
        super(x, y, width, height, Text.empty(), value);
        this.value = (value - min) / (max - min);
        this.min = min;
        this.max = max;
        this.color = color;
        this.updateMessage();
        this.colorType = colorType;
        this.entityType = entityType;
    }

    public enum ColorType {
        RED,
        GREEN,
        BLUE,
        ALPHA
    }

    public enum EntityType {
        PLAYER,
        PASSIVE,
        DROP,
        MONSTER,
        PROJECTILE
    }
    @Override
    protected void applyValue() {
        ModConfig.loadConfig();
        float newValue = getValue();
        updateColor(entityType, colorType, newValue);
        ModConfig.saveConfig();
        updateMessage();
    }

    public float getValue() {
        return (float) (this.value * (max - min) + min);
    }

    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        int x1 = this.getX();
        int y1 = this.getY();
        int x2 = this.getX() + width;
        int y2 = this.getY() + height;
        context.fill(x1, y1, x2, y2, Color.OPAQUE);
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
            context.fill(x2, y1 + height - i, x2 - 1, y1 + height - i - 1, color);
        }
        int x3 = this.getX() + (int) (value * (width - 10));
        int y3 = this.getY();
        int y4 = y3 + height;
        context.drawNineSlicedTexture(TEXTURE, x3 + 1, y3 + 1, 8, 14, 20, 4, 200, 20, 0, 40);
        context.fill(getX() + 1, y3 + 1, x3 + 1, y4 - 1, color.getRGB());
    }

    @Override
    protected void updateMessage() {
    }

    private void updateColor(EntityType entityType, ColorType colorType, float newValue) {
        float[] temp = null;

        switch (entityType) {
            case PLAYER -> temp = ModConfig.configData.getPlayerColor();
            case PASSIVE -> temp = ModConfig.configData.getPassiveColor();
            case MONSTER -> temp = ModConfig.configData.getMonsterColor();
            case DROP -> temp = ModConfig.configData.getDropColor();
            case PROJECTILE -> temp = ModConfig.configData.getProjectileColor();
        }

        if (temp != null) {
            switch (colorType) {
                case RED -> temp[0] = newValue;
                case GREEN -> temp[1] = newValue;
                case BLUE -> temp[2] = newValue;
                case ALPHA -> temp[3] = newValue;
            }
            setEntityColor(entityType, temp);
        }
    }

    private void setEntityColor(EntityType entityType, float[] color) {
        switch (entityType) {
            case PLAYER -> ModConfig.configData.setPlayerColor(color);
            case PASSIVE -> ModConfig.configData.setPassiveColor(color);
            case MONSTER -> ModConfig.configData.setMonsterColor(color);
            case DROP -> ModConfig.configData.setDropColor(color);
            case PROJECTILE -> ModConfig.configData.setProjectileColor(color);
        }
    }

}
