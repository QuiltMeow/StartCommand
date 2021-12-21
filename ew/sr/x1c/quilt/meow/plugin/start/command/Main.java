package ew.sr.x1c.quilt.meow.plugin.start.command;

import java.io.File;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info("正在啟用 Start Command 插件 ...");

        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            saveDefaultConfig();
            getLogger().info("找不到設定檔案 正在建立 ...");
        }

        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getCommand("startcommand").setExecutor(new StartCommandControl());

        int wait = getConfig().getInt("wait-before-start-command-second");
        registerStartCommandTask(wait);
    }

    public static void registerStartCommandTask(int wait) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getPlugin(), () -> {
            List<String> commandList = Main.getPlugin().getConfig().getStringList("command");
            commandList.forEach(command -> {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
            });
        }, wait * 20);
    }

    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onDisable() {
        getLogger().info("正在關閉插件 ...");
    }
}
