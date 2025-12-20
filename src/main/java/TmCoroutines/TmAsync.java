package TmCoroutines;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public abstract class TmAsync {
    //to activate coroutines
    public void CreateAsync() {
        //call coroutines
        activateCoroutine();
    }

    private void activateCoroutine(){
        coroutines.runAsync(()->{
            try{
                //coroutine
                onAsync();

                //go back to the main thread
                coroutines.runSync(()->{
                    Callback();
                });
            }catch(Exception ex){
                //get the error
                Bukkit.getLogger().log(Level.WARNING, "async error "+ex);
            }

        });
    }
    //no bukkit
    public abstract void onAsync();
    //yes bukkit
    public abstract void Callback();
}


