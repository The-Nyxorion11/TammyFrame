package TmUI.Layouts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class FillBorder {
    private Inventory inventory;
    private ItemStack item;
    private int Thickness = 1;


    public FillBorder(Inventory inventory){
        this.inventory = inventory;
        this.item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    }

    public FillBorder(Inventory inventory, ItemStack item){
        this.inventory = inventory;
        this.item = item;
    }
    public FillBorder(Inventory inventory, ItemStack item, int Thickness){
        this.inventory = inventory;
        this.item = item;
        this.Thickness = Thickness;
    }

    public FillBorder(Inventory inventory, int Thickness){
        this.inventory = inventory;
        this.item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        this.Thickness = Thickness;
    }

    public void fillBorder() {
        try{
            Layouts layouts = new Layouts(inventory);

            int slotStart = 0;
            int slotX = 9;
            int slotY = inventory.getSize();
            //fill border with the item
            layouts.fillBox(item, slotX, slotY,  slotStart);

            //fill with air

            int slotStartAir = slotStart + 10 * Thickness;
            int slotYAir = inventory.getSize() - 10 * Thickness;
            //fill border with the item
            layouts.fillBox(item, slotX, slotYAir, slotStartAir );

        }catch (Exception ex){
            Bukkit.getLogger().log(Level.WARNING, "Error SetItem "+ex);
        }

    }
}
