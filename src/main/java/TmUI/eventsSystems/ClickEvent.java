package TmUI.eventsSystems;

import TmUI.SystemUi;
import TmUI.controls.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.UUID;

public class ClickEvent implements Listener {

    @EventHandler
    public void removeItem(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getInventory();

        InventoryView view = event.getView();
        String title = view.getTitle();

        Button button = getButton(title);

        if (button != null) {
            int slot = event.getSlot();

            if(slot == button.getSlotOfItem()){
                button.onClick();
                button.setInventoryClickEvent(event);
            }
        }
    }

    private Button getButton(String title){

        Button button = SystemUi.inventoryClick.get(title);


        SystemUi.inventoryClick.remove(title);
        return button;
    }

}
