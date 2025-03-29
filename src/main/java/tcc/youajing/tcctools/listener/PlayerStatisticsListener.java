package tcc.youajing.tcctools.listener;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import tcc.youajing.tcctools.database.MCPlayerMapper;
import tcc.youajing.tcctools.services.MCPlayerManager;

import java.util.UUID;

import static tcc.youajing.tcctools.ObjectPool.mcPlayerMapper;
import static tcc.youajing.tcctools.ObjectPool.placedDebrisMapper;

/**
 * 统计钓鱼数，以及残骸挖掘数
 */
public class PlayerStatisticsListener implements Listener {
    @EventHandler
    public void onPlayerFished(PlayerFishEvent event) {
        Player player = event.getPlayer();

        // 如果玩家抓到鱼, 则统计钓鱼数+1
        if(event.getCaught() != null) {
            MCPlayerManager.add_fished_times(player, 1);
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Location location = event.getBlock().getLocation();

        // 如果玩家挖到自然生成的残骸, 统计数+1
        if(event.getBlock().getType() == Material.ANCIENT_DEBRIS) {
            if (!placedDebrisMapper.exists(location)) {
                MCPlayerManager.add_debris_mined(player, 1);
            } else {
                placedDebrisMapper.remove(location);
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Location location = event.getBlock().getLocation();

        // 记录玩家放置的残骸
        if (event.getBlock().getType() == Material.ANCIENT_DEBRIS) {
            Location loc = event.getBlock().getLocation();
            placedDebrisMapper.insert(loc);
        }
    }
}
