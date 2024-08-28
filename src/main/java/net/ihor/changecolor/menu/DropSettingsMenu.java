package net.ihor.changecolor.menu;

import net.ihor.changecolor.config.ModConfig;
import net.ihor.changecolor.menu.widgets.HSliderWidget;
import net.ihor.changecolor.util.Render;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

public class DropSettingsMenu extends Screen {

    private Screen screen;
    private HSliderWidget blue;
    private HSliderWidget red;
    private HSliderWidget green;
    private HSliderWidget alpha;
    public final Identifier MENU_BORDER = new Identifier( "changecolor", "textures/gui/border_texture.png");
    protected DropSettingsMenu(Screen screen) {
        super(Text.of("DropSettingsMenu"));
        this.screen = screen;
    }

    @Override
    protected void init() {
        Color red1 = new Color(255, 0, 0, 128);
        Color green1 = new Color(0, 255, 0, 128);
        Color blue1 = new Color(0, 0, 255, 128);
        Color gray1 = new Color(128, 128, 128, 128);
        red = new HSliderWidget(width / 2 - 2, height / 2 - 83, 60, 16, Text.empty(), ModConfig.configData.getDropColor()[0], 0.0f, 255.0f, red1, HSliderWidget.EntityType.DROP, HSliderWidget.ColorType.RED);
        green = new HSliderWidget(width / 2 - 2, height / 2 - 58, 60, 16, Text.empty(), ModConfig.configData.getDropColor()[1], 0.0f, 255.0f, green1, HSliderWidget.EntityType.DROP, HSliderWidget.ColorType.GREEN);
        blue = new HSliderWidget(width / 2 - 2, height / 2 - 33, 60, 16, Text.empty(), ModConfig.configData.getDropColor()[2], 0f, 255.0f, blue1, HSliderWidget.EntityType.DROP, HSliderWidget.ColorType.BLUE);
        alpha = new HSliderWidget(width / 2 - 2, height / 2 - 7, 60, 16, Text.empty(), ModConfig.configData.getDropColor()[3], 0.0f, 255.0f, gray1, HSliderWidget.EntityType.DROP, HSliderWidget.ColorType.ALPHA);
        ButtonWidget buttonWidget = ButtonWidget.builder(Text.of((ModConfig.configData.isEnableRenderDrop()) ? "§aEnabled" : "§cDisabled"), button -> {
            ModConfig.loadConfig();
            boolean flag = ModConfig.configData.isEnableRenderDrop();
            flag = !flag;
            ModConfig.configData.setEnableRenderDrop(flag);
            button.setMessage(Text.of(((ModConfig.configData.isEnableRenderDrop()) ? "§aEnabled" : "§cDisabled")));
            ModConfig.saveConfig();
        }).dimensions(width / 2 - 67, height / 2 - 7, 52, 16).build();
        ButtonWidget buttonWidget1 = ButtonWidget.builder(Text.of("Back"), button -> {
            client.setScreen(new MainMenu(this.screen));
        }).dimensions(width / 2 - 15, height / 2 + 12, 35, 12).build();
        this.addDrawableChild(red);
        this.addDrawableChild(green);
        this.addDrawableChild(blue);
        this.addDrawableChild(alpha);
        this.addDrawableChild(buttonWidget);
        this.addDrawableChild(buttonWidget1);
        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context);
        int x1 = width / 2 - 150 / 2;
        int x2 = width / 2 + 150 / 2;
        int y1 = height / 2 - 100;
        int y2 = height / 2 + 25;
        Color BACKGROUND = new Color(42, 39, 39, 200);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, width / 2, height / 2 - 115, Color.WHITE.getRGB());
        context.drawTexture(MENU_BORDER, x1 - 3, y1 - 3, 0, 0, 3560 / 2, 3, 3560 / 2, 256);
        context.drawTexture(MENU_BORDER, x1 - 3, y1 - 3,  0,  0, 3, 1524, 256, 1524);
        context.drawTexture(MENU_BORDER, x1 - 3, y2, 0, 0, 3560 / 2, 3, 3560 / 2, 256);
        context.drawTexture(MENU_BORDER, x2, y1 - 3, 0, 0, 3, 1524, 256, 1524);
        context.fill(x2, y2, x1, y1, BACKGROUND.getRGB());
        int r = (int) ModConfig.configData.getDropColor()[0];
        int g = (int) ModConfig.configData.getDropColor()[1];
        int b = (int) ModConfig.configData.getDropColor()[2];
        int a = (int) ModConfig.configData.getDropColor()[3];
        Color color = new Color(r, g, b, a);
        Render.drawShadowString(context, textRenderer, 0.7f, Text.of("§lColor:"), (float) width / 2 - 17, (float) height / 2 - 95, color.getRGB());
        Render.drawShadowString(context, textRenderer, 0.7f, Text.of("§r§c" + (int) red.getValue() + "§f, §r§a" + (int) green.getValue() + "§f, §r§9" + (int) blue.getValue() + "§f, §r§7" + (int) alpha.getValue()), (float) width / 2 + 11, (float) height / 2 - 95, Color.OPAQUE);
        Render.drawShadowString(context, textRenderer, 0.7f, Text.of("Render hitbox:"), (float) width / 2 - 66, (float) height / 2 - 17, Color.WHITE.getRGB());
        int redValue = (int) this.red.getValue();
        int greenValue = (int) this.green.getValue();
        int blueValue = (int) this.blue.getValue();
        int alphaValue = (int) this.alpha.getValue();
        int xx1 = width / 2 - 66;
        int xx2 = width / 2 - 15;
        int yy1 = height / 2 - 80;
        int yy2 = height / 2 - 29;
        Color hitboxColor = new Color(redValue, greenValue, blueValue, alphaValue);
        if (ModConfig.configData.isEnableRenderDrop()) {
            context.fill(xx1, yy1, xx2, yy2, hitboxColor.getRGB());
        } else {
            context.fill(xx1, yy1, xx2, yy2, Color.OPAQUE);
        }
        context.fill(xx1, yy1, xx2, yy1 + 1, Color.WHITE.getRGB());
        context.fill(xx1, yy2 - 1, xx2, yy2, Color.WHITE.getRGB());
        context.fill(xx1, yy1, xx1 + 1, yy2, Color.WHITE.getRGB());
        context.fill(xx2 - 1, yy1, xx2, yy2, Color.WHITE.getRGB());
        super.render(context, mouseX, mouseY, delta);
    }
}
