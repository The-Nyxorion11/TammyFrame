package TmCorroutines;

import org.bukkit.Bukkit;

public abstract class TmAsyncData<T>{
    public void CreateAsyncData(){
        activateCoroutine();
    }

    private void activateCoroutine(){
        corrutines.runAsync(()->{
            try{
                //corrutina
                final T checkResult = onAsync();

                //go back to the main thread
                corrutines.runSync(()->{
                    Data(checkResult);
                });
            }catch(Exception ex){
                //get the error
                Bukkit.getConsoleSender().sendMessage(utils.TammyUtils.messageColor("ERROR: " + ex.getMessage()));
            }

        });
    }

    public abstract T onAsync();

    public abstract void Data(T check);
}
