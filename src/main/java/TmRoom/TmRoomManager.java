package TmRoom;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TmRoomManager  {
    //DO NOT TOUCH OR USE!!!
    public static List<TmRoom> actDb= new ArrayList<>();

    public static void regisDb(TmRoom tmRoom){
        actDb.add(tmRoom);
    }

    public static void clsDb(){
        for(TmRoom tmRoom:actDb){
            tmRoom.TmRoomCls();
        }
        actDb.clear();
    }

}
