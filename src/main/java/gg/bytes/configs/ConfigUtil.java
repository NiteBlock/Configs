package gg.bytes.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.function.Supplier;

public class ConfigUtil {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static <C extends IConfig> C load(File file, Class<C> type) throws ConfigException {
        try (FileReader reader = new FileReader(file)) {
            C config = gson.fromJson(reader, type);
            config.validate();
            return config;
        } catch (IOException ex) {
            throw new ConfigException(ex);
        }
    }

    public static <C extends IConfig> C load(File file, Class<C> type, Supplier<C> supplier) throws ConfigException {
        C config;

        try (FileReader reader = new FileReader(file)) {
            config = gson.fromJson(reader, type);
        } catch (FileNotFoundException ex) {
            config = supplier.get();
            save(file, config);
        } catch (IOException ex) {
            throw new ConfigException(ex);
        }

        config.validate();
        return config;
    }

    public static void save(File file, IConfig config) throws ConfigException {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(gson.toJson(config));
        } catch (IOException ex) {
            throw new ConfigException(ex);
        }
    }
}
