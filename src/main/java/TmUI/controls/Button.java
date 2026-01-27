package TmUI.controls;

import TmUI.SystemUi;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public abstract class Button {

    private final ItemStack item;
    private final int slot;
    private final Inventory inventory;
    private final Player player;
    private InventoryClickEvent inventoryClickEvent;

    public Button(ItemStack item, int slot, Inventory inventory, Player player) {
        this.item = item;
        this.slot = slot;
        this.inventory = inventory;
        this.player = player;
    }

    public int getSlot() {
        return slot;
    }

    public ItemStack getItem() {
        return item;
    }

    public InventoryClickEvent getInventoryClickEvent() {
        return inventoryClickEvent;
    }

    public void setInventoryClickEvent(InventoryClickEvent inventoryClickEvent) {
        this.inventoryClickEvent = inventoryClickEvent;
    }

    public void enable() {
        String title = player.getOpenInventory().getTitle();


        SystemUi.inventoryClick.putIfAbsent(title, new HashMap<>());

        SystemUi.inventoryClick.get(title).put(slot, this);

        inventory.setItem(slot, item);
    }

    public abstract void onClick();


}
