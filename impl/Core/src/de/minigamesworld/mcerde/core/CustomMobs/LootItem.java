package de.minigamesworld.mcerde.core.CustomMobs;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class LootItem {

    private ItemStack item;
    private int min = 1, max = 1;
    private double dropRate;
    private static Random randomiser = new Random();

    public LootItem(ItemStack item, double spawnChance){
        this.item = item;
        this.dropRate = spawnChance;
    }

    public LootItem(ItemStack item, double spawnChance, int min, int max){
        this.item = item;
        this.dropRate = spawnChance;
        this.min = min;
        this.max = max;
    }

    public void dropItem(Location loc){
        if(Math.random() * 101 > dropRate)
            return;

        int amount = randomiser.nextInt(max-min +1) + min;
        if(amount == 0) amount = 1;
        ItemStack item = this.item.clone();
        item.setAmount(amount);

        loc.getWorld().dropItemNaturally(loc, item);
    }

}
