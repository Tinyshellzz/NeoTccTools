package tcc.youajing.tcctools.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class WelcomeCommand implements TabExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage(ChatColor.YELLOW + "参数不足");
            sender.sendMessage("Usage: /sendmessage <player>");
            return true;
        }

        if(sender instanceof Player) {
            Player senderPlayer = (Player) sender;

            if (args.length == 1) {
                String targetPlayerName = args[0];
                Player targetPlayer = Bukkit.getPlayer(targetPlayerName);

                if (targetPlayer != null && targetPlayer.isOnline()) {
                    // Send a message to the target player
                    // targetPlayer.sendMessage(ChatColor.GREEN + "welcome " + targetPlayerName + "！");
                    senderPlayer.chat("欢迎 " + targetPlayerName + "！");
                } else {
                    sender.sendMessage("§cPlayer not found or offline.");
                }

                return true;
            }
        } else {
            sender.sendMessage("§cThis command can only be used by a player.");
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
