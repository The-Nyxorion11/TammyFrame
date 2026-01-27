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

    public Button(ItemStack item, Inventory inventory, Player player) {
        this.item = item;
        this.inventory = inventory;
        this.player = player;
        this.slot = getSlotOfItem(inventory, item);
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

    public void enable(Button button) {
        String title = player.getOpenInventory().getTitle();


        SystemUi.inventoryClick.putIfAbsent(title, new HashMap<>());

        SystemUi.inventoryClick.get(title).put(slot, button);

        inventory.setItem(slot, item);
    }

    public int getSlotOfItem(Inventory inventory, ItemStack item) {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack current = inventory.getItem(i);
            if (current != null && current.isSimilar(item)) {
                return i; // Devuelve el slot donde está el ítem
            }
        }
        return -1; // Si no lo encuentra
    }


    public abstract void onClick();


}
