package TmUI.Layouts;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class Layouts {


    Inventory inventory;

    public Layouts(Inventory inventory){
        this.inventory = inventory;
    }
    
    public void fillBox(ItemStack item, int width, int height, int startSlot){
        try{
            int slotBlank = 8;

            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    int slot = startSlot + row * 9 + col; // 9 columnas por fila
                    inventory.setItem(slot, item);
                }
            }


        }catch (Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error SetItem "+ex);
        }
    }
}
