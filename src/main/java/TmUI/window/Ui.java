package TmUI.window;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.logging.Level;

public abstract class Ui {

    private Inventory inventory;

    public void openUi(Player player, int size, String title){
        constructor(player,  size, title);
    }

    //controller inventory
    private void constructor(Player player, int size, String title){
        // more ui logic
        createInventory(size, title);
        compose();
        openInventory(player);
    }

    private void createInventory(int size, String title){
        inventory = Bukkit.createInventory(null, size, title);
    }

    private void openInventory(Player player){
        player.openInventory(inventory);
    }
    

    //compose ui
    protected abstract void compose();

    protected ItemStack createItem(Material material, int amount, String name, List<String> description){
        try{
            ItemStack item = new ItemStack(material, amount);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(name);
            meta.setLore(description);

            item.setItemMeta(meta);

            return  item;
        }catch (Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error SetItem "+ex);
        }

        return null;
    }

    protected void setItem(int slot, ItemStack item){
        inventory.setItem(slot, item);
    }

}
