package TmUI.window;

import TmUI.Layouts.FillBorder;
import TmUI.Layouts.Layouts;
import TmUI.controls.Button;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import TmUI.SystemUi;

public abstract class Ui {

    private Inventory inventory;
    private boolean blockInventory;
    private Player player;

    protected Layouts layouts;

    public Ui(boolean blockInventory) {
        this.blockInventory = blockInventory;
    }

    public void openUi(Player player, int size, String title){
        constructor(player,  size, title);
    }

    //controller inventory
    private void constructor(Player plr, int size, String title){
        player = plr;

        // more ui logic
        createInventory(size, title);
        setConfig(player, title);
        openInventory(player);
        setParam();

        compose();
    }

    //moreSystems

    private void setParam(){
        layouts = new Layouts(inventory);
    }

    private void createInventory(int size, String title){
        inventory = Bukkit.createInventory(null, size, title);
    }

    private void openInventory(Player player){
        player.openInventory(inventory);
    }

    private void setConfig(Player player, String title){
        if(blockInventory){
            UUID uuid = player.getUniqueId();
            SystemUi.inventoryBlocked.put(uuid, title);
        }
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

    protected void closeUI(){
        getPlayer().closeInventory();
    }

    protected void navigate(Ui ui, int size, String title){
        closeUI();
        ui.openUi(getPlayer(), size, title);
    }

    //utils
    public Inventory getInventory(){
        return inventory;
    }

    public Player getPlayer(){
        return player;
    }



}
