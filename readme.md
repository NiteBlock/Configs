# Configs
Simple utility for loading/saving POJO configurations using Gson.

## Example
```java
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
        private Set<UUID> owners;

        @Override
        public void validate() throws ConfigException {
            if (this.name.isEmpty()) {
                throw new ConfigException("Name must not be blank");
            } 

            if (this.owners == null || this.owners.isEmpty()) {
                throw new ConfigException("Owners must contain at least one uuid");
            }
        }
    }
}
```
