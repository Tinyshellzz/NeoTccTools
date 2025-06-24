package tcc.youajing.tcctools.listener;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;
import tcc.youajing.tcctools.config.PluginConfig;

public class BackCommandBlocker implements Listener {
    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        String message = event.getMessage(); // Full command with leading slash
        Player player = event.getPlayer();

        if (message.toLowerCase().startsWith("/back")) {
            double player_playTime = (double) player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000;
            if(player_playTime >= PluginConfig.back_play_time_for_new_players) {
                player.sendMessage(ChatColor.RED + "只有新玩家才能使用该命令! ");
                event.setCancelled(true); // This cancels the command
            }
        }
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        String buffer = event.getBuffer(); // Full command with leading slash
        CommandSender sender = event.getSender();
        if(sender instanceof Player player) {     // 不是玩家执行，则什么都不做
            if (buffer.startsWith("/back")) {
                double player_playTime = (double) player.getStatistic(Statistic.PLAY_ONE_MINUTE) / 72000;
                if (player_playTime >= PluginConfig.back_play_time_for_new_players) {
                    event.setCancelled(true); // block completions
                }
            }
        }
    }
}
