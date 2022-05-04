package de.minigamesworld.mcerde.core.listener;

import de.minigamesworld.mcerde.core.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class CraftingEvent implements Listener {

     @EventHandler
     public void handelCraftingPrepare(PrepareItemCraftEvent e){
         if(e.getInventory().getResult() == null)
             return;

         if(e.getInventory().getResult().equals(new ItemBuilder(Material.HONEYCOMB).setName("§2Advanced Circuit").buildItem())){
             int times = 0;

             for(ItemStack i : e.getInventory().getMatrix()){
                 if(i.equals(new ItemBuilder(Material.HONEYCOMB).setName("§7Electrical Component").buildItem())){
                     times++;

                 }else
                     e.getInventory().setResult(new ItemStack(Material.AIR));
             }

             if(times >= 4){
                 e.getInventory().setResult(new ItemBuilder(Material.HONEYCOMB).setName("§2Advanced Circuit").buildItem());
                 
             }else
                 e.getInventory().setResult(new ItemStack(Material.AIR));

         }else if(e.getInventory().getResult().equals(new ItemBuilder(Material.IRON_BLOCK).setName("§7HardenedSteelBlock").buildItem())){
             int times = 0;

             for(ItemStack i : e.getInventory().getStorageContents()){
                 if(i.equals(new ItemBuilder(Material.IRON_BLOCK).setName("§7SteelBlock").buildItem())){
                     times++;

                 }else
                     e.getInventory().setResult(new ItemStack(Material.AIR));
             }

             if(times >= 4){
                 e.getInventory().setResult(new ItemBuilder(Material.IRON_BLOCK).setName("§7HardenedSteelBlock").buildItem());

             }else
                 e.getInventory().setResult(new ItemStack(Material.AIR));

         }else if(e.getInventory().getResult().equals(new ItemBuilder(Material.IRON_INGOT).setName("§7Steel").buildItem())){
             int times = 0;

             for(ItemStack i : e.getInventory().getStorageContents()){
                 if(i.equals(new ItemBuilder(Material.COAL).setName("§8Coke").buildItem())){
                     times++;
                     e.getInventory().setResult(new ItemBuilder(Material.IRON_INGOT).setName("§7Steel").buildItem());

                 }else
                     e.getInventory().setResult(new ItemStack(Material.AIR));
             }

             if(times >= 1){
                 e.getInventory().setResult(new ItemBuilder(Material.IRON_INGOT).setName("§7Steel").buildItem());

             }else
                 e.getInventory().setResult(new ItemStack(Material.AIR));

         }else if(e.getInventory().getResult().equals(new ItemBuilder(Material.IRON_BLOCK).setName("§7SteelBlock").buildItem())){
             int times = 0;

             for(ItemStack i : e.getInventory().getMatrix()){
                 if(i.equals(new ItemBuilder(Material.IRON_INGOT).setName("§7Steel").buildItem())){
                     times++;

                 }else
                     e.getInventory().setResult(new ItemStack(Material.AIR));
             }

             if(times >= 9){
                 e.getInventory().setResult(new ItemBuilder(Material.IRON_BLOCK).setName("§7SteelBlock").buildItem());

             }else
                 e.getInventory().setResult(new ItemStack(Material.AIR));

         }else if(e.getInventory().getResult().equals(new ItemBuilder(Material.LIGHTNING_ROD).setName("§7PressPiston").buildItem())){
             int times = 0;

             for(ItemStack i : e.getInventory().getMatrix()){
                 if(i.equals(new ItemBuilder(Material.IRON_BLOCK).setName("§7HardenedSteelBlock").buildItem())){
                     times++;

                 }else
                     e.getInventory().setResult(new ItemStack(Material.AIR));
             }

             if(times >= 4){
                 e.getInventory().setResult(new ItemBuilder(Material.LIGHTNING_ROD).setName("§7PressPiston").buildItem());

             }else
                 e.getInventory().setResult(new ItemStack(Material.AIR));
         }
     }

}
