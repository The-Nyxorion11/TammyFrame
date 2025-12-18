package TmCorroutines;

import org.bukkit.Bukkit;

public abstract class TmAsyncCheck{

    public void CreateAsyncCheck(){
        activateCoroutine();
    }

    private void activateCoroutine(){
        corrutines.runAsync(()->{
            try{
                //corrutina
                boolean checkResult = onAsync();

                //go back to the main thread
                corrutines.runSync(()->{
                    Check(checkResult);
                });
            }catch(Exception ex){
                //get the error
                Bukkit.getConsoleSender().sendMessage(utils.TammyUtils.messageColor("ERROR: " + ex.getMessage()));
            }

        });
    }

    public abstract boolean onAsync();

    public abstract void Check(boolean check);
    
}

