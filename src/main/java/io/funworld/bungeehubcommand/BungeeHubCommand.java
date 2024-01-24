package io.funworld.bungeehubcommand;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class BungeeHubCommand extends Plugin {
    String server_name,default_name;
    @Override
    public void onEnable() {
        try {
            if (!getDataFolder().exists())
                getDataFolder().mkdir();
            File file = new File(getDataFolder(), "config.yml");


            if (!file.exists()) {
                try (InputStream in = getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
            server_name = configuration.get("server","main");
            default_name = configuration.get("default","login");
            getProxy().getPluginManager().registerCommand(this, new HubCommand(this));
        }catch (IOException e){
            getLogger().warning("Plugin config load error!");
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
