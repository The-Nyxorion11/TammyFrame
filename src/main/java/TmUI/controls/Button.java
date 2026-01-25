package TmUI.controls;

import TmUI.SystemUi;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public abstract class Button {

    private ItemStack item;
    private Inventory inventory;
    private Player player;


    public Button(ItemStack item, Inventory inventory, Player player) {
        this.item = item;
        this.inventory = inventory;
        this.player = player;
    }



    public void enable(){
        String title = player.getOpenInventory().getTitle();

        SystemUi.inventoryClick.put(title, this);
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
