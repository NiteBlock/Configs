# Configs
Simple utility for loading/saving POJO configurations using Gson.

## Example
```java
import gg.bytes.configs.ConfigException;
import gg.bytes.configs.ConfigUtil;
import gg.bytes.configs.IConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Server {
    private Config config;

    public Server() {
        this.config = ConfigUtil.load(new File("config.json"), Config.class, Config::new);
    }

    public void save() {
        ConfigUtil.save(new File("config.json"), this.config);
    }

    public static class Config implements IConfig {
        private String name;
        private List<UUID> owners;

        public Config() {
            this.name = "";
            this.owners = new ArrayList<>();
        }

        @Override
        public void validate() throws ConfigException {
            if (this.name == null || this.name.isEmpty()) {
                throw new ConfigException("Name must not be an empty string");
            }

            if (this.owners == null || this.owners.isEmpty()) {
                throw new ConfigException("Owners must contain at least one uuid");
            }
        }
    }
}
```
