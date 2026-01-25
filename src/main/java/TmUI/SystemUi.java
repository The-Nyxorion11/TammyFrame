package TmUI;

import TmUI.controls.Button;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SystemUi {
    //for implement the config
    public static  Map<UUID, String>  inventoryBlocked = new HashMap<>();
    public static  Map<String, Button>  inventoryClick = new HashMap<>();

}
