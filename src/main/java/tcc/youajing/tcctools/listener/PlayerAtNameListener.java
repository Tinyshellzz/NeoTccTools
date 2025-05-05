package tcc.youajing.tcctools.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Collection;

public class PlayerAtNameColorListener implements Listener {
    /**
     * @+ID 加粗，并向玩家发送音效通知
     * @param event
     */
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String message = event.getMessage();
        Collection<? extends Player> onlinePlayers = Bukkit.getServer().getOnlinePlayers();
        for (Player p : onlinePlayers) {
            message = message.replace('@'+p.getName(), ChatColor.GREEN + "@" + ChatColor.BOLD  + p.getName() + ChatColor.RESET);
            p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_WORK_TOOLSMITH, 1f, 1f);
        }

        event.setMessage(message);
    }
}
