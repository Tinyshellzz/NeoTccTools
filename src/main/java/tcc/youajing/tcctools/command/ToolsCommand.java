package tcc.youajing.tcctools.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tcc.youajing.tcctools.config.PluginConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static tcc.youajing.tcctools.ObjectPool.toolsCommandService;

public class ToolsCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage(ChatColor.YELLOW + "参数不足");
            return true;
        }

        String subcommand = args[0].toLowerCase();
        if(subcommand.equals("reload")) {
            PluginConfig.reload();
            return true;
        } else if (subcommand.equals("debris")) {
            return toolsCommandService.debris(commandSender, command, s, args);
        } else if (subcommand.equals("fish")) {
            return toolsCommandService.fish(commandSender, command, s, args);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        // 判断命令发送者是否是玩家
        if (!(sender instanceof Player)) {
            return null;
        }

        // 判断命令参数的长度
        if (args.length == 1) {
            // 如果只有一个参数，返回所有子命令的列表
            return Arrays.asList("debris", "fish");
        } else if (args.length == 2) {
            // 如果有两个参数，根据第一个参数返回不同的补全列表
            String subcommand = args[0].toLowerCase();

            if(subcommand.equals("debris") || subcommand.equals("fish")) {
                List<String> players = new ArrayList<>();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    players.add(p.getName());
                }
                // 返回所有玩家昵称
                return players;
            }
        }

        return null;
    }
}
