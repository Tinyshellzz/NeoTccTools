package tcc.youajing.tcctools.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import tcc.youajing.tcctools.TccTools;

/**
 * 防耕地破坏
 */
public class EntityInteractListener implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        // 检查点击的方块是否为耕地，并且动作是否为物理踩踏
        if (event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.FARMLAND && event.getAction() == Action.PHYSICAL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityInteract(EntityInteractEvent event) {
        // 检查实体交互的方块是否为耕地
        if (event.getBlock().getType() == Material.FARMLAND) {
            event.setCancelled(true);
        }
    }
}
