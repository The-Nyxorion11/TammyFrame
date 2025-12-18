package TmCorrutines;

abstract class TmAsync {

    private void activateCoroutine(){
        corrutines.runAsync(()->{
            //corrutina
            onAsync();

            //go back to the main thread
            corrutines.runSync(()->{
               Callback();
            });

        });
    }
    //no bukkit
    public abstract void onAsync();
    //yes bukkit
    public abstract void Callback();
}


