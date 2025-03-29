package tcc.youajing.tcctools.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tcc.youajing.tcctools.TccTools;
import tcc.youajing.tcctools.config.PluginConfig;

import java.util.List;
import java.util.Random;

public class EntityDeathListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        // 当实体死亡时，根据实体类型执行不同的操作
        if (event.getEntity() instanceof Raider) {
            Player killer = event.getEntity().getKiller();

            // 袭击队长击杀时，给药水效果
            if (killer != null) {
                Raider raider = (Raider) event.getEntity();
                if (!playerHasEffect(killer, PotionEffectType.BAD_OMEN) && !playerHasEffectByName(killer, "minecraft:raid_omen") && raider.isPatrolLeader()) {
                    int randomIntensity = new Random().nextInt(5) + 1;
                    // 3分钟的药水效果
                    killer.addPotionEffect(new PotionEffect(PotionEffectType.BAD_OMEN, 20 * 60 * 3, randomIntensity));
                }
            }
            // 限制末影龙击杀音效范围
        } else if (event.getEntity() instanceof EnderDragon) {
            event.getEntity().getWorld().getPlayers().stream()
                    .filter(player -> player.getWorld().equals(event.getEntity().getWorld()) &&
                            player.getLocation().distance(event.getEntity().getLocation()) <= PluginConfig.EnderDragonSoundRange)
                    .forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, 1F, 1F));
            // 用剑砍盔甲架不掉落
        } else if (event.getEntity() instanceof ArmorStand armorStand) {
            if (armorStand.getLastDamageCause() instanceof EntityDamageByEntityEvent damageEvent) {
                Entity damager = damageEvent.getDamager();

                if (damager instanceof Player player) {
                    ItemStack itemInHand = player.getInventory().getItemInMainHand();
                    if (itemInHand.getType().toString().endsWith("_SWORD")) {
                        List<ItemStack> drops = event.getDrops();
                        event.setCancelled(true);
                        Location location = event.getEntity().getLocation();

                        for (ItemStack drop : drops) {
                            if (!drop.getType().equals(Material.ARMOR_STAND)) {
                                location.getWorld().dropItemNaturally(location, drop);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean playerHasEffect(Player player, PotionEffectType effectType) {
        // Iterate through all active potion effects of the player
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType() == effectType) {
                return true; // The player has the specific potion effect
            }
        }
        return false; // The player does not have the specific potion effect
    }


    public boolean playerHasEffectByName(Player player, String effectType) {
        // Iterate through all active potion effects of the player
        for (PotionEffect effect : player.getActivePotionEffects()) {

            if (effect.getType().getName().equals(effectType)) {
                return true; // The player has the specific potion effect
            }
        }
        return false; // The player does not have the specific potion effect
    }
}

