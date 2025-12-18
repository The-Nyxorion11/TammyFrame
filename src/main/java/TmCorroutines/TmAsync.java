package TmCorroutines;
import org.bukkit.Bukkit;


public abstract class TmAsync {
    //to activate coroutines
    public void CreateAsync() {
        //call corroutines
        activateCoroutine();
    }

    private void activateCoroutine(){
        corrutines.runAsync(()->{
            try{
                //corrutina
                onAsync();

                //go back to the main thread
                corrutines.runSync(()->{
                    Callback();
                });
            }catch(Exception ex){
                //get the error
                Bukkit.getConsoleSender().sendMessage(utils.TammyUtils.messageColor("ERROR: " + ex.getMessage()));
            }

        });
    }
    //no bukkit
    public abstract void onAsync();
    //yes bukkit
    public abstract void Callback();
}


