package TmCorroutines;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public abstract class TmAsyncCheck{

    public void CreateAsyncCheck(){
        activateCoroutine();
    }

    private void activateCoroutine(){
        corroutines.runAsync(()->{
            try{
                //corrutina
                boolean checkResult = onAsync();

                //go back to the main thread
                corroutines.runSync(()->{
                    Check(checkResult);
                });
            }catch(Exception ex){
                //get the error
                Bukkit.getLogger().log(Level.WARNING, "async error "+ex);
            }

        });
    }

    public abstract boolean onAsync();

    public abstract void Check(boolean check);
    
}

