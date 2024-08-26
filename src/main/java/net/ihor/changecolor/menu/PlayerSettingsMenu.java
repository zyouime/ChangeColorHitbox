package net.ihor.changecolor.menu;

import net.ihor.changecolor.config.ModConfig;
import net.ihor.changecolor.menu.entity.RenderEntityToMenu;
import net.ihor.changecolor.menu.widgets.HSliderWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

public class PlayerSettingsMenu extends Screen {
    private Screen screen;
    private HSliderWidget blue;
    private HSliderWidget red;
    private HSliderWidget green;
    private HSliderWidget alpha;
    public final Identifier MENU_BORDER = new Identifier( "changecolor", "textures/gui/border_texture.png");

    public PlayerSettingsMenu(Screen screen) {
        super(Text.of("PlayerSettingsMenu"));
        this.screen = screen;
    }

    @Override
    protected void init() {
        Color red1 = new Color(255, 0, 0, 128);
        Color green1 = new Color(0, 255, 0, 128);
        Color blue1 = new Color(0, 0, 255, 128);
        Color gray1 = new Color(128, 128, 128, 128);
        red = new HSliderWidget(width / 2 - 2, height / 2 - 83, 60, 16, Text.empty(), ModConfig.configData.getPlayerColor()[0], 0.0f, 255.0f, red1, HSliderWidget.EntityType.PLAYER, HSliderWidget.ColorType.RED);
        green = new HSliderWidget(width / 2 - 2, height / 2 - 58, 60, 16, Text.empty(), ModConfig.configData.getPlayerColor()[1], 0.0f, 255.0f, green1, HSliderWidget.EntityType.PLAYER, HSliderWidget.ColorType.GREEN);
        blue = new HSliderWidget(width / 2 - 2, height / 2 - 33, 60, 16, Text.empty(), ModConfig.configData.getPlayerColor()[2], 0f, 255.0f, blue1, HSliderWidget.EntityType.PLAYER, HSliderWidget.ColorType.BLUE);
        alpha = new HSliderWidget(width / 2 - 2, height / 2 - 7, 60, 16, Text.empty(), ModConfig.configData.getPlayerColor()[3], 0.0f, 255.0f, gray1, HSliderWidget.EntityType.PLAYER, HSliderWidget.ColorType.ALPHA);
        ButtonWidget buttonWidget = ButtonWidget.builder(Text.of((ModConfig.configData.isEnableRenderPlayer()) ? "§aEnabled" : "§cDisabled"), button -> {
            ModConfig.loadConfig();
            boolean flag = ModConfig.configData.isEnableRenderPlayer();
            flag = !flag;
            ModConfig.configData.setEnableRenderPlayer(flag);
            button.setMessage(Text.of(((ModConfig.configData.isEnableRenderPlayer()) ? "§aEnabled" : "§cDisabled")));
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
        int r = (int) ModConfig.configData.getPlayerColor()[0];
        int g = (int) ModConfig.configData.getPlayerColor()[1];
        int b = (int) ModConfig.configData.getPlayerColor()[2];
        int a = (int) ModConfig.configData.getPlayerColor()[3];
        Color color = new Color(r, g, b, a);
        drawShadowString(context, 0.7f, Text.of("§lColor:"), (float) width / 2 - 17, (float) height / 2 - 95, color.getRGB());
        drawShadowString(context, 0.7f, Text.of("§r§c" + (int) red.getValue() + "§f, §r§a" + (int) green.getValue() + "§f, §r§9" + (int) blue.getValue() + "§f, §r§7" + (int) alpha.getValue()), (float) width / 2 + 11, (float) height / 2 - 95, Color.OPAQUE);
        drawShadowString(context, 0.7f, Text.of("Render hitbox:"), (float) width / 2 - 66, (float) height / 2 - 17, Color.WHITE.getRGB());
        RenderEntityToMenu.drawEntity(width / 2 - 43, height / 2 - 30, 1.5f * 20, client.player);
        super.render(context, mouseX, mouseY, delta);
    }
    public void drawShadowString(DrawContext context, float scale, Text text, float x, float y, int color) {
        context.getMatrices().push();
        context.getMatrices().scale(scale, scale, scale);
        context.drawTextWithShadow(textRenderer, text, (int) (x / scale), (int) (y / scale), color);
        context.getMatrices().pop();
    }
}
