package net.ihor.changecolor.menu;

import net.ihor.changecolor.config.ModConfig;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import java.awt.*;

public class Menu extends Screen {
    private Screen parent;
    private HSliderWidget blue;
    private HSliderWidget red;
    private HSliderWidget green;
    private HSliderWidget alpha;

    public Menu(Screen screen) {
        super(Text.of("Menu"));
        this.parent = screen;
    }

    @Override
    protected void init() {
        ModConfig.loadConfig();
        red = new HSliderWidget(width / 2 + 65, height / 2 - 80, 80, 16, Text.empty(), ModConfig.configData.getRed(), 0.0f, 255.0f, Color.RED);
        green = new HSliderWidget(width / 2 + 65, height / 2 - 30, 80, 16, Text.empty(), ModConfig.configData.getGreen(), 0.0f, 255.0f, Color.GREEN);
        blue = new HSliderWidget(width / 2 + 65, height / 2 + 20, 80, 16, Text.empty(), ModConfig.configData.getBlue(), 0.0f, 255.0f, Color.BLUE);
        alpha = new HSliderWidget(width / 2 + 65, height / 2 + 70, 80, 16, Text.empty(), ModConfig.configData.getAlpha(), 0.0f, 255.0f, Color.GRAY);
        this.addDrawableChild(ButtonWidget.builder(Text.of("RenderEyes: " + ModConfig.configData.isRenderEyes()), button -> {
            boolean flag = ModConfig.configData.isRenderEyes();
            flag = !flag;
            ModConfig.configData.setRenderEyes(flag);
            ModConfig.saveConfig();
            button.setMessage(Text.of("RenderEyes: " + ModConfig.configData.isRenderEyes()));
        }).dimensions(width / 2 - 140, height / 2, 130, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.of("RenderDirection: " + ModConfig.configData.isRenderDirection()), button -> {
            boolean flag = ModConfig.configData.isRenderDirection();
            flag = !flag;
            ModConfig.configData.setRenderDirection(flag);
            ModConfig.saveConfig();
            button.setMessage(Text.of("RenderDirection: " + ModConfig.configData.isRenderDirection()));
        }).dimensions(width / 2 - 140, height / 2 + 30, 130, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.of("Save & Exit"), button -> {
            close();
        }).dimensions(width / 2 - 40, height / 2 + 70, 80, 20).build());

        this.addDrawableChild(red);
        this.addDrawableChild(green);
        this.addDrawableChild(blue);
        this.addDrawableChild(alpha);

    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float tickDelta) {
        ModConfig.loadConfig();
        this.renderBackground(context);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, width / 2, height / 2 - 115, Color.WHITE.getRGB());
        Color BACKGROUND = new Color(166, 166, 166, 200);
        Color darkGray = new Color(100, 100, 100, 179);
        Color shadow = new Color(0, 0, 0, 255);
        Color light = new Color(168, 168, 168, 255);
        Color lighter = new Color(182, 182, 182, 255);
        int menuX = width/2+150;
        int menuX2 = width/2-150;
        int menuY = height/2+100;
        int menuY2 = height/2-100;
        context.fill(menuX, menuY, menuX2, menuY2, BACKGROUND.getRGB());
        context.fill(menuX, menuY, menuX2,menuY + 1, shadow.getRGB());
        context.fill(menuX, menuY, menuX2,menuY- 2, darkGray.getRGB());
        context.fill(menuX, menuY2, menuX2,menuY2- 1, light.getRGB());
        context.fill(menuX-2,menuY2,menuX2,menuY2+ 2, lighter.getRGB());
        context.fill(menuX,menuY+ 1,menuX + 1, menuY2, shadow.getRGB());
        context.fill(menuX,menuY-1,menuX -2, menuY2- 1, darkGray.getRGB());
        context.fill(menuX2,menuY,menuX2- 1, menuY2 - 1, light.getRGB());
        context.fill(menuX2,menuY,menuX2+2,menuY2 + 2, lighter.getRGB());

        context.fill(this.red.getX() + 1, this.red.getY() + 1, this.red.getX() + this.red.getWidth() - 1, this.red.getY() + this.red.getHeight() - 1, Color.OPAQUE);
        context.drawText(this.textRenderer, "Red: ", this.red.getX() + 20, this.red.getY() - 10, Color.RED.getRGB(), false);
        context.drawText(this.textRenderer, String.valueOf((int) this.red.getValue()), this.red.getX() + 42, this.red.getY() - 10, Color.WHITE.getRGB(), false);

        context.fill(this.green.getX() + 1, this.green.getY() + 1, this.green.getX() + this.green.getWidth() - 1, this.green.getY() + this.green.getHeight() - 1, Color.OPAQUE);
        context.drawText(this.textRenderer, "Green: ", this.green.getX() + 15, this.green.getY() - 10, Color.GREEN.getRGB(), false);
        context.drawText(this.textRenderer, String.valueOf((int) this.green.getValue()), this.green.getX() + 49, this.green.getY() - 10, Color.WHITE.getRGB(), false);

        context.fill(this.blue.getX() + 1, this.blue.getY() + 1, this.blue.getX() + this.blue.getWidth() - 1, this.blue.getY() + this.blue.getHeight() - 1, Color.OPAQUE);
        context.drawText(this.textRenderer, "Blue: ", this.blue.getX() + 20, this.blue.getY() - 10, Color.BLUE.getRGB(), false);
        context.drawText(this.textRenderer, String.valueOf((int) this.blue.getValue()), this.blue.getX() + 45, this.blue.getY() - 10, Color.WHITE.getRGB(), false);

        context.fill(this.alpha.getX() + 1, this.alpha.getY() + 1, this.alpha.getX() + this.alpha.getWidth() - 1, this.alpha.getY() + this.alpha.getHeight() - 1, Color.OPAQUE);
        Color alpha = new Color(245, 245, 245, 130);
        context.drawText(this.textRenderer, "Alpha: ", this.alpha.getX() + 15, this.alpha.getY() - 10, alpha.getRGB(), false);
        context.drawText(this.textRenderer, String.valueOf((int) this.alpha.getValue()), this.alpha.getX() + 46, this.alpha.getY() - 10, Color.WHITE.getRGB(), false);

        int x1 = width / 2 - 140;
        int x2 = width / 2 - 60;
        int y1 = height / 2 - 90;
        int y2 = height / 2 - 10;
        int redValue = (int) this.red.getValue();
        int greenValue = (int) this.green.getValue();
        int blueValue = (int) this.blue.getValue();
        int alphaValue = (int) this.alpha.getValue();
        Color hitboxColor = new Color(redValue, greenValue, blueValue, alphaValue);
        context.fill(x1, y1, x2, y2, hitboxColor.getRGB());
        context.fill(x1, y1, x2, y1 + 1, Color.WHITE.getRGB());
        context.fill(x1, y2 - 1, x2, y2, Color.WHITE.getRGB());
        context.fill(x1, y1, x1 + 1, y2, Color.WHITE.getRGB());
        context.fill(x2 - 1, y1, x2, y2, Color.WHITE.getRGB());
        super.render(context, mouseX, mouseY, tickDelta);
    }

    @Override
    public void renderBackground(DrawContext context) {
        super.renderBackground(context);
    }

    @Override
    public void close() {
        ModConfig.loadConfig();
        ModConfig.configData.setRed(red.getValue());
        ModConfig.configData.setGreen(green.getValue());
        ModConfig.configData.setBlue(blue.getValue());
        ModConfig.configData.setAlpha(alpha.getValue());
        ModConfig.saveConfig();
        client.setScreen(parent);
    }
}
