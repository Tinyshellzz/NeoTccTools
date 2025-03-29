package tcc.youajing.tcctools.listener;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import tcc.youajing.tcctools.TccTools;

/**
 * 实现了一个监听器，用于处理玩家之间的伤害事件
 * 主要功能是：当玩家未使用武器对其他玩家造成伤害时，生成一个愤怒村民的粒子效果
 */
public class EntityDamageByEntityListener implements org.bukkit.event.Listener {
    /**
     * 处理玩家对玩家伤害事件
     * 当事件的伤害者和受害者都是玩家时，检查伤害者是否手持空气（即未使用武器）
     * 如果是空手，将在受害玩家上方生成一个愤怒村民的粒子效果
     *
     * @param event 实体伤害事件
     */
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        // 检查伤害来源和受伤实体是否均为玩家
        if (event.getDamager() instanceof Player damager && event.getEntity() instanceof Player damagedPlayer) {

            // 如果攻击者手持物为空，则在受伤玩家附近生成愤怒村民的粒子效果
            if (damager.getInventory().getItemInMainHand().getType() == Material.AIR) {
                damagedPlayer.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, damagedPlayer.getLocation().add(0, 2, 0), 1);
            }
        }
    }

}
