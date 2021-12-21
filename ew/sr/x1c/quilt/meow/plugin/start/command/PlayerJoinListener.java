package ew.sr.x1c.quilt.meow.plugin.start.command;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerLogin(PlayerJoinEvent event) {
        FileConfiguration config = Main.getPlugin().getConfig();
        String playerCommandEnable = config.getString("player-join-command-enable");
        if (playerCommandEnable.equalsIgnoreCase("true")) {
            String playerExecute = config.getString("execute-as-player");
            List<String> commandList = config.getStringList("player-join-command");

            Player player = event.getPlayer();
            CommandSender sender = playerExecute.equalsIgnoreCase("true") ? player : Bukkit.getConsoleSender();
            commandList.forEach(command -> {
                command = command.replace("%player_name%", player.getName());
                Bukkit.dispatchCommand(sender, command);
            });
        }
    }
}
