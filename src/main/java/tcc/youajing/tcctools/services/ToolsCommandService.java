package tcc.youajing.tcctools.services;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;
import tcc.youajing.tcctools.config.PluginConfig;
import tcc.youajing.tcctools.utils.Rcon.RconClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static tcc.youajing.tcctools.ObjectPool.mcPlayerMapper;

public class ToolsCommandService {
    public boolean debris(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.YELLOW + "用法: /neotcc debris <名称>");
            return true;
        }

        if(mcPlayerMapper.exists_name(args[1])) {
            sender.sendMessage("" + mcPlayerMapper.get_user_by_name(args[1]).debris_mined);;
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "该玩家不存在！");
        }

        return true;
    }

    public boolean fish(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length < 2) {
            sender.sendMessage(ChatColor.YELLOW + "用法: /neotcc debris <名称>");
            return true;
        }


        if(mcPlayerMapper.exists_name(args[1])) {
            sender.sendMessage("" + mcPlayerMapper.get_user_by_name(args[1]).fished_times);
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "该玩家不存在！");
        }

        return true;
    }

    public boolean stop(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Matcher _m = Pattern.compile("^.*CraftRemoteConsoleCommandSender.*$").matcher(sender.toString());
        if(!(sender instanceof ConsoleCommandSender || _m.find() || sender.isOp())){
            sender.sendMessage("只有控制台和op才能使用该命令");
            return true;
        }

        RconClient rconClient = new RconClient(PluginConfig.server_ip, PluginConfig.rcon_port, PluginConfig.rcon_password);
        rconClient.sendRconCommand("kick tinyshellzzz");

        return true;
    }
}
