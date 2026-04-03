package TmUI.Layouts;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class itemEdit {
    private int slot;
    private ItemStack item;
    private Inventory inventory;

    public itemEdit(int slot, ItemStack item, Inventory inventory) {
        this.slot = slot;
        this.item = item;
        this.inventory = inventory;
    }

    private ItemMeta meta = item.getItemMeta();

    protected void setItem() {
        item.setItemMeta(meta);
        inventory.setItem(slot, item);
    }

    protected void itemName(String itemName) {
        meta.setDisplayName(itemName);
    }

    protected void itemLore(List itemLore) {
        meta.setLore(itemLore);
    }

    protected ItemStack getItem() {
        item.setItemMeta(meta);
        return item;
    }

    protected void itemGlow() {
        meta.addEnchant(Enchantment.LUCK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    }
}
