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
                                    context.getSource().sendFeedback(Text.of("Используйте argb калькулятор: https://argb-int-calculator.netlify.app/"));
                                    context.getSource().sendFeedback(Text.of("В открывшеемся окне слева есть 4 поля: §fR§c(красный)§f; G§a(зеленый)§f; B§9(синий)§f; A§7(прозрачность)"));
                                    context.getSource().sendFeedback(Text.of("Вы указываете в этих полях значения от §b0 до 255"));
                                    context.getSource().sendFeedback(Text.of("После того, как вы выбрали цвет, вы запоминаете эти значения и прописываете команду"));
                                    context.getSource().sendFeedback(Text.of("/hitboxcolor setcolor <ваше значение для §cкрасного§f> <ваше значение для §aзеленого§f> <ваше значение для §9синего§f> <ваше значение для §7прозрачности§f(чем ниже, тем менее насыщение будет цвет)>"));
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
                                            + "; §9blue: §f" + ModConfig.configData.getBlue()
                                            + "; §agreen: §f" + ModConfig.configData.getGreen()
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
