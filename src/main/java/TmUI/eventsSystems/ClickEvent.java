package TmUI.eventsSystems;

import TmUI.SystemUi;
import TmUI.controls.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.UUID;

public class ClickEvent implements Listener {

    @EventHandler
    public void removeItem(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        Button button = getButton(inventory);

        if (button != null) {
            int slot = event.getSlot();

            if(slot == button.getSlotOfItem()){
                button.onClick();
            }
        }
    }

    private Button getButton(Inventory inventory){
        String defaultValue = "not exist, sorry! (-.,´ñllñsad<|¿320)";

        Button button = SystemUi.inventoryClick.get(inventory);


        SystemUi.inventoryClick.remove(inventory);
        return button;
    }

}
