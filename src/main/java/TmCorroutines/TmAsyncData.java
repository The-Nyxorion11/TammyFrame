package TmCorroutines;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public abstract class TmAsyncData<T>{
    public void CreateAsyncData(){
        activateCoroutine();
    }

    private void activateCoroutine(){
        corroutines.runAsync(()->{
            try{
                //corrutina
                final T checkResult = onAsync();

                //go back to the main thread
                corroutines.runSync(()->{
                    Data(checkResult);
                });
            }catch(Exception ex){
                //get the error
                Bukkit.getLogger().log(Level.WARNING, "async error "+ex);
            }

        });
    }

    public abstract T onAsync();

    public abstract void Data(T check);
}
