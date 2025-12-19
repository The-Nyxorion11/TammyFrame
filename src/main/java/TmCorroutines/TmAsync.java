package TmCorroutines;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public abstract class TmAsync {
    //to activate coroutines
    public void CreateAsync() {
        //call corroutines
        activateCoroutine();
    }

    private void activateCoroutine(){
        corroutines.runAsync(()->{
            try{
                //corrutina
                onAsync();

                //go back to the main thread
                corroutines.runSync(()->{
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


