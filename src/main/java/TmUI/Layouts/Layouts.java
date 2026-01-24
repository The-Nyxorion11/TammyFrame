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
    
    public void fillBox(ItemStack item, int x, int y, int slot){
        try{
            int slotBlank = 8;

            for(int i = 0; i <= y; i++){
                for (int s = slot; s <= 8; s++ ){
                    if(s - slotBlank == slot){
                        slot = slot  + s;

                        for(int c = 0; c <= x; c++){
                            inventory.setItem(slot + c, item);
                        }
                    }
                }
            }

        }catch (Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error SetItem "+ex);
        }
    }
}
