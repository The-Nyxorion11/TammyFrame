package TmUI.eventsSystems;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import TmUI.SystemUi;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.InventoryView;

import java.util.UUID;

public class BlockEvent implements Listener {

    @EventHandler
    public void removeItem(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();

        InventoryView view = event.getView();
        String title = view.getTitle();

        if (getTitle(player).equalsIgnoreCase(title)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent event) {
        Player player = (Player) event.getWhoClicked();

        InventoryView view = event.getView();
        String title = view.getTitle();

        if (getTitle(player).equalsIgnoreCase(event.getInventory().getTitle())) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        player.getOpenInventory();
        String title = player.getOpenInventory().getTitle();

        if (getTitle(player).equalsIgnoreCase(title)) {
            event.setCancelled(true);
        }


    }

    @EventHandler
    public void closeInventory(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        String title = getTitle(player);

        if (getTitle(player).equalsIgnoreCase(title)) {
            UUID uuid = player.getUniqueId();
            SystemUi.inventoryBlocked.remove(uuid);
        }
    }


    private String getTitle(Player player){
        String defaultValue = "not exist, sorry! (-.,´ñllñsad<|¿320)";
        UUID uuid = player.getUniqueId();

        String title = SystemUi.inventoryBlocked.getOrDefault(uuid, defaultValue);

        return title;
    }
}
