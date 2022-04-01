package de.minigamesworld.mcerde.core;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import de.minigamesworld.mcerde.core.listener.CustomMobListener;
import de.minigamesworld.mcerde.core.listener.SpawnLocManagement;
import de.minigamesworld.mcerde.core.util.CustomMob;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

import static de.minigamesworld.mcerde.core.util.ReflectionUtils.getRandomWithNeg;

public class McErdeCore extends JavaPlugin {

    private static McErdeCore instance;
    private BukkitTask task;
    public static Map<Entity, CustomMob> entities = new HashMap<>();
    public static int MobGroup_1 = 0;
    public static int MobGroup_2 = 0;

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        instance = this;

        this.getLogger().info("Loading Core!");

        PluginManager pluginManager = Bukkit.getPluginManager();
        ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();

        if (this.getConfig().get("PlayerManagement.DefaultSpawnLoc") == null) {
            Location loc = new Location(Bukkit.getWorld("world"), 1, 100, 1);

            this.getConfig().set("PlayerManagement.DefaultSpawnLoc", loc);
        }

        this.saveConfig();

        pluginManager.registerEvents(new SpawnLocManagement(), this);
        pluginManager.registerEvents(new CustomMobListener(), this);

        new BukkitRunnable() {
            Set<Entity> armorStand = CustomMobListener.damageIndec.keySet();
            List<Entity> remove = new ArrayList<>();

            @Override
            public void run() {
                for (Entity stand : armorStand) {
                    int leftShowTick = CustomMobListener.damageIndec.get(stand);

                    if (leftShowTick == 0) {
                        stand.remove();
                        remove.add(stand);
                        continue;
                    }

                    leftShowTick--;
                    CustomMobListener.damageIndec.put(stand, leftShowTick);
                }

                armorStand.removeAll(remove);
            }

        }.runTaskTimer(this, 0l, 1l);

        //do this in config?
        SpawnMobs(1, 5, 20, Bukkit.getWorld("world"), 0, 1, 0, 0);
        SpawnMobs(1, 5, 20, Bukkit.getWorld("world"), 3, 2, 0, 0);

        this.getLogger().info("LoadedCore!");
    }

    @Override
    public void onDisable() {

    }

    public static McErdeCore getInstance() {
        return instance;
    }

    public void SpawnMobs(int regionSize, int mobCap, int time, World world, int mobDefault, int id, double x, double z) {
        CustomMob[] mobTypes = CustomMob.values();

        task = new BukkitRunnable() {

            @Override
            public void run() {
                //TODO Make this less messy

                //Calc SpawnAmount
                int diff = 1;

                if(id == 1){
                    diff = mobCap - MobGroup_1;

                    if(MobGroup_1 >= mobCap){
                        return;
                    }

                }else if(id == 2){
                    diff = mobCap - MobGroup_2;

                    if(MobGroup_2 >= mobCap){
                        return;
                    }

                }else {
                    Bukkit.getLogger().warning("Id is not in register!");
                    return;
                }

                int spawnAmount = (int) (Math.random() * (diff + 1));
                int count = 0;

                //Spawning
                while (count <= spawnAmount) {
                    count++;
                    int ranX = getRandomWithNeg(regionSize), ranZ = getRandomWithNeg(regionSize);

                    Location loc2 = new Location(world, x, 100, z);
                    Location loc1 = loc2.clone().add(ranX, 0, ranZ);

                    Block block = world.getHighestBlockAt(loc1);
                    Location loc = block.getLocation().clone().add(0, 1, 0);

                    double random = Math.random() * 101, previous = 0;
                    CustomMob mobToSpawn = mobTypes[mobDefault];

                    for (CustomMob type : mobTypes) {
                        previous += type.getSpawnChance(id);

                        if (random <= previous) {
                            mobToSpawn = type;
                            break;
                        }
                    }

                    //TODO Fix that thing but maybe we don't need that

                    /*if(type.equals("Monster"))
                        if(!canSpawnMonster(loc))
                            continue;

                    if(type.equals("UnderWater"))
                        if(!canSpawnUnderWater(loc))
                            continue;

                    if(type.equals("Normal"))
                        if(!canSpawnNormal(loc))
                            continue;*/

                    //TODO Still messy...

                    if(id == 1){
                        MobGroup_1++;

                    } else if(id == 2){
                        MobGroup_2++;

                    }else{
                        Bukkit.getLogger().warning("Id is not in register!");
                        return;
                    }

                    //Container With all mobs that got spawned? <- May not be the best idea will see....
                    entities.put(mobToSpawn.spawn(loc), mobToSpawn);
                }

            }

        }.runTaskTimer(this, 0L, time);
    }

    private boolean canSpawnMonster(Location loc) {
        Block blockFeet = loc.getBlock();

        if (!(loc.getWorld().getTime() < 12300 || loc.getWorld().getTime() > 23850))
            return !blockFeet.isPassable() && !blockFeet.isLiquid();

        return false;
    }

    private boolean canSpawnUnderWater(Location loc) {
        Block blockFeet = loc.getBlock();

        return !blockFeet.isPassable() && blockFeet.isLiquid();
    }

    private boolean canSpawnNormal(Location loc) {
        Block blockFeet = loc.getBlock();

        if (loc.getWorld().getTime() > 0 && loc.getWorld().getTime() < 12300) {
            return !blockFeet.isPassable() && !blockFeet.isLiquid();
        }

        return false;
    }

}
