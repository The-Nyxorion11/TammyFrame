package MainFrame;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import utils.TammyUtils;

public class MainFrame extends JavaPlugin {

    private String version = Bukkit.getServer().getVersion();
    private String prefix = "&3[TammyFrame] ";

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(TammyUtils.messageColor(prefix + "Plugin Enabled! version: " + version));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(TammyUtils.messageColor(prefix + "Plugin Disabled!"));
    }
}
