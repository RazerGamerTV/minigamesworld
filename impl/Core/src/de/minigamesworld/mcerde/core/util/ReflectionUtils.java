package de.minigamesworld.mcerde.core.util;

import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Optional;

public class ReflectionUtils {

    @Nullable
    public static Object getPrivateObject(Object object, String fieldName) throws NoSuchFieldException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        try {
            return field.get(object);
        } catch(IllegalAccessException ignore) {}

        return null;
    }

    public static int getPrivateInteger(Object object, String fieldName) throws NoSuchFieldException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        try {
            return field.getInt(object);
        } catch(IllegalAccessException ignore) {}

        return 0;
    }

    public static int getPrivateOptionalInteger(Object object, String fieldName) throws NoSuchFieldException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);

        try {
            Optional<?> value = (Optional<?>) field.get(object);
            if(value.isPresent()) {
                return ((Optional<Integer>) field.get(object)).get().intValue();
            }
        } catch(IllegalAccessException ignore) {}

        return 0;
    }

    public static int getRandomWithNeg(int size) {

        int random = (int) (Math.random() * (size +1));

        if(Math.random() > 0.5)
            random *= ~1;

        return random;
    }

    public static ItemStack createItem(Material type, int amount, boolean glow, String name, String... loreLines){
        ItemStack item = new ItemStack(type, amount);
        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);

        if(glow ) {
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            item.addEnchantment(Enchantment.DURABILITY, 1);
        }

        if(name != null)
            meta.setDisplayName(StringUtils.color(name));

        if(loreLines != null)
            meta.setLore(Arrays.asList(loreLines));


        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack[] createArmorSet(ItemStack helmet, ItemStack chestplate, ItemStack leggins, ItemStack boots) {
        ItemStack[] armor = new ItemStack[4];

        armor[3] = helmet;
        armor[2] = chestplate;
        armor[1] = leggins;
        armor[0] = boots;

        return armor;
    }

}
