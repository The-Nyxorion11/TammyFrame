package TmUI.controls;

import TmUI.SystemUi;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class Button {

    private ItemStack item;
    private Inventory inventory;

    public Button() {

    }

    public void enable(){
        SystemUi.inventoryClick.put(inventory, this);
    }

    public int getSlotOfItem() {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack current = inventory.getItem(i);
            if (current != null && current.isSimilar(item)) {
                return i;
            }
        }
        return -1;
    }


    public abstract void onClick();

}
