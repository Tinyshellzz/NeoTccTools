package tcc.youajing.tcctools.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Collection;

public class PlayerAtNameListener implements Listener {
    /**
     * @+ID 加粗，并向玩家发送音效通知
     * @param event
     */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
        for (Player p : onlinePlayers) {
            int i = message.toLowerCase().indexOf('@'+p.getName().toLowerCase());
            if(i != -1) {
                message = message.substring(0, i) + ChatColor.GREEN + ChatColor.BOLD + message.substring(i, i+1+p.getName().length()) + ChatColor.GRAY + message.substring(i+1+p.getName().length());
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f);

            }
        }

        event.setMessage(message);
    }
}
