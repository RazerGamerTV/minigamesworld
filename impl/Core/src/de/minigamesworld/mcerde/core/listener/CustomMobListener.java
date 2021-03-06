package de.minigamesworld.mcerde.core.listener;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import de.minigamesworld.mcerde.core.McErdeCore;
import de.minigamesworld.mcerde.core.util.CustomMob;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.*;
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
    public void handelEntityDeath(EntityDeathEvent e) {

        if (!McErdeCore.entities.containsKey(e.getEntity()))
            return;

        e.setDroppedExp(0);
        e.getDrops().clear();

        CustomMob mob = McErdeCore.entities.get(e.getEntity());

        if (mob.getId() == 1) {
            McErdeCore.MobGroup_1--;
        } else if (mob.getId() == 2) {
            McErdeCore.MobGroup_2--;
        } else {
            Bukkit.getLogger().warning("Id is not in register!");
            return;
        }

        McErdeCore.entities.remove(e.getEntity()).dropLoot(e.getEntity().getLocation());
    }

    @EventHandler
    public void handleEntityDamage(EntityDamageEvent e) {
        Entity rawEntity = e.getEntity();

        CustomMob mob = McErdeCore.entities.get(rawEntity);

        LivingEntity entity = (LivingEntity) rawEntity;
        double damage = e.getFinalDamage(), finalHealth = entity.getHealth() + entity.getAbsorptionAmount();

        Location loc = entity.getLocation().clone().add(0, 1, 0);

        loc.getWorld().spawn(loc, ArmorStand.class, armorStand -> {

            armorStand.setMarker(true);
            armorStand.setVisible(false);
            armorStand.setGravity(false);
            armorStand.setSmall(true);
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName("??c??l " + decimalFormat.format(damage));

            damageIndec.put(armorStand, 10);
        });

        if (!McErdeCore.entities.containsKey(rawEntity))
            return;


        if (finalHealth > damage) {
            //Survived hit

            finalHealth = finalHealth - damage;
            entity.setCustomName(mob.getName() + "??r??c " + (int) finalHealth + "??r??7??l???");
        } else
            entity.setCustomName(mob.getName() + " ??8Dead");

    }

}
