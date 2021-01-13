package gg.bytes.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Supplier;

public class ConfigUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <C extends IConfig> C load(File file, Class<C> type, Supplier<C> supplier) throws IOException, ConfigException {
        C config;

        try (FileReader reader = new FileReader(file)) {
            config = gson.fromJson(reader, type);
        } catch (IOException ex) {
            config = supplier.get();
            save(file, config);
        }

        config.validate();
        return config;
    }

    public static void save(File file, IConfig config) throws IOException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(gson.toJson(config));
        }
    }
}
