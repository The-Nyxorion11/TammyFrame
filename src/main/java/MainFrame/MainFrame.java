package MainFrame;

import TmCoroutines.coroutines;
import TmRoom.TmRoomManager;
import TmUI.eventsSystems.BlockEvent;
import TmUI.eventsSystems.ClickEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import TmUtils.TammyUtils;

public class MainFrame extends JavaPlugin {

    private String version = Bukkit.getServer().getVersion();
    private String prefix = "&3[TammyFrame] ";

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(TammyUtils.messageColor(prefix + "Plugin Enabled! version: " + version));
        coroutines.plugin = this;
        registerEvnets();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(TammyUtils.messageColor(prefix + "Plugin Disabled!"));
        TmRoomManager.clsDb();
    }

    private void registerEvnets(){
        getServer().getPluginManager().registerEvents(new BlockEvent(), this);
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
    }
}
