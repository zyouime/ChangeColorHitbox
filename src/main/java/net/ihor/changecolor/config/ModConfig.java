package net.ihor.changecolor.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class ModConfig {

    public static final Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    public static final File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "changecolor-config.json");
    public static ConfigData configData;



    public static void loadConfig() {
        try (FileReader fileReader = new FileReader(file)) {
            ConfigData configData1 = gson.fromJson(fileReader, ConfigData.class);
            if (configData1 != null) {
                configData = configData1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveConfig() {
        try (FileWriter fileWriter = new FileWriter(file)) {
            gson.toJson(configData, fileWriter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void register() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
            try (FileWriter fileWriter = new FileWriter(file)) {
                ConfigData defaultConfigData = new ConfigData();
                gson.toJson(defaultConfigData, fileWriter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            loadConfig();
        }
    }
}
