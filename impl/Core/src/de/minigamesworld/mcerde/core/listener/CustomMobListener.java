package de.minigamesworld.mcerde.core.listener;

import de.minigamesworld.mcerde.core.McErdeCore;
import de.minigamesworld.mcerde.core.util.CustomMob;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class CustomMobListener implements Listener {

    private DecimalFormat decimalFormat = new DecimalFormat("#.##");
    public static Map<Entity, Integer> damageIndec = new HashMap<>();

    @EventHandler
    public void handelEntityDeath(EntityDeathEvent e){



        if(!McErdeCore.entities.containsKey(e.getEntity()))
            return;

        e.setDroppedExp(0);
        e.getDrops().clear();
        McErdeCore.entities.remove(e.getEntity()).dropLoot(e.getEntity().getLocation());
    }

    @EventHandler
    public void handleEntityDamage(EntityDamageEvent e){
        Entity rawEntity = e.getEntity();
        CustomMob mob = McErdeCore.entities.get(rawEntity);
        LivingEntity entity = (LivingEntity)  rawEntity;
        double damage = e.getFinalDamage(), finalHealth = entity.getHealth() + entity.getAbsorptionAmount();

        Location loc = entity.getLocation().clone().add(0, 1, 0);

        loc.getWorld().spawn(loc, ArmorStand.class, armorStand ->{

            armorStand.setMarker(true);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName("§c§l " + decimalFormat.format(damage));

            damageIndec.put(armorStand, 10);
        });

        if(!McErdeCore.entities.containsKey(rawEntity))
            return;


        if(finalHealth > damage){
            //Survived hit

            finalHealth = finalHealth - damage;
            entity.setCustomName(mob.getName() +  "§r§c " + (int) finalHealth + "§r§7§l❤");
        }else
            entity.setCustomName(mob.getName() + " §8Dead");

    }

}
