package TmUI.Layouts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class FillBorder {
    private Inventory inventory;
    private ItemStack borderItem;
    private ItemStack insideItem;
    private int Thickness = 1;

    //this.borderItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
    //this.insideItem = new ItemStack(Material.);


    public FillBorder(Inventory inventory, int thickness) {
        this.inventory = inventory;
        Thickness = thickness;

        this.borderItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        this.insideItem = new ItemStack(Material.AIR);
    }

    public FillBorder(Inventory inventory, ItemStack borderItem, int thickness) {
        this.inventory = inventory;
        this.borderItem = borderItem;
        Thickness = thickness;
        this.insideItem = new ItemStack(Material.AIR);
    }

    public FillBorder(Inventory inventory, ItemStack borderItem, ItemStack insideItem, int thickness) {
        this.inventory = inventory;
        this.borderItem = borderItem;
        this.insideItem = insideItem;
        Thickness = thickness;
    }

    public FillBorder(Inventory inventory, ItemStack borderItem, ItemStack insideItem) {
        this.inventory = inventory;
        this.borderItem = borderItem;
        this.insideItem = insideItem;
    }

    public FillBorder(Inventory inventory, ItemStack borderItem) {
        this.inventory = inventory;
        this.borderItem = borderItem;

        this.insideItem = new ItemStack(Material.AIR);
    }

    public FillBorder(Inventory inventory) {
        this.inventory = inventory;

        this.borderItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        this.insideItem = new ItemStack(Material.AIR);
    }

    public void fillBorder() {
        try {
            Layouts layouts = new Layouts(inventory);

            int rows = inventory.getSize() / 9;

            layouts.fillBox(borderItem, 9, rows, 0);

            int slotStartAir = Thickness * 9 + Thickness;
            int heightAir = rows - 2 * Thickness;
            int widthAir = 9 - 2 * Thickness;

            layouts.fillBox(insideItem, widthAir, heightAir, slotStartAir);

        } catch (Exception ex) {
            Bukkit.getLogger().log(Level.WARNING, "Error SetItem " + ex);
        }


    }
}
