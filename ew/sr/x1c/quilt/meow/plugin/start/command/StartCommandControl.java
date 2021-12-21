package ew.sr.x1c.quilt.meow.plugin.start.command;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommandControl implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command commandPrefix, String command, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.GREEN + "Star Command " + Main.getPlugin().getDescription().getVersion());
            sender.sendMessage(ChatColor.BLUE + "/startcommand reload " + ChatColor.GOLD + "重新載入設定檔案");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("start.command.reload")) {
                sender.sendMessage(ChatColor.RED + "權限不足 無法執行該操作 !");
                return true;
            }
            Main.getPlugin().reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "設定檔案重載完成");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "輸入指令錯誤");
        return false;
    }
}
