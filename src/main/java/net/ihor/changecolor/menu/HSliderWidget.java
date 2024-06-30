package net.ihor.changecolor.menu;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

public class HSliderWidget extends SliderWidget {

    private Color color;
    private static final Identifier TEXTURE = new Identifier("textures/gui/slider.png");
    private double min;
    private double max;

    public HSliderWidget(int x, int y, int width, int height, Text text, float value, float min, float max, Color color) {
        super(x, y, width, height, Text.empty(), value);
        this.value = (value - min) / (max - min);
        this.min = min;
        this.max = max;
        this.color = color;
        this.updateMessage();
    }

    @Override
    protected void updateMessage() {

    }

    @Override
    protected void applyValue() {

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
        context.fill(x1, y1, x2, y1 + 1, Color.WHITE.getRGB());
        context.fill(x1, y2 - 1, x2, y2, Color.WHITE.getRGB());
        context.fill(x1, y1, x1 + 1, y2, Color.WHITE.getRGB());
        context.fill(x2 - 1, y1, x2, y2, Color.WHITE.getRGB());
        int XX = this.getX() + (int) (value * (width - 10));
        int YY = this.getY();
        int Y3 = YY + height;
        context.drawNineSlicedTexture(TEXTURE, XX + 1, this.getY() + 1, 8, 14, 20, 4, 200, 20, 0, 40);
        context.fill(getX() + 1, YY + 1, XX + 1, Y3 - 1, color.getRGB());
    }

}
