package tcc.youajing.tcctools.listener;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import tcc.youajing.tcctools.config.PluginConfig;

/**
 * 防末影龙以及恶灵破坏方块
 */
public class EntityExplodeListener implements Listener {
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Creeper) {
            // 取消方块破坏但保留其他效果，并添加闪光粒子效果
            event.blockList().clear();
            World world = event.getLocation().getWorld();
            Location location = event.getLocation();
            world.spawnParticle(Particle.FLASH, location, 1);
        } else if (entity instanceof Fireball fireball) {
            // 检查实体是否是恶魂、末影龙或凋零发射的火球
            Entity shooter = (Entity) fireball.getShooter();
            if (shooter instanceof Ghast || shooter instanceof EnderDragon || shooter instanceof Wither) {
                event.blockList().clear();
            }
        } else if (entity instanceof EnderDragon) {
            // 取消末影龙爆炸造成的方块破坏
            event.blockList().clear();
        } else if (entity instanceof Wither) {
            // 播放凋零生成声音
            event.getLocation().getWorld().getPlayers().stream()
                    .filter(player -> player.getWorld().equals(event.getLocation().getWorld()) &&
                            player.getLocation().distance(event.getLocation()) <= PluginConfig.WitherSoundRange)
                    .forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1F, 1F));
        }
    }

}
