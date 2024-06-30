package net.ihor.changecolor;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.ihor.changecolor.command.Command;
import net.ihor.changecolor.config.ModConfig;
import net.ihor.changecolor.menu.Menu;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.io.IOException;

public class ChangeColor implements ModInitializer {

    @Override
    public void onInitialize() {
        Command.register();
        try {
            ModConfig.register();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (key.wasPressed()) {
                client.setScreen(new Menu(client.currentScreen));
            }
        });
    }

    private static final KeyBinding key = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.changecolor.openmenu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_F12,
            "key.category.changecolor.category"
    ));
}
