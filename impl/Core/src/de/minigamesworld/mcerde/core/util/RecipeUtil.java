package de.minigamesworld.mcerde.core.util;


import de.minigamesworld.mcerde.core.McErdeCore;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class RecipeUtil {

    public static ShapedRecipe ElectricalComponent() {
        ItemStack item = new ItemBuilder(Material.HONEYCOMB).setName("§7Electrical Component").buildItem();
        NamespacedKey key = new NamespacedKey(McErdeCore.getInstance(), "Electric_Component");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape(" C ", " R ", " C ");
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('C', Material.COPPER_INGOT);

        return recipe;
    }

    public static ShapedRecipe AdvancedCircuit(){
        ItemStack item = new ItemBuilder(Material.HONEYCOMB).setName("§2Advanced Circuit").buildItem();
        ItemStack component = new ItemBuilder(Material.HONEYCOMB).setName("§7Electrical Component").buildItem();
        NamespacedKey key = new NamespacedKey(McErdeCore.getInstance(), "Advanced_Circuit");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("ARA", "RCR", "ARA");
        recipe.setIngredient('C', Material.COPPER_INGOT);
        recipe.setIngredient('R', Material.REDSTONE);
        recipe.setIngredient('A', component.getType());

        return recipe;
    }

    public static ShapedRecipe HardenedSteelBlock(){

        ItemStack item = new ItemBuilder(Material.IRON_BLOCK).setName("§7HardenedSteelBlock").buildItem();
        ItemStack steel = new ItemBuilder(Material.IRON_BLOCK).setName("§7SteelBlock").buildItem();
        NamespacedKey key = new NamespacedKey(McErdeCore.getInstance(), "Hardened_Steel_Block");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("SS ", "SS ");
        recipe.setIngredient('S', steel.getType());

        return recipe;
    }

    public static FurnaceRecipe Coke(){

        ItemStack item = new ItemBuilder(Material.COAL).setName("§8Coke").buildItem();
        FurnaceRecipe recipe = new FurnaceRecipe(item, Material.COAL);
        recipe.setExperience(0F);
        recipe.setCookingTime(50);

        return recipe;
    }

    public static ShapedRecipe Steel(){
        ItemStack item = new ItemBuilder(Material.IRON_INGOT).setName("§7Steel").buildItem();
        ItemStack coke = new ItemBuilder(Material.COAL).setName("§8Coke").buildItem();
        NamespacedKey key = new NamespacedKey(McErdeCore.getInstance(), "Coke");
        ShapedRecipe recipe = new ShapedRecipe(key ,item);

        recipe.shape("CI ");
        recipe.setIngredient('C', coke.getType());
        recipe.setIngredient('I', Material.IRON_INGOT);

        return recipe;
    }

    public static ShapedRecipe SteelBlock(){
        ItemStack steel = new ItemBuilder(Material.IRON_INGOT).setName("§7Steel").buildItem();
        ItemStack item= new ItemBuilder(Material.IRON_BLOCK).setName("§7SteelBlock").buildItem();
        NamespacedKey key = new NamespacedKey(McErdeCore.getInstance(), "Steel_Block");
        ShapedRecipe recipe = new ShapedRecipe(key , item);

        recipe.shape("SSS", "SSS", "SSS");
        recipe.setIngredient('S', steel.getType());

        return recipe;
    }

    public static ShapedRecipe DurableSteelBlock(){
        ItemStack steelBlock = new ItemBuilder(Material.IRON_BLOCK).setName("§7HardenedSteelBlock").buildItem();
        ItemStack item = new ItemBuilder(Material.LIGHTNING_ROD).setName("§7PressPiston").buildItem();
        NamespacedKey key = new NamespacedKey(McErdeCore.getInstance(), "Press_Piston");
        ShapedRecipe recipe = new ShapedRecipe(key, item);

        recipe.shape("DSD", "SOS", "DSD");
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('O', Material.OBSIDIAN);
        recipe.setIngredient('S', steelBlock.getType());

        return recipe;
    }

}
