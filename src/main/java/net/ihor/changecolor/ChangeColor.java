package net.ihor.changecolor;

import net.fabricmc.api.ModInitializer;
import net.ihor.changecolor.command.Command;
import net.ihor.changecolor.config.ModConfig;

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
    }
}
