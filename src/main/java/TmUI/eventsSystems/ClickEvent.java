package TmUI.eventsSystems;

import TmUI.SystemUi;
import TmUI.controls.Button;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;

import java.util.Map;
import java.util.UUID;

public class ClickEvent implements Listener {

    @EventHandler
    public void clickButton(InventoryClickEvent event){
        InventoryView view = event.getView();
        String title = view.getTitle();

        Map<Integer, Button> buttons = SystemUi.inventoryClick.get(title);
        if (buttons == null) return;

        int slot = event.getSlot();
        Button button = buttons.get(slot);

        if (button != null) {
            button.setInventoryClickEvent(event);
            button.onClick();
        }

    }

    @EventHandler
    public void closeInventory(InventoryCloseEvent event) {
        String title = event.getView().getTitle();
        SystemUi.inventoryClick.remove(title);

    }

}
