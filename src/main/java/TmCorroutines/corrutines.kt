package TmCorroutines
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin

object corrutines {

    private val frameScope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    lateinit var plugin: Plugin

    //create the coroutine the coroutine
    @JvmStatic
    fun runAsync(CodeAsync: Runnable){
        frameScope.launch {
            CodeAsync.run()
        }
    }
    //go back to the main thread
    @JvmStatic
    fun runSync(CodeSync: Runnable){
        Bukkit.getScheduler().runTask(plugin, CodeSync);
    }
}