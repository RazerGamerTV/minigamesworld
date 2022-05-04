package de.minigamesworld.mcerde.core.util;

import de.minigamesworld.mcerde.core.CustomMobs.LootItem;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public enum CustomMob {

    Zombie("§2Zombie", 20, 75, EntityType.ZOMBIE, null, null, false, 1, new LootItem(ReflectionUtils.createItem(Material.ROTTEN_FLESH , 1, false, "§7Rotten flesh", null), 80.0d, 1, 4)),
    Warrior_Zombie("§2Warrior-Zombie", 40, 24, EntityType.ZOMBIE, new ItemStack(Material.STONE_SWORD), ReflectionUtils.createArmorSet(new ItemStack(Material.LEATHER_HELMET), new ItemStack(Material.CHAINMAIL_CHESTPLATE), new ItemStack(Material.CHAINMAIL_LEGGINGS), new ItemStack(Material.LEATHER_BOOTS)), false, 1, new LootItem(ReflectionUtils.createItem(Material.ROTTEN_FLESH , 1, false, "§7Rotten flesh", null), 80.0d, 1, 4)),
    Alpha_Zombie("§cAlpha-Zombie", 80,1 , EntityType.ZOMBIE, new ItemStack(Material.IRON_SWORD), ReflectionUtils.createArmorSet(new ItemStack(Material.IRON_HELMET), new ItemStack(Material.DIAMOND_CHESTPLATE), new ItemStack(Material.IRON_LEGGINGS), new ItemStack(Material.DIAMOND_BOOTS)), true, 1, new LootItem(ReflectionUtils.createItem(Material.ZOMBIE_HEAD , 1, false, "§c§lAlpha-ZombieHead", null), 100.0d, 1, 1)),
    Spider("§7Spider", 15, 80, EntityType.SPIDER, null, null, false, 2, new LootItem(ReflectionUtils.createItem(Material.SPIDER_EYE, 1, false, "§7Spieder Eye", null), 80.0d, 1, 8)),
    FastSpider("§7SpeedySpider", 10, 20, EntityType.CAVE_SPIDER, null, null,  false, 2, new LootItem(ReflectionUtils.createItem(Material.SPIDER_EYE, 1, false, "§7Spieder Eye", null), 80.0d, 1, 8));

    private String name;
    private int id;
    private double maxHealth, spawnChance;
    private EntityType type;
    private ItemStack itemHolding;
    private ItemStack[] armor;
    private boolean hasTotem;
    private List<LootItem> lootTable;

    public static final CustomMob[] values = values();

    CustomMob(String name, double maxHealth, double spawnChance, EntityType type, ItemStack itemHolding, ItemStack[] armor, boolean hasTotem, int id, LootItem... lootItems){
        this.name = name;
        this.maxHealth = maxHealth;
        this.spawnChance = spawnChance;
        this.type = type;
        this.itemHolding = itemHolding;
        this.armor = armor;
        this.hasTotem = hasTotem;
        this.id = id;

        lootTable = Arrays.asList(lootItems);
    }

    public void dropLoot(Location loc){
        for (LootItem item : lootTable){
            item.dropItem(loc);
        }
    }

    public LivingEntity spawn(Location loc){
        LivingEntity entity = (LivingEntity) loc.getWorld().spawnEntity(loc, type);

        entity.setCustomNameVisible(true);
        entity.setCustomName(name + " §r§c" + (int) maxHealth + "§r§7§l❤");
        Objects.requireNonNull(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(maxHealth);
        entity.setHealth(maxHealth);

        EntityEquipment eq = entity.getEquipment();

        if(armor != null)
            eq.setArmorContents(armor);

        assert eq != null;

        eq.setHelmetDropChance(0f);
        eq.setChestplateDropChance(0f);
        eq.setLeggingsDropChance(0f);
        eq.setBootsDropChance(0f);

        eq.setItemInMainHand(itemHolding);

        if(hasTotem)
            eq.setItemInOffHand(new ItemStack(Material.TOTEM_OF_UNDYING));

        eq.setItemInMainHandDropChance(0f);
        eq.setItemInOffHandDropChance(0f);

        if(name.equals("§7SpeedySpider")) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 8, false, false));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 99999, 255, false, false));
        }

        if(name.equals("§7Spider"))
            entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999, 2, false, false));

        if(name.equals("§cAlpha-Zombie")) {
            entity.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, 4, false, false));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, 2, false, false));
            entity.addPotionEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 99999, 255, false, false));
        }

        return entity;
    }

    public String getName(){
        return name;
    }

    public double getMaxHealth(){
        return maxHealth;
    }

    public double getSpawnChance(int id){

        if(this.id == id){
            return spawnChance;
        }

        return 0;
    }

    public boolean hasTotem(){
        return hasTotem;
    }

    public int getId(){
        return id;
    }

}
