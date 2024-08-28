package net.ihor.changecolor.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.ihor.changecolor.config.ModConfig;
import net.ihor.changecolor.menu.widgets.CButtonWidget;
import net.ihor.changecolor.menu.widgets.IButtonWidget;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

public class MainMenu extends Screen {
    private Screen parent;
    public final Identifier MENU_BORDER = new Identifier( "changecolor", "textures/gui/border_texture.png");

    public MainMenu(Screen screen) {
        super(Text.of("Menu"));
        this.parent = screen;
    }

    @Override
    protected void init() {
        int menuX = width / 2 + 150 / 2;
        int menuX2 = width / 2 - 150 / 2;
        ButtonWidget buttonWidget1 = new IButtonWidget(width / 2 - 75, height / 2 - 100, menuX - menuX2, 20, Text.empty(), button -> {}, IButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        ButtonWidget buttonWidget2 = new IButtonWidget(width / 2 - 75, height / 2 - 70, menuX - menuX2, 20, Text.empty(), button -> {}, IButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        ButtonWidget buttonWidget3 = new IButtonWidget(width / 2 - 75, height / 2 - 40, menuX - menuX2, 20, Text.empty(), button -> {}, IButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        ButtonWidget buttonWidget4 = new IButtonWidget(width / 2 - 75, height / 2 - 10, menuX - menuX2, 20, Text.empty(), button -> {}, IButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        ButtonWidget buttonWidget5 = new IButtonWidget(width / 2 - 75, height / 2 + 20, menuX - menuX2, 20, Text.empty(), button -> {}, IButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        ButtonWidget buttonWidget6 = new IButtonWidget(width / 2 - 75, height / 2 + 50, menuX - menuX2, 20, Text.empty(), button -> {}, IButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        ButtonWidget buttonWidget7 = new IButtonWidget(width / 2 - 75, height / 2 + 80, menuX - menuX2, 20, Text.empty(), button -> {}, IButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        ButtonWidget buttonWidget8 = new CButtonWidget(width / 2 + 50, height / 2 - 38, 14, 14, Text.empty(), button -> {
            client.setScreen(new PlayerSettingsMenu(this.parent));
        }, CButtonWidget.DEFAULT_NARRATION_SUPPLIER, CButtonWidget.EntityTypeColor.PLAYER);
        ButtonWidget buttonWidget9 = new CButtonWidget(width / 2 + 50, height / 2 - 8, 14, 14, Text.empty(), button -> {
            client.setScreen(new PassiveSettingsMenu(this.parent));
        }, CButtonWidget.DEFAULT_NARRATION_SUPPLIER, CButtonWidget.EntityTypeColor.PASSIVE);
        ButtonWidget buttonWidget10 = new CButtonWidget(width / 2 + 50, height / 2 + 22, 14, 14, Text.empty(), button -> {
            client.setScreen(new MonsterSettingsMenu(this.parent));
        }, CButtonWidget.DEFAULT_NARRATION_SUPPLIER, CButtonWidget.EntityTypeColor.MONSTER);
        ButtonWidget buttonWidget11 = new CButtonWidget(width / 2 + 50, height / 2 + 52, 14, 14, Text.empty(), button -> {
            client.setScreen(new DropSettingsMenu(this.parent));
        }, CButtonWidget.DEFAULT_NARRATION_SUPPLIER, CButtonWidget.EntityTypeColor.DROP);
        ButtonWidget buttonWidget12 = new CButtonWidget(width / 2 + 50, height / 2 + 82, 14, 14, Text.empty(), button -> {
            client.setScreen(new ProjectileSettingsMenu(this.parent));
        }, CButtonWidget.DEFAULT_NARRATION_SUPPLIER, CButtonWidget.EntityTypeColor.PROJECTILE);
        ButtonWidget buttonWidget13 = ButtonWidget.builder(Text.of((ModConfig.configData.isRenderEyes()) ? "§aEnabled" : "§cDisabled"), button -> {
            ModConfig.loadConfig();
            boolean flag = ModConfig.configData.isRenderEyes();
            flag = !flag;
            ModConfig.configData.setRenderEyes(flag);
            button.setMessage(Text.of(((ModConfig.configData.isRenderEyes()) ? "§aEnabled" : "§cDisabled")));
            ModConfig.saveConfig();
        }).dimensions(width / 2 + 22, height / 2 - 99, 50, 17).build();
        ButtonWidget buttonWidget14 = ButtonWidget.builder(Text.of((ModConfig.configData.isRenderDirection()) ? "§aEnabled" : "§cDisabled"), button -> {
            ModConfig.loadConfig();
            boolean flag = ModConfig.configData.isRenderDirection();
            flag = !flag;
            ModConfig.configData.setRenderDirection(flag);
            button.setMessage(Text.of(((ModConfig.configData.isRenderDirection()) ? "§aEnabled" : "§cDisabled")));
            ModConfig.saveConfig();
        }).dimensions(width / 2 + 22, height / 2 - 69, 50, 17).build();
        buttonWidget1.active = false;
        buttonWidget2.active = false;
        buttonWidget3.active = false;
        buttonWidget4.active = false;
        buttonWidget5.active = false;
        buttonWidget6.active = false;
        buttonWidget7.active = false;
        this.addDrawableChild(buttonWidget1);
        this.addDrawableChild(buttonWidget2);
        this.addDrawableChild(buttonWidget3);
        this.addDrawableChild(buttonWidget4);
        this.addDrawableChild(buttonWidget5);
        this.addDrawableChild(buttonWidget6);
        this.addDrawableChild(buttonWidget7);
        this.addDrawableChild(buttonWidget8);
        this.addDrawableChild(buttonWidget9);
        this.addDrawableChild(buttonWidget10);
        this.addDrawableChild(buttonWidget11);
        this.addDrawableChild(buttonWidget12);
        this.addDrawableChild(buttonWidget13);
        this.addDrawableChild(buttonWidget14);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float tickDelta) {
        this.renderBackground(context);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, width / 2, height / 2 - 115, Color.WHITE.getRGB());
        Color BACKGROUND = new Color(42, 39, 39, 200);
        int menuX = width / 2 + 150 / 2;
        int menuX2 = width / 2 - 150 / 2;
        int menuY = height / 2 + 100;
        int menuY2 = height / 2 - 100;
        ItemStack experienceBottle = new ItemStack(Items.EXPERIENCE_BOTTLE);
        ItemStack playerHead = new ItemStack(Items.PLAYER_HEAD);
        ItemStack enderPearl = new ItemStack(Items.ENDER_PEARL);
        ItemStack zombieHead = new ItemStack(Items.ZOMBIE_HEAD);
        ItemStack tropicalFish = new ItemStack(Items.TROPICAL_FISH);
        experienceBottle.addEnchantment(Enchantments.AQUA_AFFINITY, 1);
        context.drawTexture(MENU_BORDER, menuX2 - 3, menuY2 - 3, 0, 0, 3560 / 2, 3, 3560 / 2, 256);
        context.drawTexture(MENU_BORDER, menuX2 - 3, menuY2 - 3, 0, 0, 3, 2396, 256, 2396);
        context.drawTexture(MENU_BORDER, menuX2 - 3, menuY, 0, 0, 3560 / 2, 3, 3560 / 2, 256);
        context.drawTexture(MENU_BORDER, menuX, menuY2 - 3, 0, 0, 3, 2396, 256, 2396);
        context.fill(menuX, menuY, menuX2, menuY2, BACKGROUND.getRGB());
        context.drawTextWithShadow(textRenderer, Text.of("§lEye hitbox"), width / 2 - 70, height / 2 - 95, Color.WHITE.getRGB());
        context.drawTextWithShadow(textRenderer, Text.of("§lViewing line"), width / 2 - 70, height / 2 - 65, Color.WHITE.getRGB());
        context.drawTextWithShadow(textRenderer, Text.of("§6§lPlayers"), width / 2 - 70, height / 2 - 35, Color.WHITE.getRGB());
        context.drawTextWithShadow(textRenderer, Text.of("§d§lPassiveMobs"), width / 2 - 70, height / 2 - 5, Color.WHITE.getRGB());
        context.drawTextWithShadow(textRenderer, Text.of("§2§lMonsters"), width / 2 - 70, height / 2 + 25, Color.WHITE.getRGB());
        context.drawTextWithShadow(textRenderer, Text.of("§e§lDrop"), width / 2 - 70, height / 2 + 55, Color.WHITE.getRGB());
        context.drawTextWithShadow(textRenderer, Text.of("§b§lProjectile"), width / 2 - 70, height / 2 + 85, Color.WHITE.getRGB());
        context.drawItem(enderPearl, width / 2 - 8, height / 2 + 81);
        context.drawItem(experienceBottle, width / 2 - 41, height / 2 + 51);
        context.drawItem(zombieHead, width / 2 - 15, height / 2 + 21);
        context.drawItem(tropicalFish, width / 2 + 4, height / 2 - 8);
        context.drawItem(playerHead, width / 2 - 23, height / 2 - 39);
        super.render(context, mouseX, mouseY, tickDelta);
    }

    @Override
    public void close() {
        client.setScreen(parent);
    }
}
