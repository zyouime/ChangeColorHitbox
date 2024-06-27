package net.ihor.changecolor.command;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.ihor.changecolor.config.ModConfig;
import net.minecraft.text.Text;

public class Command {

    private static float red;
    private static float green;
    private static float blue;
    private static float alpha;

    public static void register() {
        ModConfig.loadConfig();
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(
                ClientCommandManager.literal("hitboxcolor")
                        .then(ClientCommandManager.literal("help")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.of("Зайдите в браузер и впишите в поиск §f'§bcolor picker§f'"));
                                    context.getSource().sendFeedback(Text.of("После выбора цвета ниже есть окошко RGB в котором указаны 3 числа вашего цвета"));
                                    context.getSource().sendFeedback(Text.of("Вы запоминаете эти числа и вводите в таком же порядке в команду ниже:"));
                                    context.getSource().sendFeedback(Text.of("/hitboxcolor setcolor <первое число> <второе число> <третье число> <уровень прозрачности от 0 до 255(чем ниже, тем менее видно будет хитбокс)>"));
                                    return 1;
                                }))
                        .then(ClientCommandManager.literal("setcolor")
                                .then(ClientCommandManager.argument("red", FloatArgumentType.floatArg(0.0f, 255.0f))
                                        .then(ClientCommandManager.argument("green", FloatArgumentType.floatArg(0.0f, 255.0f))
                                                .then(ClientCommandManager.argument("blue", FloatArgumentType.floatArg(0.0f, 255.0f))
                                                        .then(ClientCommandManager.argument("alpha", FloatArgumentType.floatArg(0.0f, 255.0f))
                                                                .executes(context -> {
                                                                    red = context.getArgument("red", Float.class);
                                                                    green = context.getArgument("green", Float.class);
                                                                    blue = context.getArgument("blue", Float.class);
                                                                    alpha = context.getArgument("alpha", Float.class);
                                                                    ModConfig.configData.setRed(red);
                                                                    ModConfig.configData.setGreen(green);
                                                                    ModConfig.configData.setBlue(blue);
                                                                    ModConfig.configData.setAlpha(alpha);
                                                                    ModConfig.saveConfig();
                                                                    return 1;
                                                                }))))))
                        .then(ClientCommandManager.literal("getColor")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.of("§cred: §f" + ModConfig.configData.getRed()
                                            + "; §agreen: §f" + ModConfig.configData.getGreen()
                                            + "; §9blue: §f" + ModConfig.configData.getBlue()
                                            + "; §7alpha: §f" + ModConfig.configData.getAlpha()));
                                    return 1;
                                }))
                        .then(ClientCommandManager.literal("renderEyes")
                                .then(ClientCommandManager.argument("render", BoolArgumentType.bool())
                                        .executes(context -> {
                                            ModConfig.configData.setRenderEyes(context.getArgument("render", Boolean.class));
                                            ModConfig.saveConfig();
                                            return 1;
                                        })))
                        .then(ClientCommandManager.literal("renderDirection")
                                .then(ClientCommandManager.argument("render", BoolArgumentType.bool())
                                        .executes(context -> {
                                            ModConfig.configData.setRenderDirection(context.getArgument("render", Boolean.class));
                                            ModConfig.saveConfig();
                                            return 1;
                                        })))
        ));
    }
}
